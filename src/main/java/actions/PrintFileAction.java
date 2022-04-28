package actions;

import ui.PJApp;
import ui.editor.PJEditor;

import java.awt.event.ActionEvent;

public class PrintFileAction extends PJAction {
  public PrintFileAction(PJApp app) {
    super(app);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    PJEditor editor = app.getTabs().getSelectedEditor();
    if (editor != null) {
      editor.printFile();
    }
  }
}
