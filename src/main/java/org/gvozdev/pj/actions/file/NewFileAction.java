package org.gvozdev.pj.actions.file;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gvozdev.pj.actions.PJAction;
import org.gvozdev.pj.ui.editor.PJEditor;
import org.gvozdev.pj.ui.main.MainWindow;

import java.awt.event.ActionEvent;
import java.lang.invoke.MethodHandles;

public class NewFileAction extends PJAction {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    public NewFileAction(MainWindow mainWindow) {
        super(mainWindow);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PJEditor editor = new PJEditor(mainWindow.drawingTools());
        editor.init();
        editor.setDefaultImage(PJEditor.DEFAULT_IMAGE_WIDTH, PJEditor.DEFAULT_IMAGE_HEIGHT);
        mainWindow.editorTabs().addEditorTab(editor);
        LOGGER.info("Image created.");
    }
}
