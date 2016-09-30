/*
 * Erik Costlow www.costlowcorp.com
*/
package com.costlowcorp.raspicam;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

/*
 * Copyright Erik Costlow.
 * Not authorized for use or view by others.
 */

/**
 *
 * @author Erik Costlow
 */
public class TryCamera {
    public static void main(String[] args) throws IOException, InterruptedException{
        System.out.println("Trying...");
        
        System.out.println("Size is " + Camera.INSTANCE.getWidth() + "x" + Camera.INSTANCE.getHeight());
        System.out.println("The bytes per grab is " + Camera.INSTANCE.getBytesPerGrab());
        System.out.println("Camera ID is " + Camera.INSTANCE.getId());
        Camera.INSTANCE.open();
        final byte[] bytes = Camera.INSTANCE.grabBytes();
        write(bytes, "check1.ppm");
        System.out.println("Sleeping for 2 seconds");
        Thread.sleep(2000);
        System.out.println("Another picture");
        final byte[] bytes2 = Camera.INSTANCE.grabBytes();
        write(bytes2, "check2.ppm");
        Camera.INSTANCE.close();
        System.out.println("Done, now converting to JPG");
        
        try (InputStream in = Files.newInputStream(Paths.get("check1.ppm"));
                OutputStream out = Files.newOutputStream(Paths.get("text.jpg"));
                ) {
            final BufferedImage img = ImageIO.read(in);
            System.out.println("Image is " + img);
            ImageIO.write(img, "JPG", out);
        }
        System.out.println("Finished");
    }
    
    private static void write(byte[] bytes, String filename) throws IOException{
        try(OutputStream out = Files.newOutputStream(Paths.get(filename))){
            out.write(bytes);
        }
    }
}
