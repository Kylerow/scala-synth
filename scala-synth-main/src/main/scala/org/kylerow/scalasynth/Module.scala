package org.kylerow.scalasynth

import org.kylerow.scalasynth.midi.SSMidiMessage
import javax.inject.Inject

trait Module {
	@Inject var ssConfiguration :SSConfiguration = _;
	def midiMessage(input :Int)(message :SSMidiMessage);
    def nextAudioBuffer(output :Int)() :Array[Word];
	def moreAudio(output :Int)() :Boolean;
}