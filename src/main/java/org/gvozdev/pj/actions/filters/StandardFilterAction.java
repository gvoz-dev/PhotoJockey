package org.gvozdev.pj.actions.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gvozdev.pj.PJApp;
import org.gvozdev.pj.actions.PJAction;
import org.gvozdev.pj.processing.filters.standard.PJFilter;
import org.gvozdev.pj.ui.editor.PJEditor;

import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;

public class StandardFilterAction extends PJAction {
  private static final Logger logger = LogManager.getLogger(StandardFilterAction.class);

  private static final String PACKAGE = "org.gvozdev.pj.processing.filters.standard.";
  private static final String CLASS_PREFIX = "PJ";

  public StandardFilterAction(PJApp app) {
    super(app);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    var editorOptional = app.getTabs().getSelectedEditor();
    editorOptional.ifPresent(pjEditor -> useFilter(pjEditor, e.getActionCommand()));
  }

  public void useFilter(PJEditor editor, String filterName) {
    try {
      var className = PACKAGE + CLASS_PREFIX + filterName;
      PJFilter pjFilter = (PJFilter) (Class.forName(className)).getConstructor().newInstance();
      var img = editor.getImg();
      editor.setImg(pjFilter.filter(editor, img));
      editor.repaint();
      logger.info(String.format("%s filter applied to '%s'.", filterName, editor.getFileName()));
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
             ClassNotFoundException e) {
      logger.error("Filter error:", e);
    }
  }
}
