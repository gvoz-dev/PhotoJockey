package org.gvozdev.pj.ui.toolbar.swing;

import org.gvozdev.pj.actions.tools.SelectColorAction;
import org.gvozdev.pj.actions.tools.SwapColorsAction;
import org.gvozdev.pj.ui.main.MainWindow;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;

import static org.gvozdev.pj.utils.SwingUtils.createImageIcon;
import static org.gvozdev.pj.utils.SwingUtils.setComponentSize;

/**
 * Панель выбора цвета инструмента рисования.
 *
 * @author Roman Gvozdev
 */
public class ColorPanel extends JPanel {
    private static final int COLOR_BUTTON_WIDTH = 56;
    private static final int COLOR_BUTTON_HEIGHT = 56;
    private static final int SWAP_BUTTON_WIDTH = 28;
    private static final int SWAP_BUTTON_HEIGHT = 28;

    private final JButton primaryColorButton = new JButton();
    private final JButton secondaryColorButton = new JButton();
    private final JButton swapColorButton = new JButton();
    private Color primaryColor = Color.BLACK;
    private Color secondaryColor = Color.WHITE;

    /**
     * Создаёт панель выбора цвета.
     */
    private ColorPanel() {
    }

    /**
     * Статический фабричный метод для создания панели выбора цвета.
     *
     * @param mainWindow ссылка на главное окно приложения
     */
    public static ColorPanel compose(MainWindow<?> mainWindow) {
        var colorPanel = new ColorPanel();
        colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.X_AXIS));
        colorPanel.addColorButtons();
        colorPanel.setColorActions(mainWindow);
        return colorPanel;
    }

    /**
     * Добавляет кнопки управления цветом на панель.
     */
    private void addColorButtons() {
        setComponentSize(primaryColorButton, COLOR_BUTTON_WIDTH, COLOR_BUTTON_HEIGHT);
        primaryColorButton.setBackground(primaryColor);

        setComponentSize(secondaryColorButton, COLOR_BUTTON_WIDTH, COLOR_BUTTON_HEIGHT);
        secondaryColorButton.setBackground(secondaryColor);

        setComponentSize(swapColorButton, SWAP_BUTTON_WIDTH, SWAP_BUTTON_HEIGHT);

        add(primaryColorButton);
        add(swapColorButton);
        add(secondaryColorButton);
    }

    /**
     * Устанавливает действия управления цветом.
     *
     * @param mainWindow ссылка на главное окно приложения
     */
    private void setColorActions(MainWindow<?> mainWindow) {
        primaryColorButton.setAction(new SelectColorAction.SelectPrimaryColorAction(mainWindow));
        secondaryColorButton.setAction(new SelectColorAction.SelectSecondaryColorAction(mainWindow));

        Action swapColorsAction = new SwapColorsAction(mainWindow);
        ImageIcon icon = createImageIcon("icons/tools/swap.png", SWAP_BUTTON_WIDTH - 6, SWAP_BUTTON_HEIGHT - 6);
        swapColorsAction.putValue(Action.LARGE_ICON_KEY, icon);
        swapColorButton.setAction(swapColorsAction);
    }

    /**
     * Возвращает основной цвет.
     */
    public Color getPrimaryColor() {
        return primaryColor;
    }

    /**
     * Устанавливает основной цвет.
     */
    public void setPrimaryColor(Color color) {
        primaryColor = color;
        primaryColorButton.setBackground(color);
    }

    /**
     * Возвращает дополнительный цвет.
     */
    public Color getSecondaryColor() {
        return secondaryColor;
    }

    /**
     * Устанавливает дополнительный цвет.
     */
    public void setSecondaryColor(Color color) {
        secondaryColor = color;
        secondaryColorButton.setBackground(color);
    }

    /**
     * Меняет местами основной и дополнительный цвета.
     */
    public void swapColors() {
        Color tmpColor = primaryColor;
        setPrimaryColor(secondaryColor);
        setSecondaryColor(tmpColor);
    }
}
