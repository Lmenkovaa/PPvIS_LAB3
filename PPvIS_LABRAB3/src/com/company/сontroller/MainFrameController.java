package com.company.сontroller;

import com.company.view.InformationPanel;
import com.company.сontroller.listeners.IStoppable;
import com.company.сontroller.listeners.StartFunctionAListener;
import com.company.сontroller.listeners.StartFunctionBListener;
import com.company.сontroller.listeners.StopAllFunctionListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class MainFrameController {

    private final InformationPanel informationPanel = new InformationPanel();
    private JFrame frame;
    private JSplitPane splitPane;
    private GraphicDrawController graphicDrawController;


    public JPanel createInfoPanel() {
        JPanel panel;
        panel = informationPanel.createInformationPanel();

        List<IStoppable> listForListeners = new ArrayList<>();

        StartFunctionAListener startFunctionAListener = new StartFunctionAListener(frame, splitPane, graphicDrawController, informationPanel);
        StartFunctionBListener startFunctionBListener = new StartFunctionBListener(frame, splitPane, graphicDrawController, informationPanel);
        listForListeners.add(startFunctionAListener);
        listForListeners.add(startFunctionBListener);
        StopAllFunctionListener stopAllFunctionListener = new StopAllFunctionListener(listForListeners);

        informationPanel.getButtonForCreateFunctionA().addActionListener(startFunctionAListener);
        informationPanel.getButtonForCreateFunctionB().addActionListener(startFunctionBListener);
        informationPanel.getButtonForStopAllFunctions().addActionListener(stopAllFunctionListener);

        return panel;
    }

    public void setComponents(JFrame mainFrame, JSplitPane splitPaneTableFunction, GraphicDrawController controller) {
        this.frame = mainFrame;
        this.splitPane = splitPaneTableFunction;
        this.graphicDrawController = controller;
    }
}