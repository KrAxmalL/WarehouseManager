package org.example.UI.Menus.Product;

import org.example.UI.Category;
import org.example.UI.UserInterface;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class AddProductMenu extends JFrame {

    private JPanel panel;

    private JTextField itemNameField;
    private JLabel itemNameLabel;
    private JTextField itemDescriptionField;
    private JLabel itemDescriptionLabel;
    private JTextField itemSupplierField;
    private JLabel itemSupplierLabel;
    private JSpinner itemPriceField;
    private JLabel itemPriceLabel;
    private JComboBox<Category> categoriesField;
    private JLabel categoriesLabel;
    private JButton button;

    public AddProductMenu() {
        super("New Product");
        initComponents();
        initPanel();
        this.getContentPane().setLayout(new FlowLayout());
        this.setSize(400,500);
        //this.setBounds(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getHeight() / 2, frame.getWidth(), frame.getHeight());
        this.add(panel);
        this.setVisible(true);
    }

    private void initComponents() {
        itemNameField = new JTextField();
        itemNameField.setSize(200, 50);
        itemNameLabel = new JLabel("New item name");

        itemDescriptionField = new JTextField();
        itemDescriptionField.setSize(200, 50);
        itemDescriptionLabel = new JLabel("New item description");

        itemSupplierField = new JTextField();
        itemSupplierField.setSize(200, 50);
        itemSupplierLabel = new JLabel("New item supplier");

        itemPriceField = new JSpinner(new SpinnerNumberModel(10.0, 1.0, 1000.0, 1.0));
        itemPriceLabel = new JLabel("Price");

        categoriesField = new JComboBox();
        //categoriesField.setRenderer(new UserInterface.CategoryRenderer());
        categoriesLabel = new JLabel("Category");

        button = new JButton("Add");
        button.setSize(200, 50);
       // button.addActionListener(new UserInterface.NewItemListener.itemAddButtonListener());
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
        panel.add(categoriesLabel);
        panel.add(categoriesField);
        panel.add(button);
    }
}
