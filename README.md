scala-synth
===========

This is a full-featured modular software synthesizer written in Scala.

It will be configurable and runnable via a DSL in the Scala REPL.

In addition to a standalone mode integrating with standard midi and audio systems, we will target VST implementation using the Java VST wrapper.  This will broaden the potential usefulness of this synthesizer.

***A note for testing in Linux:  virtual midi is setup with the following: sudo modprobe snd-virmidi
