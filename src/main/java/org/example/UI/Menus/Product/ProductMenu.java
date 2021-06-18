package org.example.UI.Menus.Product;

import org.example.UI.Menus.Category.ProductTable;
import org.example.UI.UserInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductMenu extends JMenu {

    public ProductMenu() {
        super("Product");
        init();
    }

    private void init()
    {
        //JMenu itemMenu = new JMenu("Item");

        JMenuItem newItem  = new JMenuItem("New product");
        JMenuItem changeItem  = new JMenuItem("Change product");
        JMenuItem deleteItem = new JMenuItem("Delete product");

        newItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddProductMenu();
            }
        });

        changeItem.addActionListener(new ActionListener() {
            private JFrame fr;
            private ProductTable table;

            @Override
            public void actionPerformed(ActionEvent e) {
                fr = new JFrame();
                //JPanel panel = new JPanel();
                //table = new ProductTable();
                JScrollPane pane = new JScrollPane();
                JTable t = new JTable();
                pane.setViewportView(t);

                //pane.add(new AddProductMenu());

                //panel.add(pane);

                fr.add(t);

                fr.setVisible(true);
                //pane.setV

            }
        });
        //deleteItem.addActionListener(new UserInterface.DeleteItemsListener());

        this.add(newItem);
        this.add(changeItem);
        this.add(deleteItem);
    }
}
