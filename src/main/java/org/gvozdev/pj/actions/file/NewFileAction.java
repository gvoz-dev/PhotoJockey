package org.gvozdev.pj.actions.file;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gvozdev.pj.PJApp;
import org.gvozdev.pj.actions.PJAction;
import org.gvozdev.pj.ui.editor.PJEditor;

import java.awt.event.ActionEvent;

public class NewFileAction extends PJAction {
  private static final Logger logger = LogManager.getLogger(NewFileAction.class);

  public NewFileAction(PJApp app) {
    super(app);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    PJEditor editor = new PJEditor(app.getTools());
    editor.setDefaultImage(PJEditor.DEFAULT_IMG_WIDTH, PJEditor.DEFAULT_IMG_HEIGHT);
    app.getTabs().addEditorTab(editor);
    logger.info("Image created.");
  }
}
