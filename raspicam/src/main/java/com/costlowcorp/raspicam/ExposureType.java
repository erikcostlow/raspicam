/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.costlowcorp.raspicam;

/**
 *
 * @author Erik Costlow
 */
public enum ExposureType {
    RASPICAM_EXPOSURE_OFF(0),
    RASPICAM_EXPOSURE_AUTO(1),
    RASPICAM_EXPOSURE_NIGHT(2),
    RASPICAM_EXPOSURE_NIGHTPREVIEW(3),
    RASPICAM_EXPOSURE_BACKLIGHT(4),
    RASPICAM_EXPOSURE_SPOTLIGHT(5),
    RASPICAM_EXPOSURE_SPORTS(6),
    RASPICAM_EXPOSURE_SNOW(7),
    RASPICAM_EXPOSURE_BEACH(8),
    RASPICAM_EXPOSURE_VERYLONG(9),
    RASPICAM_EXPOSURE_FIXEDFPS(10),
    RASPICAM_EXPOSURE_ANTISHAKE(11),
    RASPICAM_EXPOSURE_FIREWORKS(12);
    
    private final int cppValue;
    
    private ExposureType(int cppValue){
        this.cppValue=cppValue;
    }
    
    int getCppValue(){
        return cppValue;
    }
}
