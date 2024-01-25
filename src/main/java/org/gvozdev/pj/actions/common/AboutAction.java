package org.gvozdev.pj.actions.common;

import org.gvozdev.pj.actions.PJAction;
import org.gvozdev.pj.ui.main.MainWindow;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;

/**
 * Действие показа информационного сообщения "О программе".
 *
 * @author Roman Gvozdev
 */
public class AboutAction extends PJAction {
    private static final String INFO = "PhotoJockey 2024 Prototype";
    private static final String AUTHOR = "Roman Gvozdev: https://github.com/gvoz-dev";

    /**
     * Создаёт действие показа информационного сообщения "О программе".
     *
     * @param mainWindow ссылка на главное окно приложения
     */
    public AboutAction(MainWindow<?> mainWindow) {
        super(mainWindow);
    }

    /**
     * Обрабатывает действие.
     *
     * @param e событие, подлежащее обработке
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        final var message = INFO + '\n' + AUTHOR;

        if (mainWindow.frame() instanceof JFrame frame) {
            JOptionPane.showMessageDialog(frame, message, "About", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, message, "About", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
