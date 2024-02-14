package org.gvozdev.pj.ui.toolbar.swing;

import javax.swing.BoundedRangeModel;
import javax.swing.BoxLayout;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import static org.gvozdev.pj.utils.SwingUtils.setComponentSize;

/**
 * Панель выбора размера инструмента рисования.
 *
 * @author Roman Gvozdev
 */
public class SizePanel extends JPanel {
    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 100;
    private static final int DEFAULT_SIZE = 5;
    private static final int STEP = 1;

    private final JSlider sizeSlider = new JSlider(JSlider.HORIZONTAL);
    private final JSpinner sizeSpinner = new JSpinner();
    private int toolSize = DEFAULT_SIZE;

    /**
     * Создаёт панель выбора размера.
     */
    private SizePanel() {
    }

    /**
     * Статический фабричный метод для создания панели выбора размера.
     */
    public static SizePanel compose() {
        var sizePanel = new SizePanel();
        sizePanel.setLayout(new BoxLayout(sizePanel, BoxLayout.X_AXIS));
        sizePanel.addSizeSlider();
        sizePanel.addSizeSpinner();
        return sizePanel;
    }

    /**
     * Добавляет слайдер изменения размера на панель.
     */
    private void addSizeSlider() {
        setComponentSize(sizeSlider, 100, 30);

        BoundedRangeModel model = new DefaultBoundedRangeModel(toolSize, 0, MIN_SIZE, MAX_SIZE);
        sizeSlider.setModel(model);

        sizeSlider.addChangeListener(event -> {
            toolSize = ((JSlider) event.getSource()).getValue();
            sizeSpinner.setValue(toolSize);
        });

        add(sizeSlider);
    }

    /**
     * Добавляет спиннер изменения размера на панель.
     */
    private void addSizeSpinner() {
        setComponentSize(sizeSpinner, 60, 30);

        SpinnerModel model = new SpinnerNumberModel(toolSize, MIN_SIZE, MAX_SIZE, STEP);
        sizeSpinner.setModel(model);

        sizeSpinner.addChangeListener(event -> {
            toolSize = (Integer) ((JSpinner) event.getSource()).getValue();
            sizeSlider.setValue(toolSize);
        });

        add(sizeSpinner);
    }

    /**
     * Возвращает размер инструмента рисования.
     */
    public int getToolSize() {
        return toolSize;
    }

    /**
     * Устанавливает размер инструмента рисования.
     */
    public void setToolSize(int size) {
        toolSize = size;
        sizeSlider.setValue(toolSize);
        sizeSpinner.setValue(toolSize);
    }
}
