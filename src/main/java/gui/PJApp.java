package gui;

import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.EventQueue;

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
    frame.add(tabs, BorderLayout.CENTER);
    PJMainMenu.create(this).show();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    FlatIntelliJLaf.setup();
    EventQueue.invokeLater(PJApp::new);
  }

  public JFrame getFrame() {
    return frame;
  }

  public PJEditorTabs getTabs() {
    return tabs;
  }
}
