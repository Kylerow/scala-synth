package org.kylerow.scalasynth.module

import math._
import org.kylerow.scalasynth.midi.SSMidiMessage
import org.kylerow.scalasynth.midi.SSNoteOnMidiMessage
import org.kylerow.scalasynth.audio.AudioOutputs
import org.kylerow.scalasynth.midi.MidiInputs
import org.kylerow.scalasynth.Word
import org.kylerow.scalasynth.note._
 
/** 
 *  Basic oscillator module.
 *  
 *  DSP / Math stuff (byteTone()) started from code @
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
	var playingNote: Note = a4.off
	
	def setWave(wave: (Double)=>Double) = this.wave = wave;
	
	override def midiMessage(input :Int)(message :SSMidiMessage) = message match {
	  case SSNoteOnMidiMessage(note) => playingNote = note
	}
	
	override def nextAudioBuffer(output :Int)() :Array[Word] = 
	  if(playingNote.playing) {
	    tone(playingNote.value);
	  } else { null }
	
	override def moreAudio(output :Int)() :Boolean = playingNote.playing
	
	private def tone(tone: Int) :Array[Word] = {
	  var buf :Array[Word] = Array.fill(configuration.getWriteLength)(Word(0,0));
      for (j <- 0 until configuration.getWriteLength()) { 
    	  // We'll want to scale this out for larger sample size to 
    	  // to take advantage of the additional headroom
          val angle = (j) / (configuration.getSampleRate() / tone) * 2.0 * Pi 
          val value = (wave(angle) * 100).toInt 
          
          buf(j) = Word(configuration.getSampleSize,value)
      } 
	  buf
    }
}