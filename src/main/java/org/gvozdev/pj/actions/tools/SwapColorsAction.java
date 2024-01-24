package org.gvozdev.pj.actions.tools;

import org.gvozdev.pj.actions.PJAction;
import org.gvozdev.pj.ui.main.MainWindow;

import javax.swing.Action;
import java.awt.event.ActionEvent;

/**
 * Действие смены основного и дополнительного цветов.
 *
 * @author Roman Gvozdev
 */
public class SwapColorsAction extends PJAction {
    /**
     * Создаёт действие смены цветов.
     *
     * @param mainWindow ссылка на главное окно приложения
     */
    public SwapColorsAction(MainWindow<?> mainWindow) {
        super(mainWindow);
        putValue(Action.NAME, "↔");
    }

    /**
     * Обрабатывает действие.
     *
     * @param e событие, подлежащее обработке
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.drawingTools().swapColors();
    }
}
