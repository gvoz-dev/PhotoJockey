package org.gvozdev.pj.ui.editor;

import org.gvozdev.pj.ui.editor.handlers.DefaultPenHandler;
import org.gvozdev.pj.ui.tools.PJTools;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;

public class PJEditor extends JPanel {
    public static final int DEFAULT_IMG_WIDTH = 960;
    public static final int DEFAULT_IMG_HEIGHT = 540;
    public static final String DEFAULT_FILE_NAME = "temp.png";

    private BufferedImage img;
    private String fileName;

    public PJEditor(PJTools tools) {
        fileName = DEFAULT_FILE_NAME;
        MouseAdapter mouseAdapter = new DefaultPenHandler(this, tools);
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    public void setDefaultImage(int width, int height) {
        if (width <= 0) {
            throw new IllegalArgumentException("width <= 0");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("height <= 0");
        }
        img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics g = img.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
        repaint();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img == null) {
            g.drawString("No image...", 50, 50);
        } else {
            g.drawImage(img, 0, 0, this);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        if (img == null) {
            return new Dimension(100, 100);
        }
        return new Dimension(img.getWidth(), img.getHeight());
    }
}
