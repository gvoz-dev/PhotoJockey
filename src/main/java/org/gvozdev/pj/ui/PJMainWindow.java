package org.gvozdev.pj.ui;

import org.gvozdev.pj.ui.editor.PJEditorTabs;
import org.gvozdev.pj.ui.menu.PJMainMenu;
import org.gvozdev.pj.ui.tools.PJTools;

import javax.swing.JFrame;
import java.awt.BorderLayout;

/**
 * Класс главного окна приложения PhotoJockey.
 *
 * @author Roman Gvozdev
 */
public class PJMainWindow {
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;

    private int width;
    private int height;

    private JFrame frame;
    private final PJMainMenu mainMenu;
    private final PJEditorTabs editorTabs;
    private final PJTools tools;

    /**
     * Создаёт главное окно с размерами по умолчанию.
     *
     * @param mainMenu   ссылка на главное меню
     * @param editorTabs ссылка на вкладки редактора изображений
     * @param tools      ссылка на панель инструментов
     */
    public PJMainWindow(PJMainMenu mainMenu,
                        PJEditorTabs editorTabs,
                        PJTools tools) {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT, mainMenu, editorTabs, tools);
    }

    /**
     * Создаёт главное окно с заданными размерами.
     *
     * @param width      ширина окна
     * @param height     высота окна
     * @param mainMenu   ссылка на главное меню
     * @param editorTabs ссылка на вкладки редактора изображений
     * @param tools      ссылка на панель инструментов
     */
    public PJMainWindow(int width, int height,
                        PJMainMenu mainMenu,
                        PJEditorTabs editorTabs,
                        PJTools tools) {
        this.width = width;
        this.height = height;
        this.mainMenu = mainMenu;
        this.editorTabs = editorTabs;
        this.tools = tools;
    }

    /**
     * Отображает главное окно.
     */
    public void show() {
        frame = new JFrame("PhotoJockey");
        frame.setSize(width, height);

        var layout = new BorderLayout();
        frame.setLayout(layout);
        frame.add(editorTabs, BorderLayout.CENTER);
        frame.add(tools.getToolBar(), BorderLayout.EAST);

        mainMenu.init(this);
        frame.setJMenuBar(mainMenu.getMenuBar());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Возвращает ширину главного окна.
     *
     * @return ширина окна
     */
    public int getWidth() {
        width = frame.getWidth();
        return width;
    }

    /**
     * Устанавливает ширину главного окна.
     *
     * @param width ширина окна
     */
    public void setWidth(int width) {
        this.width = width;
        frame.setSize(this.width, this.height);
    }

    /**
     * Возвращает высоту главного окна.
     *
     * @return высота окна
     */
    public int getHeight() {
        height = frame.getHeight();
        return height;
    }

    /**
     * Устанавливает высоту главного окна.
     *
     * @param height высота окна
     */
    public void setHeight(int height) {
        this.height = height;
        frame.setSize(this.width, this.height);
    }

    /**
     * Возвращает фрейм главного окна.
     *
     * @return фрейм окна
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Возвращает вкладки редакторов.
     *
     * @return вкладки редакторов
     */
    public PJEditorTabs getEditorTabs() {
        return editorTabs;
    }

    /**
     * Возвращает панель инструментов.
     *
     * @return панель инструментов
     */
    public PJTools getTools() {
        return tools;
    }
}
