/*
 * Erik Costlow www.costlowcorp.com
*/
package com.costlowcorp.raspicam;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Access to the Pi's camera for grabbing images and frames.
 *
 * @author Erik Costlow
 */
public enum Camera implements AutoCloseable {
    /**
     * The Raspberry Pi has only one camera slot.
     */
    INSTANCE;

    private static final Logger LOG = Logger.getLogger(Camera.class.getSimpleName());

    /**
     * Load the libraries.
     */
    static {
        String libPath = System.getProperty("raspicamlibs");
        if (libPath == null) {
            LOG.info("Extracting libraries... Consider using -Draspicamlibs=DIR for faster startup.");
            extractAndLoad("META-INF/libraspicam.so", "libraspicam", "so");
            extractAndLoad("META-INF/costlow_raspicam.so", "costlow_raspicam", "so");
        } else {
            if (!libPath.endsWith("/")) {
                libPath += "/";
            }
            Runtime.getRuntime().load(libPath + "libraspicam.so");
            Runtime.getRuntime().load(libPath + "costlow_raspicam.so");
        }

        final int initialized = initializeNative();
        if (initialized != 0) {
            throw new RuntimeException("Unable to initialize camera");
        }
        
    }

    private static void extractAndLoad(String item, String prefix, String suffix) {
        try {
            final Path path = Files.createTempFile(prefix, suffix);
            try (OutputStream out = Files.newOutputStream(path);
                    InputStream in = Camera.class.getClassLoader().getResourceAsStream(item)) {
                final byte[] bytes = new byte[2048];
                for (int length = in.read(bytes); length > 0; length = in.read(bytes)) {
                    out.write(bytes, 0, length);
                }
                final String file = String.valueOf(path.toAbsolutePath());
                Runtime.getRuntime().load(file);
                Files.deleteIfExists(path);
            }
        } catch (IOException ex) {
            throw new RuntimeException("Unable to extract and load libraries", ex);
        }
    }

    /**
     *
     * @return an id of the camera. We assume the camera id is the one of the
     * raspberry the id is obtained using raspberry serial number obtained in
     * /proc/cpuinfo
     */
    public String getId() {
        return getIdNative();
    }
    
    public void setExposure(ExposureType type){
        setExposureNative(type.getCppValue());
    }

    /**
     *
     * @return Height in pixels
     */
    public int getHeight() {
        return getHeightNative();
    }

    /**
     *
     * @return Width in pixels
     */
    public int getWidth() {
        return getWidthNative();
    }

    /**
     *
     * @param height in pixels
     */
    public void setHeight(int height) {
        if (isOpen()) {
            LOG.warning("Cannot change size of an open camera. Please: close, set(Width|Height), open.");
        } else if (height % 240 != 0) {
            LOG.warning("Height must be multiple of 240");
        } else {
            setHeightNative(height);
        }
    }

    /**
     *
     * @param width in pixels
     */
    public void setWidth(int width) {
        if (isOpen()) {
            LOG.warning("Cannot change size of an open camera. Please: close, set(Width|Height), open.");
        } else if (width % 320 != 0) {
            LOG.warning("Width must be multiple of 320");
        } else {
            setWidthNative(width);
        }
    }

    /**
     * Blocks until the next frame is received.
     *
     * @return The current frame grab if available, or null.
     */
    public BufferedImage grabImage() {
        final byte[] bytes = grabBytes();
        if(bytes==null){
            return null;
        }
        try (final InputStream in = new ByteArrayInputStream(bytes)) {
            final BufferedImage img = ImageIO.read(in);
            return img;
        } catch (IOException ex) {
            LOG.log(Level.WARNING, "Unable to process image", ex);
        }
        return null;
    }

    /**
     * Blocks until the next frame is received.
     *
     * @return bytes of the current frame, or null.
     */
    public byte[] grabBytes() {
        if (!isOpen()) {
            LOG.warning("Camera is closed, cannot grab frames.");
            return null;
        }
        final byte[] retval = grabNative();
        return retval;
    }

