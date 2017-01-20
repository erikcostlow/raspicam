/*
 * Erik Costlow www.costlowcorp.com
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
public class WrappedRaspiStillDriver implements WebcamDriver{

    public WrappedRaspiStillDriver() {
        
    }

    @Override
    public List<WebcamDevice> getDevices() {
        return Arrays.asList(new WrappedCameraDevice());
    }

    @Override
    public boolean isThreadSafe() {
        return false;
    }
    
}
