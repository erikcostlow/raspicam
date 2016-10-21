#include <iostream>
#include <string>
#include <sstream>
#include <unistd.h>
#include <jni.h>
#include <stdio.h>
#include "com_costlowcorp_raspicam_Camera.h"
#include "raspicam-0.1.3/raspicam.h"
#include "raspicam-0.1.3/raspicamtypes.h"

/**
 * Erik Costlow www.costlowcorp.com
 * Written 2016.
 * JNI Wrapper for accessing a Raspberry Pi's ob-board camera.
 */

using namespace std;
raspicam::RaspiCam Camera;

int open() {
    if (!Camera.open()) {
        cerr << "Error opening camera" << endl;
        return -1;
    }
    //Let the camera stabilize
    sleep(3);
    return 0;
}

jint Java_com_costlowcorp_raspicam_Camera_initializeNative(JNIEnv *, jclass) {
    raspicam::RASPICAM_FORMAT format = raspicam::RASPICAM_FORMAT_RGB;
    Camera.setFormat(format);
}

jboolean Java_com_costlowcorp_raspicam_Camera_openNative(JNIEnv *, jobject) {
    return open();
}

jboolean Java_com_costlowcorp_raspicam_Camera_isOpenNative(JNIEnv *, jobject) {
    jboolean b = Camera.isOpened();
    return b;
}

jint Java_com_costlowcorp_raspicam_Camera_getHeightNative(JNIEnv *, jobject obj) {
    //cout<<"checking if opened "<<Java_com_costlowcorp_raspicam_Camera_isOpenNative(env, obj)<<endl;
    return Camera.getHeight();
}

jint Java_com_costlowcorp_raspicam_Camera_getWidthNative(JNIEnv *, jobject) {
    return Camera.getWidth();
}

void Java_com_costlowcorp_raspicam_Camera_setHeightNative(JNIEnv *, jobject, jint height) {
    Camera.setHeight(height);
}

void Java_com_costlowcorp_raspicam_Camera_setWidthNative(JNIEnv *, jobject, jint width) {
    Camera.setWidth(width);
}

jlong Java_com_costlowcorp_raspicam_Camera_getBytesPerGrabNative(JNIEnv *, jobject) {
    return Camera.getImageTypeSize(Camera.getFormat());
}

jbyteArray Java_com_costlowcorp_raspicam_Camera_grabNative(JNIEnv *env, jobject) {
    Camera.grab();
    unsigned int length = Camera.getImageTypeSize(Camera.getFormat());
    unsigned char *data = new unsigned char[ length];

    Camera.retrieve(data, raspicam::RASPICAM_FORMAT_IGNORE);

    std::string prefix = "P6\n";
    unsigned int width = Camera.getWidth();
    unsigned int height = Camera.getHeight();
    std::string space = " ";
    std::string suffix = " 255\n";

    std::ostringstream ss;
    ss << prefix << width << space << height << suffix;
    ss.write((char*) data, length);
    std::string full = ss.str();

    unsigned int total = full.length();

    jbyteArray j_value = env->NewByteArray(total);
    env->SetByteArrayRegion(j_value, 0, length, (const jbyte*) ((char*) full.c_str()));
    delete[] data;
    return j_value;
}

void Java_com_costlowcorp_raspicam_Camera_closeNative(JNIEnv *, jobject) {
    Camera.release();
}

jint Java_com_costlowcorp_raspicam_Camera_getISONative(JNIEnv *, jobject) {
    return Camera.getISO();
}

void Java_com_costlowcorp_raspicam_Camera_setISONative(JNIEnv *, jobject, jint toMe) {
    int newISO = toMe;
    Camera.setISO(newISO);
}

jint Java_com_costlowcorp_raspicam_Camera_getSharpnessNative(JNIEnv *, jobject) {
    return Camera.getSharpness();
}

void Java_com_costlowcorp_raspicam_Camera_setSharpnessNative(JNIEnv *, jobject, jint sharpness) {
    Camera.setSharpness(sharpness);
}

jint Java_com_costlowcorp_raspicam_Camera_getBrightnessNative(JNIEnv *, jobject) {
    return Camera.getBrightness();
}

void Java_com_costlowcorp_raspicam_Camera_setBrightnessNative(JNIEnv *, jobject, jint brightness) {
    Camera.setBrightness(brightness);
}

void Java_com_costlowcorp_raspicam_Camera_setExposureCompensationNative(JNIEnv *, jobject, jint exposure) {
    Camera.setExposureCompensation(exposure);
}

