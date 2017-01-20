/*
 * Erik Costlow www.costlowcorp.com
*/
package com.costlowcorp.raspicam.sarxos;

import com.costlowcorp.raspicam.ExposureType;
import com.costlowcorp.raspicam.WrappedProcessCamera;
import com.github.sarxos.webcam.WebcamDevice;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Erik Costlow
 */
public class WrappedCameraDevice implements WebcamDevice {

    private final static Dimension[] DIMENSIONS = new Dimension[]{
        //new Dimension(176, 144),
        //new Dimension(320, 240),
        //new Dimension(352, 288),
        //new Dimension(640, 400),
        new Dimension(640, 480),
        new Dimension(1024, 768),
        new Dimension(1280, 960),//1MP
        new Dimension(1600, 1200),//2MP
        new Dimension(2048, 1536),//3MP
        new Dimension(2240, 1680),//4MP
        new Dimension(2560, 1920),//5MP
        new Dimension(3032, 2008),//6MP
        new Dimension(3072, 2304),//7MP
        new Dimension(3264, 2448)//8MP
    };

    private Dimension size = DIMENSIONS[2];
    
    private boolean open;
    
    private boolean vFlip;
    
    private boolean hFlip;

    @Override
    public String getName() {
        return "RaspiStill-executing wrapper";
    }

    @Override
    public Dimension[] getResolutions() {
        return DIMENSIONS;
    }

    @Override
    public Dimension getResolution() {
        return size;
    }

    @Override
    public void setResolution(Dimension dmnsn) {
        size = dmnsn;
    }

    @Override
    public BufferedImage getImage() {
        try {
            final byte[] bytes = WrappedProcessCamera.INSTANCE.grabFrame(ExposureType.RASPICAM_EXPOSURE_OFF, size, vFlip, hFlip);
            return ImageIO.read(new ByteArrayInputStream(bytes));
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public void open() {
        open=true;
    }

    @Override
    public void close() {
        open=false;
    }

    @Override
    public void dispose() {
        close();
    }

    @Override
    public boolean isOpen() {
        return open;
    }
    
    public void setVFlip(boolean vFlip){
        this.vFlip=vFlip;
    }

    public void sethFlip(boolean hFlip) {
        this.hFlip = hFlip;
    }

}
