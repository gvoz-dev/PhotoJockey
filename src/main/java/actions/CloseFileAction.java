package actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ui.PJApp;

import java.awt.event.ActionEvent;

public class CloseFileAction extends PJAction {
  private static final Logger logger = LogManager.getLogger(CloseFileAction.class);

  public CloseFileAction(PJApp app) {
    super(app);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    var editorOptional = app.getTabs().getSelectedEditor();
    if (editorOptional.isPresent()) {
      var fileName = editorOptional.get().getFileName();
      app.getTabs().removeSelectedEditorTab();
      logger.info(String.format("Image %s closed.", fileName));
    }
  }
}
