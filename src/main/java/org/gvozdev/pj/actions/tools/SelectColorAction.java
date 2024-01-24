package org.gvozdev.pj.actions.tools;

import org.gvozdev.pj.actions.PJAction;
import org.gvozdev.pj.ui.main.MainWindow;
import org.gvozdev.pj.ui.toolbar.swing.ColorDialog;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

/**
 * Действие выбора цвета.
 *
 * @author Roman Gvozdev
 */
public sealed class SelectColorAction extends PJAction
        permits SelectColorAction.SelectPrimaryColorAction, SelectColorAction.SelectSecondaryColorAction {
    protected final ColorDialog colorDialog;

    /**
     * Создаёт действие выбора цвета.
     *
     * @param title         заголовок диалогового окна выбора цвета
     * @param colorConsumer потребитель цвета
     */
    public SelectColorAction(String title, Consumer<Color> colorConsumer) {
        this.colorDialog = new ColorDialog(title);
        var selectionModel = colorDialog.colorChooser().getSelectionModel();
        selectionModel.addChangeListener(event -> {
            var color = colorDialog.colorChooser().getColor();
            colorConsumer.accept(color);
        });
    }

    /**
     * Обрабатывает действие.
     *
     * @param e событие, подлежащее обработке
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        colorDialog.setVisible(true);
    }

    /**
     * Действие выбора основного цвета.
     */
    public static final class SelectPrimaryColorAction extends SelectColorAction {
        /**
         * Создаёт действие выбора основного цвета.
         *
         * @param mainWindow ссылка на главное окно приложения
         */
        public SelectPrimaryColorAction(MainWindow<?> mainWindow) {
            super("Primary color", mainWindow.drawingTools()::setPrimaryColor);
        }
    }

    /**
     * Действие выбора дополнительного цвета.
     */
    public static final class SelectSecondaryColorAction extends SelectColorAction {
        /**
         * Создаёт действие выбора дополнительного цвета.
         *
         * @param mainWindow ссылка на главное окно приложения
         */
        public SelectSecondaryColorAction(MainWindow<?> mainWindow) {
            super("Secondary color", mainWindow.drawingTools()::setSecondaryColor);
        }
    }
}
