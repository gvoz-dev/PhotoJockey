package org.gvozdev.pj.ui.tools;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ChangeListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

public class ColorPanel extends JPanel {
  private final ColorDialog colorDialog = new ColorDialog(null);
  private final JButton colorButton1 = new JButton();
  private final JButton colorButton2 = new JButton();
  private final JButton swapColorButton = new JButton("\u2194");
  private Color toolColor1 = Color.BLACK;
  private Color toolColor2 = Color.WHITE;

  public ColorPanel() {
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    addColorButtons();
  }

  private void addColorButtons() {
    var d = new Dimension(50, 50);

    colorButton1.setPreferredSize(d);
    colorButton1.setMaximumSize(d);
    colorButton1.setBackground(toolColor1);

    colorButton2.setPreferredSize(d);
    colorButton2.setMaximumSize(d);
    colorButton2.setBackground(toolColor2);

    addListeners();
    add(colorButton1);
    add(swapColorButton);
    add(colorButton2);
  }

  private void addListeners() {
    ChangeListener changeColor1 = event -> {
      var color = colorDialog.getChooser().getColor();
      toolColor1 = color;
      colorButton1.setBackground(color);
    };
    ChangeListener changeColor2 = event -> {
      var color = colorDialog.getChooser().getColor();
      toolColor2 = color;
      colorButton2.setBackground(color);
    };

    ActionListener colorButtonHandler = event -> {
      var selectionModel = colorDialog.getChooser().getSelectionModel();
      selectionModel.removeChangeListener(changeColor1);
      selectionModel.removeChangeListener(changeColor2);
      if (event.getSource() == colorButton1) {
        selectionModel.addChangeListener(changeColor1);
      } else {
        selectionModel.addChangeListener(changeColor2);
      }
      colorDialog.setVisible(true);
    };

    colorButton1.addActionListener(colorButtonHandler);
    colorButton2.addActionListener(colorButtonHandler);
    swapColorButton.addActionListener(e -> swapColors());
  }

  public Color getToolColor1() {
    return toolColor1;
  }

  public Color getToolColor2() {
    return toolColor2;
  }

  public void swapColors() {
    Color tmp = toolColor1;
    toolColor1 = toolColor2;
    toolColor2 = tmp;
    colorButton1.setBackground(toolColor1);
    colorButton2.setBackground(toolColor2);
  }
}
