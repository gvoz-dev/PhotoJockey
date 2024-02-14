package org.gvozdev.pj.actions.tools;

import org.gvozdev.pj.actions.PJAction;
import org.gvozdev.pj.ui.main.MainWindow;

import java.awt.event.ActionEvent;

/**
 * Действие выбора инструмента "Ластик".
 *
 * @author Roman Gvozdev
 */
public class SelectEraserAction extends PJAction {
    /**
     * Создаёт действие выбора инструмента "Ластик".
     *
     * @param mainWindow ссылка на главное окно приложения
     */
    public SelectEraserAction(MainWindow<?> mainWindow) {
        super(mainWindow);
    }

    /**
     * Обрабатывает действие.
     *
     * @param e событие, подлежащее обработке
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
