package actions;

import gui.PJApp;
import gui.editor.PJEditor;

import javax.swing.Icon;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;

public class NewFileAction extends PJAction {
  PJApp app;

  public NewFileAction(PJApp app, String name, Icon icon, String description, KeyStroke accelerator, Integer mnemonic) {
    super(name, icon, description, accelerator, mnemonic);
    this.app = app;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    PJEditor editor = new PJEditor();
    editor.setDefaultImage(PJEditor.DEFAULT_IMG_WIDTH, PJEditor.DEFAULT_IMG_HEIGHT);
    app.getTabs().addEditor(editor);
  }
}
