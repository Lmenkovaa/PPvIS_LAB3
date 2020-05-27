package com.company.view;

import com.company.сontroller.GraphicDrawController;
import com.company.сontroller.listeners.MouseListener;

import javax.swing.*;
import java.awt.*;

public class SizeInformationPanel extends JPanel {

    private final GraphicDrawPanel graphicDrawPanel;
    private final JLabel labelForFunctionA = new JLabel("Функция A ");
    private final JLabel labelForFunctionB = new JLabel("  Функция B");
    private final JLabel labelWithColorForFunctionA = new JLabel(" - желтый цвет!");
    private final JLabel labelWithColorForFunctionB = new JLabel(" - фиолетовый цвет!");
    private JSlider sliderToDisplayZoom;

    public SizeInformationPanel(GraphicDrawController controller) {
        super();
        this.setLayout(new BorderLayout());

        JToolBar toolBar = new JToolBar(JToolBar.HORIZONTAL);
        graphicDrawPanel = new GraphicDrawPanel(controller);
        toolBar.setFloatable(false);
        toolBar.setLayout(new FlowLayout());
        JScrollPane scrollPanel = new JScrollPane(graphicDrawPanel);
        scrollPanel.setAutoscrolls(true);
        MouseListener mouseListener = new MouseListener();
        mouseListener.setGraphicDrawPanel(graphicDrawPanel);
        graphicDrawPanel.addMouseListener(mouseListener);
        graphicDrawPanel.addMouseMotionListener(mouseListener);
        scrollPanel.setPreferredSize(new Dimension(800, 600));
        scrollPanel.setVisible(true);
        createToolBar(toolBar);
        this.add(scrollPanel, BorderLayout.WEST);
        this.add(toolBar, BorderLayout.SOUTH);
        this.setVisible(true);
        mouseListener.setSliderToDisplayZoom(sliderToDisplayZoom);
        scrollPanel.addMouseWheelListener(mouseListener);
    }

    public void createToolBar(JToolBar toolBar) {
        labelForFunctionA.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        labelWithColorForFunctionA.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        labelWithColorForFunctionB.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        labelForFunctionB.setFont(new Font("TimesRoman", Font.PLAIN, 15));

        sliderToDisplayZoom = new JSlider(JSlider.HORIZONTAL, 50, 150, 100);
        sliderToDisplayZoom.setMajorTickSpacing(25);
        sliderToDisplayZoom.setMinorTickSpacing(5);
        sliderToDisplayZoom.setPaintLabels(true);
        sliderToDisplayZoom.setPaintTicks(true);
        sliderToDisplayZoom.setSnapToTicks(true);

        labelWithColorForFunctionA.setForeground(Color.ORANGE);
        labelWithColorForFunctionB.setForeground(Color.MAGENTA);

        toolBar.add(labelForFunctionA);
        toolBar.add(labelWithColorForFunctionA);
        toolBar.add(labelForFunctionB);
        toolBar.add(labelWithColorForFunctionB);
        toolBar.add(sliderToDisplayZoom);
    }

    public JSlider getSliderToDisplayZoom() {
        return sliderToDisplayZoom;
    }

    public GraphicDrawPanel getGraphicDrawPanel() {
        return graphicDrawPanel;
    }
}