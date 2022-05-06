package org.gvozdev.pj.ui.editor;

import org.gvozdev.pj.ui.editor.handlers.DefaultPenHandler;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
  private Color brushColor;
  private int brushSize;

  public PJEditor() {
    brushColor = Color.BLACK;
    fileName = DEFAULT_FILE_NAME;
    brushSize = 5;
    MouseAdapter mouseAdapter = new DefaultPenHandler(this);
    addMouseListener(mouseAdapter);
    addMouseMotionListener(mouseAdapter);
  }

  public void setDefaultImage(int width, int height) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("width <= 0 or height <= 0!");
    }
    img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics g = img.getGraphics();
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, width, height);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(img, 0, 0, this);
  }

  public void changeBrushColor() {
    JColorChooser chooser = new JColorChooser();
    chooser.getSelectionModel().addChangeListener(e -> setBrushColor(chooser.getColor()));
    ColorDialog dialog = new ColorDialog(null, "Select color", chooser);
    dialog.setVisible(true);
  }

  public void changeBrushSize() {
    var size = JOptionPane.showInputDialog("Enter brush size");
    try {
      setBrushSize(Integer.parseInt(size));
    } catch (NumberFormatException e) {
      System.err.println("Incorrect input");
    }
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

  public Color getBrushColor() {
    return brushColor;
  }

  public void setBrushColor(Color brushColor) {
    this.brushColor = brushColor;
  }

  public int getBrushSize() {
    return brushSize;
  }

  public void setBrushSize(int brushSize) {
    this.brushSize = brushSize;
  }

  public Dimension getPreferredSize() {
    if (img == null) {
      return new Dimension(100, 100);
    }
    return new Dimension(img.getWidth(this), img.getHeight(this));
  }
}

class ColorDialog extends JDialog {
  public ColorDialog(JFrame owner, String title, JColorChooser chooser) {
    super(owner, title, false);
    setSize(400, 400);
    add(chooser);
  }
}
