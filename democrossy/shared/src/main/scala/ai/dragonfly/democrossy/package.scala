package ai.dragonfly

import java.io.PrintStream

package object democrossy {
  lazy val os:PrintStream = ai.dragonfly.democrossy.native.os
}
