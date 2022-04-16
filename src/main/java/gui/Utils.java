package gui;

import javax.swing.*;
import java.awt.*;

public class Utils {
  public static void setMenuFont(MenuElement element, Font font) {
    element.getComponent().setFont(font);
    MenuElement[] menuElements = element.getSubElements();
    if (menuElements.length == 0) return;
    for (MenuElement e : menuElements) {
      setMenuFont(e, font);
    }
  }
}
