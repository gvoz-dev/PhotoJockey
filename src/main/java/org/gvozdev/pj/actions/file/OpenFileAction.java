package org.gvozdev.pj.actions.file;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gvozdev.pj.actions.PJAction;
import org.gvozdev.pj.ui.editor.PJEditor;
import org.gvozdev.pj.ui.main.MainWindow;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.Optional;

/**
 * Действие открытия файла изображения.
 *
 * @author Roman Gvozdev
 */
public class OpenFileAction extends PJAction {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    /**
     * Создаёт действие открытия файла изображения.
     *
     * @param mainWindow ссылка на главное окно приложения
     */
    public OpenFileAction(MainWindow<?> mainWindow) {
        super(mainWindow);
    }

    /**
     * Обрабатывает действие.
     *
     * @param e событие, подлежащее обработке
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Optional<PJEditor> editorOptional = openFile();
        editorOptional.ifPresent(pjEditor -> mainWindow.editorTabs().addEditorTab(pjEditor));
    }

    /**
     * Открывает файл изображения. Возвращает {@link Optional} редактора с открытым файлом.
     */
    protected Optional<PJEditor> openFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        if (mainWindow.frame() instanceof JFrame frame) {
            int res = chooser.showOpenDialog(frame);
            if (res == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                try {
                    var img = ImageIO.read(file);
                    if (img != null) {
                        var fileName = file.getName();
                        PJEditor editor = new PJEditor(mainWindow.drawingTools());
                        editor.init();
                        editor.setImage(img);
                        editor.setName(fileName);
                        LOGGER.info("Image '{}' loaded", fileName);
                        return Optional.of(editor);
                    }
                } catch (IOException e) {
                    LOGGER.error("Image read error:", e);
                }
            }
        }
        return Optional.empty();
    }
}
