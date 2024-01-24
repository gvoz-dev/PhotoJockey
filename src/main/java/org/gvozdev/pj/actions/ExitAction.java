package org.gvozdev.pj.actions;

import org.gvozdev.pj.ui.main.MainWindow;

import java.awt.event.ActionEvent;

public class ExitAction extends PJAction {
    public ExitAction(MainWindow<?> mainWindow) {
        super(mainWindow);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
