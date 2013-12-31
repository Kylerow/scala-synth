package org.kylerow

import org.kylerow.scalasynth.module.Module
import org.kylerow.scalasynth.midi.Midi
import org.kylerow.scalasynth.module.BasicModule
import org.kylerow.scalasynth.audio.Audio._
import org.kylerow.scalasynth.audio.Audio
import org.kylerow.scalasynth.module.ModuleRegistry
import com.google.inject.Inject
import org.kylerow.scalasynth.Injectable
import org.kylerow.util.fineLogging

object synth {
	object primary {
	  def apply (module :BasicModule) = {
	    fineLogging();
	    Midi() >> module 
	    module >> Audio()
	    println("Basic audio and midi attached to ["+module+"].")
	  }
	}
	implicit class moduleString (moduleName :String) extends Injectable{
	  val moduleRegistry :ModuleRegistry  = injector.getInstance(classOf[ModuleRegistry])
	  def module = moduleRegistry.get(moduleName);
	}
}