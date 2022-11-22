package ai.dragonfly.democrossy.native

import ai.dragonfly.democrossy.*

object DivConsole {
  private val dc:DivConsole = new DivConsole()
  def apply(id:String = "console", fg:String = "#eeeeee", bg:String = "#2b2b2b", style:String = ""):DivConsole = dc
  def dark(i:String = "console", s:String = ""):DivConsole = dc
  def light(id:String = "console", style:String = ""):DivConsole = dc
}
