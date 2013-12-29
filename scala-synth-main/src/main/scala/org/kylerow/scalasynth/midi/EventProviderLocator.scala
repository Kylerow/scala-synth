package org.kylerow.scalasynth.midi

import com.google.inject.Inject
import scala.collection.JavaConversions._
import org.kylerow.scalasynth.util.AvoidException._
import scala.collection.mutable.MutableList

class EventProviderLocator {
	@Inject var midiSystem :MidiSystem = _;
	
	def getAllSystemMidiTransmitters() :List[EventProvider] = {
	   midiSystem.getMidiDeviceInfo.flatMap {
	     (midiDeviceInfo) =>
		     val device = midiSystem.getMidiDevice(midiDeviceInfo);
		     device.open;
		     val transmitters = MutableList[Transmitter]();
		     avoidAll(()=>transmitters+=device.getTransmitter)
		     avoidAll(()=>transmitters.addAll(device.getTransmitters));
		     transmitters.filter(_!=null).map(TransmitterEventProvider(_))
	   }
	}
}