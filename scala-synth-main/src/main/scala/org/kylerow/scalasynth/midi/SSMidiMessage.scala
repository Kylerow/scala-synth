package org.kylerow.scalasynth.midi

import org.kylerow.scalasynth.note.Note

class SSMidiMessage;
case class SSNoteOnMidiMessage(note :Note) extends SSMidiMessage;