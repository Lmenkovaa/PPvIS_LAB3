package com.company.сontroller.listeners;

import com.company.view.GraphicDrawPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class MouseListener extends MouseAdapter {
    private Point origin;
    private GraphicDrawPanel graphicDrawPanel;
    private JSlider sliderToDisplayZoom;


    public void setGraphicDrawPanel(GraphicDrawPanel graphicDrawPanel) {
        this.graphicDrawPanel = graphicDrawPanel;
    }

    public void setSliderToDisplayZoom(JSlider slider) {
        this.sliderToDisplayZoom = slider;
    }

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
                int deltaX = (int) origin.getX() - e.getX();
                int deltaY = (int) origin.getY() - e.getY();

                Rectangle view = viewPort.getViewRect();
                view.x += deltaX * 0.3;
                view.y += deltaY * 0.2;
                graphicDrawPanel.scrollRectToVisible(view);
            }
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        super.mouseWheelMoved(e);
        if (e.isControlDown()) {
            if (e.getWheelRotation() < 0) {
                graphicDrawPanel.setZoom(graphicDrawPanel.getZoom() + 0.1);

            } else {
                graphicDrawPanel.setZoom(graphicDrawPanel.getZoom() - 0.1);
            }
            sliderToDisplayZoom.setValue((int) (graphicDrawPanel.getZoom() * 100));
            graphicDrawPanel.revalidate();
        }
    }
}
