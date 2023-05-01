/*
 * Copyright 2023 dragonfly.ai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ai.dragonfly.democrossy.native

import ai.dragonfly.democrossy.*
import org.scalajs.dom

import scala.collection.mutable

object NativeConsole {

  /**
   * this detects the environment by trying to insert support for ANSI blink formatting and also a monospace font.
   */
  val browser:Boolean = try {
    dom.window.document.head.append({
      val style:dom.HTMLStyleElement  = dom.document.createElement("style").asInstanceOf[dom.HTMLStyleElement]
      style.append("@keyframes blink { 0% { opacity: 1; } 100% { opacity: 0; } }")
      style.append("font-family: SFMono-Regular, Menlo, Monaco, Consolas, 'Liberation Mono', 'Courier New', Ubuntu, monospace;")
//      style.append("@font -face { font-family: 'Gardens'; src: url('../fonts/GardensC_03_1.ttf') format ('truetype'); }")
//      style.append("<!--[if IE]><link rel='stylesheet' type='text/css' media='all' href='./css/ie-fonts.css' /><![endif]-->")
      style
    })
    true
  } catch {
    case _ :Throwable => false
  }

  def apply(id:String = "console", fg:String = "#eeeeee", bg:String = "#2b2b2b", style:String = ""):NativeConsole = {
    if (browser) BrowserDivConsole(id, fg, bg, style)
    else new NodeJS_Console()
  }

  def dark(i:String = "console", s:String = ""):NativeConsole = apply(id = i, style = s)

  def light(id:String = "console", style:String = ""):NativeConsole = apply(id, "#2b2b2b", "#eeeeee", style)
}

class NodeJS_Console extends NativeConsole {

  import scala.scalajs.js
  import js.Dynamic.global

  private val prompt_sync:js.Dynamic = global.require("prompt-sync")() //js.Dynamic.literal(sigint = true))

  override def readLine(): String = prompt_sync().asInstanceOf[String]

  override def prompt(message: String): String = {
    println(message)
    readLine()
  }
}


object BrowserDivConsole {
  def apply(id:String = "console", fg:String = "#eeeeee", bg:String = "#2b2b2b", style:String = ""):NativeConsole = new BrowserDivConsole(id, fg, bg, style)
}

class BrowserDivConsole private (
  val id:String,
  val dFG:String,
  val dBG:String,
  val style:String
) extends NativeConsole {

  private var fg: String = dFG
  private var bg: String = dBG
  private var reversed:Boolean = false
  private var or:Boolean = false
  private val mods:mutable.HashSet[StyleSignal] = new mutable.HashSet[StyleSignal]()
  private var lineNumber:Long = 0L

  override def readLine():String = {
    val line:String = dom.window.prompt(
      dom.document.getElementById(s"$id-${lineNumber - 1}").innerText
    )
    append(line)
    append("\n")
    line
  }

  override def prompt(message: String): String = {
    append(message)
    append(" ")
    val line: String = dom.window.prompt(message)
    append(line)
    append("\n")
    line
  }

  val body:dom.HTMLBodyElement = dom.window.document.body.asInstanceOf[dom.HTMLBodyElement]

  val cdiv:dom.HTMLDivElement = {
    val temp:dom.HTMLDivElement = dom.window.document.getElementById(id) match {
      case null =>
        val t0 = dom.document.createElement("div")
        t0.id = id
        body.append(t0)
        t0.asInstanceOf[dom.HTMLDivElement]
      case t0: dom.HTMLDivElement => t0
    }

    temp.setAttribute("style",s"color:$dFG;background-color:$dBG;white-space:pre;$style")

    temp
  }

  var currentLine:dom.HTMLSpanElement = newLine()
  var currentSpan:dom.HTMLSpanElement = newSpan()

  def modStyle:String = {
    var modStr:String = ""
    for (m <- mods) modStr = modStr + m.css
    modStr
  }

  def colorCss(tfg:String, tbg:String): String = {
    (if (tfg == dFG) "" else s"color: $tfg;") + (if (tbg == dBG) "" else s"background-color: $tbg;")
  }

  def colorStyle:String = {
    if (reversed) colorCss(bg, fg)
    else colorCss(fg, bg)
  }

  def currentStyle():String = s"$colorStyle$modStyle"

  def newSpan():dom.HTMLSpanElement = {
    val span:dom.HTMLSpanElement = dom.document.createElement("span").asInstanceOf[dom.HTMLSpanElement]
    currentSpan = span
    span
  }

  def nestedSpan():dom.HTMLSpanElement = {
    val span:dom.HTMLSpanElement = if (currentSpan.childNodes.size < 1) {
      //if (currentSpan.hasAttribute("style"))
      currentSpan.removeAttribute("style")
      currentSpan
    } else {
      val tempSpan:dom.HTMLSpanElement = newSpan()
      currentLine.append(tempSpan)
      tempSpan
    }

    val spanStyle:String = currentStyle()

    if (spanStyle.nonEmpty) span.setAttribute("style", spanStyle)
    else currentSpan = currentLine

    span
  }

  def newLine():dom.HTMLSpanElement = {
    or = false

    if (lineNumber > 0 && currentLine != currentSpan && currentSpan.childNodes.size < 1) {
      currentLine.removeChild(currentSpan)
    }

    val span:dom.HTMLSpanElement = newSpan()
    span.setAttribute("id", s"$id-$lineNumber")
    cdiv.append("\n")
    cdiv.append(span)
    currentLine = span
    lineNumber += 1
    span
  }

  def clearCurrentLine():Unit = {
    while (currentLine.childNodes.size > 0) {
      currentLine.removeChild(currentLine.firstChild)
    }
    val span:dom.HTMLSpanElement = nestedSpan()
    currentLine.append(span)
  }

  def overWright():Unit = this.or = true

  def reset():Unit = {
    this.fg = dFG
    this.bg = dBG
    this.reversed = false
    this.or = false
    this.mods.clear()
  }

  def apply(os:OutputSignal):Unit = {
    //val prevStyle:String = currentStyle()
    os match {
      case OutputSignal.RESET => reset()
      case ReverseSignal => reversed = true
      case fgc: ForegroundColor => this.fg = fgc.hexColor
      case bgc: BackgroundColor => this.bg = bgc.hexColor
      case ss: StyleSignal => this.mods.add(ss)
      case _ => //append(s"Unknown Signal: $os")
    }
    nestedSpan()
  }

  def append(s:String):Unit = if (s.nonEmpty) {
    //val s = s0.replaceAll("\\[[0-9]{1,2}m", "")

    if (or) clearCurrentLine()
    currentSpan.append(s)
    or = false
  }

}
