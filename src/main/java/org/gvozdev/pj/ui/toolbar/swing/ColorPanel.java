package org.gvozdev.pj.ui.toolbar.swing;

import org.gvozdev.pj.actions.tools.SelectColorAction;
import org.gvozdev.pj.actions.tools.SwapColorsAction;
import org.gvozdev.pj.ui.main.MainWindow;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Панель выбора цвета инструмента рисования.
 *
 * @author Roman Gvozdev
 */
public class ColorPanel extends JPanel {
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
        var d = new Dimension(50, 50);

        primaryColorButton.setPreferredSize(d);
        primaryColorButton.setMaximumSize(d);
        primaryColorButton.setBackground(primaryColor);

        secondaryColorButton.setPreferredSize(d);
        secondaryColorButton.setMaximumSize(d);
        secondaryColorButton.setBackground(secondaryColor);

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
        swapColorButton.setAction(new SwapColorsAction(mainWindow));
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

    /**
     * Добавляет слушатели кнопок.
     */
    @Deprecated
    private void addListeners() {
        ActionListener colorButtonListener = new ColorButtonListener();
        primaryColorButton.addActionListener(colorButtonListener);
        secondaryColorButton.addActionListener(colorButtonListener);
    }

    /**
     * Слушатель кнопок основного и дополнительного цветов.
     */
    @Deprecated
    private class ColorButtonListener implements ActionListener {
        private final ColorDialog colorDialog = new ColorDialog();
        private final ChangeListener changePrimaryColor = this::changePrimaryColor;
        private final ChangeListener changeSecondaryColor = this::changeSecondaryColor;

        /**
         * Обрабатывает нажатие кнопки основного или дополнительного цвета.
         *
         * @param event событие нажатия кнопки
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            var selectionModel = colorDialog.colorChooser().getSelectionModel();
            // Удаление обработчиков изменения цвета
            selectionModel.removeChangeListener(changePrimaryColor);
            selectionModel.removeChangeListener(changeSecondaryColor);

            if (event.getSource() == primaryColorButton) {
                selectionModel.addChangeListener(changePrimaryColor);
            } else {
                selectionModel.addChangeListener(changeSecondaryColor);
            }
            colorDialog.setVisible(true);
        }

        /**
         * Обрабатывает событие изменения основного цвета.
         *
         * @param event событие изменения цвета
         */
        private void changePrimaryColor(ChangeEvent event) {
            var color = colorDialog.colorChooser().getColor();
            primaryColor = color;
            primaryColorButton.setBackground(color);
        }

        /**
         * Обрабатывает событие изменения дополнительного цвета.
         *
         * @param event событие изменения цвета
         */
        private void changeSecondaryColor(ChangeEvent event) {
            var color = colorDialog.colorChooser().getColor();
            secondaryColor = color;
            secondaryColorButton.setBackground(color);
        }
    }
}
