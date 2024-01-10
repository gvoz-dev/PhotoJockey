package org.gvozdev.pj.actions;

import org.gvozdev.pj.ui.PJMainWindow;

import java.awt.event.ActionEvent;

public class ExitAction extends PJAction {
    public ExitAction(PJMainWindow app) {
        super(app);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
