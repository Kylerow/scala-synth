package org.kylerow.scalasynth.midi

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalamock.scalatest.MockFactory

@RunWith(classOf[JUnitRunner])
class EventProviderSpec 
extends FlatSpec 
	with ShouldMatchers
	with MockFactory{
	
  "AddReceiver" should "put a new receiver on the " +
  		"instance list" in {
    
    // arrange
    val mockReceiver = mock[EventReceiver]
    val eventProvider = new EventProvider();
    
    // act
    eventProvider.addReceiver(mockReceiver);
    
    // assert
    assert(eventProvider.receivers.contains(mockReceiver))
  }
  
  "ReceiveEvent" should "send event to each of the " +
  		"receivers" in{
    // arrange
    val mockReceiver = mock[EventReceiver]
    val eventProvider = new EventProvider()
    val mockEvent = mock[Event]
    
    (mockReceiver.receive _) expects (mockEvent)
    eventProvider.receivers = List(mockReceiver)
    
    // act
    eventProvider.sendEvent(mockEvent)
    
  }
}