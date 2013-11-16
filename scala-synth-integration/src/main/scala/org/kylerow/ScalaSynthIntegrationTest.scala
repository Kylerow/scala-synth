package org.kylerow
import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner
import javax.sound.midi.MidiSystem
import org.scalamock.scalatest.MockFactory
import javax.sound.midi.MidiMessage
import org.kylerow.scalasynth.midi.Midi
import javax.sound.midi.ShortMessage

@RunWith(classOf[JUnitRunner])
class ScalaSynthIntegrationTest 
	extends FlatSpec 
	with ShouldMatchers
	with MockFactory{
  
  "scala-synth" should "send midi messages to the receiver" in
  {
    // arrange
    val tx = MidiSystem.getTransmitter()
    val mockedReceiverFunction = mockFunction[MidiMessage,Unit];
    mockedReceiverFunction expects *;
    
    var midi = Midi()
    midi >>> mockedReceiverFunction
    val rx = tx.getReceiver
    
    // act
    rx.send(new ShortMessage(ShortMessage.NOTE_ON, 0, 60, 93),-1) 
  }
}