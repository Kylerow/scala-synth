package org.kylerow.scalasynth

import math._
import org.kylerow.midi.SSMidiMessage
import org.kylerow.util.Note
import org.kylerow.midi.SSNoteOnMidiMessage
 
/** 
 *  Basic oscillator module.
 *  
 *  DSP / Math stuff started from code @
 *  	http://vigtig.it/blog/blog/2011/04/12/programming-music/
 * 
 *  @Author KyleRow
 */
class BasicOscillatorModule
	extends Module 
	   with MidiInputs 
	   with AudioOutputs{
	val numberOfMidiInputs = 1
	val numberOfAudioOutpus = 2
	
	def square(d: Double): Double = if (sin(d) < 0) -1 else 1 
  	def sine(d: Double): Double = sin(d) 
  	def saw(d: Double): Double = (Pi - (d % (Pi * 2))) / Pi 
	def key(n: Int): Int = (440f * pow((pow(2, 1 / 12f)), n - 49 - 12)).toInt 
	
	var wave: (Double)=>Double = _
	var playingNote: Note = _
	
	def setWave(wave: (Double)=>Double) = this.wave = wave;
	
	override def midiMessage(input :Int, message :SSMidiMessage) = message match {
	  case SSNoteOnMidiMessage(note) => playingNote = note
	}
}