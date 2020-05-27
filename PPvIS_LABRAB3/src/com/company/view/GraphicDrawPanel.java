package com.company.view;

import com.company.model.PointsComparatorAxis;
import com.company.сontroller.GraphicDrawController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraphicDrawPanel extends JPanel {

    private final Color functionColorA = Color.ORANGE;
    private final Color functionColorB = Color.MAGENTA;
    private final Color functionPointColor = Color.DARK_GRAY;

    private final Stroke functionStroke = new BasicStroke(3f);
    private final int pointWidthAndHeight = 7;

    private final List<Point> pointsForFunctionA;
    private final List<Point> pointsForFunctionB;

    private double zoom;
    private double maxX;
    private double maxY;
    private double xDivision;
    private double yDivision;
    private double hatchNumber;
    private int indexFromAxisWithZoom;
    private int strokeLengthSingleSegment;

    public GraphicDrawPanel(GraphicDrawController controller) {
        this.pointsForFunctionA = controller.getPointList("A");
        this.pointsForFunctionA.sort(new PointsComparatorAxis());
        this.pointsForFunctionB = controller.getPointList("B");
        this.pointsForFunctionB.sort(new PointsComparatorAxis());
        this.zoom = 0.5;
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        findFunctionLength();
        setAxisDivision();

        List<Point> zoomedPointsListA = new ArrayList<>();
        List<Point> zoomedPointsListB = new ArrayList<>();
        addNewPointOnAxis(zoomedPointsListA, pointsForFunctionA);
        addNewPointOnAxis(zoomedPointsListB, pointsForFunctionB);

        drawAxis(g2);

        hatchNumber = maxY / 100000;
        creationSingleReviewsAndSignaturesOnAxis(g2);
        hatchNumber = maxX / 100;
        creationSingleReviewsAndSignaturesOnAxis(g2);

        Stroke oldStroke = g2.getStroke();
        setColorAndStroke(g2, functionColorA, functionStroke);
        calculationsForDrawFunction(g2, zoomedPointsListA);
        setColorAndStroke(g2, functionPointColor, oldStroke);
        calculationsForFillColor(g2, zoomedPointsListA);
        setColorAndStroke(g2, functionColorB, functionStroke);
        calculationsForDrawFunction(g2, zoomedPointsListB);
        setColorAndStroke(g2, functionPointColor, oldStroke);
        calculationsForFillColor(g2, zoomedPointsListB);
    }

    private void drawAxis(Graphics2D graphics2D) {

        graphics2D.setColor(Color.GRAY);

        graphics2D.drawLine(indexFromAxisWithZoom, getHeight() - indexFromAxisWithZoom, indexFromAxisWithZoom, indexFromAxisWithZoom);
        graphics2D.drawLine(indexFromAxisWithZoom, getHeight() - indexFromAxisWithZoom, getWidth() - indexFromAxisWithZoom, getHeight() - indexFromAxisWithZoom);

        graphics2D.drawLine(getWidth() - indexFromAxisWithZoom, getHeight() - indexFromAxisWithZoom, getWidth() - indexFromAxisWithZoom - 10, getHeight() - indexFromAxisWithZoom - 10);
        graphics2D.drawLine(getWidth() - indexFromAxisWithZoom, getHeight() - indexFromAxisWithZoom, getWidth() - indexFromAxisWithZoom - 10, getHeight() - indexFromAxisWithZoom + 10);
        graphics2D.drawLine(indexFromAxisWithZoom, indexFromAxisWithZoom, indexFromAxisWithZoom + 10, indexFromAxisWithZoom + 10);
        graphics2D.drawLine(indexFromAxisWithZoom, indexFromAxisWithZoom, indexFromAxisWithZoom - 10, indexFromAxisWithZoom + 10);

        graphics2D.drawString("Y", indexFromAxisWithZoom, indexFromAxisWithZoom - 15);
        graphics2D.drawString("X", getWidth() - indexFromAxisWithZoom, getHeight() - indexFromAxisWithZoom - 15);
        graphics2D.drawString("0", indexFromAxisWithZoom, getHeight() - indexFromAxisWithZoom / 2);
    }

    private void creationSingleReviewsAndSignaturesOnAxis(Graphics2D graphics2D) {
        int yHatchCnt = 10;
        int fontSize = 10;
        if (hatchNumber == maxY / 100000) {
            for (int index = 0; index < yHatchCnt; index++) {
                int x0 = indexFromAxisWithZoom - strokeLengthSingleSegment / 2;
                int x1 = x0 + strokeLengthSingleSegment;
                int y0 = getHeight() - (((index + 1) * (getHeight() - indexFromAxisWithZoom * 2)) / yHatchCnt + indexFromAxisWithZoom);
                graphics2D.drawLine(x0, y0, x1, y0);
                graphics2D.drawString(String.format("%.4f", hatchNumber * (index + 1)), 0, y0 + fontSize / 2);
            }
        } else {
            for (int index = 0; index < yHatchCnt; index++) {
                int x0 = (index + 1) * (getWidth() - indexFromAxisWithZoom * 2) / yHatchCnt + indexFromAxisWithZoom;
                int y0 = getHeight() - indexFromAxisWithZoom + strokeLengthSingleSegment / 2;
                int y1 = y0 - strokeLengthSingleSegment;
                graphics2D.drawLine(x0, y0, x0, y1);
                graphics2D.drawString(String.format("%.4f", hatchNumber * (index + 1)), x0 - fontSize / 2, y0 + indexFromAxisWithZoom / 2);
            }
        }
    }

    private void addNewPointOnAxis(List<Point> zoomedPointsList, List<Point> pointsForFunction) {
        for (Point point : pointsForFunction) {
            Point newPoint = new Point();
            newPoint.x = (int) (indexFromAxisWithZoom + point.getX() * xDivision);
            newPoint.y = (int) (getHeight() - point.getY() * yDivision - indexFromAxisWithZoom);
            zoomedPointsList.add(newPoint);
        }
    }

    private void calculationsForFillColor(Graphics2D graphics2D, List<Point> zoomedPointsList) {
        for (Point point : zoomedPointsList) {
            int x = ((int) point.getX() - pointWidthAndHeight / 2);
            int y = ((int) point.getY() - pointWidthAndHeight / 2);
            graphics2D.fillOval(x, y, pointWidthAndHeight, pointWidthAndHeight);
        }
    }

    private void calculationsForDrawFunction(Graphics2D graphics2D, List<Point> zoomedPointsList) {
        for (int i = 0; i < zoomedPointsList.size() - 1; i++) {
            Point point = zoomedPointsList.get(i);
            int x1 = (int) point.getX();
            int y1 = (int) point.getY();
            point = zoomedPointsList.get(i + 1);
            int x2 = (int) point.getX();
            int y2 = (int) point.getY();
            graphics2D.drawLine(x1, y1, x2, y2);
        }
    }

    private void setColorAndStroke(Graphics2D graphics2D, Color color, Stroke stroke) {
        graphics2D.setStroke(stroke);
        graphics2D.setColor(color);
    }

    private void findFunctionLength() {
        double maxXFunctionA = findStep((int) Collections.max(pointsForFunctionA, new PointsComparatorAxis()).getX());
        double maxYFunctionA = findStep((int) Collections.max(pointsForFunctionA, new PointsComparatorAxis()).getY());
        double maxXFunctionB = findStep((int) Collections.max(pointsForFunctionB, new PointsComparatorAxis()).getX());
        double maxYFunctionB = findStep((int) Collections.max(pointsForFunctionB, new PointsComparatorAxis()).getY());

        maxX = Math.max(maxXFunctionA, maxXFunctionB);
        maxY = Math.max(maxYFunctionA, maxYFunctionB);
    }


    public int findStep(int number) {
        int step = 10;
        while (step < number) {
            step = step + 10;
        }
        return step;
    }

    private void setAxisDivision() {
        int indexFromAxis = 60;
        indexFromAxisWithZoom = (int) (indexFromAxis * zoom);
        strokeLengthSingleSegment = (int) (pointWidthAndHeight * zoom);
        xDivision = (((double) getWidth() - 2 * indexFromAxisWithZoom) / maxX);
        yDivision = (((double) getWidth() - 2 * indexFromAxisWithZoom) / maxY);
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
    public Dimension getPreferredSize() {
        int prefW = 1500;
        int prefH = 900;
        return new Dimension((int) (prefW * zoom), (int) (prefH * zoom));
    }
}