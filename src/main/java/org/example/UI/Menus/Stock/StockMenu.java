package org.example.UI.Menus.Stock;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StockMenu extends JMenu {

    private JMenuItem increaseProductAmount;
    private JMenuItem decreaseProductAmount;

    public StockMenu() {
        super("Stock");
        init();
    }

    private void init()
    {
        increaseProductAmount = new JMenuItem("Increase product amount");
        increaseProductAmount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new IncreaseProductAmountMenu();
            }
        });

        decreaseProductAmount  = new JMenuItem("Decrease product amount");
        decreaseProductAmount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DecreaseProductAmountMenu();
            }
        });

        this.add(increaseProductAmount);
        this.add(decreaseProductAmount);
    }

    public JMenuItem getIncreaseProductAmount() {
        return increaseProductAmount;
    }

    public void setIncreaseProductAmount(JMenuItem increaseProductAmount) {
        this.increaseProductAmount = increaseProductAmount;
    }

    public JMenuItem getDecreaseProductAmount() {
        return decreaseProductAmount;
    }

    public void setDecreaseProductAmount(JMenuItem decreaseProductAmount) {
        this.decreaseProductAmount = decreaseProductAmount;
    }
}
