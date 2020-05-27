package com.company.view;

import javax.swing.*;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;

public class InformationPanel {

    private final JButton buttonForCreateFunctionA = new JButton("Вычислить А");
    private final JButton buttonForCreateFunctionB = new JButton("Вычислить B");
    private final JButton buttonForStopAllFunctions = new JButton("Остановить всё");

    private final JTextField textFieldForParameterAFunctionB = new JTextField();
    private final JTextField textFieldForXMaxFunctionB = new JTextField();
    private final JTextField textFieldForXMinFunctionB = new JTextField();
    private final JTextField textFieldForXMaxFunctionA = new JTextField();
    private final JTextField textFieldForXMinFunctionA = new JTextField();

    private final JLabel labelForConstants = new JLabel("Значения констант: ");
    private final JLabel labelForFunctionB = new JLabel("Запустить функцию В: ");
    private final JLabel labelForFunctionA = new JLabel("Запустить функцию А: ");
    private final JLabel labelForParameterA = new JLabel("Введите параметр а: ");

    private final JLabel labelForXMaxFunctionA = new JLabel("Введите верхнюю границу х: ");
    private final JLabel labelForXMinFunctionA = new JLabel("Введите нижнюю границу х: ");
    private final JLabel labelForXMaxFunctionB = new JLabel("Введите верхнюю границу x: ");
    private final JLabel labelForXMinFunctionB = new JLabel("Введите нижнюю границу x: ");

    private final JLabel labelForH = new JLabel("                   h = 0.1;");
    private final JLabel labelForE = new JLabel("e = 0.0001;");
    private final JLabel labelForUnitOne = new JLabel("* 0.1");
    private final JLabel labelForUnitTwo = new JLabel("* 0.1");

    public JPanel createInformationPanel() {

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        createHorizontalGroupLayout(layout);
        createLinkSizeLayout(layout);
        createVerticalGroupLayout(layout);
        return panel;
    }

    public void createHorizontalGroupLayout(GroupLayout layout) {
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
                                .addComponent(textFieldForParameterAFunctionB))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(labelForXMaxFunctionB)
                                .addComponent(textFieldForXMaxFunctionB)
                                .addComponent(labelForUnitOne))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(labelForXMinFunctionB)
                                .addComponent(textFieldForXMinFunctionB)
                                .addComponent(labelForUnitTwo))
                        .addComponent(buttonForStopAllFunctions)
                )
        );
    }

    public void createLinkSizeLayout(GroupLayout layout) {
        layout.linkSize(SwingConstants.HORIZONTAL, labelForFunctionA, buttonForCreateFunctionA,
                labelForParameterA, textFieldForParameterAFunctionB, labelForXMaxFunctionB, textFieldForXMaxFunctionB,
                labelForXMinFunctionB, textFieldForXMinFunctionB, labelForFunctionB, buttonForCreateFunctionB, labelForE,
                labelForH, labelForUnitOne, labelForUnitTwo, labelForXMaxFunctionA, labelForXMinFunctionA,
                textFieldForXMaxFunctionA, textFieldForXMinFunctionA, buttonForStopAllFunctions);
    }

    public void createVerticalGroupLayout(GroupLayout layout) {
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
                        .addComponent(textFieldForParameterAFunctionB))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(labelForXMaxFunctionB)
                        .addComponent(textFieldForXMaxFunctionB)
                        .addComponent(labelForUnitOne))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(labelForXMinFunctionB)
                        .addComponent(textFieldForXMinFunctionB)
                        .addComponent(labelForUnitTwo))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(labelForConstants)
                        .addComponent(labelForH)
                        .addComponent(labelForE))
                .addComponent(buttonForStopAllFunctions)
        );
    }

    public JButton getButtonForCreateFunctionA() {
        return buttonForCreateFunctionA;
    }

    public JButton getButtonForCreateFunctionB() {
        return buttonForCreateFunctionB;
    }

    public JButton getButtonForStopAllFunctions() {
        return buttonForStopAllFunctions;
    }

    public JTextField getTextFieldForParameterAFunctionB() {
        return textFieldForParameterAFunctionB;
    }

    public JTextField getTextFieldForXMaxFunctionB() {
        return textFieldForXMaxFunctionB;
    }

    public JTextField getTextFieldForXMinFunctionB() {
        return textFieldForXMinFunctionB;
    }

    public JTextField getTextFieldForXMaxFunctionA() {
        return textFieldForXMaxFunctionA;
    }

    public JTextField getTextFieldForXMinFunctionA() {
        return textFieldForXMinFunctionA;
    }
}
