package com.company.сontroller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StopAllFunctionListener implements ActionListener {

    ArrayList<IStoppable> listForStop;

    public StopAllFunctionListener(ArrayList<IStoppable> listForListeners) {
        this.listForStop = listForListeners;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (IStoppable element : listForStop) {
            element.stop();
        }
    }
}
