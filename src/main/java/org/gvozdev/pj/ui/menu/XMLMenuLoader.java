package org.gvozdev.pj.ui.menu;

import org.gvozdev.pj.PJApp;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.JComponent;
import javax.swing.JMenuBar;
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

public class XMLMenuLoader {
  private final InputSource source;
  private final SAXParser parser;
  private final DefaultHandler handler;
  private final Map<String, JComponent> menuStorage = new HashMap<>();

  public XMLMenuLoader(PJApp app, InputStream stream) {
    try {
      Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
      source = new InputSource(reader);
      parser = SAXParserFactory.newInstance().newSAXParser();
    } catch (ParserConfigurationException | SAXException e) {
      throw new RuntimeException(e);
    }
    handler = new XMLMenuHandler(app, menuStorage);
  }

  public void parse() throws IOException, SAXException {
    parser.parse(source, handler);
  }

  public JMenuBar getMenuBar(String name) {
    return (JMenuBar) menuStorage.get(name);
  }
}
