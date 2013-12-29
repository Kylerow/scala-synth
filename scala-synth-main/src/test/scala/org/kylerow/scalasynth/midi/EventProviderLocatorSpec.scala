package org.kylerow.scalasynth.midi

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import scala.collection.JavaConversions._
import org.scalamock.scalatest.MockFactory

/**
 * Notes:
 * Having to use mockito - since scalamock doesn't seem to be up
 * to the task here.  
 */
@RunWith(classOf[JUnitRunner])
class EventProviderLocatorSpec
	extends FlatSpec 
	with ShouldMatchers
	with MockFactory{
	"getAllSystemMidiTransmitters" should 
	"return a list of event providers corresponding to transmitters plugged into the falsified midiSystem" in {
	  // arrange
	  var mockMidiDeviceInfo = mock[MidiDeviceInfo];
	  var mockMidiSystem = mock[org.kylerow.scalasynth.midi.MidiSystem];
	  var mockMidiDevice = mock[MidiDevice];
	  var mockTransmitter = stub[Transmitter];
	  
	 (mockMidiSystem.getMidiDeviceInfo _) expects() returning(List(mockMidiDeviceInfo));
	 (mockMidiSystem getMidiDevice _)
	 		.expects(mockMidiDeviceInfo)
	  		.returning(mockMidiDevice);
	 (mockMidiDevice.open _)
	  		.expects();
	  (mockMidiDevice.getTransmitters _)
	  		.expects()
	  		.returning(List[Transmitter]())	;  
	  (mockMidiDevice.getTransmitter _)
	  		.expects()
	  		.returning(mockTransmitter);
	 	  		
	  var eventProviderLocator = new EventProviderLocator;
	  eventProviderLocator.midiSystem = mockMidiSystem;
	  
	  // act
	  var results = eventProviderLocator.getAllSystemMidiTransmitters();
	  
	  // assert
	  assert(
	      results(0)
	      	.asInstanceOf[TransmitterEventProvider]
	      	.getTransmitter() == mockTransmitter)
	}
}