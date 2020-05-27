package com.company.сontroller;

import com.company.model.PointListForFunction;
import com.company.view.SizeInformationPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class GraphicDrawController {

    private final PointListForFunction pointListForFunctionA;
    private final PointListForFunction pointListForFunctionB;
    private final SizeInformationPanel sizeInformationPanel;

    public GraphicDrawController(JSplitPane splitPaneTableFunction) {
        pointListForFunctionA = new PointListForFunction();
        pointListForFunctionB = new PointListForFunction();
        sizeInformationPanel = new SizeInformationPanel(this);
        splitPaneTableFunction.setRightComponent(sizeInformationPanel);
    }

    public void addPointToList(Point point, String graphName) {
        if (Objects.equals(graphName, "A")) {
            pointListForFunctionA.addPoint(point);
        } else {
            pointListForFunctionB.addPoint(point);
        }

        getSizeInformationPanel().getGraphicDrawPanel().repaint();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clearPointList(String graphName) {
        if (Objects.equals(graphName, "A")) {
            pointListForFunctionA.clear();
        } else {
            pointListForFunctionB.clear();
        }
    }

    public List<Point> getPointList(String graphName) {
        if (Objects.equals(graphName, "A")) {
            return pointListForFunctionA.getPointList();
        } else {
            return pointListForFunctionB.getPointList();
        }
    }

    public SizeInformationPanel getSizeInformationPanel() {
        return sizeInformationPanel;
    }
}
