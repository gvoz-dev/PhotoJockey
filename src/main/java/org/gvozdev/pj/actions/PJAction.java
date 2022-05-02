package org.gvozdev.pj.actions;

import org.gvozdev.pj.PJApp;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class PJAction extends AbstractAction {
  protected PJApp app;

  public PJAction() {
  }

  public PJAction(PJApp app) {
    this.app = app;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
  }
}
