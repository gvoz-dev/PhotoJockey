package org.gvozdev.pj.processing.filters;

import java.awt.image.BufferedImage;

/**
 * Интерфейс фильтра изображения.
 *
 * @author Roman Gvozdev
 */
@FunctionalInterface
public interface ImageFilter {
    /**
     * Применяет к исходному изображению алгоритм фильтрации.
     *
     * @param image исходное изображение
     * @return отфильтрованное изображение
     */
    BufferedImage filter(BufferedImage image);
}
