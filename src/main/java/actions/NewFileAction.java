package actions;

import ui.PJApp;
import ui.editor.PJEditor;

import java.awt.event.ActionEvent;

public class NewFileAction extends PJAction {
  public NewFileAction(PJApp app) {
    super(app);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    PJEditor editor = new PJEditor();
    editor.setDefaultImage(PJEditor.DEFAULT_IMG_WIDTH, PJEditor.DEFAULT_IMG_HEIGHT);
    app.getTabs().addEditor(editor);
  }
}