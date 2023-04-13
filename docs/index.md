# DemoCrossy
## About this Demo:
&nbsp;&nbsp;&nbsp;The Console Output to the right exercises three main features of DemoCrossy:<br />

1. Redefine `readLine()` as `window.prompt()`:<br />

```scala
// Simple example taken from:
// https://docs.scala-lang.org/overviews/scala-book/command-line-io.html
override def demo(): Unit = {
  println()
  print("Enter your first name: ")
  val firstName = readLine()
  print("Enter your last name: ")
  val lastName = readLine()
  println(s"Your name is $firstName $lastName")
  println()
}
```

&nbsp;&nbsp;&nbsp;Because `window.prompt()` takes a message parameter, `readLine()` passes the most recent line of consol output to prompt.<br />

2.  DemoCrossy provides every `XApp` context with a `prompt()` method.  In the browser, this maps directly to `window.prompt()`, in node.js it relies on the <a href="https://www.npmjs.com/package/prompt-sync">prompt-sync</a> node module so make sure you've installed prompt-sync if you plan on running DemoCrossy command line programs on node.<br />

```scala
override def demo(): Unit = {
  println()
  val nationality: String = prompt("What is your nationality?")
  println(s"$nationality, huh?  Let me find my globe.")
  println()
}
```

&nbsp;&nbsp;&nbsp;Although not standard Scala, you can take advantage of the `prompt()` method to provide a more consistent experience across the various Scala platforms.<br />

3.  The final demonstration aims at exhaustive coverage of ANSI text formatting.  Although many modern ignore blink formatting, DemoCrossy honors it (for now).<br />

<hr />
<a href="https://github.com/dragonfly-ai/DemoCrossy">Back to DemoCrossy GitHub Project</a><br />
<a href="https://github.com/dragonfly-ai/">Other Projects by dragonfly.ai</a><br />

<script type="application/javascript" src="js/main.js"></script>