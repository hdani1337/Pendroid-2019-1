/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package hu.csanyzeg.master.ParentClasses.LiquidFun;

public class Joint {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected Joint(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(Joint obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        throw new UnsupportedOperationException("C++ destructor does not have public access");
      }
      swigCPtr = 0;
    }
  }

  public JointType getType() {
    return JointType.swigToEnum(liquidfunJNI.Joint_getType(swigCPtr, this));
  }

  public Body getBodyA() {
    long cPtr = liquidfunJNI.Joint_getBodyA(swigCPtr, this);
    return (cPtr == 0) ? null : new Body(cPtr, false);
  }

  public Body getBodyB() {
    long cPtr = liquidfunJNI.Joint_getBodyB(swigCPtr, this);
    return (cPtr == 0) ? null : new Body(cPtr, false);
  }

}
