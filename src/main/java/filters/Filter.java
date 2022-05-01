package filters;

import javax.swing.JComponent;
import java.awt.image.BufferedImage;

public interface Filter {
  BufferedImage filter(JComponent component, BufferedImage image);
}
