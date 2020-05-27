package com.company.сontroller.listeners;

import com.company.view.InformationPanel;
import com.company.сontroller.GraphicDrawController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class FunctionListener implements ActionListener, IStoppable {
    protected JFrame frame;
    protected JSplitPane splitPane;
    protected GraphicDrawController graphicDrawController;
    protected InformationPanel informationPanel;

    public FunctionListener(JFrame mainFrame, JSplitPane splitPaneTableFunction, GraphicDrawController controller, InformationPanel informationPanel) {
        this.frame = mainFrame;
        this.splitPane = splitPaneTableFunction;
        this.graphicDrawController = controller;
        this.informationPanel = informationPanel;
    }

    public abstract void stop();

    @Override
    public abstract void actionPerformed(ActionEvent e);
}
