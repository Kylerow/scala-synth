package org.kylerow.scalasynth.midi

import com.google.inject.Inject
import scala.collection.JavaConversions._
import org.kylerow.scalasynth.util.AvoidException._

class EventProviderLocator {
	@Inject var midiSystem :MidiSystem = _;
	
	def getAllSystemMidiTransmitters() :List[EventProvider] = {
     midiSystem
   		.getMidiDeviceInfo()
   		.flatMap(x => avoidMidiUnavailableException (()=>{
   		  midiSystem
   		  	.getMidiDevice(x)
   		  	.open(); 
   		  midiSystem
   		  	.getMidiDevice(x)
   		  	.getTransmitters()
   		    .toList.::(avoidAll(()=>
   		      	midiSystem
   		      		.getMidiDevice(x)
   		      		.getTransmitter())) 
   		})).map(x=>TransmitterEventProvider(x))
	}
}