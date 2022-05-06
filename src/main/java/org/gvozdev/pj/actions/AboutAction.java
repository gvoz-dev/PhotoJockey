package org.gvozdev.pj.actions;

import org.gvozdev.pj.PJApp;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;

public class AboutAction extends PJAction {
  private static final String INFO = "PhotoJockey 2022 Prototype";
  private static final String AUTHOR = "Roman Gvozdev (github.com/gvoz-dev)";

  public AboutAction(PJApp app) {
    super(app);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    final var message = INFO + '\n' + AUTHOR;
    JOptionPane.showMessageDialog(app.getFrame(), message, "About", JOptionPane.INFORMATION_MESSAGE);
  }
}
