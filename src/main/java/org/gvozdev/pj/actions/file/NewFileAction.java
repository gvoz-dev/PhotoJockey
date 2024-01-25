package org.gvozdev.pj.actions.file;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gvozdev.pj.actions.PJAction;
import org.gvozdev.pj.ui.editor.PJEditor;
import org.gvozdev.pj.ui.main.MainWindow;

import java.awt.event.ActionEvent;
import java.lang.invoke.MethodHandles;

/**
 * Действие создания нового файла изображения.
 *
 * @author Roman Gvozdev
 */
public class NewFileAction extends PJAction {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    /**
     * Конструирует действие создания нового файла изображения.
     *
     * @param mainWindow ссылка на главное окно приложения
     */
    public NewFileAction(MainWindow<?> mainWindow) {
        super(mainWindow);
    }

    /**
     * Обрабатывает действие.
     *
     * @param e событие, подлежащее обработке
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        PJEditor editor = new PJEditor(mainWindow.drawingTools());
        editor.init();
        editor.setDefaultImage(PJEditor.DEFAULT_IMAGE_WIDTH, PJEditor.DEFAULT_IMAGE_HEIGHT);
        mainWindow.editorTabs().addEditorTab(editor);
        LOGGER.info("Image created");
    }
}
