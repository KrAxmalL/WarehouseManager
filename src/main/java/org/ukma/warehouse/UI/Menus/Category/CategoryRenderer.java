package org.ukma.warehouse.UI.Menus.Category;

import org.ukma.warehouse.Models.Category;

import javax.swing.*;
import java.awt.*;

public class CategoryRenderer extends DefaultListCellRenderer {

    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value != null) {
            setText(((Category) value).getName());
        }
        return this;
    }
}
