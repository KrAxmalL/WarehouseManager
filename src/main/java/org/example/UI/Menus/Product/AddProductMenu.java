package org.example.UI.Menus.Product;

import org.example.Models.Category;
import org.example.UI.Menus.MainWindow.MainWindow;
import org.example.UI.Menus.Category.CategoryRenderer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class AddProductMenu extends JFrame {

    private JPanel panel;

    private JTextField productNameField;
    private JLabel productNameLabel;
    private JTextField productDescriptionField;
    private JLabel productDescriptionLabel;
    private JTextField productSupplierField;
    private JLabel productSupplierLabel;
    private JTextField productPriceField;
    private JLabel productPriceLabel;
    private JComboBox<Category> categoriesField;
    private JLabel categoriesLabel;

    private JButton addButton;
    private JButton cancelButton;

    public AddProductMenu() {
        super("New Product");
        initComponents();
        initPanel();
        this.getContentPane().setLayout(new FlowLayout());
        this.setSize(400,500);
        this.setBounds(MainWindow.DIMENSION.width / 2 - this.getWidth() / 2, MainWindow.DIMENSION.height / 2 - this.getHeight() / 2, this.getWidth(), this.getHeight());
        this.add(panel);
        //this.setVisible(true);
    }

    private void initComponents() {
        productNameField = new JTextField();
        productNameField.setSize(200, 50);
        productNameLabel = new JLabel("New product name");

        productDescriptionField = new JTextField();
        productDescriptionField.setSize(200, 50);
        productDescriptionLabel = new JLabel("New product description");

        productSupplierField = new JTextField();
        productSupplierField.setSize(200, 50);
        productSupplierLabel = new JLabel("New product supplier");

        productPriceField = new JTextField();
        productPriceField.setSize(200, 50);
        productPriceLabel = new JLabel("Price");

        categoriesField = new JComboBox();
        categoriesField.setRenderer(new CategoryRenderer());
        categoriesLabel = new JLabel("Category");

        addButton = new JButton("Add");
        addButton.setSize(200, 50);

        cancelButton = new JButton("Cancel");
        cancelButton.setSize(200, 50);
       // button.addActionListener(new MainWindow.NewProductListener.productAddButtonListener());
    }

    private void initPanel() {
        panel = new JPanel(new GridLayout(0, 1, 0, 10));
        Border border = BorderFactory.createEmptyBorder();
        panel.setBorder(border);
        panel.add(productNameLabel);
        panel.add(productNameField);
        panel.add(productDescriptionLabel);
        panel.add(productDescriptionField);
        panel.add(productSupplierLabel);
        panel.add(productSupplierField);
        panel.add(productPriceLabel);
        panel.add(productPriceField);
        panel.add(categoriesLabel);
        panel.add(categoriesField);
        panel.add(addButton);
        panel.add(cancelButton);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JTextField getProductNameField() {
        return productNameField;
    }

    public void setProductNameField(JTextField productNameField) {
        this.productNameField = productNameField;
    }

    public JLabel getProductNameLabel() {
        return productNameLabel;
    }

    public void setProductNameLabel(JLabel productNameLabel) {
        this.productNameLabel = productNameLabel;
    }

    public JTextField getProductDescriptionField() {
        return productDescriptionField;
    }

    public void setProductDescriptionField(JTextField productDescriptionField) {
        this.productDescriptionField = productDescriptionField;
    }

    public JLabel getProductDescriptionLabel() {
        return productDescriptionLabel;
    }

    public void setProductDescriptionLabel(JLabel productDescriptionLabel) {
        this.productDescriptionLabel = productDescriptionLabel;
    }

    public JTextField getProductSupplierField() {
        return productSupplierField;
    }

    public void setProductSupplierField(JTextField productSupplierField) {
        this.productSupplierField = productSupplierField;
    }

    public JLabel getProductSupplierLabel() {
        return productSupplierLabel;
    }

    public void setProductSupplierLabel(JLabel productSupplierLabel) {
        this.productSupplierLabel = productSupplierLabel;
    }

    public JTextField getProductPriceField() {
        return productPriceField;
    }

    public void setProductPriceField(JTextField productPriceField) {
        this.productPriceField = productPriceField;
    }

    public JLabel getProductPriceLabel() {
        return productPriceLabel;
    }

    public void setProductPriceLabel(JLabel productPriceLabel) {
        this.productPriceLabel = productPriceLabel;
    }

    public JComboBox<Category> getCategoriesField() {
        return categoriesField;
    }

    public void setCategoriesField(JComboBox<Category> categoriesField) {
        this.categoriesField = categoriesField;
    }

    public JLabel getCategoriesLabel() {
        return categoriesLabel;
    }

    public void setCategoriesLabel(JLabel categoriesLabel) {
        this.categoriesLabel = categoriesLabel;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }
}
