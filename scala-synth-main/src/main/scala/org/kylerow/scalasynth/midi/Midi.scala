package org.kylerow.scalasynth.midi

import javax.sound.midi.MidiMessage
import javax.sound.midi.Receiver
import javax.sound.midi.MidiUnavailableException
import org.kylerow.scalasynth.Injectable
import com.google.inject.Inject
import org.kylerow.scalasynth
import javax.sound.midi
import org.kylerow.scalasynth.note._

class Midi{
	@Inject var eventProviderLocator :EventProviderLocator = _
	
    def connect (tx :List[Transmitter], rx :Receiver) = 
       tx.filter(_!=null);
   
    def connectReceiver( input :SSMidiMessage => Unit ) = {
		new Receiver{
	      def send( msg :MidiMessage, timeStamp :Long ) = {
	        input( SSNoteOnMidiMessage( d5 ))
	      }
	      def close() = {}
	    }
	}
    
    
    def registerReceiver() = {
      val eventProviders = eventProviderLocator.getAllSystemMidiTransmitters();
    }
    
    def >>> (rx :SSMidiMessage => Unit) = {}//connectAll(rx);
    def >> (rx :MidiInputs, inp :Int) = {}//connectAll(rx.midiMessage(1))
    
    def playNote (note :Note) =  {}
}

object Midi extends Injectable{
  def apply() :Midi = injector.getInstance(classOf[Midi]);
}