package org.ukma.warehouse.UI.Menus.Product;

import org.ukma.warehouse.Models.Product;

import javax.swing.*;
import java.awt.*;

public class ProductRenderer extends DefaultListCellRenderer {

    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value != null) {
            setText(((Product) value).getName());
        }
        return this;
    }
}
