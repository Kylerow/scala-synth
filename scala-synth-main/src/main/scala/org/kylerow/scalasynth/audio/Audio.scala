package org.kylerow.scalasynth.audio

import org.kylerow.scalasynth.Injectable
import org.kylerow.scalasynth.module.Module
import javax.sound.sampled.AudioFormat
import com.google.inject.Inject
import org.kylerow.scalasynth.Word

/**
 * The primary audio control - provides input and output
 * 
 * @Author Kyle
 */
trait Audio{
  def attachSender(sender :Module);
}

object Audio {
  
  def apply() :Audio = 
    Injectable.injector.getInstance(classOf[AudioImplementation])  
    
  implicit class audioOutputCapture (param :Module)
  {	
    def >> (audio :Audio) = {
      audio attachSender param 
    }
  }
}

class AudioImplementation extends Audio {
	@Inject var audioSystem :AudioSystem = _
  	
	override def attachSender(sender :Module ) = {
  	  import scala.concurrent._
	  import ExecutionContext.Implicits.global
	  
  	  this.senderOfRecord = sender;
	  future ( runAudio(sender) )
  	}
  	
	var senderOfRecord :Module = _;
	
  	def runAudio(audioSender :Module){
  	  val audioPort = audioSystem.getPort();
  	  val dataSource =
  	    audioSender.nextAudioBuffer(1) _;
  	  while(audioSender == this.senderOfRecord) {
  	    audioPort.sendData(dataSource());
  	  }
  	}
}

