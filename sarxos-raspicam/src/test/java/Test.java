
import com.costlowcorp.raspicam.sarxos.RaspiCamDriver;
import com.github.sarxos.webcam.Webcam;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;

/*
 * Copyright Erik Costlow.
 * Not authorized for use or view by others.
 */

/**
 *
 * @author Erik Costlow
 */
public class Test {
    public static void main(String[] args) throws IOException{
        Webcam.setDriver(new RaspiCamDriver());
        Webcam webcam = Webcam.getDefault();
        System.out.println("Webcam is " + webcam);
        
        System.out.println("View sizes");
        Arrays.stream(webcam.getViewSizes()).forEach(System.out::println);
        webcam.setViewSize(webcam.getViewSizes()[webcam.getViewSizes().length-1]);
        webcam.open();
        
        BufferedImage image = webcam.getImage();

        ImageIO.write(image, "JPG", new File("test.jpg"));
    }
}
