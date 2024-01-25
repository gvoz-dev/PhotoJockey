package org.gvozdev.pj.actions;

import org.gvozdev.pj.ui.main.MainWindow;

import javax.swing.AbstractAction;

/**
 * Абстрактное действие PhotoJockey.
 *
 * @author Roman Gvozdev
 */
public abstract class PJAction extends AbstractAction {
    protected MainWindow<?> mainWindow;

    /**
     * Создаёт действие PhotoJockey.
     */
    public PJAction() {
    }

    /**
     * Создаёт действие PhotoJockey.
     *
     * @param mainWindow ссылка на главное окно приложения
     */
    public PJAction(MainWindow<?> mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * Возвращает ссылку на главное окно приложения.
     */
    public MainWindow<?> getMainWindow() {
        return mainWindow;
    }

    /**
     * Устанавливает ссылку на главное окно приложения.
     *
     * @param mainWindow ссылка на главное окно приложения
     */
    public void setMainWindow(MainWindow<?> mainWindow) {
        this.mainWindow = mainWindow;
    }
}
