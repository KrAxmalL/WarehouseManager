package org.ukma.warehouse.UI.Menus.Product;

import org.ukma.warehouse.Models.Product;
import org.ukma.warehouse.UI.Utils.CustomColumnModel;
import org.ukma.warehouse.UI.Utils.CustomDataModel;

import javax.swing.*;

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
