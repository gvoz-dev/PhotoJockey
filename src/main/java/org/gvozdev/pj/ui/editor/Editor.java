package org.gvozdev.pj.ui.editor;

import java.awt.image.BufferedImage;

/**
 * Интерфейс редактора изображений.
 *
 * @param <C> тип компонента редактора
 * @author Roman Gvozdev
 */
public interface Editor<C> {
    String DEFAULT_IMAGE_NAME = "temp.png";

    /**
     * Инициализирует редактор изображений.
     */
    void init();

    /**
     * Устанавливает изображение по умолчанию.
     *
     * @param width  ширина
     * @param height высота
     */
    void setDefaultImage(int width, int height);

    /**
     * Возвращает изображение.
     */
    BufferedImage getImage();

    /**
     * Устанавливает изображение.
     *
     * @param image изображение
     */
    void setImage(BufferedImage image);

    /**
     * Возвращает название изображения.
     */
    String getName();

    /**
     * Устанавливает название изображения.
     *
     * @param name название
     */
    void setName(String name);

    /**
     * Перерисовывает изображение.
     */
    void repaint();

    /**
     * Возвращает ui-компонент редактора.
     */
    C editorComponent();
}
