package ai.dragonfly.democrossy.native

import java.io.OutputStream

class DivOutputStream(val divConsole:DivConsole = DivConsole()) extends OutputStream {

  val out:java.io.PrintStream = System.out

  def write(s: String): Unit = {
    //out.println(s)
    var start:Int = 0
    var i:Int = 0

    while (i < s.length) {
      s.charAt(i) match {
        case '\n' => divConsole.newLine()
        case '\r' => divConsole.overWright()
        case '\u001b' if s.charAt(i + 1) == '[' => // Is this a formatting signal?

          var end: Int = i + 2

          while (end < s.length && end - i < 5 && (s.charAt(end).isDigit || s.charAt(end) == 'm')) end += 1

          val signal: String = s.substring(i, end)

          if (signal.endsWith("m")) {
            if (start < i) {
              val chunk: String = s.substring(start, i)
              divConsole.append(chunk)
              out.print(chunk)
            }

            OutputSignal.controlSignals.get(signal) match {
              case Some(os: OutputSignal) => divConsole(os)
              case None => out.println(s"Unknown signal: $signal")
            }

            start = end
            i = end - 1
          }
       case _ =>
      }
      i += 1
    }
    if (start < i) {
      val chunk:String = s.substring(start, i)
      divConsole.append(chunk)
      out.print(chunk)
    }
  }

  override def write(b: Int): Unit = write((b & 0x000000ff).toChar.toString)

  override def write(b: Array[Byte]): Unit = write(new String(b))

  override def write(b: Array[Byte], off: Int, len: Int): Unit = write(new String(b, off, len))

}
