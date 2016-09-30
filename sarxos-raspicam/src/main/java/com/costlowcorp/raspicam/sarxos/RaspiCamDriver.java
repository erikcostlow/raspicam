/*
 * Copyright Erik Costlow.
 * Not authorized for use or view by others.
 */
package com.costlowcorp.raspicam.sarxos;

import com.github.sarxos.webcam.WebcamDevice;
import com.github.sarxos.webcam.WebcamDriver;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Erik Costlow
 */
public class RaspiCamDriver implements WebcamDriver{

    public RaspiCamDriver() {
        
    }

    @Override
    public List<WebcamDevice> getDevices() {
        return Arrays.asList(new RaspiCamDevice());
    }

    @Override
    public boolean isThreadSafe() {
        return false;
    }
    
}
