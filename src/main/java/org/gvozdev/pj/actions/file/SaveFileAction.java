package org.gvozdev.pj.actions.file;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gvozdev.pj.actions.PJAction;
import org.gvozdev.pj.ui.editor.Editor;
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
 * Действие сохранения файла изображения.
 *
 * @author Roman Gvozdev
 */
public class SaveFileAction extends PJAction {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    /**
     * Создаёт действие сохранения файла изображения.
     *
     * @param mainWindow ссылка на главное окно приложения
     */
    public SaveFileAction(MainWindow<?> mainWindow) {
        super(mainWindow);
    }

    /**
     * Обрабатывает действие.
     *
     * @param e событие, подлежащее обработке
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        var editorOptional = mainWindow.editorTabs().getSelectedEditor();
        if (editorOptional.isPresent()) {
            Optional<String> nameOptional = saveFile(editorOptional.get());
            nameOptional.ifPresent(s -> mainWindow.editorTabs().setSelectedTabTitle(s));
        }
    }

    /**
     * Сохраняет файл изображение.
     *
     * @param editor редактор изображения
     */
    public Optional<String> saveFile(Editor<?> editor) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
        File file = new File(editor.getName());
        chooser.setSelectedFile(file);

        if (mainWindow.frame() instanceof JFrame frame) {
            int res = chooser.showSaveDialog(frame);
            if (res == JFileChooser.APPROVE_OPTION) {
                file = chooser.getSelectedFile();
                try {
                    var img = editor.getImage();
                    if (img != null) {
                        ImageIO.write(img, "png", file);
                        var fileName = file.getName();
                        editor.setName(fileName);
                        LOGGER.info("Image '{}' saved", fileName);
                        return Optional.of(fileName);
                    }
                } catch (IOException e) {
                    LOGGER.error("Image write error:", e);
                }
            }
        }
        return Optional.empty();
    }
}
