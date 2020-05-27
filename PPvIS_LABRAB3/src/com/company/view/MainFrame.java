package com.company.view;

import com.company.сontroller.GraphicDrawController;
import com.company.сontroller.MainFrameController;

import javax.swing.*;
import java.awt.*;

public class MainFrame {

    private final JFrame mainFrame = new JFrame("Лабораторная работа №3");
    private final JSplitPane splitPaneTableGraph = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    private final GraphicDrawController controller = new GraphicDrawController(splitPaneTableGraph);
    private final JPanel backgroundPanel = new JPanel();
    MainFrameController mainFrameController = new MainFrameController();

    public void initialize() {
        backgroundPanel.setLayout(new BorderLayout());
        mainFrameController.setComponents(mainFrame, splitPaneTableGraph, controller);
        JPanel infoPanel = mainFrameController.createInfoPanel();
        infoPanel.setBorder(BorderFactory.createEtchedBorder());
        JSplitPane splitPaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        backgroundPanel.add(splitPaneH, BorderLayout.CENTER);
        splitPaneH.setLeftComponent(infoPanel);
        splitPaneH.setRightComponent(splitPaneTableGraph);

        mainFrame.add(backgroundPanel);
        mainFrame.setSize(1800, 800);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}