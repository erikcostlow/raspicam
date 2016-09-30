/*
 * Erik Costlow www.costlowcorp.com
*/
package com.costlowcorp.raspicam.sarxos;

import com.costlowcorp.raspicam.Camera;
import com.github.sarxos.webcam.WebcamDevice;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

/**
 *
 * @author Erik Costlow
 */
public class RaspiCamDevice implements WebcamDevice {

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

    private Dimension size = DIMENSIONS[0];

    @Override
    public String getName() {
        return "RaspiCam";
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
        final BufferedImage img = Camera.INSTANCE.grabImage();
        return img;
    }

    @Override
    public void open() {
        Camera.INSTANCE.open();
    }

    @Override
    public void close() {
        Camera.INSTANCE.close();
    }

    @Override
    public void dispose() {
        close();
    }

    @Override
    public boolean isOpen() {
        return Camera.INSTANCE.isOpen();
    }

}
