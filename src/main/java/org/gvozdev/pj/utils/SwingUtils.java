package org.gvozdev.pj.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.lang.invoke.MethodHandles;

/**
 * Утилиты для работы с UI-фреймворком Swing.
 *
 * @author Roman Gvozdev
 */
public class SwingUtils {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    private SwingUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Создаёт иконку {@link ImageIcon}.
     *
     * @param path путь к файлу
     */
    public static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = SwingUtils.class.getClassLoader().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            LOGGER.error("Couldn't find file: {}", path);
            return null;
        }
    }

    /**
     * Создаёт иконку {@link ImageIcon}.
     *
     * @param path   путь к файлу
     * @param width  ширина иконки
     * @param height высота иконки
     */
    public static ImageIcon createImageIcon(String path, int width, int height) {
        java.net.URL imgURL = SwingUtils.class.getClassLoader().getResource(path);
        if (imgURL != null) {
            Image img = new ImageIcon(imgURL).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } else {
            LOGGER.error("Couldn't find file: {}", path);
            return null;
        }
    }

    /**
     * Создаёт иконку {@link ImageIcon}.
     *
     * @param path        путь к файлу
     * @param description описание
     */
    public static ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = SwingUtils.class.getClassLoader().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            LOGGER.error("Couldn't find file: {}", path);
            return null;
        }
    }

    /**
     * Устанавливает размеры компонента.
     *
     * @param component компонент
     * @param width     ширина
     * @param height    высота
     */
    public static void setComponentSize(Component component, int width, int height) {
        var dimension = new Dimension(width, height);
        component.setPreferredSize(dimension);
        component.setMaximumSize(dimension);
    }
}
