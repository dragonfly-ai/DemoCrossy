package ai.dragonfly.democrossy.native

import org.scalajs.dom

import scala.collection.mutable

object DivConsole {
  def apply(id:String = "console", fg:String = "#eeeeee", bg:String = "#2b2b2b", style:String = ""):DivConsole = new DivConsole(id, fg, bg, style)
  def dark(i:String = "console", s:String = ""):DivConsole = apply(id = i, style = s)
  def light(id:String = "console", style:String = ""):DivConsole = apply(id, "#2b2b2b", "#eeeeee", style)
}

class DivConsole private (
  val id:String,
  val dFG:String,
  val dBG:String,
  val style:String
) {

  private var fg: String = dFG
  private var bg: String = dBG
  private var reversed:Boolean = false
  private var or:Boolean = false
  private val mods:mutable.HashSet[StyleSignal] = new mutable.HashSet[StyleSignal]()
  private var lineNumber:Long = 0L

  val body:dom.HTMLBodyElement = {
    dom.window.document.head.append({
      val style:dom.HTMLStyleElement  = dom.document.createElement("style").asInstanceOf[dom.HTMLStyleElement]
      style.append("@keyframes blink { 0% { opacity: 1; } 100% { opacity: 0; } }")
      style
    })

    dom.window.document.body.asInstanceOf[dom.HTMLBodyElement]
  }

  val cdiv:dom.HTMLDivElement = {
    val temp:dom.HTMLDivElement = dom.window.document.getElementById(id) match {
      case null =>
        val t0 = dom.document.createElement("div")
        t0.id = id
        t0.asInstanceOf[dom.HTMLDivElement]
      case t0: dom.HTMLDivElement => t0
    }

    temp.setAttribute("style",s"font-family:monospace;color:$dFG;background-color:$dBG;white-space:pre;$style")

    body.append(temp)
    temp
  }

  var parent:dom.HTMLElement = cdiv
  var currentSpan:dom.HTMLSpanElement = newLine()

  def modStyle:String = {
    var modStr:String = ""
    for (m <- mods) modStr = modStr + m.css
    modStr
  }

  def currentStyle():String = (
    if (reversed) s"color: $bg;background-color: $fg;"
    else s"color: $fg;background-color: $bg;"
  ) + modStyle

  def newSpan():dom.HTMLSpanElement = {
    val span:dom.HTMLSpanElement = dom.document.createElement("span").asInstanceOf[dom.HTMLSpanElement]
    span.setAttribute("style", currentStyle() )
    span
  }

  def nestedSpan():Unit = {
    val ts:dom.HTMLSpanElement = newSpan()
    currentSpan.append(ts)
    parent = currentSpan
    currentSpan = ts
  }

  def newLine():dom.HTMLSpanElement = {
//    currentSpan.append("\n")
    or = false
    parent = cdiv
    val span:dom.HTMLSpanElement = newSpan()
    span.setAttribute("id", lineNumber.toString)
    currentSpan = span
    parent.append(currentSpan)
    lineNumber += 1
    span
  }

  def clearCurrentLine():Unit = {
    while (currentSpan.childNodes.size > 0) {
      currentSpan.removeChild(currentSpan.firstChild)
    }
  }

  def overWright():Unit = this.or = true

  def reset():Unit = {
    this.fg = dFG
    this.bg = dBG
    this.reversed = false
    this.or = false
    this.mods.clear()

    parent = cdiv
  }

  def apply(os:OutputSignal):Unit = {
    if (os match {
      case OutputSignal.RESET => reset(); false
      case ReverseSignal => if(this.reversed) false else { reversed = true; true }
      case fgc: ForegroundColor => if (this.fg != fgc.hexColor) { this.fg = fgc.hexColor; true } else false
      case bgc: BackgroundColor => if (this.bg != bgc.hexColor) { this.bg = bgc.hexColor; true } else false
      case ss: StyleSignal => val l:Int = this.mods.size; this.mods.add(ss); this.mods.size == l
      case _ => append(s"Unknown Signal: $os"); false
    }) nestedSpan()
  }

  def append(s:String):Unit = if (s.nonEmpty) {
    if (or) clearCurrentLine()
    currentSpan.append(s)
    or = false
  }

}