    /**
     * Opens the camera to monitor.
     * @return true if the camera was opened, false if it was already open or
     * could not be opened.
     */
    public boolean open() {
        if(isOpen()){
            LOG.warning("Camera is already open");
            return false;
        }else{
            return openNative();
        }
    }

    /**
     *
     * @return if the Camera is open
     */
    public boolean isOpen() {
        boolean i=isOpenNative();
        return i;
    }

    /**
     * Close and release resources of camera access.
     */
    @Override
    public void close() {
        closeNative();
    }

    private void doIfNotOpen(Runnable r){
        if(isOpen()){
            LOG.warning("Cannot change an open camera");
        }else{
            r.run();
        }
    }
    
    /**
     *
     * @return the current ISO
     */
    public int getISO() {
        return getISONative();
    }

    /**
     *
     * @param iso desired ISO
     */
    public void setISO(int iso) {
        doIfNotOpen(() -> setISONative(iso));
    }

    /**
     * Number of bytes per frame-grab from the camera.
     * Straight grab, does not include any Java padding.
     * @return number of bytes.
     */
    public long getBytesPerGrab() {
        return getBytesPerGrabNative();
    }

    public int getSharpness() {
        return getSharpnessNative();
    }
    
    public void setSharpness(int sharpness) {
        doIfNotOpen(() -> setSharpnessNative(sharpness));
    }

    public int getBrightness() {
        return getBrightnessNative();
    }

    public void setBrightness(int brightness) {
        doIfNotOpen(() -> setBrightnessNative(brightness));
    }

    public void setExposureCompensation(int exposureCompensation) {
        doIfNotOpen(() -> {
            if (exposureCompensation >= -10 && exposureCompensation <= 10) {
                setExposureCompensationNative(exposureCompensation);
            } else {
                LOG.warning("Exposure composition must be between -10 and 10");
            }
        });
    }

    public int getSaturation() {
        return getSaturationNative();
    }

    public void setSaturation(int saturation) {
        doIfNotOpen(() -> {
            if (saturation < -100 || saturation > 100) {
                LOG.info("Saturation must be between -100 and 100");
            } else {
                setSaturationNative(saturation);
            }
        });
    }

    /**
     *
     * @return The rotation
     */
    public int getRotation() {
        return getRotationNative();
    }

    /**
     *
     * @param rotation
     */
    public void setRotation(int rotation) {
        //Is this degrees?
        doIfNotOpen(() -> setRotationNative(rotation));
    }

    public int getShutterSpeed() {
        return getShutterSpeedNative();
    }

    public void setShutterSpeed(int ss) {
        setShutterSpeedNative(ss);
    }

    public boolean isHorizontalFlip() {
        return getHorizontalFlipNative();
    }

    public void setHorizontalFlip(boolean hFlip) {
        doIfNotOpen(() -> setHorizontalFlipNative(hFlip));
    }

    public boolean isVerticalFlip() {
        return isVerticalFlipNative();
    }

    public void setVerticalFlip(boolean vFlip) {
        doIfNotOpen(() -> setVerticalFlipNative(vFlip));
    }

    private static native int initializeNative();

    private native boolean openNative();

    private native boolean isOpenNative();

    private native void closeNative();

    private native int getHeightNative();

    private native void setHeightNative(int height);

    private native int getWidthNative();

    private native void setWidthNative(int width);

    private native long getBytesPerGrabNative();

    private native byte[] grabNative();

    private native int getISONative();

    private native void setISONative(int iso);

    private native int getSharpnessNative();

    private native void setSharpnessNative(int sharpness);

    private native int getBrightnessNative();

    private native void setBrightnessNative(int brightness);

    private native void setExposureCompensationNative(int exposureCompensation);

    private native int getSaturationNative();

    private native void setSaturationNative(int saturation);

    private native int getRotationNative();

    private native void setRotationNative(int rotation);

    private native int getShutterSpeedNative();

    private native void setShutterSpeedNative(int ss);

    private native boolean getHorizontalFlipNative();

    private native void setHorizontalFlipNative(boolean hFlip);

    private native boolean isVerticalFlipNative();

    private native void setVerticalFlipNative(boolean vFlip);

    private native String getIdNative();

    private native void setExposureNative(int cppValue);
}
