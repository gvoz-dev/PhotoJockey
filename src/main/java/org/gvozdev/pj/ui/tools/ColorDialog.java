package org.gvozdev.pj.ui.tools;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class ColorDialog extends JDialog {
  private final JColorChooser chooser = new JColorChooser();

  public ColorDialog(JFrame owner) {
    super(owner, "Color", false);
    add(chooser);
    pack();
  }

  public JColorChooser getChooser() {
    return chooser;
  }
}
