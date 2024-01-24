package org.gvozdev.pj.actions.file;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gvozdev.pj.actions.PJAction;
import org.gvozdev.pj.ui.main.MainWindow;

import java.awt.event.ActionEvent;
import java.lang.invoke.MethodHandles;

public class CloseFileAction extends PJAction {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    public CloseFileAction(MainWindow mainWindow) {
        super(mainWindow);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var editorOptional = mainWindow.editorTabs().getSelectedEditor();
        if (editorOptional.isPresent()) {
            var fileName = editorOptional.get().getName();
            mainWindow.editorTabs().removeSelectedEditorTab();
            LOGGER.info(String.format("Image '%s' closed.", fileName));
        }
    }
}
