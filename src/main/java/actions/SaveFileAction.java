package actions;

import gui.PJApp;
import gui.editor.PJEditor;

import javax.swing.Icon;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;

public class SaveFileAction extends PJAction {
  PJApp app;

  public SaveFileAction(PJApp app, String name, Icon icon, String description, KeyStroke accelerator, Integer mnemonic) {
    super(name, icon, description, accelerator, mnemonic);
    this.app = app;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    PJEditor editor = app.getTabs().getSelectedEditor();
    if (editor != null) {
      if (editor.saveToFile()) {
        app.getTabs().setTabTitle(editor.getFileName());
      }
    }
  }
}
