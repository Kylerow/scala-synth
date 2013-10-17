package org.kylerow.util


object avoidException {
 def apply[T <: Throwable,R](clz :Class[T])(func :()=>R) :R = {
	  try{
	    func()
	  } catch {
          case un :R  => null.asInstanceOf[R];
          case ex :Throwable => println("Exception: "+ ex); throw ex;
      }
  }
}

