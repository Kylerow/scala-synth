package org.kylerow.scalasynth

class  Word(val clz :Class[_], val value :Any);
object Word{
  def apply(value :Byte) = {new Word(classOf[Byte],value);}
  implicit class arrayWithBytes (array :Array[Word])
  {	
    def getBytes() :Array[Byte]= {
    	null
    } 
  }
}