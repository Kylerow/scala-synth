package org.kylerow
import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner
import javax.sound.midi.MidiSystem
import org.scalamock.scalatest.MockFactory
import javax.sound.midi.MidiMessage
import javax.sound.sampled.SourceDataLine
import org.kylerow.scalasynth.audio.AudioPort
import org.kylerow.scalasynth.audio.AudioSystem
import org.kylerow.scalasynth.Injectable
import com.google.inject.Guice
import com.google.inject.AbstractModule
import org.kylerow.scalasynth.midi.Midi
import org.kylerow.scalasynth.audio.Audio
import org.kylerow.scalasynth.module.BasicOscillatorModule
import org.kylerow.scalasynth.audio.Audio._
import org.kylerow.scalasynth.note.a4

@RunWith(classOf[JUnitRunner])
class ScalaSynthIntegrationTest 
	extends FlatSpec 
	with ShouldMatchers
	with MockFactory{
  
  "main midi input" should "drive basic oscillator" in 
  {
    // arrange
    val mockSourceDataLine = stub[SourceDataLine]
    val audioPort = new AudioPort
    audioPort.sourceDataLine = mockSourceDataLine
    
    val mockAudioSystem = mock[AudioSystem]
    (mockAudioSystem.getPort _).expects().returning(audioPort);
    
    Injectable.injector = Guice.createInjector(
    new AbstractModule{
    	def configure() = bind(classOf[AudioSystem]).toInstance(mockAudioSystem);
    });
    
    val midi = Midi()
    val audio = Audio()
    val basicOscillator = Injectable.injector.getInstance(classOf[BasicOscillatorModule]);
    
    basicOscillator.setWave(basicOscillator.sine)
    
    // act 
    midi >> basicOscillator
    (basicOscillator,1) >> audio;
    a4.on >> midi;
    
    Thread.sleep(300000);
    
    // assert
    val period = 1/440
    val s = period * 96000
    var endMinusOneHundredSamples :Byte=0;
    inSequence{
	    (mockSourceDataLine.write _).verify(where{
	      (data :Array[Byte],buf,len) =>
	        endMinusOneHundredSamples = data(data.length-100)
	        ((data(1000)==data(1000+Math.ceil(s).asInstanceOf[Int]) ||
	        data(1000)==data(1000+Math.floor(s).asInstanceOf[Int])) &&
	        (data(2016)==data(2016+Math.ceil(s).asInstanceOf[Int])  ||
	        data(2016)==data(2016+Math.floor(s).asInstanceOf[Int])))
	    })
	    (mockSourceDataLine.write _).verify(where{
	      (data :Array[Byte],buf,len) =>	        
	        ((data(100+Math.ceil(s).asInstanceOf[Int])==endMinusOneHundredSamples   ||
	         data(100+Math.floor(s).asInstanceOf[Int])==endMinusOneHundredSamples) &&
	        (data(1000)==data(1000+Math.ceil(s).asInstanceOf[Int]) ||
	        data(1000)==data(1000+Math.floor(s).asInstanceOf[Int])) &&
	        (data(2016)==data(2016+Math.ceil(s).asInstanceOf[Int])  ||
	        data(2016)==data(2016+Math.floor(s).asInstanceOf[Int])))
	    })
    }
  }
  
}