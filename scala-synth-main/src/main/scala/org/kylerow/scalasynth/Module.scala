package org.kylerow.scalasynth

import org.kylerow.midi.SSMidiMessage

trait Module {
	def midiMessage(input :Int, message :SSMidiMessage);
}