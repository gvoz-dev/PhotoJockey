package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PJMainMenu {
  private final JMenuBar menuBar;
  private final PJMainWindow window;

  private PJMainMenu(PJMainWindow window) {
    this.window = window;
    menuBar = createMenuBar();
  }

  public static PJMainMenu create(PJMainWindow window) {
    return new PJMainMenu(window);
  }

  public void show() {
    window.getFrame().setJMenuBar(menuBar);
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

    JTabbedPane tabs = window.getTabs();
    newFile.addActionListener(e -> {
      PJEditor editor = new PJEditor();
      tabs.addTab("New image", editor);
      editor.setDefaultImage(640, 360);
    });
    openFile.addActionListener(e -> {
      PJEditor editor = new PJEditor();
      if (editor.openFromFile()) {
        tabs.addTab(editor.getFileName(), editor);
      }
    });
    saveFile.addActionListener(e -> {
      int index = tabs.getSelectedIndex();
      if (index != -1) {
        PJEditor editor = (PJEditor) tabs.getComponentAt(index);
        if (editor.saveToFile()) {
          tabs.setTitleAt(index, editor.getFileName());
        }
      }
    });
    closeFile.addActionListener(e -> {
      int index = tabs.getSelectedIndex();
      if (index != -1) {
        tabs.remove(tabs.getSelectedIndex());
      }
    });
    printFile.addActionListener(e -> {
      int index = tabs.getSelectedIndex();
      if (index != -1) {
        PJEditor editor = (PJEditor) tabs.getComponentAt(index);
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

    JTabbedPane tabs = window.getTabs();
    brushColor.addActionListener(e -> {
      int index = tabs.getSelectedIndex();
      if (index != -1) {
        PJEditor editor = (PJEditor) tabs.getComponentAt(index);
        editor.changeBrushColor();
      }
    });
    brushSize.addActionListener(e -> {
      int index = tabs.getSelectedIndex();
      if (index != -1) {
        PJEditor editor = (PJEditor) tabs.getComponentAt(index);
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

    grayscale.addActionListener(new FilterListener(window));
    negative.addActionListener(new FilterListener(window));
    sepia.addActionListener(new FilterListener(window));

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
    JTabbedPane tabs;

    public FilterListener(PJMainWindow window) {
      tabs = window.getTabs();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      int index = tabs.getSelectedIndex();
      if (index != -1) {
        PJEditor editor = (PJEditor) tabs.getComponentAt(index);
        editor.useFilter(e.getActionCommand());
      }
    }
  }
}
