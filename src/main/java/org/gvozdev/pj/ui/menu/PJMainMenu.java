package org.gvozdev.pj.ui.menu;

import org.gvozdev.pj.ui.main.MainWindow;
import org.gvozdev.pj.ui.menu.factory.XMLMenuFactory;

import javax.swing.JComponent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.io.File;
import java.util.Map;

/**
 * Swing-реализация главного меню приложения.
 * Конфигурация меню загружается из XML-файла.
 *
 * @author Roman Gvozdev
 */
public class PJMainMenu implements MainMenu<JMenuBar, JMenuItem> {
    private final File xmlConfigFile;
    private Map<String, JComponent> menuStorage;

    /**
     * Создаёт Swing-реализацию главного меню приложения.
     *
     * @param xmlConfigFile XML-файл конфигурации меню
     */
    public PJMainMenu(File xmlConfigFile) {
        this.xmlConfigFile = xmlConfigFile;
    }

    /**
     * Инициализирует главное меню приложения:
     * создаёт экземпляр {@link JMenuBar},
     * заполняет его элементами управления {@link JMenuItem},
     * привязывает к ним действия {@link javax.swing.Action}.
     *
     * @param mainWindow ссылка на главное окно приложения
     */
    @Override
    public void init(MainWindow<?> mainWindow) {
        XMLMenuFactory factory = new XMLMenuFactory(mainWindow, xmlConfigFile);
        menuStorage = factory.menuStorage();
    }

    /**
     * Возвращает панель главного меню.
     */
    public JMenuBar menuBar() {
        if (menuStorage == null) {
            throw new IllegalStateException("Main menu has not been initialized");
        }

        if (menuStorage.get("MainMenu") instanceof JMenuBar menuBar) {
            return menuBar;
        } else {
            throw new IllegalStateException("Menu bar has not yet been created");
        }
    }

    /**
     * Возвращает элемент главного меню по идентификатору.
     */
    public JMenuItem elementById(String elementId) {
        if (menuStorage == null) {
            throw new IllegalStateException("Main menu has not been initialized");
        }

        if (menuStorage.get(elementId) instanceof JMenuItem item) {
            return item;
        } else {
            throw new IllegalStateException("Menu element not found");
        }
    }
}
