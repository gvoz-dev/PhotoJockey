package org.gvozdev.pj;

import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;
import org.gvozdev.pj.ui.editor.PJEditorTabs;
import org.gvozdev.pj.ui.menu.PJMainMenu;
import org.gvozdev.pj.ui.tools.PJTools;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;

public class PJApp {
  private static final int DEFAULT_WIDTH = 1280;
  private static final int DEFAULT_HEIGHT = 720;

  private final JFrame frame;
  private final PJEditorTabs tabs;
  private final PJTools tools;

  public PJApp() {
    frame = new JFrame("PhotoJockey");
    frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    frame.setLayout(new BorderLayout());

    PJMainMenu.create(this).show();
    tabs = new PJEditorTabs();
    frame.add(tabs, BorderLayout.CENTER);
    tools = PJTools.create(this);
    tools.show();

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

  public PJTools getTools() {
    return tools;
  }
}
