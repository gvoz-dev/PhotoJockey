package org.gvozdev.pj.ui.menu;

import org.gvozdev.pj.PJApp;
import org.xml.sax.SAXException;

import javax.swing.JMenuBar;
import java.io.IOException;
import java.io.InputStream;

public class PJMainMenu {
  private final PJApp app;
  private final JMenuBar menuBar;

  private PJMainMenu(PJApp app) {
    this.app = app;
    menuBar = createMenuBar();
  }

  public static PJMainMenu create(PJApp app) {
    return new PJMainMenu(app);
  }

  public void show() {
    app.getFrame().setJMenuBar(menuBar);
  }

  private JMenuBar createMenuBar() {
    JMenuBar menuBar;
    try {
      InputStream stream = getClass().getClassLoader().getResourceAsStream("ui/MainMenu.xml");
      XMLMenuLoader loader = new XMLMenuLoader(app, stream);
      loader.parse();
      menuBar = loader.getMenuBar("MainMenu");
    } catch (IOException | SAXException e) {
      throw new RuntimeException(e);
    }
    return menuBar;
  }
}
