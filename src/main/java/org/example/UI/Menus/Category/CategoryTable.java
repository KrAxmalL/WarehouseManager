package org.example.UI.Menus.Category;

import org.example.Models.Category;
import org.example.UI.Utils.CustomColumnModel;
import org.example.UI.Utils.CustomDataModel;

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
