package org.kylerow.scalasynth.midi

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import scala.collection.JavaConversions._
import org.mockito.Mockito._

/**
 * Notes:
 * Having to use mockito - since scalamock doesn't seem to be up
 * to the task here.  
 */
@RunWith(classOf[JUnitRunner])
class EventProviderLocatorSpec
	extends FlatSpec 
	with ShouldMatchers{
	"getAllSystemMidiTransmitters" should 
	"return a list of event providers corresponding to transmitters plugged into the falsified midiSystem" in {
	  // arrange
	  var mockMidiDeviceInfo = mock(classOf[MidiDeviceInfo]);
	  var mockMidiSystem = mock(classOf[org.kylerow.scalasynth.midi.MidiSystem]);
	  var mockMidiDevice = mock(classOf[MidiDevice]);
	  var mockTransmitter = mock(classOf[Transmitter]);
	  
	  when(mockMidiSystem.getMidiDeviceInfo())
	  		.thenReturn(List(mockMidiDeviceInfo));
	  when(mockMidiSystem.getMidiDevice(mockMidiDeviceInfo))
	  		.thenReturn(mockMidiDevice);
	  when(mockMidiDevice.getTransmitters())
	  		.thenReturn(List[Transmitter]())	  
	  when(mockMidiDevice.getTransmitter())
	  		.thenReturn(mockTransmitter)

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