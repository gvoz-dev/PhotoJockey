package org.gvozdev.pj.actions;

import org.gvozdev.pj.PJApp;

import java.awt.event.ActionEvent;

public class ExitAction extends PJAction {
  public ExitAction(PJApp app) {
    super(app);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    System.exit(0);
  }
}
