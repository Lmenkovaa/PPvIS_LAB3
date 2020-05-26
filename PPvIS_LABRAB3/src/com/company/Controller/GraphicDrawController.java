package com.company.Controller;

import com.company.Model.PointListForFunctionA;
import com.company.Model.PointListForFunctionB;
import com.company.View.SizeInformationPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GraphicDrawController {

    private final PointListForFunctionA pointListForFunctionA;
    private final PointListForFunctionB pointListForFunctionB;
    private final SizeInformationPanel sizeInformationPanel;

    public GraphicDrawController(JSplitPane splitPaneTableFunction) {
        pointListForFunctionA = new PointListForFunctionA();
        pointListForFunctionB = new PointListForFunctionB();
        sizeInformationPanel = new SizeInformationPanel(this);
        splitPaneTableFunction.setRightComponent(sizeInformationPanel);
    }

    public void addPointToList(Point point, String graphName) {
        if (graphName.equals("A")) {
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
        if (graphName.equals("A")) {
            pointListForFunctionA.clear();
        } else {
            pointListForFunctionB.clear();
        }

    }

    public List<Point> getPointList(String graphName) {
        if (graphName.equals("A")) {
            return pointListForFunctionA.getPointList();
        } else {
            return pointListForFunctionB.getPointList();
        }
    }

    public SizeInformationPanel getSizeInformationPanel() {
        return sizeInformationPanel;
    }
}
