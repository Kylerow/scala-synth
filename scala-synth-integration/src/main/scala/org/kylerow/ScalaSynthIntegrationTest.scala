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
import org.kylerow.scalasynth.note.a4
import java.util.logging.Logger
import java.util.logging.Level
import java.util.logging.LogManager
import org.kylerow.util.fineLogging
import org.kylerow.scalasynth.audio.Audio
import org.kylerow.scalasynth.audio.Audio._
import org.kylerow.scalasynth.module.BasicOscillator

@RunWith(classOf[JUnitRunner])
class ScalaSynthIntegrationTest 
	extends FlatSpec 
	with ShouldMatchers
	with MockFactory{
  
  "main midi input" should "drive basic oscillator" in 
  {
    val logger = Logger.getLogger(this.getClass().getName());
    
    // arrange
    val mockSourceDataLine = mock[SourceDataLine]
    val audioPort = new AudioPort
    audioPort.sourceDataLine = mockSourceDataLine
    
    val mockAudioSystem = mock[AudioSystem]
    (mockAudioSystem.getPort _).expects().returning(audioPort);
     
    val period :Double = 1.asInstanceOf[Double]/440.asInstanceOf[Double]
    val samplesPerPeriod :Double = period * 96000
    (mockSourceDataLine.write _) expects (where{
      (data :Array[Byte],buf,len) =>
        logger.fine(
            "[samplesPerPeriod="+samplesPerPeriod+
            ", period="+period+
            ", data(1000)="+data(1000)+
            ",data(1000+s)={"+
            data(1000+Math.ceil(samplesPerPeriod).asInstanceOf[Int])+","+
            data(1000+Math.floor(samplesPerPeriod).asInstanceOf[Int])+
            "}]")
        ((data(1000)==data(1000+Math.ceil(samplesPerPeriod).asInstanceOf[Int]) ||
        data(1000)==data(1000+Math.floor(samplesPerPeriod).asInstanceOf[Int])) &&
        (data(2016)==data(2016+Math.ceil(samplesPerPeriod).asInstanceOf[Int])  ||
        data(2016)==data(2016+Math.floor(samplesPerPeriod).asInstanceOf[Int])))
      })
     Injectable.injector = Guice.createInjector(
    new AbstractModule{
    	def configure() = bind(classOf[AudioSystem]).toInstance(mockAudioSystem);
    });
    
    val midi = Midi()
    val audio = Audio()
    val basicOscillator = BasicOscillator("TestOscillator") 
    basicOscillator.setWave(basicOscillator.sine)
    
    //fineLogging();
	
    // act 
    midi >> basicOscillator
    (basicOscillator,1) >> audio;
    a4.on >> midi;
    
    Thread.sleep(3000);
  }
}