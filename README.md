# raspicam
Java library for accessing a Raspberry Pi onboard camera.
Tested on a Pi 2 model B+ using a [Pi Noir 2](https://www.raspberrypi.org/products/pi-noir-camera-v2/).

##Usage:
Basic
```java
Camera.INSTANCE.open();
BufferedImage image = Camera.INSTANCE.grabImage();
Path jpeg = "file.jpg";
try(OutputStream out = Files.newOutputStream(jpeg)){
  ImageIO.write(image, "JPG", out);
}
```
##Usage with [Sarxos Webcam-Capture](https://github.com/sarxos/webcam-capture)
This code works with the native library, so the sarxos-raspicam driver includes
a test program that is not a unit test.
```java
Webcam.setDriver(new RaspiCamDriver());
webcam.open();
BufferedImage image = webcam.getImage();
//And so on like the above example
```
##Compiling
The project only compiles Java, you need to compile the C++ separately, on a Pi.
There is a file called go.sh which does this, then you need to copy the .so
file into the JAR's META-INF folder.
The native code links into (and includes) [RaspiCam C++](http://www.uco.es/investiga/grupos/ava/node/40)
