package com.company.View;

import com.company.Controller.GraphicDrawController;
import com.company.Controller.MainFrameController;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    private final JFrame mainFrame = new JFrame("Лабораторная работа №3");
    private final JPanel backgroundPanel = new JPanel();
    private final JSplitPane splitPaneTableGraph = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    private final GraphicDrawController controller = new GraphicDrawController(splitPaneTableGraph);
    MainFrameController mainFrameController = new MainFrameController();
    private JPanel infoPanel = new JPanel();
    private JSplitPane splitPaneH;

    public void initialize() {
        backgroundPanel.setLayout(new BorderLayout());

        infoPanel = mainFrameController.createInfoPanel();
        infoPanel.setBorder(BorderFactory.createEtchedBorder());
        splitPaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        backgroundPanel.add(splitPaneH, BorderLayout.CENTER);
        splitPaneH.setLeftComponent(infoPanel);
        splitPaneH.setRightComponent(splitPaneTableGraph);

        mainFrame.add(backgroundPanel);
        mainFrame.setSize(1800, 800);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrameController.action(mainFrame, splitPaneTableGraph, controller);
        mainFrame.setVisible(true);
    }
}