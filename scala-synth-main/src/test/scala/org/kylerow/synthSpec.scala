package org.kylerow

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalamock.scalatest.MockFactory
import org.kylerow.scalasynth.Injectable
import com.google.inject.Guice
import com.google.inject.AbstractModule
import org.kylerow.scalasynth.midi.Midi
import org.kylerow.scalasynth.audio.Audio
import org.kylerow.scalasynth.module.Module
import org.kylerow.scalasynth.module.BasicOscillator
import org.kylerow.scalasynth.midi.EventReceiver
import org.kylerow.scalasynth.module.BasicModule
import org.kylerow.scalasynth.audio.AudioImplementation
import org.kylerow.scalasynth.module.ModuleRegistry
import org.kylerow.util.fakeInjector


@RunWith(classOf[JUnitRunner])
class synthSpec 
	extends FlatSpec 
	with ShouldMatchers
	with MockFactory{

	"primary" should "connect audio and midi and return a configuration function" in {
		// arrange
		val mockMidi = mock[Midi]
		val mockAudio = mock[AudioImplementation]
		val mockModule = mock[BasicModule]
		
		fakeInjector useMapping 
			(classOf[Midi] -> mockMidi) and 
		    (classOf[AudioImplementation] -> mockAudio)
		
		(mockMidi.>> _) expects (mockModule)
		(mockAudio.attachSender _) expects (mockModule)
		
		// act
		import synth._;
		
		primary {
		  mockModule
		}
	}
	"fancy module string" should "hit module registry to get the module back" in {
		// arrange
		val mockModule = mock[Module]
		val mockModuleRegistry = mock[ModuleRegistry]
		fakeInjector useMapping
		  (classOf[ModuleRegistry] -> mockModuleRegistry)
		
		(mockModuleRegistry get _) expects("Hello World") returns (mockModule)
		
		// act
		import synth._
		val testString = "Hello World";
		val result = testString.module
		
		// assert
		assert(result == mockModule)
	}
}