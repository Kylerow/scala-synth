scala-synth
===========

This is a full-featured modular software synthesizer written in Scala.

It will be configurable and runnable via a DSL in the Scala REPL.

In addition to a standalone mode integrating with standard midi and audio systems, we will target VST implementation using the Java VST wrapper.  This will broaden the potential usefulness of this synthesizer.

Building & Using
==========
There are two separate components:

1) <b>scala-synth-main</b> - the synthesizer code<br>
2) <b>scala-synth-integration</b> - the integration tests


Both can be built via Maven - 
- In <b>scala-synth-main</b>: <br>
  <pre>mvn clean package install</pre> 
  (this will put the package in the local repository so the integration package can test it
- In <b>scala-synth-integration</b> - 
  <pre>mvn clean package</pre>

To use the system - go into <b>scala-synth-main</b> - use the following command:

<pre>mvn scala:console</pre>

In Linux you may need to engage virtual midi: 
<pre>sudo modprobe snd-virmidi</pre>

Status
=========
A basic integration test, and a suite of unit tests are in place - this should allow for a harness to be updating or expanding on existing code.

There are a handful of open "issues" that I have brainstormed - there are a ton of other things that could be done with this - so if you have any additional ideas - let me know and I'll log them.
