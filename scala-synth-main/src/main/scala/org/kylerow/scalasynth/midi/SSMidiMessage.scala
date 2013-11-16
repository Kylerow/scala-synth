package org.kylerow.scalasynth.midi

import org.kylerow.scalasynth.util.Note
 
class SSMidiMessage;
case class SSNoteOnMidiMessage(note :Note) extends SSMidiMessage;