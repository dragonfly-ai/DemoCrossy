package ai.dragonfly.democrossy.native

trait OutputSignal {
  val name:String
  def console:String
}

object ReverseSignal extends OutputSignal {
  override val name: String = "REVERSED"
  override val console:String = Console.REVERSED
}

trait Stylish extends OutputSignal {
  def css:String
}

case class ForegroundColor(override val name:String, override val console:String, hexColor:String) extends Stylish {
  override def css: String = s"color: $hexColor;"
}

case class BackgroundColor(override val name:String, override val console:String, hexColor:String) extends Stylish {
  override def css: String = s"background-color: $hexColor;"
}

case class StyleSignal(override val name:String, override val console:String, css:String) extends Stylish

object OutputSignal {

  final val BLACK: OutputSignal = ForegroundColor("BLACK", Console.BLACK, "#000000;")
  final val RED: OutputSignal = ForegroundColor("RED", Console.RED, "#f05147;")
  final val GREEN: OutputSignal = ForegroundColor("GREEN", Console.GREEN, "#5a8f2c;")
  final val YELLOW: OutputSignal = ForegroundColor("YELLOW", Console.YELLOW, "#a18415;")
  final val BLUE: OutputSignal = ForegroundColor("BLUE", Console.BLUE, "#3384cb;")
  final val MAGENTA: OutputSignal = ForegroundColor("MAGENTA", Console.MAGENTA, "#a76f9c;")
  final val CYAN: OutputSignal = ForegroundColor("CYAN", Console.CYAN, "#109aa2;")
  final val GRAY: OutputSignal = ForegroundColor("GRAY", Console.WHITE, "#807e6c;")
  final val WHITE: OutputSignal = ForegroundColor("WHITE", Console.RESET, "#eeeeee;")

  final val BLACK_B: OutputSignal = BackgroundColor("BLACK_B", Console.BLACK_B, "#000000;")
  final val RED_B: OutputSignal = BackgroundColor("RED_B", Console.RED_B, "#f05147;")
  final val GREEN_B: OutputSignal = BackgroundColor("GREEN_B", Console.GREEN_B, "#5a8f2c;")
  final val YELLOW_B: OutputSignal = BackgroundColor("YELLOW_B", Console.YELLOW_B, "#a18415;")
  final val BLUE_B: OutputSignal = BackgroundColor("BLUE_B", Console.BLUE_B, "#3384cb;")
  final val MAGENTA_B: OutputSignal = BackgroundColor("MAGENTA_B", Console.MAGENTA_B, "#a76f9c;")
  final val CYAN_B: OutputSignal = BackgroundColor("CYAN_B", Console.CYAN_B, "#109aa2;")
  final val WHITE_B: OutputSignal = BackgroundColor("WHITE_B", Console.WHITE_B, "#eeeeee;")

  final val RESET: OutputSignal = StyleSignal("RESET", Console.RESET, "")

  final val BOLD: OutputSignal = StyleSignal("BOLD", Console.BOLD, "font-weight: bold;")
  final val UNDERLINED: OutputSignal = StyleSignal("UNDERLINED", Console.UNDERLINED, "text-decoration: underline;")
  final val BLINK: OutputSignal = StyleSignal("BLINK", Console.BLINK, "animation: blink 1s infinite;")
  final val INVISIBLE: OutputSignal = StyleSignal("INVISIBLE", Console.INVISIBLE, "opacity: 0.0;")

  final val REVERSED: OutputSignal = ReverseSignal

  val controlSignals:Map[String, OutputSignal] = Map[String, OutputSignal](
    // Foreground Colors
    Console.BLACK -> BLACK,
    Console.RED -> RED,
    Console.GREEN -> GREEN,
    Console.YELLOW -> YELLOW,
    Console.BLUE -> BLUE,
    Console.MAGENTA -> MAGENTA,
    Console.CYAN -> CYAN,
    Console.WHITE -> GRAY,
    // Background Colors
    Console.BLACK_B -> BLACK_B,
    Console.RED_B -> RED_B,
    Console.GREEN_B -> GREEN_B,
    Console.YELLOW_B -> YELLOW_B,
    Console.BLUE_B -> BLUE_B,
    Console.MAGENTA_B -> MAGENTA_B,
    Console.CYAN_B -> CYAN_B,
    Console.WHITE_B -> WHITE_B,
    // Swap Foreground and Background
    Console.RESET -> RESET,
    // Style
    Console.BOLD -> BOLD,
    Console.UNDERLINED -> UNDERLINED,
    // Effects
    Console.BLINK -> BLINK,
    Console.REVERSED -> REVERSED,
    Console.INVISIBLE -> INVISIBLE
  )

}
