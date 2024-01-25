package org.gvozdev.pj.ui.menu.factory;

import org.gvozdev.pj.actions.PJAction;
import org.gvozdev.pj.ui.main.MainWindow;
import org.gvozdev.pj.utils.SVGImageLoader;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;

/**
 * Класс обработчика XML-файла конфигурации главного меню.
 *
 * @author Roman Gvozdev
 */
public class XMLMenuHandler extends DefaultHandler {
    private static final String TAG_MENU_BAR = "menuBar";
    private static final String TAG_MENU = "menu";
    private static final String TAG_MENU_ITEM = "item";
    private static final String TAG_SEPARATOR = "separator";
    private static final String ATTRIBUTE_ID = "id";
    private static final String ATTRIBUTE_ACTION = "action";
    private static final String ATTRIBUTE_TEXT = "text";
    private static final String ATTRIBUTE_ICON = "icon";
    private static final String ATTRIBUTE_DESCRIPTION = "description";
    private static final String ATTRIBUTE_ACCELERATOR = "accelerator";
    private static final String ATTRIBUTE_MNEMONIC = "mnemonic";
    private static final String ATTRIBUTE_ENABLED = "enabled";

    private static final float ICON_WIDTH = 16;
    private static final float ICON_HEIGHT = 16;

    private final MainWindow<?> mainWindow;
    private final Map<String, JComponent> menuStorage;
    private final Deque<JMenu> menus = new LinkedList<>();
    private JMenuBar menuBar;

    /**
     * Создаёт обработчик XML-файла конфигурации главного меню.
     *
     * @param mainWindow  главное окно
     * @param menuStorage хранилище элементов меню
     */
    public XMLMenuHandler(MainWindow<?> mainWindow, Map<String, JComponent> menuStorage) {
        this.mainWindow = mainWindow;
        this.menuStorage = menuStorage;
    }

    /**
     * Обрабатывает начало элемента XML.
     * <p>
     * Из официальной документации:
     * Receive notification of the start of an element.
     *
     * @param uri        The Namespace URI, or the empty string if the
     *                   element has no Namespace URI or if Namespace
     *                   processing is not being performed.
     * @param localName  The local name (without prefix), or the
     *                   empty string if Namespace processing is not being
     *                   performed.
     * @param qName      The qualified name (with prefix), or the
     *                   empty string if qualified names are not available.
     * @param attributes The attributes attached to the element.  If
     *                   there are no attributes, it shall be an empty
     *                   Attributes object.
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (qName) {
            case TAG_MENU_BAR -> handleMenuBar(attributes);
            case TAG_MENU -> handleMenu(attributes);
            case TAG_MENU_ITEM -> handleMenuItem(attributes);
            case TAG_SEPARATOR -> handleSeparator();
        }
    }

    /**
     * Обрабатывает конец элемента XML.
     * <p>
     * Из официальной документации:
     * Receive notification of the end of an element.
     *
     * @param uri       The Namespace URI, or the empty string if the
     *                  element has no Namespace URI or if Namespace
     *                  processing is not being performed.
     * @param localName The local name (without prefix), or the
     *                  empty string if Namespace processing is not being
     *                  performed.
     * @param qName     The qualified name (with prefix), or the
     *                  empty string if qualified names are not available.
     */
    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals(TAG_MENU)) {
            menus.pop();
        }
    }

    /**
     * Обрабатывает элемент MenuBar.
     *
     * @param attributes аттрибуты элемента
     */
    protected void handleMenuBar(Attributes attributes) {
        menuBar = new JMenuBar();
        String id = attributes.getValue(ATTRIBUTE_ID);
        menuStorage.put(id, menuBar);
    }

    /**
     * Обрабатывает элемент Menu.
     *
     * @param attributes аттрибуты элемента
     */
    protected void handleMenu(Attributes attributes) {
        JMenu menu = new JMenu();
        String id = attributes.getValue(ATTRIBUTE_ID);
        menuStorage.put(id, menu);
        adjustProperties(menu, attributes);

        if (menus.isEmpty()) {
            menuBar.add(menu);
        } else {
            menus.peek().add(menu);
        }
        menus.push(menu);
    }

    /**
     * Обрабатывает элемент MenuItem.
     *
     * @param attributes аттрибуты элемента
     */
    protected void handleMenuItem(Attributes attributes) {
        JMenuItem menuItem = new JMenuItem();
        String id = attributes.getValue(ATTRIBUTE_ID);
        menuStorage.put(id, menuItem);
        adjustProperties(menuItem, attributes);

        if (menus.peek() != null) {
            menus.peek().add(menuItem);
        }
    }

    /**
     * Обрабатывает элемент Separator (разделитель).
     */
    protected void handleSeparator() {
        if (menus.peek() != null) {
            menus.peek().addSeparator();
        }
    }

    /**
     * Настраивает элемент меню.
     *
     * @param menuItem   элемент меню
     * @param attributes аттрибуты элемента
     */
    private void adjustProperties(JMenuItem menuItem, Attributes attributes) {
        String actionClass = attributes.getValue(ATTRIBUTE_ACTION);
        PJAction action = loadAction(actionClass);

        String text = attributes.getValue(ATTRIBUTE_TEXT);
        if (text != null) {
            action.putValue(Action.NAME, text);
        }

        String iconPath = attributes.getValue(ATTRIBUTE_ICON);
        if (iconPath != null) {
            URL imgURL = getClass().getClassLoader().getResource(iconPath);
            if (imgURL != null) {
                try {
                    Icon icon = new ImageIcon(SVGImageLoader.loadSVG(imgURL, ICON_WIDTH, ICON_HEIGHT));
                    action.putValue(Action.SMALL_ICON, icon);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        String description = attributes.getValue(ATTRIBUTE_DESCRIPTION);
        if (description != null) {
            action.putValue(Action.SHORT_DESCRIPTION, description);
        }

        String accelerator = attributes.getValue(ATTRIBUTE_ACCELERATOR);
        if (accelerator != null) {
            action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(accelerator));
        }

        String mnemonic = attributes.getValue(ATTRIBUTE_MNEMONIC);
        if (mnemonic != null) {
            int ch = mnemonic.charAt(0);
            action.putValue(Action.MNEMONIC_KEY, KeyEvent.getExtendedKeyCodeForChar(ch));
        }

        String enabled = attributes.getValue(ATTRIBUTE_ENABLED);
        if (enabled != null) {
            action.setEnabled(Boolean.parseBoolean(enabled));
        }

        menuItem.setAction(action);
    }

    /**
     * Загружает объект действия {@link Action}.
     *
     * @param name имя класса действия
     */
    private PJAction loadAction(String name) {
        PJAction action;
        if (name != null) {
            try {
                action = (PJAction) (Class.forName(name)).getConstructor(MainWindow.class).newInstance(mainWindow);
            } catch (InstantiationException |
                     IllegalAccessException |
                     InvocationTargetException |
                     NoSuchMethodException |
                     ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            action = new PJAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Пустая реализация действия
                }
            };
        }
        return action;
    }
}
