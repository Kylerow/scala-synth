package org.kylerow.scalasynth.module

import math._
import org.kylerow.scalasynth.midi.SSMidiMessage
import org.kylerow.scalasynth.midi.SSNoteOnMidiMessage
import org.kylerow.scalasynth.audio.AudioOutputs
import org.kylerow.scalasynth.Word
import org.kylerow.scalasynth.note._
import org.kylerow.scalasynth.midi.EventInputs
import org.kylerow.scalasynth.midi.Event
import java.util.logging.Logger
import com.google.inject.Inject
 
/** 
 *  Basic oscillator module.
 *  
 *  DSP / Math stuff (byteTone()) started from code @
 *  	http://vigtig.it/blog/blog/2011/04/12/programming-music/
 * 
 *  @Author KyleRow
 */
class BasicOscillator
	extends Module 
	   with EventInputs 
	   with AudioOutputs{
  
	val numberOfEventInputs = 1
	val numberOfAudioOutpus = 2
	
	@Inject var logger :Logger = _
	
	def square(d: Double): Double = if (sin(d) < 0) -1 else 1 
  	def sine(d: Double): Double = sin(d) 
  	def saw(d: Double): Double = (Pi - (d % (Pi * 2))) / Pi 
	def key(n: Int): Int = (440f * pow((pow(2, 1 / 12f)), n - 69)).toInt 
	
	var wave: (Double)=>Double = _
	var playingNote: Note = a4.off
	var angularOffset: Double = 0.0
	
	def setWave(wave: (Double)=>Double) = this.wave = wave;
	
	override def eventMessage(input :Int)(message :Event) =
    message match {
	  	case x :Note => playingNote = x
	}
	
	override def nextAudioBuffer(output :Int)() :Array[Word] = 
	  if(playingNote.playing) {
	    tone(key(playingNote.value));
	  } else { null }
	
	override def moreAudio(output :Int)() :Boolean = playingNote.playing
	
	private def tone(tone: Int) :Array[Word] = {
	  var buf :List[Word] = List[Word]()
      for (j <- 0 until configuration.getWriteLength()) { 
    	  val angle = ((j * 2.0 * Pi * tone) / configuration.getSampleRate)+angularOffset 
          
          val value = (wave(angle) * 100).toInt 
          logger.fine("[tone="+tone+", angularOffset="+angularOffset+", angle="+angle+",wave(angle)="+wave(angle)+",j="+j+",value="+value+"]")
          
          buf = buf:::List(Word(configuration.getSampleSize,value))
      }
	  angularOffset = 
	    (((configuration.getWriteLength() * 2.0 * Pi * tone) /
	    		configuration.getSampleRate) + angularOffset) % (2.0*Pi)
	  buf.toArray
    }
}