/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.costlowcorp.raspicam;

import java.awt.Dimension;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author erik
 */


public class TryWrapped {
    public static void main(String[] args) throws IOException{
        System.out.println("Grabbing frame");
        final byte[] bytes = WrappedProcessCamera.INSTANCE.grabFrame(ExposureType.RASPICAM_EXPOSURE_NIGHT, new Dimension(1280, 920), false, false);
        final Path p = Paths.get("wrapped.jpg");
        try(OutputStream out = Files.newOutputStream(p)){
            out.write(bytes);
        }
    }
}