void Java_com_costlowcorp_raspicam_Camera_setExposureNative(JNIEnv *, jobject, jint exposureCppValue) {
    switch (exposureCppValue) {
        case 0:
            Camera.setExposure(raspicam::RASPICAM_EXPOSURE_OFF);
            break;
        case 1:
            Camera.setExposure(raspicam::RASPICAM_EXPOSURE_AUTO);
            break;
        case 2:
            Camera.setExposure(raspicam::RASPICAM_EXPOSURE_NIGHT);
            break;
        case 3:
            Camera.setExposure(raspicam::RASPICAM_EXPOSURE_NIGHTPREVIEW);
            break;
        case 4:
            Camera.setExposure(raspicam::RASPICAM_EXPOSURE_BACKLIGHT);
            break;
        case 5:
            Camera.setExposure(raspicam::RASPICAM_EXPOSURE_SPOTLIGHT);
            break;
        case 6:
            Camera.setExposure(raspicam::RASPICAM_EXPOSURE_SPORTS);
            break;
        case 7:
            Camera.setExposure(raspicam::RASPICAM_EXPOSURE_SNOW);
            break;
        case 8:
            Camera.setExposure(raspicam::RASPICAM_EXPOSURE_BEACH);
            break;
        case 9:
            Camera.setExposure(raspicam::RASPICAM_EXPOSURE_VERYLONG);
            break;
        case 10:
            Camera.setExposure(raspicam::RASPICAM_EXPOSURE_FIXEDFPS);
            break;
        case 11:
            Camera.setExposure(raspicam::RASPICAM_EXPOSURE_ANTISHAKE);
            break;
        case 12:
            Camera.setExposure(raspicam::RASPICAM_EXPOSURE_FIREWORKS);
            break;
    }
}

jint Java_com_costlowcorp_raspicam_Camera_getSaturationNative(JNIEnv *, jobject) {
    return Camera.getSaturation();
}

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    setSaturationNative
 * Signature: (I)V
 */
void Java_com_costlowcorp_raspicam_Camera_setSaturationNative
(JNIEnv *, jobject, jint saturation) {
    Camera.setSaturation(saturation);
}

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    getRotationNative
 * Signature: ()I
 */
jint Java_com_costlowcorp_raspicam_Camera_getRotationNative(JNIEnv *, jobject) {
    return Camera.getRotation();
}

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    setRotationNative
 * Signature: (I)V
 */
void Java_com_costlowcorp_raspicam_Camera_setRotationNative(JNIEnv *, jobject, jint rotation) {
    Camera.setRotation(rotation);
}

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    getShutterSpeedNative
 * Signature: ()I
 */
jint Java_com_costlowcorp_raspicam_Camera_getShutterSpeedNative(JNIEnv *, jobject) {
    return Camera.getShutterSpeed();
}

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    setShutterSpeedNative
 * Signature: (I)V
 */
void Java_com_costlowcorp_raspicam_Camera_setShutterSpeedNative(JNIEnv *, jobject, jint speed) {
    Camera.setShutterSpeed(speed);
}

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    getHorizontalFlipNative
 * Signature: ()Z
 */
jboolean Java_com_costlowcorp_raspicam_Camera_getHorizontalFlipNative(JNIEnv *, jobject) {
    return Camera.isHorizontallyFlipped();
}

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    setHorizontalFlipNative
 * Signature: (Z)V
 */
void Java_com_costlowcorp_raspicam_Camera_setHorizontalFlipNative(JNIEnv *, jobject, jboolean flip) {
    Camera.setHorizontalFlip(flip);
}

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    isVerticalFlipNative
 * Signature: ()Z
 */
jboolean Java_com_costlowcorp_raspicam_Camera_isVerticalFlipNative(JNIEnv *, jobject) {
    return Camera.isVerticallyFlipped();
}

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    setVerticalFlipNative
 * Signature: (Z)V
 */
void Java_com_costlowcorp_raspicam_Camera_setVerticalFlipNative(JNIEnv *, jobject, jboolean flip) {
    Camera.setVerticalFlip(flip);
}

/*
 * Class:     com_costlowcorp_raspicam_Camera
 * Method:    getIdNative
 * Signature: ()Ljava/lang/String;
 */
jstring Java_com_costlowcorp_raspicam_Camera_getIdNative(JNIEnv *env, jobject) {
    return env->NewStringUTF(Camera.getId().c_str());
}