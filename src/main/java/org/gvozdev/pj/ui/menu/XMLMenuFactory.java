package org.gvozdev.pj.ui.menu;

import org.gvozdev.pj.ui.PJMainWindow;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.JComponent;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс фабрики элементов главного меню, конфигурация которого загружается из XML-файла.
 *
 * @author Roman Gvozdev
 */
public class XMLMenuFactory {
    private final InputSource source;
    private final SAXParser parser;
    private final DefaultHandler handler;
    private final Map<String, JComponent> menuStorage = new HashMap<>();

    /**
     * Создаёт фабрику элементов главного меню.
     *
     * @param mainWindow ссылка на главное окно приложения
     * @param xmlName    имя XML-файла из ресурсов
     */
    public XMLMenuFactory(PJMainWindow mainWindow, String xmlName) {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(xmlName);
        if (stream == null) {
            throw new IllegalArgumentException("XML resource '%s' not found".formatted(xmlName));
        }
        try {
            Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
            source = new InputSource(reader);
            parser = SAXParserFactory.newInstance().newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        }
        handler = new XMLMenuHandler(mainWindow, menuStorage);
    }

    /**
     * Парсит XML-файл, загружая конфигурацию элементов главного меню.
     */
    public synchronized void parse() throws IOException, SAXException {
        parser.parse(source, handler);
    }

    /**
     * Возвращает хранилище элементов главного меню.
     */
    public synchronized Map<String, JComponent> getMenuStorage() {
        return menuStorage;
    }
}
