package com.company.сontroller.listeners;

import com.company.view.InformationPanel;
import com.company.сontroller.GraphicDrawController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StartFunctionAListener extends FunctionListener {

    protected JPanel functionAPanel;
    private Thread threadForFunctionA;

    public StartFunctionAListener(JFrame mainFrame, JSplitPane splitPaneTableFunction, GraphicDrawController controller, InformationPanel informationPanel) {
        super(mainFrame, splitPaneTableFunction, controller, informationPanel);
    }

    private int calculateFunctionA(int x) {
        return x * x * x;
    }

    @Override
    public void stop() {

        if (threadForFunctionA.isAlive()) {
            threadForFunctionA.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (informationPanel.getTextFieldForXMaxFunctionA().getText() != null
                && informationPanel.getTextFieldForXMinFunctionA().getText() != null) {
            try {
                graphicDrawController.clearPointList("A");
                threadForFunctionA = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int x = Integer.parseInt(informationPanel.getTextFieldForXMinFunctionA().getText());
                        int xMax = Integer.parseInt(informationPanel.getTextFieldForXMaxFunctionA().getText());
                        while (x <= xMax) {
                            int F = calculateFunctionA(x);
                            Point point = new Point(x * 10, F * 10000);
                            graphicDrawController.addPointToList(point, "A");
                            x += 1;
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException en) {
                                JOptionPane.showMessageDialog(frame, "Ошибка!\nЧто-то пошло не так!");
                            }
                        }
                    }
                });
                threadForFunctionA.start();
                splitPane.setLeftComponent(functionAPanel);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(frame, "Ошибка!\nОстались незаполненные поля для функции А!\nЗаполните все поля и повторите попытку!");
            }
        }
    }
}
