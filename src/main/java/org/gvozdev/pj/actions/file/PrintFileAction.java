package org.gvozdev.pj.actions.file;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gvozdev.pj.actions.PJAction;
import org.gvozdev.pj.ui.PJMainWindow;
import org.gvozdev.pj.ui.editor.PJEditor;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class PrintFileAction extends PJAction {
    private static final Logger logger = LogManager.getLogger(PrintFileAction.class);

    public PrintFileAction(PJMainWindow app) {
        super(app);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var editorOptional = app.getEditorTabs().getSelectedEditor();
        editorOptional.ifPresent(this::printFile);
    }

    public void printFile(PJEditor editor) {
        PrinterJob printerJob = PrinterJob.getPrinterJob();

        if (printerJob.printDialog()) {
            PageFormat pageFormat = printerJob.pageDialog(printerJob.defaultPage());

            printerJob.setPrintable((graphics, pf, pageIndex) -> {
                if (pageIndex != 0) {
                    return Printable.NO_SUCH_PAGE;
                } else {
                    Graphics2D g2d = (Graphics2D) graphics;
                    g2d.translate(pf.getImageableX(), pf.getImageableY());
                    var img = editor.getImg();
                    if (img != null) {
                        var s = pf.getImageableWidth() / img.getWidth();
                        g2d.scale(s, s);
                        g2d.drawImage(img, 0, 0, null);
                    } else {
                        g2d.drawString("Oops: img == null!", 10, 10);
                    }
                    return Printable.PAGE_EXISTS;
                }
            }, pageFormat);

            try {
                printerJob.print();
                logger.info(String.format("Image '%s' printed.", editor.getFileName()));
            } catch (PrinterException e) {
                logger.error("Image print error:", e);
            }
        }
    }
}
