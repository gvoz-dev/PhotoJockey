package ui.editor.listeners;

import ui.editor.PJEditor;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DefaultMouseHandler extends MouseAdapter {
  private final PJEditor editor;
  private Graphics2D g2d;
  private int x1;
  private int y1;
  private int x2;
  private int y2;

  public DefaultMouseHandler(PJEditor editor) {
    this.editor = editor;
  }

  private void setGraphics() {
    g2d = (Graphics2D) editor.getImg().getGraphics();
    g2d.setColor(editor.getBrushColor());
    Stroke stroke = new BasicStroke(editor.getBrushSize(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10.0f, null, 0.0f);
    g2d.setStroke(stroke);
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (editor.getImg() != null) {
      setGraphics();
      x1 = e.getX();
      y1 = e.getY();
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (editor.getImg() != null) {
      x2 = e.getX();
      y2 = e.getY();
      g2d.drawLine(x1, y1, x2, y2);
      editor.repaint();
    }
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    if (editor.getImg() != null) {
      x2 = e.getX();
      y2 = e.getY();
      g2d.drawLine(x1, y1, x2, y2);
      x1 = x2;
      y1 = y2;
      editor.repaint();
    }
  }
}
