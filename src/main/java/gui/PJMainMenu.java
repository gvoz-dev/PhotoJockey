package gui;

import actions.CloseFileAction;
import actions.ExitAction;
import actions.NewFileAction;
import actions.OpenFileAction;
import actions.PrintFileAction;
import actions.SaveFileAction;
import gui.editor.PJEditor;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    JMenuBar menuBar = new JMenuBar();
    menuBar.add(createFileMenu());
    menuBar.add(createEditMenu());
    menuBar.add(createHelpMenu());
    return menuBar;
  }

  private JMenu createFileMenu() {
    JMenu fileMenu = new JMenu("File");
    JMenuItem newFile = new JMenuItem(new NewFileAction(app, "New", null, null, null, null));
    JMenuItem openFile = new JMenuItem(new OpenFileAction(app, "Open...", null, null, null, null));
    JMenuItem saveFile = new JMenuItem(new SaveFileAction(app, "Save...", null, null, null, null));
    JMenuItem closeFile = new JMenuItem(new CloseFileAction(app, "Close", null, null, null, null));
    JMenuItem printFile = new JMenuItem(new PrintFileAction(app, "Print...", null, null, null, null));
    JMenuItem exit = new JMenuItem(new ExitAction(app, "Exit", null, null, null, null));

    fileMenu.add(newFile);
    fileMenu.add(openFile);
    fileMenu.add(saveFile);
    fileMenu.add(closeFile);
    fileMenu.add(printFile);
    fileMenu.addSeparator();
    fileMenu.add(exit);
    return fileMenu;
  }

  private JMenu createEditMenu() {
    JMenu editMenu = new JMenu("Edit");
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
