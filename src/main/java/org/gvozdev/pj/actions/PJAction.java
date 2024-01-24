package org.gvozdev.pj.actions;

import org.gvozdev.pj.ui.main.MainWindow;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class PJAction extends AbstractAction {
    protected MainWindow<?> mainWindow;

    public PJAction() {
    }

    public PJAction(MainWindow<?> mainWindow) {
        this.mainWindow = mainWindow;
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
