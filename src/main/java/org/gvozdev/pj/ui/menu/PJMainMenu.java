package org.gvozdev.pj.ui.menu;

import org.gvozdev.pj.PJApp;
import org.gvozdev.pj.ui.editor.PJEditor;
import org.gvozdev.pj.ui.editor.PJEditorTabs;
import org.gvozdev.pj.ui.menu.utils.XMLMenuLoader;
import org.xml.sax.SAXException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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

    menuBar.add(createEditMenu()); //TODO: remove
    return menuBar;
  }

  private JMenu createEditMenu() {
    JMenu editMenu = new JMenu("...");
    JMenuItem brushColor = new JMenuItem("Brush color");
    JMenuItem brushSize = new JMenuItem("Brush size");

    final PJEditorTabs tabs = app.getTabs();
    brushColor.addActionListener(e -> {
      var editor = tabs.getSelectedEditor();
      editor.ifPresent(PJEditor::changeBrushColor);
    });
    brushSize.addActionListener(e -> {
      var editor = tabs.getSelectedEditor();
      editor.ifPresent(PJEditor::changeBrushSize);
    });

    editMenu.add(brushColor);
    editMenu.add(brushSize);
    return editMenu;
  }
}
