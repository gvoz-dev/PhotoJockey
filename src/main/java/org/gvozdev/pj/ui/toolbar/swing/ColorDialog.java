package org.gvozdev.pj.ui.toolbar.swing;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Frame;

/**
 * Диалоговое окно выбора цвета.
 *
 * @author Roman Gvozdev
 */
public class ColorDialog extends JDialog {
    private final JColorChooser chooser = new JColorChooser();

    /**
     * Создаёт диалоговое окно выбора цвета с заголовком по умолчанию.
     */
    public ColorDialog() {
        this("Color");
    }

    /**
     * Создаёт диалоговое окно выбора цвета.
     *
     * @param title заголовок окна
     */
    public ColorDialog(String title) {
        super((Frame) null, title, false);
        chooser.setPreviewPanel(new JPanel()); // Замена стандартной панели превью на пустую
        add(chooser);
        pack();
    }

    /**
     * Возвращает {@link JColorChooser}.
     */
    public JColorChooser colorChooser() {
        return chooser;
    }
}
