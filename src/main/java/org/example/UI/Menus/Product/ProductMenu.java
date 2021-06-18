package org.example.UI.Menus.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductMenu extends JMenu {

    private JMenuItem newProduct;
    private JMenuItem editProduct;
    private JMenuItem deleteProduct;

    public ProductMenu() {
        super("Product");
        init();
    }

    private void init() {
        newProduct = new JMenuItem("New product");
        editProduct = new JMenuItem("Change product");
        deleteProduct = new JMenuItem("Delete product");

        this.add(newProduct);
        this.add(editProduct);
        this.add(deleteProduct);
    }

    public JMenuItem getNewProduct() {
        return newProduct;
    }

    public void setNewProduct(JMenuItem newProduct) {
        this.newProduct = newProduct;
    }

    public JMenuItem getEditProduct() {
        return editProduct;
    }

    public void setEditProduct(JMenuItem editProduct) {
        this.editProduct = editProduct;
    }

    public JMenuItem getDeleteProduct() {
        return deleteProduct;
    }

    public void setDeleteProduct(JMenuItem deleteProduct) {
        this.deleteProduct = deleteProduct;
    }
}
