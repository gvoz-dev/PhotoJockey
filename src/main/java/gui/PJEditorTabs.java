package gui;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import java.util.ArrayList;
import java.util.List;

public class PJEditorTabs extends JTabbedPane {
  private final List<PJEditor> editors;

  public PJEditorTabs() {
    editors = new ArrayList<>();
  }

  public void addEditor(PJEditor editor) {
    int index = getSelectedIndex() + 1; // next tab index
    editors.add(index, editor);
    JScrollPane scroller = new JScrollPane(editor);
    insertTab(editor.getFileName(), null, scroller, null, index);
  }

  public void removeSelectedEditor() {
    int index = getSelectedIndex();
    if (index != -1) {
      editors.remove(index);
      remove(index);
    }
  }

  public PJEditor getSelectedEditor() {
    int index = getSelectedIndex();
    if (index != -1) {
      return editors.get(index);
    }
    return null;
  }

  public void updateTitle(String newTitle) {
    int index = getSelectedIndex();
    if (index != -1) {
      setTitleAt(index, newTitle);
    }
  }
}
