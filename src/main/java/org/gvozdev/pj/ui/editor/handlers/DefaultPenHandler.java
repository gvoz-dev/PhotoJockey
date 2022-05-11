package org.gvozdev.pj.ui.editor.handlers;

import org.gvozdev.pj.ui.editor.PJEditor;
import org.gvozdev.pj.ui.tools.PJTools;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DefaultPenHandler extends MouseAdapter {
  private static final int LEFT_BUTTON = MouseEvent.BUTTON1;
  private static final int RIGHT_BUTTON = MouseEvent.BUTTON3;
  private static final int WHEEL_BUTTON = MouseEvent.BUTTON2;

  private final PJEditor editor;
  private final PJTools tools;
  private Graphics2D g2d;
  private int x1;
  private int y1;
  private boolean isPainting;

  public DefaultPenHandler(PJEditor editor, PJTools tools) {
    this.editor = editor;
    this.tools = tools;
  }

  private void setup(MouseEvent e) {
    g2d = (Graphics2D) editor.getImg().getGraphics();
    if (e.getButton() == LEFT_BUTTON) {
      g2d.setColor(tools.getColor1());
    } else if (e.getButton() == RIGHT_BUTTON) {
      g2d.setColor(tools.getColor2());
    } else {
      isPainting = false;
      return;
    }
    isPainting = true;
    Stroke stroke = new BasicStroke(tools.getSize(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10.0f, null, 0.0f);
    g2d.setStroke(stroke);
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
  }

  private void reset() {
    isPainting = false;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (editor.getImg() != null) {
      setup(e);
      x1 = e.getX();
      y1 = e.getY();
    } else {
      reset();
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (isPainting) {
      g2d.drawLine(x1, y1, e.getX(), e.getY());
      editor.repaint();
    }
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    if (isPainting) {
      int x2 = e.getX();
      int y2 = e.getY();
      g2d.drawLine(x1, y1, x2, y2);
      x1 = x2;
      y1 = y2;
      editor.repaint();
    }
  }
}
