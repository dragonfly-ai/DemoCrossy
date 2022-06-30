package ai.dragonfly

package object democrossy {

  val DivConsole:ai.dragonfly.democrossy.native.DivConsole.type = ai.dragonfly.democrossy.native.DivConsole

  class DivConsole

  def out(dc:DivConsole):java.io.PrintStream = ai.dragonfly.democrossy.native.out(dc)
}
