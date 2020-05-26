package com.company.Controller;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;


public class MainFrameController {
    static JPanel functionAPanel;
    private final JButton buttonForCreateFunctionA = new JButton("Вычислить А");
    private final JButton buttonForCreateFunctionB = new JButton("Вычислить B");
    private final JButton buttonForStopAllFunctions = new JButton("Остановить всё");
    private final JTextField textFieldForParameterA = new JTextField();
    private final JTextField textFieldForXMax = new JTextField();
    private final JTextField textFieldForXMin = new JTextField();
    private final JTextField textFieldForXMaxFunctionA = new JTextField();
    private final JTextField textFieldForXMinFunctionA = new JTextField();
    private final JLabel labelForConstants = new JLabel("Значения констант: ");
    private final JLabel labelForFunctionB = new JLabel("Запустить функцию В: ");
    private final JLabel labelForFunctionA = new JLabel("Запустить функцию А: ");
    private final JLabel labelForParameterA = new JLabel("Введите параметр а: ");
    private final JLabel labelForXMax = new JLabel("Введите верхнюю границу x: ");
    private final JLabel labelForXMin = new JLabel("Введите нижнюю границу x: ");
    private final JLabel labelForXMaxFunctionA = new JLabel("Введите верхнюю границу х: ");
    private final JLabel labelForXMinFunctionA = new JLabel("Введите нижнюю границу х: ");
    private final JLabel labelForH = new JLabel("                   h = 0.1;");
    private final JLabel labelForE = new JLabel("e = 0.0001;");
    private final JLabel labelForIzm1 = new JLabel("* 0.1");
    private final JLabel labelForIzm2 = new JLabel("* 0.1");
    private final TableController tableController = new TableController();
    private boolean stop = false;
    private Thread threadForFunctionB = new Thread();
    private Thread threadForFunctionA = new Thread();
    private JPanel tablePanelForInformationFuncB;
    private JTable tableForFunctionBInformation;
    private JScrollPane scrollPaneForTable;

