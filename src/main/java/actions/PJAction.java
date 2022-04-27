package actions;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.KeyStroke;

public abstract class PJAction extends AbstractAction {
  public PJAction() {
  }

  public PJAction(String name) {
    putValue(Action.NAME, name);
  }

  public PJAction(String name, Icon icon) {
    this(name);
    putValue(Action.SMALL_ICON, icon);
  }

  public PJAction(String name, Icon icon, String description) {
    this(name, icon);
    putValue(Action.SHORT_DESCRIPTION, description);
  }

  public PJAction(String name, Icon icon, String description, KeyStroke accelerator, Integer mnemonic) {
    this(name, icon, description);
    putValue(Action.ACCELERATOR_KEY, accelerator); //KeyStroke.getKeyStroke(accelerator)
    putValue(Action.MNEMONIC_KEY, mnemonic); //KeyEvent.getExtendedKeyCodeForChar(mnemonic)
  }
}
