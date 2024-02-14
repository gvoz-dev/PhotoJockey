package org.gvozdev.pj.actions.tools;

import org.gvozdev.pj.actions.PJAction;
import org.gvozdev.pj.ui.main.MainWindow;

import java.awt.event.ActionEvent;

/**
 * Действие выбора инструмента "Кисть".
 *
 * @author Roman Gvozdev
 */
public class SelectBrushAction extends PJAction {
    /**
     * Создаёт действие выбора инструмента "Кисть".
     *
     * @param mainWindow ссылка на главное окно приложения
     */
    public SelectBrushAction(MainWindow<?> mainWindow) {
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
