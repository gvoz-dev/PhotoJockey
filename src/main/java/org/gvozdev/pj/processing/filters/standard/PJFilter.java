package org.gvozdev.pj.processing.filters.standard;

import javax.swing.JComponent;
import java.awt.image.BufferedImage;

public interface PJFilter {
  BufferedImage filter(JComponent component, BufferedImage image);
}
