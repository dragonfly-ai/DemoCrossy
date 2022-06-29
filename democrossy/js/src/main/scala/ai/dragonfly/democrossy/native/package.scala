package ai.dragonfly.democrossy

import java.io.{OutputStream, PrintStream}

/**
 * Tries to discover the runtime environment to select the default text output path
 */

package object native {

  lazy val os:PrintStream = try {
    new PrintStream(new DivOutputStream)
  } catch {
    case _: Throwable => System.out
  }

}
