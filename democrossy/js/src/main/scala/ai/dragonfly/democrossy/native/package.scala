package ai.dragonfly.democrossy

import scala.compiletime.*
import scala.reflect.ClassTag
import scala.scalajs.js
import scala.scalajs.js.typedarray.{BigInt64Array, Float32Array, Float64Array, Int16Array, Int32Array, Int8Array, TypedArray}

/**
 * Tries to discover the runtime environment to select the default text output path
 */

package object native {

  def out(dc:DivConsole):java.io.PrintStream = dc match {
    case bdc: BrowserDivConsole => new java.io.PrintStream(new DivOutputStream(bdc))
    case _ => System.out
  }

}
