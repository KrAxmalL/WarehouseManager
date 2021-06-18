package org.example.UI.Menus.Category;

import org.example.Models.Product;
import org.example.UI.Menus.CustomColumnModel;
import org.example.UI.Menus.CustomDataModel;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.JTableHeader;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProductTable extends JTable {

    private CustomDataModel<Product> dataModel;
    private CustomColumnModel columnModel;
    //private static final String[] header = {"Id", "Name", "Producer", "Price", "Amount"};

    public ProductTable(Product[] products) {
        setAutoCreateColumnsFromModel(false);
        columnModel = new CustomColumnModel(Product.class);
        setColumnModel(columnModel);
        dataModel = new CustomDataModel<Product>(columnModel, products);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.addMouseListener(new OnItemClicked());
        setModel(dataModel);
    }

    private class OnItemClicked implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if(e.getButton() == MouseEvent.BUTTON3) {
                int r = ProductTable.this.rowAtPoint(e.getPoint());
                JPopupMenu menu = new JPopupMenu();
                menu.show(e.getComponent(), e.getX(), e.getY());
                System.out.println(r);
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
