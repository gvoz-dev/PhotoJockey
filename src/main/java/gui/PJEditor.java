package gui;

import filters.Filter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class PJEditor extends JPanel {
  private Image img;
  private String fileName;
  private Color brushColor = Color.CYAN;
  private int brushSize = 5;

  public PJEditor() {
    addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        if (img == null) return;
        Graphics buffer = img.getGraphics();
        buffer.setColor(brushColor);
        buffer.fillOval(e.getX(), e.getY(), brushSize, brushSize);
        repaint();
      }
    });
    addMouseMotionListener(new MouseAdapter() {
      @Override
      public void mouseDragged(MouseEvent e) {
        if (img == null) return;
        Graphics buffer = img.getGraphics();
        buffer.setColor(brushColor);
        buffer.fillOval(e.getX(), e.getY(), brushSize, brushSize);
        repaint();
      }
    });
  }

  public void setDefaultImage(int width, int height) {
    img = createImage(width, height);
    Graphics g = img.getGraphics();
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, width, height);
  }

  @Override
  public void paintComponent(Graphics g) {
    if (img == null) {
      g.drawString("No image...", 50, 50);
      return;
    }
    super.paintComponent(g);
    g.drawImage(img, 0, 0, this);
  }

  public boolean openFromFile() {
    JFileChooser chooser = new JFileChooser(".");
    chooser.showOpenDialog(this);
    if (chooser.getSelectedFile() == null) {
      return false;
    }
    File input = chooser.getSelectedFile();
    fileName = input.getName();
    try {
      img = ImageIO.read(input);
      repaint();
    } catch (IOException e) {
      System.err.println("Unable to load image file!");
      return false;
    }
    return true;
  }

  public boolean saveToFile() {
    JFileChooser chooser = new JFileChooser(".");
    chooser.showSaveDialog(this);
    if (chooser.getSelectedFile() == null) {
      return false;
    }
    File output = chooser.getSelectedFile();
    fileName = output.getName();
    try {
      BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
      bufferedImage.getGraphics().drawImage(img, 0, 0, null);
      ImageIO.write(bufferedImage, "png", output);
    } catch (IOException e) {
      System.err.println("Unable to save image file!");
      return false;
    }
    return true;
  }

  public void printFile() {
    PrinterJob printerJob = PrinterJob.getPrinterJob();
    if (!printerJob.printDialog()) return;
    PageFormat pageFormat = printerJob.defaultPage();
    pageFormat = printerJob.pageDialog(pageFormat);
    printerJob.setPrintable((graphics, pf, pageIndex) -> {
      if (pageIndex != 0) {
        return Printable.NO_SUCH_PAGE;
      }
      graphics.drawImage(img, (int) pf.getImageableX(), (int) pf.getImageableY(), img.getWidth(null), img.getHeight(null), null);
      return Printable.PAGE_EXISTS;
    }, pageFormat);
    try {
      printerJob.print();
    } catch (PrinterException e) {
      System.err.println("Unable to print image file!");
    }
  }

  public void useFilter(String name) {
    try {
      Filter filter = (Filter) (Class.forName("filters." + name)).getConstructor().newInstance();
      img = filter.filter(this, img);
      repaint();
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
             ClassNotFoundException e) {
      e.printStackTrace();
    }
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

  public void setBrushColor(Color brushColor) {
    this.brushColor = brushColor;
  }

  public void setBrushSize(int brushSize) {
    this.brushSize = brushSize;
  }

  public String getFileName() {
    return fileName;
  }

  public Dimension getSize() {
    if (img == null) {
      return new Dimension(0, 0);
    }
    return new Dimension(img.getWidth(this), img.getHeight(this));
  }

  public Dimension getPreferredSize() {
    return getSize();
  }

  public Dimension getMinimumSize() {
    return getSize();
  }
}

class ColorDialog extends JDialog {
  public ColorDialog(JFrame owner, String title, JColorChooser chooser) {
    super(owner, title, false);
    setSize(400, 400);
    add(chooser);
  }
}
