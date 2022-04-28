package actions;

import ui.PJApp;
import ui.editor.PJEditor;

import java.awt.event.ActionEvent;

public class SaveFileAction extends PJAction {
  public SaveFileAction(PJApp app) {
    super(app);
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
