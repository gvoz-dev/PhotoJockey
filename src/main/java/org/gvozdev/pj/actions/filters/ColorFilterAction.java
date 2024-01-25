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
 *
 * @author Roman Gvozdev
 */
public class ColorFilterAction extends PJAction {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
    private static final String PACKAGE = "org.gvozdev.pj.processing.filters.color";

    /**
     * Создаёт действие фильтрации компонентов цвета изображения.
     *
     * @param mainWindow ссылка на главное окно приложения
     */
    public ColorFilterAction(MainWindow<?> mainWindow) {
        super(mainWindow);
    }

    /**
     * Обрабатывает действие.
     *
     * @param e событие, подлежащее обработке
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        var editorOptional = mainWindow.editorTabs().getSelectedEditor();
        editorOptional.ifPresent(editor -> useFilter(editor, e.getActionCommand()));
    }

    /**
     * Применяет фильтр к изображению в редакторе.
     *
     * @param editor     редактор изображений
     * @param filterName название фильтра
     */
    private void useFilter(Editor<?> editor, String filterName) {
        try {
            var className = PACKAGE + "." + filterName;
            ImageFilter imageFilter = (ImageFilter) (Class.forName(className)).getConstructor().newInstance();
            var img = editor.getImage();
            editor.setImage(imageFilter.filter(img));
            LOGGER.debug("{} filter applied to '{}'", filterName, editor.getName());
        } catch (InstantiationException |
                 IllegalAccessException |
                 InvocationTargetException |
                 NoSuchMethodException |
                 ClassNotFoundException e) {
            LOGGER.error("Filter error:", e);
        }
    }
}
