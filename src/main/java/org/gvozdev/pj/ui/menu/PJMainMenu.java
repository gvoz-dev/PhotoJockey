package org.gvozdev.pj.ui.menu;

import org.gvozdev.pj.ui.PJMainWindow;
import org.xml.sax.SAXException;

import javax.swing.JComponent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс главного меню приложения.
 *
 * @author Roman Gvozdev
 */
public class PJMainMenu {
    private final Map<String, JComponent> menuStorage = new HashMap<>();

    /**
     * Создаёт главное меню.
     */
    public PJMainMenu() {
    }

    /**
     * Создаёт экземпляр {@link JMenuBar}, заполняет его элементами управления и привязывает к ним действия.
     * Конфигурация меню загружается из XML-файла MainMenu.xml.
     * Все элементы меню помещаются в ассоциативный массив menuStorage.
     *
     * @param mainWindow ссылка на главное окно приложения
     */
    public void init(PJMainWindow mainWindow) {
        try {
            XMLMenuFactory factory = new XMLMenuFactory(mainWindow, "ui/MainMenu.xml");
            factory.parse();
            menuStorage.clear();
            menuStorage.putAll(factory.getMenuStorage());
        } catch (IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Возвращает панель главного меню.
     */
    public JMenuBar getMenuBar() {
        if (menuStorage.get("MainMenu") instanceof JMenuBar menuBar) {
            return menuBar;
        } else {
            throw new IllegalStateException("MenuBar has not yet been created");
        }
    }

    /**
     * Возвращает элемент главного меню по идентификатору.
     */
    public JMenuItem getMenuElement(String elementID) {
        if (menuStorage.get(elementID) instanceof JMenuItem item) {
            return item;
        } else {
            throw new IllegalStateException("Element not found");
        }
    }
}
