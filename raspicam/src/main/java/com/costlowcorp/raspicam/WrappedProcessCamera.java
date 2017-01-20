/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.costlowcorp.raspicam;

import java.awt.Dimension;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author erik
 */
public enum WrappedProcessCamera {
    INSTANCE;

    public synchronized byte[] grabFrame(ExposureType exposure, Dimension dimension, boolean vFlip, boolean hFlip) throws IOException {
        final ByteArrayOutputStream bout = new ByteArrayOutputStream();
        final List<String> commands = new ArrayList<>();
        commands.add("raspistill");
        commands.add("--nopreview");
        commands.add("-w");
        commands.add(String.valueOf(dimension.getWidth()));
        commands.add("-h");
        commands.add(String.valueOf(dimension.getHeight()));
        commands.add("-ex");
        commands.add(exposure.getRaspiStillTerm());
        if (vFlip) {
            commands.add("-vf");
        }
        if (hFlip) {
            commands.add("-hf");
        }
        commands.add("-o");
        commands.add("-");
        final ProcessBuilder pb = new ProcessBuilder(commands);
        pb.redirectErrorStream(true);
        final byte[] bytes = new byte[2048];
        final Process p = pb.start();
        for (int length = p.getInputStream().read(bytes); length > 0; length = p.getInputStream().read(bytes)) {
            bout.write(bytes, 0, length);
        }
        return bout.toByteArray();
    }
}
