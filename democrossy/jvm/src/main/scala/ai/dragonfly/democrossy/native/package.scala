package ai.dragonfly.democrossy

/**
 * Tries to discover the runtime environment to select the default text output path
 */

package object native {
  def out(dc:DivConsole):java.io.PrintStream = System.out
}
