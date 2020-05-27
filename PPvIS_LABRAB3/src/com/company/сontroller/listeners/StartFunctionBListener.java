package com.company.сontroller.listeners;

import com.company.view.InformationPanel;
import com.company.сontroller.GraphicDrawController;
import com.company.сontroller.TableController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class StartFunctionBListener extends FunctionListener {

    private final TableController tableController = new TableController();
    private JScrollPane scrollPaneForTable;
    private JTable tableForFunctionBInformation;
    private JPanel tablePanelForInformationFuncB;
    private Thread threadForFunctionB;

    public StartFunctionBListener(JFrame mainFrame, JSplitPane splitPaneTableFunction, GraphicDrawController controller, InformationPanel informationPanel) {
        super(mainFrame, splitPaneTableFunction, controller, informationPanel);
    }

    public JPanel createTableBPanel(JScrollPane scrollPane) {
        JPanel panel = new JPanel();
        scrollPane.setMinimumSize(new Dimension(200, 180));
        panel.setLayout(new BorderLayout());

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private double calculateFunctionB(double x) {
        double F = 0;
        double value = 1;
        int a = Integer.parseInt(informationPanel.getTextFieldForParameterAFunctionB().getText());
        int i = 0;
        while (value >= 0.1 && i < Integer.parseInt(informationPanel.getTextFieldForXMaxFunctionB().getText()) / 0.1) {
            value = (pow(-1, i) * a * pow(x, 2 * i + 1)) / (calculateFactorial(i));
            F += value;
            i++;
        }
        return abs(F);
    }

    private double calculateFactorial(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        return result;
    }

    @Override
    public void stop() {
        if (threadForFunctionB.isAlive()) {
            threadForFunctionB.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (splitPane.getLeftComponent() != null) {
            tablePanelForInformationFuncB.remove(scrollPaneForTable);
        }
        if (informationPanel.getTextFieldForParameterAFunctionB().getText() != null
                && informationPanel.getTextFieldForXMaxFunctionB().getText() != null
                && informationPanel.getTextFieldForXMinFunctionB().getText() != null) {
            try {

                tableForFunctionBInformation = tableController.createTable();
                scrollPaneForTable = new JScrollPane(tableForFunctionBInformation);
                tablePanelForInformationFuncB = createTableBPanel(scrollPaneForTable);
                splitPane.setLeftComponent(tablePanelForInformationFuncB);

                graphicDrawController.clearPointList("B");
                threadForFunctionB = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        double x = Integer.parseInt(informationPanel.getTextFieldForXMinFunctionB().getText());
                        while (x <= Integer.parseInt(informationPanel.getTextFieldForXMaxFunctionB().getText())) {
                            double F = calculateFunctionB(x);
                            Point point = new Point((int) x, (int) (F * 10));
                            graphicDrawController.addPointToList(point, "B");
                            tableForFunctionBInformation = tableController.addRow(tableForFunctionBInformation, String.format("%.4f", (x / 10)), String.format("%.4f", (F / 10)));
                            x += 1;
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException en) {
                                JOptionPane.showMessageDialog(frame, "Ошибка!\nЧто-то пошло не так!");
                            }
                        }
                    }
                });

                threadForFunctionB.start();
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(frame, "Ошибка!\nОстались незаполненные поля для функции В!\nЗаполните все поля и повторите попытку!");
            }
        }
    }
}
