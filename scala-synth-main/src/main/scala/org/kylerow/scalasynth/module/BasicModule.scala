package org.kylerow.scalasynth.module

import org.kylerow.scalasynth.audio.AudioOutputs
import org.kylerow.scalasynth.midi.EventReceiver

abstract class BasicModule extends Module 
		with EventReceiver 
		with AudioOutputs;