    public JPanel createInfoPanel() {
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(labelForFunctionA)
                                .addComponent(buttonForCreateFunctionA))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(labelForXMaxFunctionA)
                                .addComponent(textFieldForXMaxFunctionA))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(labelForXMinFunctionA)
                                .addComponent(textFieldForXMinFunctionA))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(labelForFunctionB)
                                .addComponent(buttonForCreateFunctionB))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(labelForConstants)
                                .addComponent(labelForH)
                                .addComponent(labelForE))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(labelForParameterA)
                                .addComponent(textFieldForParameterA))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(labelForXMax)
                                .addComponent(textFieldForXMax)
                                .addComponent(labelForIzm1))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(labelForXMin)
                                .addComponent(textFieldForXMin)
                                .addComponent(labelForIzm2))
                        .addComponent(buttonForStopAllFunctions)
                )
        );

        layout.linkSize(SwingConstants.HORIZONTAL, labelForFunctionA, buttonForCreateFunctionA,
                labelForParameterA, textFieldForParameterA, labelForXMax, textFieldForXMax,
                labelForXMin, textFieldForXMin, labelForFunctionB, buttonForCreateFunctionB, labelForE,
                labelForH, labelForIzm1, labelForIzm2, labelForXMaxFunctionA, labelForXMinFunctionA,
                textFieldForXMaxFunctionA, textFieldForXMinFunctionA, buttonForStopAllFunctions);

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(labelForFunctionA)
                        .addComponent(buttonForCreateFunctionA))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(labelForXMaxFunctionA)
                        .addComponent(textFieldForXMaxFunctionA))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(labelForXMinFunctionA)
                        .addComponent(textFieldForXMinFunctionA))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(labelForFunctionB)
                        .addComponent(buttonForCreateFunctionB))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(labelForParameterA)
                        .addComponent(textFieldForParameterA))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(labelForXMax)
                        .addComponent(textFieldForXMax)
                        .addComponent(labelForIzm1))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(labelForXMin)
                        .addComponent(textFieldForXMin)
                        .addComponent(labelForIzm2))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(labelForConstants)
                        .addComponent(labelForH)
                        .addComponent(labelForE))
                .addComponent(buttonForStopAllFunctions)
        );
        return panel;
    }

    public JPanel createTableBPanel(JScrollPane scrollPane) {
        JPanel panel = new JPanel();
        scrollPane.setMinimumSize(new Dimension(200, 180));
        panel.setLayout(new BorderLayout());

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    public void action(JFrame mainFrame, JSplitPane splitPaneTableFunction, GraphicDrawController controller) {
        buttonForCreateFunctionA.addActionListener(e -> {

            if (textFieldForXMaxFunctionA.getText() != null && textFieldForXMinFunctionA.getText() != null) {
                try {
                    Integer.valueOf(textFieldForXMaxFunctionA.getText());

                    stop = false;
                    if (threadForFunctionA.isAlive()) {
                        threadForFunctionA.stop();
                    }
                    controller.clearPointList("A");
                    threadForFunctionA = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            int x = Integer.parseInt(textFieldForXMinFunctionA.getText());
                            int xMax = Integer.parseInt(textFieldForXMaxFunctionA.getText());
                            while (x <= xMax && !stop) {
                                int F = functionA(x);
                                Point point = new Point(x * 10, F * 10000);
                                controller.addPointToList(point, "A");
                                x += 1;
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException en) {
                                    JOptionPane.showMessageDialog(mainFrame, "Ошибка!\nЧто-то пошло не так!");
                                }
                            }
                        }

                        int functionA(int x) {
                            return x * x * x;
                        }
                    });

                    threadForFunctionA.start();
                    splitPaneTableFunction.setLeftComponent(functionAPanel);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(mainFrame, "Ошибка!\nОстались незаполненные поля для функции А!\nЗаполните все поля и повторите попытку!");
                }
            }
        });

        buttonForCreateFunctionB.addActionListener(e -> {
            if (splitPaneTableFunction.getLeftComponent() != null) {
                tablePanelForInformationFuncB.remove(scrollPaneForTable);
            }
            if (textFieldForParameterA.getText() != null && textFieldForXMax.getText() != null && textFieldForXMin.getText() != null) {
                try {
                    Integer.valueOf(textFieldForXMax.getText());
                    tableForFunctionBInformation = tableController.createTable();
                    scrollPaneForTable = new JScrollPane(tableForFunctionBInformation);
                    tablePanelForInformationFuncB = createTableBPanel(scrollPaneForTable);
                    splitPaneTableFunction.setLeftComponent(tablePanelForInformationFuncB);
                    stop = false;
                    if (threadForFunctionB.isAlive()) {
                        threadForFunctionB.stop();
                    }
                    controller.clearPointList("B");
                    threadForFunctionB = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            double x = Integer.parseInt(textFieldForXMin.getText());
                            while (x <= Integer.parseInt(textFieldForXMax.getText()) && !stop) {
                                double F = functionB(x);
                                Point point = new Point((int) x, (int) (F * 10));
                                controller.addPointToList(point, "B");
                                tableForFunctionBInformation = tableController.addRow(tableForFunctionBInformation, String.format("%.4f", (x / 10)), String.format("%.4f", (F / 10)));
                                x += 1;
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException en) {
                                    JOptionPane.showMessageDialog(mainFrame, "Ошибка!\nЧто-то пошло не так!");
                                }
                            }
                        }

                        private double functionB(double x) {
                            double F = 0;
                            double value = 1;
                            int a = Integer.parseInt(textFieldForParameterA.getText());
                            int i = 0;
                            while (value >= 0.1 && i < Integer.parseInt(textFieldForXMax.getText()) / 0.1) {
                                value = (pow(-1, i) * a * pow(x, 2 * i + 1)) / (factorial(i));
                                F += value;
                                i++;
                            }
                            return abs(F);
                        }

                        private double factorial(int n) {
                            int result = 1;
                            for (int i = 1; i <= n; i++) {
                                result = result * i;
                            }
                            return result;
                        }
                    });

                    threadForFunctionB.start();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(mainFrame, "Ошибка!\nОстались незаполненные поля для функции В!\nЗаполните все поля и повторите попытку!");
                }
            }
        });
        buttonForStopAllFunctions.addActionListener(e -> {
            stop = !stop;
        });
    }
}