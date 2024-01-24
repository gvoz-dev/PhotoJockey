package org.gvozdev.pj.actions;

import org.gvozdev.pj.ui.main.MainWindow;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;

public class AboutAction extends PJAction {
    private static final String INFO = "PhotoJockey 2024 Prototype";
    private static final String AUTHOR = "Roman Gvozdev (github.com/gvoz-dev)";

    public AboutAction(MainWindow<? extends JFrame> mainWindow) {
        super(mainWindow);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final var message = INFO + '\n' + AUTHOR;

        if (mainWindow.frame() instanceof JFrame frame) {
            JOptionPane.showMessageDialog(frame, message, "About", JOptionPane.INFORMATION_MESSAGE);
        } else {
            throw new RuntimeException(); // TODO: ?
        }
    }
}
