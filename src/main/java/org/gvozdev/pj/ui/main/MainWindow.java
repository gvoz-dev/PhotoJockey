package org.gvozdev.pj.ui.main;

import org.gvozdev.pj.ui.editor.EditorTabs;
import org.gvozdev.pj.ui.menu.MainMenu;
import org.gvozdev.pj.ui.toolbar.DrawingTools;

/**
 * Интерфейс главного окна приложения PhotoJockey.
 *
 * @param <F> тип фрейма главного окна
 * @author Roman Gvozdev
 */
public interface MainWindow<F> {
    /**
     * Отображает главное окно.
     */
    void show();

    /**
     * Возвращает ширину главного окна.
     *
     * @return ширина окна
     */
    int getWidth();

    /**
     * Устанавливает ширину главного окна.
     *
     * @param width ширина окна
     */
    void setWidth(int width);

    /**
     * Возвращает высоту главного окна.
     *
     * @return высота окна
     */
    int getHeight();

    /**
     * Устанавливает высоту главного окна.
     *
     * @param height высота окна
     */
    void setHeight(int height);

    /**
     * Возвращает фрейм главного окна.
     *
     * @return фрейм окна
     */
    F frame();

    /**
     * Возвращает главное меню приложения.
     *
     * @return главное меню
     */
    MainMenu<?, ?> mainMenu();

    /**
     * Возвращает вкладки редакторов.
     *
     * @return вкладки редакторов
     */
    EditorTabs<?> editorTabs();

    /**
     * Возвращает инструменты рисования.
     *
     * @return инструменты рисования
     */
    DrawingTools<?> drawingTools();
}
