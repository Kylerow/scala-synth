package org.kylerow.scalasynth

class BasicOscillatorModule(val numberOfMidiInputs :Int, val numberOfAudioOutpus :Int) 
	extends Module 
	   with MidiInputs 
	   with AudioOutputs{
	
}