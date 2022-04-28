package actions;

import ui.PJApp;
import ui.editor.PJEditor;

import java.awt.event.ActionEvent;

public class OpenFileAction extends PJAction {
  public OpenFileAction(PJApp app) {
    super(app);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    PJEditor editor = new PJEditor();
    if (editor.openFromFile()) {
      app.getTabs().addEditor(editor);
    }
  }
}
