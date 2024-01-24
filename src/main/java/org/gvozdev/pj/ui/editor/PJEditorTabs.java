package org.gvozdev.pj.ui.editor;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Swing-реализация контейнера вкладок редакторов изображений.
 *
 * @author Roman Gvozdev
 */
public class PJEditorTabs implements EditorTabs<JTabbedPane> {
    protected final JTabbedPane tabbedPane;
    protected final List<Editor<?>> editors;

    /**
     * Создаёт Swing-реализацию контейнера вкладок редакторов.
     */
    public PJEditorTabs() {
        tabbedPane = new JTabbedPane();
        editors = new ArrayList<>();
    }

    /**
     * Добавляет вкладку редактора изображений.
     *
     * @param editor редактор
     */
    @Override
    public void addEditorTab(Editor<?> editor) {
        if (editor instanceof PJEditor pjEditor) {
            int nextTabIndex = tabbedPane.getSelectedIndex() + 1;
            JScrollPane scrollPane = new JScrollPane(pjEditor.editorComponent());
            tabbedPane.insertTab(editor.getName(), null, scrollPane, null, nextTabIndex);
            editors.add(nextTabIndex, editor);
        } else {
            throw new IllegalArgumentException("Incompatible editor");
        }
    }

    /**
     * Удаляет выбранную вкладку редактора изображений.
     */
    @Override
    public void removeSelectedEditorTab() {
        int index = tabbedPane.getSelectedIndex();
        if (index != -1) {
            tabbedPane.remove(index);
            editors.remove(index);
        }
    }

    /**
     * Возвращает выбранную вкладку редактора изображений.
     */
    @Override
    public Optional<Editor<?>> getSelectedEditor() {
        int index = tabbedPane.getSelectedIndex();
        if (index != -1) {
            return Optional.of(editors.get(index));
        }
        return Optional.empty();
    }

    /**
     * Устанавливает название выбранной вкладки редактора изображений.
     *
     * @param title название
     */
    @Override
    public void setSelectedTabTitle(String title) {
        int index = tabbedPane.getSelectedIndex();
        if (index != -1) {
            tabbedPane.setTitleAt(index, title);
        }
    }

    /**
     * Возвращает панель вкладок Swing.
     */
    @Override
    public JTabbedPane tabsComponent() {
        return tabbedPane;
    }
}
