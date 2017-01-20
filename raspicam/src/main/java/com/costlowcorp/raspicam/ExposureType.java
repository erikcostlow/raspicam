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
    RASPICAM_EXPOSURE_OFF(0, "off"),
    RASPICAM_EXPOSURE_AUTO(1, "auto"),
    RASPICAM_EXPOSURE_NIGHT(2, "night"),
    RASPICAM_EXPOSURE_NIGHTPREVIEW(3, "nightpreview"),
    RASPICAM_EXPOSURE_BACKLIGHT(4, "backlight"),
    RASPICAM_EXPOSURE_SPOTLIGHT(5, "spotlight"),
    RASPICAM_EXPOSURE_SPORTS(6, "sports"),
    RASPICAM_EXPOSURE_SNOW(7, "snow"),
    RASPICAM_EXPOSURE_BEACH(8, "beach"),
    RASPICAM_EXPOSURE_VERYLONG(9, "verylong"),
    RASPICAM_EXPOSURE_FIXEDFPS(10, "fixedfps"),
    RASPICAM_EXPOSURE_ANTISHAKE(11, "antishake"),
    RASPICAM_EXPOSURE_FIREWORKS(12, "fireworks");
    
    private final int cppValue;
    private final String raspiStillTerm;
    
    private ExposureType(int cppValue, String raspiStillTerm){
        this.cppValue=cppValue;
        this.raspiStillTerm=raspiStillTerm;
    }
    
    int getCppValue(){
        return cppValue;
    }
    
    String getRaspiStillTerm(){
        return raspiStillTerm;
    }
}
