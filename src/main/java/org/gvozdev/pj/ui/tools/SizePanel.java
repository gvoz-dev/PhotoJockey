package org.gvozdev.pj.ui.tools;

import javax.swing.BoundedRangeModel;
import javax.swing.BoxLayout;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import java.awt.Dimension;

public class SizePanel extends JPanel {
  private static final int INITIAL_VALUE = 5;
  private static final int MIN_VALUE = 1;
  private static final int MAX_VALUE = 100;
  private static final int STEP = 1;

  private final JSlider sizeSlider = new JSlider(JSlider.HORIZONTAL);
  private final JSpinner sizeSpinner = new JSpinner();
  private int toolSize = INITIAL_VALUE;

  public SizePanel() {
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    addSizeSlider();
    addSizeSpinner();
  }

  private void addSizeSlider() {
    var d = new Dimension(120, 30);
    sizeSlider.setPreferredSize(d);
    sizeSlider.setMaximumSize(d);

    BoundedRangeModel model = new DefaultBoundedRangeModel(INITIAL_VALUE, 0, MIN_VALUE, MAX_VALUE);
    sizeSlider.setModel(model);

    sizeSlider.addChangeListener(event -> {
      toolSize = ((JSlider) event.getSource()).getValue();
      sizeSpinner.setValue(toolSize);
    });

    add(sizeSlider);
  }

  private void addSizeSpinner() {
    var d = new Dimension(60, 30);
    sizeSpinner.setPreferredSize(d);
    sizeSpinner.setMaximumSize(d);

    SpinnerModel model = new SpinnerNumberModel(INITIAL_VALUE, MIN_VALUE, MAX_VALUE, STEP);
    sizeSpinner.setModel(model);

    sizeSpinner.addChangeListener(event -> {
      toolSize = (Integer) ((JSpinner) event.getSource()).getValue();
      sizeSlider.setValue(toolSize);
    });

    add(sizeSpinner);
  }

  public int getToolSize() {
    return toolSize;
  }
}
