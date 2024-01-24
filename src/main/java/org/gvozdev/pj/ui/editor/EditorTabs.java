package org.gvozdev.pj.ui.editor;

import java.util.Optional;

/**
 * Интерфейс контейнера вкладок редакторов изображений.
 *
 * @param <C> тип компонента панели вкладок
 * @author Roman Gvozdev
 */
public interface EditorTabs<C> {
    /**
     * Добавляет вкладку редактора изображений.
     *
     * @param editor редактор
     */
    void addEditorTab(Editor<?> editor);

    /**
     * Удаляет выбранную вкладку редактора изображений.
     */
    void removeSelectedEditorTab();

    /**
     * Возвращает редактор изображений из выбранной вкладки.
     */
    Optional<Editor<?>> getSelectedEditor();

    /**
     * Устанавливает название выбранной вкладки редактора изображений.
     *
     * @param title название
     */
    void setSelectedTabTitle(String title);

    /**
     * Возвращает ui-компонент панели вкладок.
     */
    C tabsComponent();
}
