# RaspiCam
A native library to access the raspberry pi onboard camera.

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
