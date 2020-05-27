package com.company.сontroller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StopAllFunctionListener implements ActionListener {

    List<IStoppable> listForStop;

    public StopAllFunctionListener(List<IStoppable> listForListeners) {
        this.listForStop = listForListeners;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (IStoppable element : listForStop) {
            element.stop();
        }
    }
}
