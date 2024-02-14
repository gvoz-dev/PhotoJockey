package org.gvozdev.pj;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneDarkIJTheme;
import nu.pattern.OpenCV;
import org.gvozdev.pj.ui.main.PJMainWindow;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.SwingUtilities;

/**
 * Главный класс, который реализует точку входа в приложение PhotoJockey:
 * инициализация библиотеки OpenCV,
 * инициализация FlatLaf (Flat Look anf Feel),
 * создание контекста Spring,
 * отображение главного окна.
 */
public class PJApp {
    public static void main(String[] args) {
        OpenCV.loadLocally();
        FlatAtomOneDarkIJTheme.setup();

        ApplicationContext context = new ClassPathXmlApplicationContext("spring/AppContext.xml");
        PJMainWindow mainWindow = context.getBean("mainWindow", PJMainWindow.class);
        SwingUtilities.invokeLater(mainWindow::show);
    }
}
