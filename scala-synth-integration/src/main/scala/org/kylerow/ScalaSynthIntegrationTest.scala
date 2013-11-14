package org.kylerow
import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner
import javax.sound.midi.MidiSystem
import org.scalamock.scalatest.MockFactory

@RunWith(classOf[JUnitRunner])
class ScalaSynthIntegrationTest 
	extends FlatSpec 
	with ShouldMatchers{
  
  "scala-synth" should "send midi messages to the receiver" in
  {
    // arrange
	var syn = MidiSystem.getSynthesizer();
	syn.open();
	val mc = syn.getChannels();
    val mockedReceiverFunction = MockFactory.mockFunction[MidiMessage,Unit];
    
    var midi = Midi()
    midi >>> mockedReceiverFunction
    
    // act
    mc(5).noteOn(50,1000);
    
    // assert
    mockedReceiverFunction expects (new MidiMessage());
    
  }
}