package org.kylerow.scalasynth.midi

trait MidiInputs{
	val numberOfMidiInputs :Int
	def midiMessage(input :Int)(message :SSMidiMessage);
}