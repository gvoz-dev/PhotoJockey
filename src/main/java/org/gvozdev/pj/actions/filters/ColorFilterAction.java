package org.gvozdev.pj.actions.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gvozdev.pj.actions.PJAction;
import org.gvozdev.pj.processing.filters.ImageFilter;
import org.gvozdev.pj.ui.editor.Editor;
import org.gvozdev.pj.ui.main.MainWindow;

import java.awt.event.ActionEvent;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;

/**
 * Действие фильтрации компонентов цвета изображения.
 */
public class ColorFilterAction extends PJAction {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    private static final String PACKAGE = "org.gvozdev.pj.processing.filters.color";

    public ColorFilterAction(MainWindow mainWindow) {
        super(mainWindow);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var editorOptional = mainWindow.editorTabs().getSelectedEditor();
        editorOptional.ifPresent(editor -> useFilter(editor, e.getActionCommand()));
    }

    public void useFilter(Editor editor, String filterName) {
        try {
            var className = PACKAGE + "." + filterName;
            ImageFilter imageFilter = (ImageFilter) (Class.forName(className)).getConstructor().newInstance();
            var img = editor.getImage();
            editor.setImage(imageFilter.filter(img));
            LOGGER.debug("{} filter applied to '{}'.", filterName, editor.getName());
        } catch (InstantiationException |
                 IllegalAccessException |
                 InvocationTargetException |
                 NoSuchMethodException |
                 ClassNotFoundException e) {
            LOGGER.error("Filter error:", e);
        }
    }
}
