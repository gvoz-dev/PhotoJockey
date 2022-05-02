package ui.editor;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PJEditorTabs extends JTabbedPane {
  private final List<PJEditor> editors;

  public PJEditorTabs() {
    editors = new ArrayList<>();
  }

  public void addEditorTab(PJEditor editor) {
    int nextTabIndex = getSelectedIndex() + 1;
    JScrollPane scroller = new JScrollPane(editor);
    insertTab(editor.getFileName(), null, scroller, null, nextTabIndex);
    editors.add(nextTabIndex, editor);
  }

  public void removeSelectedEditorTab() {
    int index = getSelectedIndex();
    if (index != -1) {
      remove(index);
      editors.remove(index);
    }
  }

  public Optional<PJEditor> getSelectedEditor() {
    int index = getSelectedIndex();
    if (index != -1) {
      return Optional.of(editors.get(index));
    }
    return Optional.empty();
  }

  public void setSelectedTabTitle(String title) {
    int index = getSelectedIndex();
    if (index != -1) {
      setTitleAt(index, title);
    }
  }
}
