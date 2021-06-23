package org.example.UI.Menus.Product;

import org.example.Models.Product;
import org.example.UI.Menus.CustomColumnModel;
import org.example.UI.Menus.CustomDataModel;

import javax.swing.*;
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
        setModel(dataModel);
    }
}
