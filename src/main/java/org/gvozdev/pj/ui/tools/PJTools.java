package org.gvozdev.pj.ui.tools;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JToolBar;
import java.awt.Color;

public class PJTools {
    public JToolBar getToolBar() {
        return toolBar;
    }

    private final JToolBar toolBar;
    private ColorPanel colorPanel;
    private SizePanel sizePanel;

    public PJTools() {
        toolBar = new JToolBar("Tools", JToolBar.VERTICAL);
        toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.Y_AXIS));
        toolBar.setFloatable(true);

        int strutHeight = 25;
        toolBar.add(Box.createVerticalStrut(strutHeight));
        addColorPanel();
        toolBar.add(Box.createVerticalStrut(strutHeight));
        addSizePanel();
    }

    private void addColorPanel() {
        colorPanel = new ColorPanel();
        toolBar.add(colorPanel);
    }

    private void addSizePanel() {
        sizePanel = new SizePanel();
        toolBar.add(sizePanel);
    }

    public Color getColor1() {
        return colorPanel.getToolColor1();
    }

    public Color getColor2() {
        return colorPanel.getToolColor2();
    }

    public void swapColors() {
        colorPanel.swapColors();
    }

    public int getSize() {
        return sizePanel.getToolSize();
    }
}
