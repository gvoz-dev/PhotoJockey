package ui;

import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;
import ui.editor.PJEditorTabs;
import ui.menu.PJMainMenu;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;

public class PJApp {
  private static final int DEFAULT_WIDTH = 1280;
  private static final int DEFAULT_HEIGHT = 720;

  private final JFrame frame;
  private final PJEditorTabs tabs;

  public PJApp() {
    frame = new JFrame("PhotoJockey");
    frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    frame.setLayout(new BorderLayout());
    tabs = new PJEditorTabs();
    PJMainMenu.create(this).show();
    frame.add(tabs, BorderLayout.CENTER);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    FlatCarbonIJTheme.setup();
    SwingUtilities.invokeLater(PJApp::new);
  }

  public JFrame getFrame() {
    return frame;
  }

  public PJEditorTabs getTabs() {
    return tabs;
  }
}
