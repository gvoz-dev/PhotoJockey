package org.gvozdev.pj.processing.filters.standard;

import javax.swing.JComponent;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

public class PJSepia extends RGBImageFilter implements PJFilter {
  @Override
  public BufferedImage filter(JComponent component, BufferedImage image) {
    Image img = component.createImage(new FilteredImageSource(image.getSource(), this));
    BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
    bufferedImage.getGraphics().drawImage(img, 0, 0, null);
    return bufferedImage;
  }

  @Override
  public int filterRGB(int x, int y, int rgb) {
    int inR = (rgb >> 16) & 0xff;
    int inG = (rgb >> 8) & 0xff;
    int inB = rgb & 0xff;
    int outR = (int) (0.9 * (inR + inG + inB) / 3);
    int outG = (int) (0.75 * (inR + inG + inB) / 3);
    int outB = (int) (0.45 * (inR + inG + inB) / 3);
    return (0xff000000 | outR << 16 | outG << 8 | outB);
  }
}
