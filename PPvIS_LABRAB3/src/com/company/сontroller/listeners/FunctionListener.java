package com.company.сontroller.listeners;

import com.company.view.InformationPanel;
import com.company.сontroller.GraphicDrawController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FunctionListener implements ActionListener, IStoppable {
    JFrame frame;
    JSplitPane splitPane;
    GraphicDrawController graphicDrawController;
    InformationPanel informationPanel;

    public FunctionListener(JFrame mainFrame, JSplitPane splitPaneTableFunction, GraphicDrawController controller, InformationPanel informationPanel) {
        this.frame = mainFrame;
        this.splitPane = splitPaneTableFunction;
        this.graphicDrawController = controller;
        this.informationPanel = informationPanel;
    }

    public void stop() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
