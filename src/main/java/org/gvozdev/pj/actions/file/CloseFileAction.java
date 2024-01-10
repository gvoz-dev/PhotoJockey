package org.gvozdev.pj.actions.file;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gvozdev.pj.actions.PJAction;
import org.gvozdev.pj.ui.PJMainWindow;

import java.awt.event.ActionEvent;

public class CloseFileAction extends PJAction {
    private static final Logger logger = LogManager.getLogger(CloseFileAction.class);

    public CloseFileAction(PJMainWindow app) {
        super(app);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var editorOptional = app.getEditorTabs().getSelectedEditor();
        if (editorOptional.isPresent()) {
            var fileName = editorOptional.get().getFileName();
            app.getEditorTabs().removeSelectedEditorTab();
            logger.info(String.format("Image '%s' closed.", fileName));
        }
    }
}
