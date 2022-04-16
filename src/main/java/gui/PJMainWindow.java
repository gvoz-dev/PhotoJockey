package gui;

import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import java.awt.*;

public class PJMainWindow {
  private static final int WIDTH = 960;
  private static final int HEIGHT = 540;
  private final JFrame frame;
  private JTabbedPane tabs;

  public PJMainWindow() {
    frame = new JFrame("PhotoJockey");
    frame.setSize(WIDTH, HEIGHT);
    frame.setLayout(new BorderLayout());
    addTabs();
    PJMainMenu.create(this).show();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    FlatIntelliJLaf.setup();
    EventQueue.invokeLater(PJMainWindow::new);
  }

  public JFrame getFrame() {
    return frame;
  }

  public JTabbedPane getTabs() {
    return tabs;
  }

  public void addTabs() {
    tabs = new JTabbedPane();
    tabs.setBackground(Color.LIGHT_GRAY);
    frame.add(tabs, BorderLayout.CENTER);
  }
}
