package org.example.UI.Menus.Product;

import org.example.Models.Category;
import org.example.Models.Product;
import org.example.UI.MainWindow;
import org.example.UI.Menus.Category.CategoryRenderer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class EditProductMenu extends JFrame {

    private JPanel panel;

    private JLabel productLabel;
    private JComboBox<Product> productField;
    private JTextField productNameField;
    private JLabel productNameLabel;
    private JTextField productDescriptionField;
    private JLabel productDescriptionLabel;
    private JTextField productSupplierField;
    private JLabel productSupplierLabel;
    private JSpinner productPriceField;
    private JLabel productPriceLabel;
    private JLabel categoryLabel;
    private JComboBox<Category> categoryField;
    private JButton editButton;
    private JButton cancelButton;

    public EditProductMenu() {
        super("Edit Product");
        initComponents();
        initPanel();

        this.getContentPane().setLayout(new FlowLayout());
        this.setSize(400,550);
        this.setBounds(MainWindow.DIMENSION.width / 2 - this.getWidth() / 2, MainWindow.DIMENSION.height / 2 - this.getHeight() / 2, this.getWidth(), this.getHeight());
        this.add(panel);
        //this.setVisible(true);
    }

    private void initComponents() {
        productLabel = new JLabel("Choose product to edit");
        productField = new JComboBox<Product>();
        productField.setRenderer(new ProductRenderer());

        productNameField = new JTextField();
        productNameField.setSize(200, 50);
        productNameLabel = new JLabel("New product name");

        productDescriptionField = new JTextField();
        productDescriptionField.setSize(200, 50);
        productDescriptionLabel = new JLabel("New product description");

        productSupplierField = new JTextField();
        productSupplierField.setSize(200, 50);
        productSupplierLabel = new JLabel("New product supplier");

        productPriceField = new JSpinner(new SpinnerNumberModel(10.0, 0.01, 1000000.0, 0.01));
        productPriceLabel = new JLabel("Price");

        categoryLabel = new JLabel("New category");
        categoryField = new JComboBox<>();
        categoryField.setRenderer(new CategoryRenderer());

        editButton = new JButton("Edit");
        editButton.setSize(200, 50);

        cancelButton = new JButton("Cancel");
        cancelButton.setSize(200, 50);
    }

    private void initPanel() {
        panel = new JPanel(new GridLayout(0, 1, 0, 10));
        Border border = BorderFactory.createEmptyBorder();
        panel.setBorder(border);
        panel.add(productLabel);
        panel.add(productField);
        panel.add(productNameLabel);
        panel.add(productNameField);
        panel.add(productDescriptionLabel);
        panel.add(productDescriptionField);
        panel.add(productSupplierLabel);
        panel.add(productSupplierField);
        panel.add(productPriceLabel);
        panel.add(productPriceField);
        panel.add(categoryLabel);
        panel.add(categoryField);
        panel.add(editButton);
        panel.add(cancelButton);
    }

    public JLabel getProductLabel() {
        return productLabel;
    }

    public void setProductLabel(JLabel productLabel) {
        this.productLabel = productLabel;
    }

    public JComboBox<Product> getProductField() {
        return productField;
    }

    public void setProductField(JComboBox<Product> productField) {
        this.productField = productField;
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

    public JSpinner getProductPriceField() {
        return productPriceField;
    }

    public void setProductPriceField(JSpinner productPriceField) {
        this.productPriceField = productPriceField;
    }

    public JLabel getProductPriceLabel() {
        return productPriceLabel;
    }

    public void setProductPriceLabel(JLabel productPriceLabel) {
        this.productPriceLabel = productPriceLabel;
    }

    public JLabel getCategoryLabel() {
        return categoryLabel;
    }

    public void setCategoryLabel(JLabel categoryLabel) {
        this.categoryLabel = categoryLabel;
    }

    public JComboBox<Category> getCategoryField() {
        return categoryField;
    }

    public void setCategoryField(JComboBox<Category> categoryField) {
        this.categoryField = categoryField;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public void setEditButton(JButton editButton) {
        this.editButton = editButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }
}
