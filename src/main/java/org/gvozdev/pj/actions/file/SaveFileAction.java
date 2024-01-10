package org.gvozdev.pj.actions.file;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gvozdev.pj.actions.PJAction;
import org.gvozdev.pj.ui.PJMainWindow;
import org.gvozdev.pj.ui.editor.PJEditor;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class SaveFileAction extends PJAction {
    private static final Logger logger = LogManager.getLogger(SaveFileAction.class);

    public SaveFileAction(PJMainWindow app) {
        super(app);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var editorOptional = app.getEditorTabs().getSelectedEditor();
        if (editorOptional.isPresent()) {
            Optional<String> nameOptional = saveFile(editorOptional.get());
            nameOptional.ifPresent(s -> app.getEditorTabs().setSelectedTabTitle(s));
        }
    }

    public Optional<String> saveFile(PJEditor editor) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
        File file = new File(editor.getFileName());
        chooser.setSelectedFile(file);
        int res = chooser.showSaveDialog(app.getFrame());

        if (res == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
            try {
                var img = editor.getImg();
                if (img != null) {
                    ImageIO.write(img, "png", file);
                    var fileName = file.getName();
                    editor.setFileName(fileName);
                    logger.info(String.format("Image '%s' saved.", fileName));
                    return Optional.of(fileName);
                }
            } catch (IOException e) {
                logger.error("Image write error:", e);
            }
        }
        return Optional.empty();
    }
}
