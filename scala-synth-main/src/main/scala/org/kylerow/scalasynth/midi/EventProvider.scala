package org.kylerow.scalasynth.midi

trait EventProvider {
  def addReceiver(eventReceiver :EventReceiver) = {
    
  }
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
}