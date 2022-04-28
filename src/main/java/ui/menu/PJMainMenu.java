package ui.menu;

import ui.PJApp;
import ui.editor.PJEditorTabs;
import ui.editor.PJEditor;
import ui.menu.utils.XMLMenuLoader;
import org.xml.sax.SAXException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    menuBar.add(createEditMenu());
    menuBar.add(createHelpMenu());
    return menuBar;
  }

  private JMenu createEditMenu() {
    JMenu editMenu = new JMenu("Old edit");
    JMenuItem brushColor = new JMenuItem("Brush color");
    JMenuItem brushSize = new JMenuItem("Brush size");

    final PJEditorTabs tabs = app.getTabs();
    brushColor.addActionListener(e -> {
      PJEditor editor = tabs.getSelectedEditor();
      if (editor != null) {
        editor.changeBrushColor();
      }
    });
    brushSize.addActionListener(e -> {
      PJEditor editor = tabs.getSelectedEditor();
      if (editor != null) {
        editor.changeBrushSize();
      }
    });

    editMenu.add(brushColor);
    editMenu.add(brushSize);
    editMenu.add(createFilterSubMenu());
    return editMenu;
  }

  private JMenu createFilterSubMenu() {
    JMenu filterMenu = new JMenu("Filter");
    JMenuItem grayscale = new JMenuItem("Grayscale");
    JMenuItem negative = new JMenuItem("Negative");
    JMenuItem sepia = new JMenuItem("Sepia");

    grayscale.addActionListener(new FilterListener(app));
    negative.addActionListener(new FilterListener(app));
    sepia.addActionListener(new FilterListener(app));

    filterMenu.add(grayscale);
    filterMenu.add(negative);
    filterMenu.add(sepia);
    return filterMenu;
  }

  private JMenu createHelpMenu() {
    JMenu helpMenu = new JMenu("Help");
    JMenuItem about = new JMenuItem("About");

    about.addActionListener(e -> JOptionPane.showMessageDialog(null, "PhotoJockey 2022 Prototype", "About", JOptionPane.INFORMATION_MESSAGE));

    helpMenu.add(about);
    return helpMenu;
  }

  static class FilterListener implements ActionListener {
    PJEditorTabs tabs;

    public FilterListener(PJApp app) {
      tabs = app.getTabs();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      PJEditor editor = tabs.getSelectedEditor();
      if (editor != null) {
        editor.useFilter(e.getActionCommand());
      }
    }
  }
}
