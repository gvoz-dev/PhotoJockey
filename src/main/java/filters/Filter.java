package filters;

import java.awt.*;

public interface Filter {
  Image filter(Component component, Image image);
}
