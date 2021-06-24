package org.example.UI.Utils;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import java.lang.reflect.Field;

public class CustomColumnModel extends DefaultTableColumnModel {

    public CustomColumnModel(Class tClass) {
        for (Field tField : tClass.getDeclaredFields()){
            TableColumn tColumn = new TableColumn();
            tColumn.setHeaderValue(tField.getName());
            tColumn.setModelIndex(getColumnCount());
            tColumn.setIdentifier(tField);
            tField.setAccessible(true);
            this.addColumn(tColumn);
        }
    }
}
