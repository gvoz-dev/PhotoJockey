package org.gvozdev.pj.actions.file;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gvozdev.pj.PJApp;
import org.gvozdev.pj.actions.PJAction;
import org.gvozdev.pj.ui.editor.PJEditor;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class OpenFileAction extends PJAction {
  private static final Logger logger = LogManager.getLogger(OpenFileAction.class);

  public OpenFileAction(PJApp app) {
    super(app);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Optional<PJEditor> editorOptional = openFile();
    editorOptional.ifPresent(pjEditor -> app.getTabs().addEditorTab(pjEditor));
  }

  public Optional<PJEditor> openFile() {
    JFileChooser chooser = new JFileChooser();
    chooser.setDialogType(JFileChooser.OPEN_DIALOG);
    int res = chooser.showOpenDialog(app.getFrame());

    if (res == JFileChooser.APPROVE_OPTION) {
      File file = chooser.getSelectedFile();
      try {
        var img = ImageIO.read(file);
        if (img != null) {
          var fileName = file.getName();
          PJEditor editor = new PJEditor();
          editor.setImg(img);
          editor.setFileName(fileName);
          logger.info(String.format("Image %s loaded.", fileName));
          return Optional.of(editor);
        }
      } catch (IOException e) {
        logger.error("Image read exception:", e);
      }
    }
    return Optional.empty();
  }
}
