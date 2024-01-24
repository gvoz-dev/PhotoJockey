package org.gvozdev.pj.ui.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gvozdev.pj.ui.editor.EditorTabs;
import org.gvozdev.pj.ui.menu.MainMenu;
import org.gvozdev.pj.ui.toolbar.DrawingTools;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.awt.BorderLayout;
import java.lang.invoke.MethodHandles;

/**
 * Класс главного окна приложения PhotoJockey.
 *
 * @author Roman Gvozdev
 */
public class PJMainWindow implements MainWindow<JFrame> {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;

    private int width;
    private int height;

    private JFrame frame;
    private final MainMenu<?, ?> mainMenu;
    private final EditorTabs<?> editorTabs;
    private final DrawingTools<?> tools;

    /**
     * Создаёт главное окно с размерами по умолчанию.
     *
     * @param mainMenu   ссылка на главное меню
     * @param editorTabs ссылка на вкладки редактора изображений
     * @param tools      ссылка на панель инструментов
     */
    public PJMainWindow(MainMenu<?, ?> mainMenu,
                        EditorTabs<?> editorTabs,
                        DrawingTools<?> tools) {
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
                        MainMenu<?, ?> mainMenu,
                        EditorTabs<?> editorTabs,
                        DrawingTools<?> tools) {
        this.width = width;
        this.height = height;
        this.mainMenu = mainMenu;
        this.editorTabs = editorTabs;
        this.tools = tools;
    }

    /**
     * Отображает главное окно.
     */
    @Override
    public void show() {
        frame = new JFrame("PhotoJockey");
        frame.setSize(width, height);

        var layout = new BorderLayout();
        frame.setLayout(layout);

        if (editorTabs.tabsComponent() instanceof JComponent tabsComponent) {
            frame.add(tabsComponent, BorderLayout.CENTER);
        } else {
            LOGGER.error("This tabs component type is not supported");
        }

        tools.init(this);
        if (tools.toolBarComponent() instanceof JComponent toolbarComponent) {
            frame.add(toolbarComponent, BorderLayout.EAST);
        } else {
            LOGGER.error("This toolbar type is not supported");
        }

        mainMenu.init(this);
        if (mainMenu.menuBar() instanceof JMenuBar menuComponent) {
            frame.setJMenuBar(menuComponent);
        } else {
            LOGGER.error("This menu type is not supported");
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Возвращает ширину главного окна.
     *
     * @return ширина окна
     */
    @Override
    public int getWidth() {
        width = frame.getWidth();
        return width;
    }

    /**
     * Устанавливает ширину главного окна.
     *
     * @param width ширина окна
     */
    @Override
    public void setWidth(int width) {
        this.width = width;
        frame.setSize(this.width, this.height);
    }

    /**
     * Возвращает высоту главного окна.
     *
     * @return высота окна
     */
    @Override
    public int getHeight() {
        height = frame.getHeight();
        return height;
    }

    /**
     * Устанавливает высоту главного окна.
     *
     * @param height высота окна
     */
    @Override
    public void setHeight(int height) {
        this.height = height;
        frame.setSize(this.width, this.height);
    }

    /**
     * Возвращает фрейм главного окна.
     *
     * @return фрейм окна
     */
    @Override
    public JFrame frame() {
        return frame;
    }

    /**
     * Возвращает главное меню приложения.
     *
     * @return главное меню
     */
    @Override
    public MainMenu<?, ?> mainMenu() {
        return mainMenu;
    }

    /**
     * Возвращает вкладки редакторов.
     *
     * @return вкладки редакторов
     */
    @Override
    public EditorTabs<?> editorTabs() {
        return editorTabs;
    }

    /**
     * Возвращает панель инструментов.
     *
     * @return панель инструментов
     */
    @Override
    public DrawingTools<?> drawingTools() {
        return tools;
    }
}
