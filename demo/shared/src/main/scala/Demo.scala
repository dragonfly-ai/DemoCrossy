import ai.dragonfly.democrossy.*

import Console.*

object Demo extends XApp(DivConsole(style = "padding: 8px; width: 50%;")) with App {

  val d1: Demonstration = new Demonstration {

    override def demo(): Unit = {

      println(s"Default: Hello World!")
      println(s"BOLD: $BOLD Hello world! $RESET")
      println(s"UNDERLINED: $UNDERLINED Hello world! $RESET")
      println(s"BLINK: $BLINK Hello world! $RESET")
      println(s"REVERSED: ${REVERSED}Hello world! $RESET")
      println(s"INVISIBLE: $INVISIBLE Hello world! $RESET")
      println("\n\n\n\n\n\n\n")
      println(s"$RESET\n\nColors:")
      println(s"BLACK: $BLACK Hello world!${REVERSED}REVERSED: Hello world! $RESET$RESET")
      println(s"RED: $RED Hello world!${REVERSED}REVERSED: Hello world! $RESET")
      println(s"GREEN: $GREEN Hello world!${REVERSED}REVERSED: Hello world! $RESET")
      println(s"YELLOW: $YELLOW Hello world!${REVERSED}REVERSED: Hello world! $RESET")
      println(s"BLUE: $BLUE Hello world!${REVERSED}REVERSED: Hello world! $RESET")
      println(s"MAGENTA: $MAGENTA Hello world!${REVERSED}REVERSED: Hello world! $RESET")
      println(s"CYAN: $CYAN Hello world! ${REVERSED}REVERSED: Hello world! $RESET")
      println(s"WHITE: $WHITE Hello world!${REVERSED}REVERSED: Hello world! $RESET")

      println(s"\nConsecutive REVERSED: $RED Hello world! ${REVERSED}REVERSED: Hello world! ${REVERSED}REVERSED: Hello world! $RESET")
      println(s"\nConsecutive REVERSED: $RED Hello world! ${REVERSED}REVERSED: Hello world! ${REVERSED}\rtesting \\r $RESET")
      println(s"\nKitchenSink: ${BOLD}$BLACK$WHITE_B 1 $RED$CYAN_B 2 $GREEN$MAGENTA_B 3 $YELLOW$BLUE_B 4 $BLUE$YELLOW_B 5 $MAGENTA$GREEN_B 6 $CYAN$RED_B 7 $WHITE$BLACK_B 8 ${UNDERLINED}Hello world! ${REVERSED}REVERSED: Hello world! BLINK: ${BLINK}Hello world! Invisible: $INVISIBLE Hello world! $RESET")

      println(s"\nTabs and Newlines:\n\tOnce upon a time in a land far away,\nthere lived a lonely command line Scala\napplication.\n\tOne day, you deployed it through a\nbrowser interface to make it more\naccessible.\n\tAs people began to use it, the\nlittle app felt loved and lived happily\never after.\n\tThe End\n\n")
      println(s"Testing $RED 1 $GREEN 2 $BLUE 3 $RESET $BLINK !!! $RESET")

    }

    override def name: String = "Hello World"
  }

  d1.demonstrate
}
