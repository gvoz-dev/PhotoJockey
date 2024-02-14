package org.gvozdev.pj.ui.toolbar.swing;

import org.gvozdev.pj.actions.tools.SelectBrushAction;
import org.gvozdev.pj.actions.tools.SelectColorFillAction;
import org.gvozdev.pj.actions.tools.SelectEraserAction;
import org.gvozdev.pj.ui.main.MainWindow;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import static org.gvozdev.pj.utils.SwingUtils.createImageIcon;
import static org.gvozdev.pj.utils.SwingUtils.setComponentSize;

/**
 * Панель выбора инструмента рисования.
 *
 * @author Roman Gvozdev
 */
public class ToolsPanel extends JPanel {
    private static final int BUTTON_WIDTH = 46;
    private static final int BUTTON_HEIGHT = 46;

    private final ButtonGroup toolsGroup = new ButtonGroup();
    private final JToggleButton brushButton = new JToggleButton();
    private final JToggleButton eraserButton = new JToggleButton();
    private final JToggleButton fillButton = new JToggleButton();

    /**
     * Создаёт панель выбора инструмента.
     */
    private ToolsPanel() {
    }

    /**
     * Статический фабричный метод для создания панели выбора инструмента.
     *
     * @param mainWindow ссылка на главное окно приложения
     */
    public static ToolsPanel compose(MainWindow<?> mainWindow) {
        var toolsPanel = new ToolsPanel();
        toolsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        toolsPanel.addToolsButtons();
        toolsPanel.setToolsActions(mainWindow);
        return toolsPanel;
    }

    /**
     * Добавляет кнопки выбора инструмента на панель.
     */
    private void addToolsButtons() {
        var gridLayout = new GridLayout(0, 3, 5, 5);
        var gridPanel = new JPanel(gridLayout);

        setComponentSize(brushButton, BUTTON_WIDTH, BUTTON_HEIGHT);
        setComponentSize(eraserButton, BUTTON_WIDTH, BUTTON_HEIGHT);
        setComponentSize(fillButton, BUTTON_WIDTH, BUTTON_HEIGHT);

        toolsGroup.add(brushButton);
        toolsGroup.add(eraserButton);
        toolsGroup.add(fillButton);
        toolsGroup.setSelected(brushButton.getModel(), true);

        gridPanel.add(brushButton);
        gridPanel.add(eraserButton);
        gridPanel.add(fillButton);
        add(gridPanel);
    }

    /**
     * Устанавливает действия выбора инструмента.
     *
     * @param mainWindow ссылка на главное окно приложения
     */
    private void setToolsActions(MainWindow<?> mainWindow) {
        int iconWidth = BUTTON_WIDTH - 8;
        int iconHeight = BUTTON_HEIGHT - 8;

        Action brushAction = new SelectBrushAction(mainWindow);
        ImageIcon icon = createImageIcon("icons/tools/brush.png", iconWidth, iconHeight);
        brushAction.putValue(Action.LARGE_ICON_KEY, icon);
        brushButton.setAction(brushAction);

        Action eraserAction = new SelectEraserAction(mainWindow);
        icon = createImageIcon("icons/tools/eraser.png", iconWidth, iconHeight);
        eraserAction.putValue(Action.LARGE_ICON_KEY, icon);
        eraserButton.setAction(eraserAction);

        Action fillAction = new SelectColorFillAction(mainWindow);
        icon = createImageIcon("icons/tools/color-fill.png", iconWidth, iconHeight);
        fillAction.putValue(Action.LARGE_ICON_KEY, icon);
        fillButton.setAction(fillAction);
    }
}
