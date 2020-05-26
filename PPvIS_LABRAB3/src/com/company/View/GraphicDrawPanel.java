package com.company.View;

import com.company.Controller.GraphicDrawController;
import com.company.Model.PointsComparatorXAxis;
import com.company.Model.PointsComparatorYAxis;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraphicDrawPanel extends JPanel {
    private final Color functionColorB = Color.MAGENTA;
    private final Color functionColorA = Color.ORANGE;
    private final int prefW = 1000;
    private final int prefH = 900;
    private final int borderGap = 60;
    private final Color functionPointColor = Color.DARK_GRAY;
    private final Stroke functionStroke = new BasicStroke(3f);
    private final int functionPointWidth = 7;
    private final int yHatchCnt = 10;
    private final int fontSize = 10;
    private final List<Point> pointsA;
    private final List<Point> pointsB;
    private double zoom;
    private double hatchNumber;


    public GraphicDrawPanel(GraphicDrawController controller) {
        this.pointsA = controller.getPointList("A");
        this.pointsA.sort(new PointsComparatorXAxis());
        this.pointsB = controller.getPointList("B");
        this.pointsB.sort(new PointsComparatorXAxis());
        this.zoom = 0.5;
        this.setVisible(true);
    }

    public double getZoom() {
        return zoom;
    }

    public void setZoom(double zoom) {
        if (zoom < 0.5) {
            this.zoom = 0.5;
        } else if (zoom > 1.5) {
            this.zoom = 1.5;
        } else {
            this.zoom = zoom;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        double maxXB = findStep((int) Collections.max(pointsB, new PointsComparatorXAxis()).getX());
        double maxYB = findStep((int) Collections.max(pointsB, new PointsComparatorYAxis()).getY());
        double maxXA = findStep((int) Collections.max(pointsA, new PointsComparatorXAxis()).getX());
        double maxYA = findStep((int) Collections.max(pointsA, new PointsComparatorYAxis()).getY());
        double maxX = Math.max(maxXB, maxXA);
        double maxY = Math.max(maxYB, maxYA);

        int borderGapF = (int) (borderGap * zoom);
        int hatchLength = (int) (functionPointWidth * zoom);
        double xScale = (((double) getWidth() - 2 * borderGapF) / maxX);
        double yScale = (((double) getHeight() - 2 * borderGapF) / maxY);
        int fontSize;
        if (zoom < 3) {
            fontSize = (int) (this.fontSize * zoom);
        } else {
            fontSize = this.fontSize * 3;
        }
        List<Point> zoomedPointsListA = new ArrayList<>();
        List<Point> zoomedPointsListB = new ArrayList<>();

        for (Point point : pointsA) {
            Point newPoint = new Point();
            newPoint.x = (int) (borderGapF + point.x * xScale);
            newPoint.y = (int) (getHeight() - point.y * yScale - borderGapF);
            zoomedPointsListA.add(newPoint);
        }

        for (Point point : pointsB) {
            Point newPoint = new Point();
            newPoint.x = (int) (borderGapF + point.x * xScale);
            newPoint.y = (int) (getHeight() - point.y * yScale - borderGapF);
            zoomedPointsListB.add(newPoint);
        }

        g2.setColor(Color.GRAY);
        g2.drawLine(borderGapF, getHeight() - borderGapF, borderGapF, borderGapF);
        g2.drawLine(borderGapF, getHeight() - borderGapF, borderGapF, borderGapF);
        g2.drawLine(borderGapF, getHeight() - borderGapF, getWidth() - borderGapF, getHeight() - borderGapF);
        g2.drawLine(getWidth() - borderGapF, getHeight() - borderGapF, getWidth() - borderGapF - 10, getHeight() - borderGapF - 10);
        g2.drawLine(getWidth() - borderGapF, getHeight() - borderGapF, getWidth() - borderGapF - 10, getHeight() - borderGapF + 10);
        g2.drawLine(borderGapF, borderGapF, borderGapF + 10, borderGapF + 10);
        g2.drawLine(borderGapF, borderGapF, borderGapF - 10, borderGapF + 10);
        g2.drawString("Y", borderGapF, borderGapF - 15);
        g2.drawString("X", getWidth() - borderGapF, getHeight() - borderGapF - 15);


        if (pointsA.size() < 2) {
            hatchNumber = maxY / 100000;
        } else {
            hatchNumber = maxY / 100000;
        }

        for (int index = 0; index < yHatchCnt; index++) {
            int x0 = borderGapF - hatchLength / 2;
            int x1 = x0 + hatchLength;
            int y0 = getHeight() - (((index + 1) * (getHeight() - borderGapF * 2)) / yHatchCnt + borderGapF);
            g2.drawLine(x0, y0, x1, y0);
            g2.drawString(String.format("%.4f", hatchNumber * (index + 1)), 0, y0 + fontSize / 2);
        }

        if (pointsA.size() < 2) {
            hatchNumber = maxX / 100;
        } else {
            hatchNumber = maxX / 100;
        }

        for (int index = 0; index < yHatchCnt; index++) {
            int x0 = (index + 1) * (getWidth() - borderGapF * 2) / yHatchCnt + borderGapF;
            int y0 = getHeight() - borderGapF + hatchLength / 2;
            int y1 = y0 - hatchLength;
            g2.drawLine(x0, y0, x0, y1);
            g2.drawString(String.format("%.4f", hatchNumber * (index + 1)), x0 - fontSize / 2, y0 + borderGapF / 2);
        }
        g2.drawString("0", borderGapF, getHeight() - borderGapF / 2);
        Stroke oldStroke = g2.getStroke();
        g2.setColor(functionColorA);
        g2.setStroke(functionStroke);
        for (int i = 0; i < zoomedPointsListA.size() - 1; i++) {
            Point point = zoomedPointsListA.get(i);
            int x1 = point.x;
            int y1 = point.y;
            point = zoomedPointsListA.get(i + 1);
            int x2 = point.x;
            int y2 = point.y;
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setStroke(oldStroke);
        g2.setColor(functionPointColor);
        for (Point point : zoomedPointsListA) {
            int x = (point.x - functionPointWidth / 2);
            int y = (point.y - functionPointWidth / 2);
            int ovalW = functionPointWidth;
            int ovalH = functionPointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
        }
        g2.setColor(functionColorB);
        g2.setStroke(functionStroke);
        for (int i = 0; i < zoomedPointsListB.size() - 1; i++) {
            Point point = zoomedPointsListB.get(i);
            int x1 = point.x;
            int y1 = point.y;
            point = zoomedPointsListB.get(i + 1);
            int x2 = point.x;
            int y2 = point.y;
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setStroke(oldStroke);
        g2.setColor(functionPointColor);
        for (Point point : zoomedPointsListB) {
            int x = point.x - functionPointWidth / 2;
            int y = point.y - functionPointWidth / 2;
            int ovalW = functionPointWidth;
            int ovalH = functionPointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }

    public int findStep(int number) {
        int step = 10;
        while (step < number) {
            step = step + 10;
        }
        return step;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension((int) (prefW * zoom), (int) (prefH * zoom));
    }
}