package ai.dragonfly.democrossy

/**
 * Tries to discover the runtime environment to select the default text output path
 */

package object native {

  def out(dc:DivConsole):java.io.PrintStream = dc match {
    case bdc: BrowserDivConsole => new java.io.PrintStream(new DivOutputStream(bdc))
    case _ => System.out
  }

}
