package ai.dragonfly.democrossy

trait XApp(dc:DivConsole = DivConsole()) {
  System.setOut(out(dc))
}
