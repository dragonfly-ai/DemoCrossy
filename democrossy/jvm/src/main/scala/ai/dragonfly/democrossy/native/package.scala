package ai.dragonfly.democrossy

import java.io.PrintStream

/**
 * Tries to discover the runtime environment to select the default text output path
 */

package object native {
  lazy val os:PrintStream = System.out
}
