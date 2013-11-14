scala-synth
===========

Midi synth written in Scala

My goal for right now is to have a basic synthesizer with a dsl to configure the audio components as well as the midi.  

It would be cool if the synth could be run just from the REPL.


Also - there is a Java Wrapper for VST ... it would be nice to wrap this in VST - this could then be used from any DAW.  Though - of course - for my personal favorite, Protools, it will still mean getting the VST translator.

For now though, we'll start with the just connecting with the midi system.  A note for testing in Linux:  virtual midi is setup with the following: sudo modprobe snd-virmidi
