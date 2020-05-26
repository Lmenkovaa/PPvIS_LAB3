package com.company.View;

import com.company.Controller.GraphicDrawController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SizeInformationPanel extends JPanel {
    private final GraphicDrawPanel graphicDrawPanel;
    private final JSlider sliderToDisplayZoom;
    private final JLabel labelForFunctionA = new JLabel("Функция A ");
    private final JLabel labelWithColorForFunctionA = new JLabel(" - желтый цвет!");
    private final JLabel labelWithColorForFunctionB = new JLabel(" - фиолетовый цвет!");
    private final JLabel labelForFunctionB = new JLabel("  Функция B");

    public SizeInformationPanel(GraphicDrawController controller) {
        super();
        this.setLayout(new BorderLayout());
        JToolBar toolBar = new JToolBar(JToolBar.HORIZONTAL);
        sliderToDisplayZoom = new JSlider(JSlider.HORIZONTAL, 50, 150, 100);
        sliderToDisplayZoom.setMajorTickSpacing(25);
        sliderToDisplayZoom.setMinorTickSpacing(5);
        sliderToDisplayZoom.setPaintLabels(true);
        sliderToDisplayZoom.setPaintTicks(true);
        sliderToDisplayZoom.setSnapToTicks(true);

        graphicDrawPanel = new GraphicDrawPanel(controller);

        toolBar.setFloatable(false);
        toolBar.setLayout(new FlowLayout());


        JScrollPane scrollPanel = new JScrollPane(graphicDrawPanel);

        scrollPanel.setAutoscrolls(true);
        MouseAdapter mouseAdapter = new MouseAdapter() {
            private Point origin;

            @Override
            public void mousePressed(MouseEvent e) {
                origin = e.getPoint();
            }


            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (origin != null) {
                    JViewport viewPort = (JViewport) SwingUtilities.getAncestorOfClass(JViewport.class, graphicDrawPanel);
                    if (viewPort != null) {
                        int deltaX = origin.x - e.getX();
                        int deltaY = origin.y - e.getY();

                        Rectangle view = viewPort.getViewRect();
                        view.x += deltaX * 0.3;
                        view.y += deltaY * 0.2;
                        graphicDrawPanel.scrollRectToVisible(view);
                    }
                }
            }
        };

        graphicDrawPanel.addMouseListener(mouseAdapter);
        graphicDrawPanel.addMouseMotionListener(mouseAdapter);
        scrollPanel.setPreferredSize(new Dimension(800, 600));
        scrollPanel.setVisible(true);

        labelForFunctionA.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        labelWithColorForFunctionA.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        labelWithColorForFunctionB.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        labelForFunctionB.setFont(new Font("TimesRoman", Font.PLAIN, 15));

        labelWithColorForFunctionA.setForeground(Color.ORANGE);
        labelWithColorForFunctionB.setForeground(Color.MAGENTA);

        toolBar.add(labelForFunctionA);
        toolBar.add(labelWithColorForFunctionA);
        toolBar.add(labelForFunctionB);
        toolBar.add(labelWithColorForFunctionB);
        toolBar.add(sliderToDisplayZoom);

        this.add(scrollPanel, BorderLayout.WEST);
        this.add(toolBar, BorderLayout.SOUTH);
        this.setVisible(true);

        scrollPanel.addMouseWheelListener(e -> {
            if (e.isControlDown()) {
                if (e.getWheelRotation() < 0) {
                    graphicDrawPanel.setZoom(graphicDrawPanel.getZoom() + 0.1);

                } else {
                    graphicDrawPanel.setZoom(graphicDrawPanel.getZoom() - 0.1);
                }
                sliderToDisplayZoom.setValue((int) (graphicDrawPanel.getZoom() * 100));
                graphicDrawPanel.revalidate();
            }
        });

    }

    public GraphicDrawPanel getGraphicDrawPanel() {
        return graphicDrawPanel;
    }
}