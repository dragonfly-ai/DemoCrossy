package ai.dragonfly.democrossy.native

import java.io.OutputStream

class DivOutputStream(val divConsole:BrowserDivConsole) extends OutputStream {

  final val out:java.io.PrintStream = System.out

  private var signalBuffer:Array[Char] = Array[Char]('\u001b', '[', '0', '0', 'm')
  private var state:Int = 0

  def appendChunk( s:String, start:Int, i:Int ):Unit = {
    if (start < i) {
      val chunk: String = s.substring(start, i)
      // System.err.println(s"appendChunk: " + chunk)
      divConsole.append(chunk)
      out.print(chunk)
    }
  }


  def write(s: String): Unit = {

    var start:Int = 0
    var i:Int = 0

    def resetState():Unit = {
      if (state == 0) {
        appendChunk(s, start, i - state)
      }
      start = i + 1
      state = 0
      signalBuffer = Array[Char]('\u001b', '[', '0', '0', 'm')
    }

    def processControlSignal():Unit = {
      val signal:String = new String(signalBuffer, 0, state + 1)
      // System.err.println(s"signal: $signal")

      OutputSignal.controlSignals.get(signal) match {
        case Some(os: OutputSignal) =>
          appendChunk(s, start, i - state)
          divConsole(os)
        case None =>
          // System.err.println(s"Unknown signal: $signal")
      }

      resetState()
    }

    while (i < s.length) {
      // System.err.println(s"${s.substring(i - state, i)} vs ${new String(signalBuffer)}")
      val c:Char = s.charAt(i)
      state match {
        case 0 =>
          c match {
            case '\n' =>
              appendChunk(s, start, i)
              start = i + 1
              divConsole.newLine()
              out.print('\n')
              out.flush()
            case '\r' => divConsole.overWright()
            // case '\u001b' if (i + 3) < s.length && s.charAt(i + 1) == '[' => // Is this a formatting signal?
            case '\u001b' => state = 1 // Is this a formatting signal?
            case _ =>
          }
        case 1 =>
          c match {
            case '[' =>
              signalBuffer(1) = '['
              state = 2
            case _ => resetState()
          }
        case 2 =>
          if (c.isDigit) {
            signalBuffer(2) = c
            state = 3
          }
          else resetState()
        case 3 =>
          // System.err.println(s"state = $state and c = $c")
          if (c.isDigit) {
            signalBuffer(3) = c
            state = 4
          } else if (c == 'm') {
            signalBuffer(3) = 'm'
            processControlSignal()
          } else resetState()
        case 4 =>
          if (c == 'm') {
            signalBuffer(4) = 'm'
            processControlSignal()
          } else resetState()
        case _ => resetState()
      }
      i += 1
    }
    appendChunk(s, start, i - state)
  }

  override def write(b: Int): Unit = write((b & 0x000000ff).toChar.toString)

  override def write(b: Array[Byte]): Unit = write(new String(b))

  override def write(b: Array[Byte], off: Int, len: Int): Unit = write(new String(b, off, len))

}
