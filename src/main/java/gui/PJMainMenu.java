package gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PJMainMenu {
  private final JMenuBar menuBar;
  private final PJApp app;

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
    JMenuItem newFile = new JMenuItem("New");
    JMenuItem openFile = new JMenuItem("Open...");
    JMenuItem saveFile = new JMenuItem("Save...");
    JMenuItem closeFile = new JMenuItem("Close");
    JMenuItem printFile = new JMenuItem("Print...");
    JMenuItem exit = new JMenuItem("Exit");

    final PJEditorTabs tabs = app.getTabs();
    newFile.addActionListener(e -> {
      PJEditor editor = new PJEditor();
      editor.setDefaultImage(PJEditor.DEFAULT_IMG_WIDTH, PJEditor.DEFAULT_IMG_HEIGHT);
      tabs.addEditor(editor);
    });
    openFile.addActionListener(e -> {
      PJEditor editor = new PJEditor();
      if (editor.openFromFile()) {
        tabs.addEditor(editor);
      }
    });
    saveFile.addActionListener(e -> {
      PJEditor editor = tabs.getSelectedEditor();
      if (editor != null) {
        if (editor.saveToFile()) {
          tabs.updateTitle(editor.getFileName());
        }
      }
    });
    closeFile.addActionListener(e -> tabs.removeSelectedEditor());
    printFile.addActionListener(e -> {
      PJEditor editor = tabs.getSelectedEditor();
      if (editor != null) {
        editor.printFile();
      }
    });
    exit.addActionListener(e -> System.exit(0));

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

    about.addActionListener(e -> JOptionPane.showMessageDialog(null, "PhotoJockey Alpha 2022", "About", JOptionPane.INFORMATION_MESSAGE));

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
