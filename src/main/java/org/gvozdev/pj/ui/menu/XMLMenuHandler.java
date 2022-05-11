package org.gvozdev.pj.ui.menu;

import org.gvozdev.pj.actions.PJAction;
import org.gvozdev.pj.PJApp;
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
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;

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

  private final PJApp app;
  private final Map<String, JComponent> menuStorage;
  private final Deque<JMenu> menus = new LinkedList<>();
  private JMenuBar menuBar;

  public XMLMenuHandler(PJApp app, Map<String, JComponent> menuStorage) {
    this.app = app;
    this.menuStorage = menuStorage;
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) {
    switch (qName) {
      case TAG_MENU_BAR -> handleMenuBar(attributes);
      case TAG_MENU -> handleMenu(attributes);
      case TAG_MENU_ITEM -> handleMenuItem(attributes);
      case TAG_SEPARATOR -> handleSeparator();
    }
  }

  @Override
  public void endElement(String uri, String localName, String qName) {
    if (qName.equals(TAG_MENU)) {
      menus.pop();
    }
  }

  protected void handleMenuBar(Attributes attributes) {
    menuBar = new JMenuBar();
    String id = attributes.getValue(ATTRIBUTE_ID);
    menuStorage.put(id, menuBar);
  }

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

  protected void handleMenuItem(Attributes attributes) {
    JMenuItem menuItem = new JMenuItem();
    String id = attributes.getValue(ATTRIBUTE_ID);
    menuStorage.put(id, menuItem);
    adjustProperties(menuItem, attributes);

    if (menus.peek() != null) {
      menus.peek().add(menuItem);
    }
  }

  protected void handleSeparator() {
    if (menus.peek() != null) {
      menus.peek().addSeparator();
    }
  }

  private void adjustProperties(JMenuItem menuItem, Attributes attributes) {
    String actionClass = attributes.getValue(ATTRIBUTE_ACTION);
    String text = attributes.getValue(ATTRIBUTE_TEXT);
    String iconPath = attributes.getValue(ATTRIBUTE_ICON);
    String description = attributes.getValue(ATTRIBUTE_DESCRIPTION);
    String accelerator = attributes.getValue(ATTRIBUTE_ACCELERATOR);
    String mnemonic = attributes.getValue(ATTRIBUTE_MNEMONIC);
    String enabled = attributes.getValue(ATTRIBUTE_ENABLED);

    PJAction action = loadAction(actionClass);
    if (text != null) {
      action.putValue(Action.NAME, text);
    }
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
    if (description != null) {
      action.putValue(Action.SHORT_DESCRIPTION, description);
    }
    if (accelerator != null) {
      action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(accelerator));
    }
    if (mnemonic != null) {
      int ch = mnemonic.charAt(0);
      action.putValue(Action.MNEMONIC_KEY, KeyEvent.getExtendedKeyCodeForChar(ch));
    }
    if (enabled != null) {
      action.setEnabled(Boolean.parseBoolean(enabled));
    }
    menuItem.setAction(action);
  }

  private PJAction loadAction(String name) {
    PJAction action;
    if (name != null) {
      try {
        action = (PJAction) (Class.forName(name)).getConstructor(PJApp.class).newInstance(app);
      } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
               ClassNotFoundException e) {
        throw new RuntimeException(e);
      }
    } else {
      action = new PJAction();
    }
    return action;
  }
}
