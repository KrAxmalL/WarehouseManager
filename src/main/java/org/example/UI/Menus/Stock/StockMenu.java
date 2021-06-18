package org.example.UI.Menus.Stock;

import org.example.UI.UserInterface;

import javax.swing.*;

public class StockMenu extends JMenu {

    public StockMenu() {
        super("Stock");
        init();
    }

    private void init()
    {
        JMenuItem addStock  = new JMenuItem("Manipulate stocks");

        //addStock.addActionListener(new UserInterface.ManipulateStocksListener());

        this.add(addStock);
    }
}
