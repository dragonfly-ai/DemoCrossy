import ai.dragonfly.democrossy.*

import Console.*
import scala.io.StdIn

object Demo extends XApp with App {

  val d1: Demonstration = new Demonstration {

    override def demo(): Unit = {
      println(s"Default: Hello World!")

      println(s"BOLD: $BOLD Hello world! $RESET")
      println(s"UNDERLINED: $UNDERLINED Hello world! $RESET")
      println(s"BLINK: $BLINK Hello world! $RESET")
      println(s"REVERSED: $REVERSED Hello world! $RESET")
      println(s"INVISIBLE: $INVISIBLE Hello world! $RESET")
      println(s"\nColors:")
      println(s"BLACK: $BLACK Hello world! $REVERSED REVERSED: Hello world! $REVERSED REVERSED: Hello world! $RESET")
      println(s"RED: $RED Hello world! $REVERSED REVERSED: Hello world! $REVERSED REVERSED: Hello world! $RESET")
      println(s"GREEN: $GREEN Hello world! $REVERSED REVERSED: Hello world! $REVERSED REVERSED: Hello world! $RESET")
      println(s"YELLOW: $YELLOW Hello world! $REVERSED REVERSED: Hello world! $REVERSED REVERSED: Hello world! $RESET")
      println(s"BLUE: $BLUE Hello world! $REVERSED REVERSED: Hello world! $REVERSED REVERSED: Hello world! $RESET")
      println(s"MAGENTA: $MAGENTA Hello world! $REVERSED REVERSED: Hello world! $REVERSED REVERSED: Hello world! $RESET")
      println(s"CYAN: $CYAN Hello world! $REVERSED REVERSED: Hello world! $REVERSED REVERSED: Hello world! $RESET")
      //println(s"GRAY: $GRAY Hello world! $REVERSED REVERSED: Hello world! $REVERSED REVERSED: Hello world! $RESET")
      println(s"WHITE: $WHITE Hello world! $REVERSED REVERSED: Hello world! $REVERSED REVERSED: Hello world! $RESET")

      println(s"Testing $RED 1 $GREEN 2 $BLUE 3 $RESET $BLINK !!! $RESET")
    }

    override def name: String = "Hello World"
  }

  d1.demonstrate
}
