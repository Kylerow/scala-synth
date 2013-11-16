package org.kylerow.scalasynth.midi

import javax.sound.midi.MidiMessage
import javax.sound.midi.Receiver
import javax.sound.midi.Transmitter
import org.kylerow.scalasynth.util.avoidException
import javax.sound.midi.MidiUnavailableException
import scala.collection.JavaConversions._
import org.kylerow.scalasynth.Injectable
import com.google.inject.Inject
import org.kylerow.scalasynth.sound.Generator
import org.kylerow.scalasynth
import javax.sound.midi

class Midi {
	@Inject var midiSystem :MidiSystem = _
	
    def avoidMidiUnavailableException[R](func :()=>R) = avoidException(classOf[MidiUnavailableException])(func);
    def avoidAll[R](func :()=>R) = avoidException(classOf[Exception])(func);
 
    def connect (tx :List[Transmitter], rx :Receiver) = 
       tx.filter(_!=null).foreach({ _.setReceiver(rx)});
   
    def connectReceiver( input :MidiMessage => Unit ) = {
		new Receiver{
	      def send( msg :MidiMessage, timeStamp :Long ) = input(msg)
	      def close() = {}
	    }
	}
    
    def connectAll (rx :MidiMessage => Unit) = {
      connect ( 
        getSystemTransmitters, 
        connectReceiver(rx) 
      );
    }
    
    def getSystemTransmitters() :List[Transmitter] = 
     midiSystem
   		.getMidiDeviceInfo()
   		.flatMap(x => avoidMidiUnavailableException (()=>{
   		  midiSystem
   		  	.getMidiDevice(x)
   		  	.open(); 
   		  midiSystem
   		  	.getMidiDevice(x)
   		  	.getTransmitters()
   		    .toList.::(avoidAll(()=>midiSystem.getMidiDevice(x).getTransmitter()))
   		}));
    
    def >>> (rx :MidiMessage => Unit) = connectAll(rx);
    def >> (generator :Generator) = {}
    
}

object Midi extends Injectable{
  def apply() :Midi = injector.getInstance(classOf[Midi]);
}