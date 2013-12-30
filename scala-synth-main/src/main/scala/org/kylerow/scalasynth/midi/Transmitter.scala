package org.kylerow.scalasynth.midi

import javax.sound.midi.Receiver
import javax.sound.midi.MidiMessage
import org.kylerow.scalasynth.note.a4

class Transmitter {
  var transmitter: javax.sound.midi.Transmitter = _
	def setTransmitter(transmitter: javax.sound.midi.Transmitter)={
	  this.transmitter = transmitter;
	  this
	}
  	def getTransmitter() = transmitter
  	def setCallback(callback :(Event)=>Unit) = {
  	  transmitter.setReceiver(
  	       new Receiver{
  	    	   def send( msg :MidiMessage, timeStamp :Long ) =
  	    	     callback(a4.on)
			   def close() = {}
  	       })
  	}
}
object Transmitter {
  def apply(transmitter: javax.sound.midi.Transmitter) = 
    new Transmitter().setTransmitter(transmitter);
}