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
  def attachSender(sender :(Module,Int) );
}

object Audio extends Injectable{
  
  def apply() :Audio = injector.getInstance(classOf[AudioImplementation])  
  
  implicit class audioOutputCapture (param :(Module,Int))
  {	def >> (audio :Audio) = audio attachSender param }
}

class AudioImplementation extends Audio {
	@Inject var audioSystem :AudioSystem = _
  	
	override def attachSender(sender :(Module,Int) ) = {
  	  import scala.concurrent._
	  import ExecutionContext.Implicits.global
	  
  	  this.senderOfRecord = sender;
	  future ( runAudio(sender) )
  	}
  	
	var senderOfRecord :(Module,Int) = _;
	
  	def runAudio(audioSender :(Module,Int)){
  	  val audioPort = audioSystem.getPort();
  	  val dataSource =
  	    audioSender._1.nextAudioBuffer(audioSender._2) _;
  	  while(audioSender == this.senderOfRecord) 
  	    audioPort.sendData(dataSource());
  	}
}

