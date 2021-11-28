package org.ukma.warehouse.UI.Menus.Category;

import org.ukma.warehouse.Models.Category;
import org.ukma.warehouse.UI.Utils.CustomColumnModel;
import org.ukma.warehouse.UI.Utils.CustomDataModel;

import javax.swing.*;

public class CategoryTable extends JTable{

    private CustomDataModel<Category> dataModel;
    private CustomColumnModel columnModel;
    //private static final String[] header = {"Id", "Name", "Producer", "Price", "Amount"};

    public CategoryTable(Category[] categories) {
        setAutoCreateColumnsFromModel(false);
        columnModel = new CustomColumnModel(Category.class);
        setColumnModel(columnModel);
        dataModel = new CustomDataModel<Category>(columnModel, categories);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setModel(dataModel);
    }
}
