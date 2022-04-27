package actions;

import gui.PJApp;

import javax.swing.Icon;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;

public class ExitAction extends PJAction {
  PJApp app;

  public ExitAction(PJApp app, String name, Icon icon, String description, KeyStroke accelerator, Integer mnemonic) {
    super(name, icon, description, accelerator, mnemonic);
    this.app = app;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    System.exit(0);
  }
}
