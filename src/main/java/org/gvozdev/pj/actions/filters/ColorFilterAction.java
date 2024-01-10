package org.gvozdev.pj.actions.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gvozdev.pj.actions.PJAction;
import org.gvozdev.pj.processing.filters.ImageFilter;
import org.gvozdev.pj.ui.PJMainWindow;
import org.gvozdev.pj.ui.editor.PJEditor;

import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;

/**
 * Действие фильтрации компонентов цвета изображения.
 */
public class ColorFilterAction extends PJAction {
    private static final Logger LOGGER = LogManager.getLogger(ColorFilterAction.class);

    private static final String PACKAGE = "org.gvozdev.pj.processing.filters.color";

    public ColorFilterAction(PJMainWindow app) {
        super(app);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var editorOptional = app.getEditorTabs().getSelectedEditor();
        editorOptional.ifPresent(pjEditor -> useFilter(pjEditor, e.getActionCommand()));
    }

    public void useFilter(PJEditor editor, String filterName) {
        try {
            var className = PACKAGE + "." + filterName;
            ImageFilter imageFilter = (ImageFilter) (Class.forName(className)).getConstructor().newInstance();
            var img = editor.getImg();
            editor.setImg(imageFilter.filter(img));
            LOGGER.debug("{} filter applied to '{}'.", filterName, editor.getFileName());
        } catch (InstantiationException |
                 IllegalAccessException |
                 InvocationTargetException |
                 NoSuchMethodException |
                 ClassNotFoundException e) {
            LOGGER.error("Filter error:", e);
        }
    }
}
