package org.kylerow.scalasynth.midi

class TransmitterEventProvider extends EventProvider {
  var transmitter :Transmitter = _;
  def getTransmitter() = {this.transmitter}
  def setTransmitter(transmitter :Transmitter) = {
    this.transmitter = transmitter;
    this;
  }
  def begin() = {
    
  /*
   *  def connectReceiver( input :SSMidiMessage => Unit ) = {
		new Receiver{
	      def send( msg :MidiMessage, timeStamp :Long ) = {
	        input( SSNoteOnMidiMessage( d5 ))
	      }
	      def close() = {}
	    }
	}
   */
    this
  }
}
object TransmitterEventProvider {
  def apply (transmitter :Transmitter) = 
    new TransmitterEventProvider()
  			.setTransmitter(transmitter)
  			.begin()
}