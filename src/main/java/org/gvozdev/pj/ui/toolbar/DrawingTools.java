package org.gvozdev.pj.ui.toolbar;

import org.gvozdev.pj.ui.main.MainWindow;

import java.awt.Color;

/**
 * Интерфейс инструментов рисования.
 *
 * @param <T> тип компонента панели инструментов рисования
 * @author Roman Gvozdev
 */
public interface DrawingTools<T> {
    /**
     * Инициализирует инструменты рисования.
     *
     * @param mainWindow ссылка на главное окно приложения
     */
    void init(MainWindow<?> mainWindow);

    /**
     * Возвращает основной цвет.
     */
    Color getPrimaryColor();

    /**
     * Устанавливает основной цвет.
     */
    void setPrimaryColor(Color color);

    /**
     * Возвращает дополнительный цвет.
     */
    Color getSecondaryColor();

    /**
     * Устанавливает дополнительный цвет.
     */
    void setSecondaryColor(Color color);

    /**
     * Меняет местами основной и дополнительный цвета.
     */
    void swapColors();

    /**
     * Возвращает размер инструмента рисования.
     */
    int getToolSize();

    /**
     * Устанавливает размер инструмента рисования.
     */
    void setToolSize(int size);

    /**
     * Возвращает ui-компонент панели инструментов рисования.
     */
    T toolBarComponent();
}
