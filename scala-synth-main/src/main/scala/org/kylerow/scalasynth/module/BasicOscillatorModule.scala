package org.kylerow.scalasynth.module

import math._
import org.kylerow.scalasynth.midi.SSMidiMessage
import org.kylerow.scalasynth.util.Note
import org.kylerow.scalasynth.midi.SSNoteOnMidiMessage
import org.kylerow.scalasynth.sound.AudioOutputs
import org.kylerow.scalasynth.midi.MidiInputs
import org.kylerow.scalasynth.Word
 
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
	var playingNote: Note = Note(false, 0);
	
	def setWave(wave: (Double)=>Double) = this.wave = wave;
	
	override def midiMessage(input :Int)(message :SSMidiMessage) = message match {
	  case SSNoteOnMidiMessage(note) => playingNote = note
	}
	
	override def nextAudioBuffer(output :Int)() :Array[Word] = 
	  if(playingNote.on) {
	    byteTone(key(playingNote.value));
	  } else { null }
	
	override def moreAudio(output :Int)() :Boolean = playingNote.on
	
	private def byteTone(tone: Int, speed: Double = 2) :Array[Word] = {
	  var buf :Array[Word] = Array.fill(configuration.getSampleRate())(Word(0));
      for (i <- 0 until (configuration.getSampleRate() / (speed * 2)).toInt by configuration.getBufferSize()) { 
        for (j <- 0 until configuration.getBufferSize()) { 
          val angle = (i + j) / (configuration.getSampleRate() / tone) * 2.0 * Pi 
          buf(i+j) = Word((wave(angle) * 100).toByte)
        } 
      } 
	  buf
    }
}