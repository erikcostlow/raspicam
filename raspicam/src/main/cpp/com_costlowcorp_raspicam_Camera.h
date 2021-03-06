/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_costlowcorp_raspicam_Camera */

#ifndef _Included_com_costlowcorp_raspicam_Camera
#define _Included_com_costlowcorp_raspicam_Camera
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    initializeNative
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_costlowcorp_raspicam_Camera_initializeNative
  (JNIEnv *, jclass);

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    openNative
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_com_costlowcorp_raspicam_Camera_openNative
  (JNIEnv *, jobject);

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    isOpenNative
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_com_costlowcorp_raspicam_Camera_isOpenNative
  (JNIEnv *, jobject);

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    closeNative
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_costlowcorp_raspicam_Camera_closeNative
  (JNIEnv *, jobject);

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    getHeightNative
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_costlowcorp_raspicam_Camera_getHeightNative
  (JNIEnv *, jobject);

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    setHeightNative
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_costlowcorp_raspicam_Camera_setHeightNative
  (JNIEnv *, jobject, jint);

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    getWidthNative
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_costlowcorp_raspicam_Camera_getWidthNative
  (JNIEnv *, jobject);

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    setWidthNative
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_costlowcorp_raspicam_Camera_setWidthNative
  (JNIEnv *, jobject, jint);

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    getBytesPerGrabNative
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_com_costlowcorp_raspicam_Camera_getBytesPerGrabNative
  (JNIEnv *, jobject);

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    grabNative
 * Signature: ()[B
 */
JNIEXPORT jbyteArray JNICALL Java_com_costlowcorp_raspicam_Camera_grabNative
  (JNIEnv *, jobject);

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    getISONative
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_costlowcorp_raspicam_Camera_getISONative
  (JNIEnv *, jobject);

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    setISONative
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_costlowcorp_raspicam_Camera_setISONative
  (JNIEnv *, jobject, jint);

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    getSharpnessNative
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_costlowcorp_raspicam_Camera_getSharpnessNative
  (JNIEnv *, jobject);

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    setSharpnessNative
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_costlowcorp_raspicam_Camera_setSharpnessNative
  (JNIEnv *, jobject, jint);

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    getBrightnessNative
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_costlowcorp_raspicam_Camera_getBrightnessNative
  (JNIEnv *, jobject);

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    setBrightnessNative
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_costlowcorp_raspicam_Camera_setBrightnessNative
  (JNIEnv *, jobject, jint);

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    setExposureCompensationNative
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_costlowcorp_raspicam_Camera_setExposureCompensationNative
  (JNIEnv *, jobject, jint);

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    getSaturationNative
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_costlowcorp_raspicam_Camera_getSaturationNative
  (JNIEnv *, jobject);

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    setSaturationNative
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_costlowcorp_raspicam_Camera_setSaturationNative
  (JNIEnv *, jobject, jint);

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    getRotationNative
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_costlowcorp_raspicam_Camera_getRotationNative
  (JNIEnv *, jobject);

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    setRotationNative
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_costlowcorp_raspicam_Camera_setRotationNative
  (JNIEnv *, jobject, jint);

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    getShutterSpeedNative
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_costlowcorp_raspicam_Camera_getShutterSpeedNative
  (JNIEnv *, jobject);

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    setShutterSpeedNative
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_costlowcorp_raspicam_Camera_setShutterSpeedNative
  (JNIEnv *, jobject, jint);

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    getHorizontalFlipNative
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_com_costlowcorp_raspicam_Camera_getHorizontalFlipNative
  (JNIEnv *, jobject);

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    setHorizontalFlipNative
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_com_costlowcorp_raspicam_Camera_setHorizontalFlipNative
  (JNIEnv *, jobject, jboolean);

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    isVerticalFlipNative
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_com_costlowcorp_raspicam_Camera_isVerticalFlipNative
  (JNIEnv *, jobject);

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    setVerticalFlipNative
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_com_costlowcorp_raspicam_Camera_setVerticalFlipNative
  (JNIEnv *, jobject, jboolean);

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    getIdNative
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_costlowcorp_raspicam_Camera_getIdNative
  (JNIEnv *, jobject);

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    setExposureNative
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_costlowcorp_raspicam_Camera_setExposureNative
  (JNIEnv *, jobject, jint);

#ifdef __cplusplus
}
#endif
#endif
