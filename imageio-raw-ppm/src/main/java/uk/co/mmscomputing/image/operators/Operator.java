package uk.co.mmscomputing.image.operators;

import java.awt.image.BufferedImage;

abstract public class Operator{

  static final int WHITE=0x00FFFFFF;
  static final int BLACK=0x00000000;

  static final int BWHITE=0xFF;
  static final int BBLACK=0x00;

  public BufferedImage filter(BufferedImage src){return null;};

}

