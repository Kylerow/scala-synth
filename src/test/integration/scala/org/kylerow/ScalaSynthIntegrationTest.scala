package org.kylerow

import org.kylerow.midi.Midi
import javax.sound.midi.MidiMessage
import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner
import org.scalamock.scalatest.MockFactory

@RunWith(classOf[JUnitRunner])
class ScalaSynthIntegrationTest 
	extends FlatSpec 
	with ShouldMatchers{
  
  "scala-synth" should "send midi messages to the receiver" in
  {
    // arrange
    // *** TODO: set up origination of midi data
    val mockedReceiverFunction  = mockFunction[MidiMessage,Unit];
    
    var midi = Midi()
    midi >>> mockedReceiverFunction
    
    // act
    // *** TODO: do midi stuff
    
    // assert
    mockedReceiverFunction expects (new MidiMessage());
    
  }
}