package org.gvozdev.pj.ui.toolbar;

import org.gvozdev.pj.ui.main.MainWindow;
import org.gvozdev.pj.ui.toolbar.swing.ColorPanel;
import org.gvozdev.pj.ui.toolbar.swing.SizePanel;
import org.gvozdev.pj.ui.toolbar.swing.ToolsPanel;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JToolBar;
import java.awt.Color;

/**
 * Swing-реализация инструментов рисования.
 *
 * @author Roman Gvozdev
 */
public class PJDrawingTools implements DrawingTools<JToolBar> {
    private JToolBar toolBar;
    private ColorPanel colorPanel;
    private ToolsPanel toolsPanel;
    private SizePanel sizePanel;

    /**
     * Создаёт Swing-реализацию инструментов рисования.
     */
    public PJDrawingTools() {
    }

    /**
     * Инициализирует инструменты рисования.
     *
     * @param mainWindow ссылка на главное окно приложения
     */
    @Override
    public void init(MainWindow<?> mainWindow) {
        toolBar = new JToolBar("Tools", JToolBar.VERTICAL);
        toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.Y_AXIS));
        toolBar.setFloatable(true);

        int strutHeight = 25;
        toolBar.add(Box.createVerticalStrut(strutHeight));
        colorPanel = ColorPanel.compose(mainWindow);
        toolBar.add(colorPanel);

        toolBar.add(Box.createVerticalStrut(strutHeight));
        sizePanel = SizePanel.compose();
        toolBar.add(sizePanel);

        toolBar.add(Box.createVerticalStrut(strutHeight));
        toolsPanel = ToolsPanel.compose(mainWindow);
        toolBar.add(toolsPanel);
    }

    /**
     * Возвращает основной цвет.
     */
    @Override
    public Color getPrimaryColor() {
        return colorPanel.getPrimaryColor();
    }

    /**
     * Устанавливает основной цвет.
     */
    @Override
    public void setPrimaryColor(Color color) {
        colorPanel.setPrimaryColor(color);
    }

    /**
     * Возвращает дополнительный цвет.
     */
    @Override
    public Color getSecondaryColor() {
        return colorPanel.getSecondaryColor();
    }

    /**
     * Устанавливает дополнительный цвет.
     */
    @Override
    public void setSecondaryColor(Color color) {
        colorPanel.setSecondaryColor(color);
    }

    /**
     * Меняет местами основной и дополнительный цвета.
     */
    @Override
    public void swapColors() {
        colorPanel.swapColors();
    }

    /**
     * Возвращает размер инструмента рисования.
     */
    @Override
    public int getToolSize() {
        return sizePanel.getToolSize();
    }

    /**
     * Устанавливает размер инструмента рисования.
     */
    @Override
    public void setToolSize(int size) {
        sizePanel.setToolSize(size);
    }

    /**
     * Возвращает Swing-панель инструментов рисования.
     */
    @Override
    public JToolBar toolBarComponent() {
        return toolBar;
    }
}
