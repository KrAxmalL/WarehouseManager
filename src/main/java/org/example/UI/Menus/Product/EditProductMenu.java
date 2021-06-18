package org.example.UI.Menus.Product;

import org.example.UI.Item;
import org.example.UI.UserInterface;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class EditProductMenu extends JFrame {

    private JPanel panel;

    private JTextField itemNameField;
    private JLabel itemNameLabel;
    private JTextField itemDescriptionField;
    private JLabel itemDescriptionLabel;
    private JTextField itemSupplierField;
    private JLabel itemSupplierLabel;
    private JSpinner itemPriceField;
    private JLabel itemPriceLabel;
    private JButton button;

    public EditProductMenu() {
        super("Edit Product");
        initComponents();
        initPanel();

        this.getContentPane().setLayout(new FlowLayout());
        this.setSize(400,500);
        //this.setBounds(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getHeight() / 2, frame.getWidth(), frame.getHeight());
        this.add(panel);
        this.setVisible(true);
    }

    private void initComponents() {
        //Krystya selectedItem -> (Item) itemsField.getSelectedItem()
        //itemNameField = new JTextField(selectedItem .getName());
        itemNameField = new JTextField();

        itemNameField.setSize(200, 50);
        itemNameLabel = new JLabel("New item name");

        //Krystya selectedItem -> (Item) itemsField.getSelectedItem()
        //itemDescriptionField = new JTextField(selectedItem.getDescription());
        itemDescriptionField = new JTextField();

        itemDescriptionField.setSize(200, 50);
        itemDescriptionLabel = new JLabel("New item description");

        //Krystya selectedItem -> (Item) itemsField.getSelectedItem()
        //itemSupplierField = new JTextField(selectedItem.getSupplier());
        itemSupplierField = new JTextField();
        itemSupplierField.setSize(200, 50);
        itemSupplierLabel = new JLabel("New item supplier");

        //Krystya selectedItem -> (Item) itemsField.getSelectedItem()
        //itemPriceField = new JSpinner(new SpinnerNumberModel(selectedItem.getPrice(), 1.0, 1000.0, 1.0));
        itemPriceField = new JSpinner();
        itemPriceLabel = new JLabel("Price");

        button = new JButton("Edit");
        button.setSize(200, 50);
       // button.addActionListener(new UserInterface.ChangeItemsListener.ItemEditActionListener.itemUpdateButtonListener());
    }

    private void initPanel() {
        panel = new JPanel(new GridLayout(0, 1, 0, 10));
        Border border = BorderFactory.createEmptyBorder();
        panel.setBorder(border);
        panel.add(itemNameLabel);
        panel.add(itemNameField);
        panel.add(itemDescriptionLabel);
        panel.add(itemDescriptionField);
        panel.add(itemSupplierLabel);
        panel.add(itemSupplierField);
        panel.add(itemPriceLabel);
        panel.add(itemPriceField);
        panel.add(button);
    }
}
