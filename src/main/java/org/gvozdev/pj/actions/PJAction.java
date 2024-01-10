package org.gvozdev.pj.actions;

import org.gvozdev.pj.ui.PJMainWindow;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class PJAction extends AbstractAction {
    protected PJMainWindow app;

    public PJAction() {
    }

    public PJAction(PJMainWindow app) {
        this.app = app;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
