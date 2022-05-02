package actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ui.PJApp;
import ui.editor.PJEditor;

import java.awt.event.ActionEvent;

public class PrintFileAction extends PJAction {
  private static final Logger logger = LogManager.getLogger(PrintFileAction.class);

  public PrintFileAction(PJApp app) {
    super(app);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    var editorOptional = app.getTabs().getSelectedEditor();
    editorOptional.ifPresent(PJEditor::printFile);
  }
}
