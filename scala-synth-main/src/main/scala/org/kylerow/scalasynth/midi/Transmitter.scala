package org.kylerow.scalasynth.midi

class Transmitter {
  var transmitter: javax.sound.midi.Transmitter = _
	def setTransmitter(transmitter: javax.sound.midi.Transmitter)={
	  this.transmitter = transmitter;
	  this
	}
}
object Transmitter {
  def apply(transmitter: javax.sound.midi.Transmitter) = 
    new Transmitter().setTransmitter(transmitter);
}