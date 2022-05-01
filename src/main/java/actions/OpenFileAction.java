package actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ui.PJApp;
import ui.editor.PJEditor;

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
    editorOptional.ifPresent(pjEditor -> app.getTabs().addEditor(pjEditor));
  }

  public Optional<PJEditor> openFile() {
    JFileChooser chooser = new JFileChooser();
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
    logger.warn("Unable to load image file!");
    return Optional.empty();
  }
}
