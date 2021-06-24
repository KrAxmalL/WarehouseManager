package org.example.UI.Menus.Product;

import org.example.Models.Product;
import org.example.UI.Menus.MainWindow.MainWindow;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class DeleteProductMenu extends JFrame {

    private JPanel panel;

    private JComboBox<Product> productsField;
    private JLabel productsLabel;
    private JButton deleteButton;
    private JButton cancelButton;

    public DeleteProductMenu() {
        super("Delete product");
        initComponents();
        initPanel();
        this.getContentPane().setLayout(new FlowLayout());
        this.setSize(400,500);
        this.setBounds(MainWindow.DIMENSION.width / 2 - this.getWidth() / 2, MainWindow.DIMENSION.height / 2 - this.getHeight() / 2, this.getWidth(), this.getHeight());
        this.add(panel);
        //this.setVisible(true);
    }

    private void initComponents() {
        productsField = new JComboBox();
        productsField.setRenderer(new ProductRenderer());
        productsLabel = new JLabel("Choose product to delete");

        deleteButton = new JButton("Delete");
        deleteButton.setSize(200, 50);

        cancelButton = new JButton("Cancel");
        cancelButton.setSize(200, 50);
        //button.addActionListener(new MainWindow.DeleteItemsListener.ItemDeleteActionListener());
    }

    private void initPanel() {
        panel = new JPanel(new GridLayout(0, 1, 0, 10));
        Border border = BorderFactory.createEmptyBorder();
        panel.setBorder(border);
        panel.add(productsLabel);
        panel.add(productsField);
        panel.add(deleteButton);
        panel.add(cancelButton);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JComboBox<Product> getProductsField() {
        return productsField;
    }

    public void setProductsField(JComboBox<Product> productsField) {
        this.productsField = productsField;
    }

    public JLabel getProductsLabel() {
        return productsLabel;
    }

    public void setProductsLabel(JLabel productsLabel) {
        this.productsLabel = productsLabel;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }
}
