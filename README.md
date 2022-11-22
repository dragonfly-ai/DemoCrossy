# DemoCrossy


&nbsp;&nbsp;&nbsp;A Scala.js library that painlessly routes standard ouptut from Scala.js cross projects to selectable DOMElements when deployed in the browser while leaving stdout functionally unaffected when deployed in Native, JVM and Node.js environments.
<br />

<h3>What is DemoCrossy's Intended Purpose:</h3>

&nbsp;&nbsp;&nbsp;As a tool, DemoCrossy occupies a space between Scastie and Fiddle by making it painless and easy to deploy Scala.js Cross Projects with command line applications directly into web pages.  This gives developers a convenient way to deploy demonstrations of their libraries so that other developers can comfortably evaluate them without pulling source code or installing dependencies.

<h3>How to use DemoCrossy:</h3>

&nbsp;&nbsp;&nbsp;To include it as an SBT dependency, modify `build.sbt` as follows:

```scala
resolvers += "dragonfly.ai" at "https://code.dragonfly.ai/"
libraryDependencies += "ai.dragonfly.code" %%% "democrossy" % "0.02"
```

&nbsp;&nbsp;&nbsp;Then extend the XApp trait from the main class.

&nbsp;&nbsp;&nbsp;If it relies on a main method, simply extend XApp directly:

```scala
object Demo extends XApp(DivConsole()) { ... }
object Demo extends XApp(DivConsole(id = "domId")) { ... }
object Demo extends XApp(DivConsole.light(id = "domId")) { ... }
object Demo extends XApp(DivConsole.dark(id = "domId")) { ... }
object Demo extends XApp(DivConsole(id = "domId", fg = "#fefefe", bg = "#3d3d3d", style = "padding: 8px; width: 50%;")) { ... }
```

&nbsp;&nbsp;&nbsp;If the main class extends App, it can inherit from both XApp and App as follows:

```scala
object Demo extends XApp(DivConsole()) with App { ... }
object Demo extends XApp(DivConsole(id = "domId")) with App { ... }
object Demo extends XApp(DivConsole.light(id = "domId")) with App { ... }
object Demo extends XApp(DivConsole.dark(id = "domId")) with App { ... }
object Demo extends XApp(DivConsole(id = "domId", fg = "#fefefe", bg = "#3d3d3d", style = "padding: 8px; width: 50%;")) with App { ... }
```
<br />
<h3>How DemoCrossy Works:</h3>

&nbsp;&nbsp;&nbsp;DemoCrossy relies on a parameterized trait called: `XApp` to inject an alternative implementation of an OutputStream between System.out and the default stdout if it detects a browser runtime environment.  `XApp` has no effect when running in Scala native, node.js, or the JVM.

&nbsp;&nbsp;&nbsp;The entire `XApp` trait:
```scala
package ai.dragonfly.democrossy

trait XApp(dc:DivConsole = DivConsole()) {
  System.setOut(out(dc))
}
```
&nbsp;&nbsp;&nbsp;Here, the `out(dc)` method call injects a DivOutputStream via System.setOut() if it detects a Web Browser runtime environment.  DivOutputStream parses all text written to System.out, usually through calls to `print(s:String)`, `println()`, and `println(s:String)`, then maps it to sequences of dom elements.  It further forwards these statements to the browser console.

&nbsp;&nbsp;&nbsp;DivOutputStream handles ANSI colors and other style codes like `BOLD`, and `ITALIC` through inline css style attributes on span elements.  It also supports special characters like: '\t', '\n', and '\r'.

&nbsp;&nbsp;&nbsp;Developers can customize the DOM element that hosts the console output by using the `DivConsole` factory methods:

```scala
def apply(id:String = "console", fg:String = "#eeeeee", bg:String = "#2b2b2b", style:String = ""):DivConsole

def dark(id:String = "console", style:String = ""):DivConsole

def light(id:String = "console", style:String = ""):DivConsole
```

&nbsp;&nbsp;&nbsp;The `id` parameter represents the selectable element id of a `<div>` element already present in the host html, however, if the page hasn't declared a `<div>` element with that id, DivConsole appends a new one to the `<body>`.
&nbsp;&nbsp;&nbsp;The `fg` and `bg` parameters represent the html hex strings of foreground and background colors respectively.  In the DivConsole, these map to css attributes: `color` and `background-color`.
&nbsp;&nbsp;&nbsp;Any additional customization occurs through the `style` parameter which should take the form of correctly formatted inline css.  For example, for padding and overflow scrolling, pass: `style = "padding: 8px; overflow: scroll;"`.  DivConsole will not validate any of these parameters, so users must take care to provide valid css literals.

<br />
<h3>Live DemoCrossy Demonstrations:</h3>

https://dragonfly.ai/democrossy/index.html

https://dragonfly.ai/cliviz/index.html