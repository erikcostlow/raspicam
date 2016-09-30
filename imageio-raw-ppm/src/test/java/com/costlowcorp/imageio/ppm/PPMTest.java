/*
 * Copyright Erik Costlow.
 * Not authorized for use or view by others.
 */
package com.costlowcorp.imageio.ppm;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;
import org.testng.annotations.Test;

/**
 *
 * @author Erik Costlow
 */
public class PPMTest {
 
    @Test
    public void testRead(){
        try(InputStream in = PPMTest.class.getClassLoader().getResourceAsStream("raspicam_image.ppm")){
            final BufferedImage img = ImageIO.read(in);
            assertNotNull(img, "Image was null.");
            assertEquals(1280, img.getWidth(), "Wrong width");
            assertEquals(960, img.getHeight(), "Wrong height");
        } catch (IOException ex) {
            fail("Unable to read image", ex);
        }
    }
}
