package org.kylerow

import org.kylerow.scalasynth.module.Module
import org.kylerow.scalasynth.midi.Midi
import org.kylerow.scalasynth.module.BasicModule
import org.kylerow.scalasynth.audio.Audio._
import org.kylerow.scalasynth.audio.Audio
import org.kylerow.scalasynth.module.ModuleRegistry
import com.google.inject.Inject
import org.kylerow.scalasynth.Injectable

object synth {
	object primary {
	  def apply (module :BasicModule) = {
	    Midi() >> module 
	    module >> Audio()
	  }
	}
	implicit class moduleString (moduleName :String) extends Injectable{
	  val moduleRegistry :ModuleRegistry  = injector.getInstance(classOf[ModuleRegistry])
	  def module = moduleRegistry.get(moduleName);
	}
}