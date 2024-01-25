package org.gvozdev.pj.actions.common;

import org.gvozdev.pj.actions.PJAction;
import org.gvozdev.pj.ui.main.MainWindow;

import java.awt.event.ActionEvent;

/**
 * Действие закрытия приложения.
 *
 * @author Roman Gvozdev
 */
public class ExitAction extends PJAction {
    /**
     * Создаёт действие закрытия приложения.
     *
     * @param mainWindow ссылка на главное окно приложения
     */
    public ExitAction(MainWindow<?> mainWindow) {
        super(mainWindow);
    }

    /**
     * Обрабатывает действие.
     *
     * @param e событие, подлежащее обработке
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
