# DemoCrossy


&nbsp;&nbsp;&nbsp;This Scala.js library can help you deploy Scala command line applications directly into web pages by routing and formatting `System.out` to a DOM element and expressing `readLine()`statements as calls to `window.prompt()`.  To quickly understand what DemoCrossy can do, you may <a href="https://dragonfly-ai.github.io/DemoCrossy/index">try the demo.</a>

&nbsp;&nbsp;&nbsp;As a tool, DemoCrossy occupies a space between <a href="https://scastie.scala-lang.org/">Scastie</a>, <a href="https://github.com/scalafiddle">scalafiddle</a>, and <a href="https://scalameta.org/mdoc/">mdoc</a> by making it painless and easy to deploy Scala.js Cross Projects with command line applications directly into web pages.  This gives developers a convenient way to deploy demonstrations of their libraries so that other developers can comfortably evaluate them without pulling source code or installing dependencies.

<h3>How to use DemoCrossy:</h3>

&nbsp;&nbsp;&nbsp;To include it as an SBT dependency, modify `build.sbt` as follows:

```scala
libraryDependencies += "ai.dragonfly.code" %%% "democrossy" % "0.1"
```

&nbsp;&nbsp;&nbsp;Then extend the `XApp` trait from the main class.

&nbsp;&nbsp;&nbsp;If it relies on a main method, simply extend XApp directly:

```scala
object Demo extends XApp(NativeConsole()) { /* minimal */ }

object Demo extends XApp(NativeConsole(id = "domId")) { /* specify a DOM Element ID */ }

object Demo extends XApp(NativeConsole.light(id = "domId")) { /* light themed with specified DOM Element ID */ }

object Demo extends XApp(NativeConsole.dark(id = "domId")) { /* dark themed with specified DOM Element ID*/ }

object Demo extends XApp(
  NativeConsole(
    id = "domId", fg = "#fefefe", bg = "#3d3d3d", style = "padding: 8px; width: 50%;"
  )
) { /* specified DOM Element ID, foreground and background colors along with inline style */ }
```

&nbsp;&nbsp;&nbsp;If the main class extends App, it can inherit from both XApp and App as follows:

```scala
object Demo extends XApp(NativeConsole()) with App { /*...*/ }
```

<h3>How It Works:</h3>

&nbsp;&nbsp;&nbsp;DemoCrossy relies on a parameterized trait called: `XApp` to inject an alternative implementation of an `OutputStream` between `System.out` and the default `stdout` if it detects a browser runtime environment.  `XApp` has no effect when running in Scala native or the JVM, but for node.js environments, it depends on the <a href="https://www.npmjs.com/package/prompt-sync">prompt-sync</a> node module to accept command line input.

&nbsp;&nbsp;&nbsp;The entire `XApp` trait:
```scala
trait XApp(dc:NativeConsole = NativeConsole()) {
  System.setOut(out(dc))
  export dc.*
}
```
&nbsp;&nbsp;&nbsp;Here, the `out(dc)` method call injects a `DivOutputStream` via `System.setOut()` if it detects a Web Browser runtime environment, otherwise it does nothing.  `DivOutputStream` parses all text written to `System.out`, usually through calls to `print(s:String)`, `println()`, and `println(s:String)`, then maps them to sequences of dom elements before passing each line on to the browser console.  

&nbsp;&nbsp;&nbsp;`DivOutputStream` handles ANSI colors and other style codes like `BOLD`, and `ITALIC` through inline css style attributes on span elements.  It also supports special characters like: '\t', '\n', and '\r'.

&nbsp;&nbsp;&nbsp;You can customize console formatting by providing a CSS class for the console DOM element that hosts the console output, or by using the `NativeConsole` factory methods:

```scala
def apply(id:String = "console", fg:String = "#eeeeee", bg:String = "#2b2b2b", style:String = ""):NativeConsole

def dark(id:String = "console", style:String = ""):NativeConsole

def light(id:String = "console", style:String = ""):NativeConsole
```

&nbsp;&nbsp;&nbsp;The `id` parameter represents the selectable element id of a `<div>` element already present in the host html, however, if the page hasn't declared a `<div>` element with that id, NativeConsole appends a new one to the `<body>`.
&nbsp;&nbsp;&nbsp;The `fg` and `bg` parameters represent the html hex strings of foreground and background colors respectively.  In the NativeConsole, these map to css attributes: `color` and `background-color`.
&nbsp;&nbsp;&nbsp;Any additional customization occurs through the `style` parameter which should take the form of correctly formatted inline css.  For example, for padding and overflow scrolling, pass: `style = "padding: 8px; overflow: scroll;"`.  NativeConsole will not validate any of these parameters, so users must take care to provide valid css literals.

<a href="https://dragonfly-ai.github.io/DemoCrossy/index">Try the demo!</a><br />
<hr />
<a href="https://github.com/dragonfly-ai/">Other Projects by dragonfly.ai</a><br />