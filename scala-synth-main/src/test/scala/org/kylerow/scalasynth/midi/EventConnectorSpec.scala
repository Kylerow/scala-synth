package org.kylerow.scalasynth.midi

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalamock.scalatest.MockFactory


@RunWith(classOf[JUnitRunner])
class EventConnectorSpec 
	extends FlatSpec 
	with ShouldMatchers
	with MockFactory{

	"Connect" should 
	"call add receiver on each provider" in {
		// arrange
		val mockProvider = mock[EventProvider]
		val providers = List(mockProvider)
		val mockReceiver = mock[EventReceiver]
		
		(mockProvider.addReceiver _) expects(mockReceiver)
		
		val eventConnector = new EventConnector()
		
		// act
		eventConnector connect(providers,mockReceiver);
	}
}