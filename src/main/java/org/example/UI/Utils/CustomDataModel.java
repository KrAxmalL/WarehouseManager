package org.example.UI.Utils;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;

public class CustomDataModel<T> extends AbstractTableModel {

    private CustomColumnModel columnModel;
    private T[] dataList;

    public CustomDataModel(CustomColumnModel columnModel, T[] dataList) {
        this.dataList = dataList;
        this.columnModel = columnModel;
    }

    @Override
    public int getRowCount() {
        return this.dataList.length;
    }

    @Override
    public int getColumnCount() {
        return this.columnModel.getColumnCount();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Field tField = ((Field) columnModel.getColumn(columnIndex).getIdentifier());
            return tField.get(dataList[rowIndex]);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return "Exc";
    }
}
