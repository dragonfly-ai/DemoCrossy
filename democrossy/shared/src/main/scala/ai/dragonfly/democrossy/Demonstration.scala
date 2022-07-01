package ai.dragonfly.democrossy
import ai.dragonfly.democrossy.*
import ai.dragonfly.democrossy.given
import scala.language.implicitConversions

import Console.*

trait Demonstration {

  def name:String
  def demo():Unit

  def demonstrate: Unit = {
    println(s"$RESET/*$GREEN Begin $BOLD$name$RESET$GREEN Demonstration$RESET */")
    demo()
    println(s"$RESET/*$RED End $BOLD$name$RESET$RED Demonstration$RESET */")
  }

  def main(args:Array[String]):Unit = demonstrate

}