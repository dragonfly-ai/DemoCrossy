/*
 * Copyright 2023 dragonfly.ai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import ai.dragonfly.democrossy.*

import Console.*

object Demo extends XApp(NativeConsole(style = "padding: 8px; overflow: scroll;")) with App {


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
      println("\n")
    }

    override def name: String = "Hello World"
  }

  val d2: Demonstration = new Demonstration {

    override def demo(): Unit = {
      println()
      // simple demo taken from: https://docs.scala-lang.org/overviews/scala-book/command-line-io.html
      print("Enter your first name: ")
      val firstName = readLine()
      print("Enter your last name: ")
      val lastName = readLine()
      println(s"Your name is $firstName $lastName")
      println()
    }

    override def name: String = "readLine()"

  }


  val d3: Demonstration = new Demonstration {

    override def demo(): Unit = {
      println()
      val nationality:String = prompt("What is your nationality?")
      println(s"$nationality, huh?  Let me find my globe.")
      println()
    }

    override def name: String = "prompt(message)"

  }

  d1.demonstrate
  println("\n\n\n")
  d2.demonstrate
  println("\n\n\n")
  d3.demonstrate

}
