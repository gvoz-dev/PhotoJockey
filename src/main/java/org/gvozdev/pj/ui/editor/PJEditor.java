package org.gvozdev.pj.ui.editor;

import org.gvozdev.pj.ui.editor.handlers.DefaultPenHandler;
import org.gvozdev.pj.ui.toolbar.DrawingTools;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;

/**
 * Swing-реализация редактора изображений.
 *
 * @author Roman Gvozdev
 */
public class PJEditor implements Editor<JPanel> {
    public static final int DEFAULT_IMAGE_WIDTH = 960;
    public static final int DEFAULT_IMAGE_HEIGHT = 540;

    protected final JPanel editorPanel;
    protected DrawingTools<?> drawingTools;
    protected BufferedImage image;
    protected String name;


    /**
     * Создаёт Swing-реализацию редактора изображений.
     *
     * @param drawingTools ссылка на инструменты рисования
     */
    public PJEditor(DrawingTools<?> drawingTools) {
        this.drawingTools = drawingTools;
        this.editorPanel = new PJEditorPanel();
        this.name = DEFAULT_IMAGE_NAME;
    }

    /**
     * Инициализирует редактор изображений.
     */
    @Override
    public void init() {
        MouseAdapter mouseAdapter = new DefaultPenHandler(this, drawingTools);
        editorPanel.addMouseListener(mouseAdapter);
        editorPanel.addMouseMotionListener(mouseAdapter);
    }

    /**
     * Устанавливает изображение по умолчанию.
     *
     * @param width  ширина
     * @param height высота
     */
    @Override
    public void setDefaultImage(int width, int height) {
        if (width <= 0) {
            throw new IllegalArgumentException("Width <= 0");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("Height <= 0");
        }
        image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics g = image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
    }

    /**
     * Возвращает изображение.
     */
    @Override
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Устанавливает изображение.
     * Автоматически перерисовывает панель редактора.
     *
     * @param image изображение
     */
    @Override
    public void setImage(BufferedImage image) {
        this.image = image;
        editorPanel.repaint();
    }

    /**
     * Возвращает название изображения.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Устанавливает название изображения.
     *
     * @param name название
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Перерисовывает изображение.
     */
    @Override
    public void repaint() {
        editorPanel.repaint();
    }

    /**
     * Возвращает панель редактора Swing.
     */
    @Override
    public JPanel editorComponent() {
        return editorPanel;
    }

    /**
     * Панель редактора, который расширяет Swing-контейнер {@link JPanel}.
     */
    protected class PJEditorPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image == null) {
                g.drawString("No image...", 50, 50);
            } else {
                g.drawImage(image, 0, 0, this);
            }
        }

        @Override
        public Dimension getPreferredSize() {
            if (image == null) {
                return new Dimension(100, 100);
            }
            return new Dimension(image.getWidth(), image.getHeight());
        }
    }
}
