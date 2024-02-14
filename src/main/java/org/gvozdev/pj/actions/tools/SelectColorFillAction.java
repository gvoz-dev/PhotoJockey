package org.gvozdev.pj.actions.tools;

import org.gvozdev.pj.actions.PJAction;
import org.gvozdev.pj.ui.main.MainWindow;

import java.awt.event.ActionEvent;

/**
 * Действие выбора инструмента "Заливка цветом".
 *
 * @author Roman Gvozdev
 */
public class SelectColorFillAction extends PJAction {
    /**
     * Создаёт действие выбора инструмента "Заливка цветом".
     *
     * @param mainWindow ссылка на главное окно приложения
     */
    public SelectColorFillAction(MainWindow<?> mainWindow) {
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
