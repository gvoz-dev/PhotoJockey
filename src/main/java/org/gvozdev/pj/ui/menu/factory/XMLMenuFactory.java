package org.gvozdev.pj.ui.menu.factory;

import org.gvozdev.pj.ui.main.MainWindow;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.JComponent;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Класс фабрики элементов главного меню, конфигурация которого загружается из XML-файла.
 *
 * @author Roman Gvozdev
 */
public class XMLMenuFactory implements MenuFactory<String, JComponent> {
    private final Map<String, JComponent> menuStorage = new ConcurrentHashMap<>();

    /**
     * Создаёт фабрику элементов главного меню.
     * Загружает конфигурацию элементов меню из XML-файла.
     *
     * @param mainWindow    ссылка на главное окно приложения
     * @param xmlConfigFile XML-файл конфигурации меню
     */
    public XMLMenuFactory(MainWindow<?> mainWindow, File xmlConfigFile) {
        try {
            InputStream stream = new FileInputStream(xmlConfigFile);
            Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
            InputSource source = new InputSource(reader);
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            DefaultHandler handler = new XMLMenuHandler(mainWindow, menuStorage);
            parser.parse(source, handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Возвращает хранилище элементов главного меню.
     */
    public Map<String, JComponent> menuStorage() {
        // Возвращаемый ассоциативный массив является неглубокой копией внутреннего
        return new HashMap<>(menuStorage);
    }
}
