package org.ukma.warehouse.UI.Menus.Stock;

import org.ukma.warehouse.Models.Product;
import org.ukma.warehouse.UI.Menus.MainWindow.MainWindow;
import org.ukma.warehouse.UI.Menus.Product.ProductRenderer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class IncreaseProductAmountMenu extends JFrame {

    private JPanel panel;

    private JLabel itemsBoxLabel;
    private JLabel currentStockLabel;
    private JComboBox<Product> itemsBox;
    private JTextField stocksToAdd;
    private JButton okButton;
    private JButton cancelButton;

    public IncreaseProductAmountMenu() {
        super("Increase product amount");
        initComponents();
        initPanel();
        this.getContentPane().setLayout(new FlowLayout());
        this.setSize(400,500);
        this.setBounds(MainWindow.DIMENSION.width / 2 - this.getWidth() / 2, MainWindow.DIMENSION.height / 2 - this.getHeight() / 2, this.getWidth(), this.getHeight());
        this.add(panel);
        //this.setVisible(true);
    }

    private void initComponents() {
        itemsBox = new JComboBox();
        itemsBox.setRenderer(new ProductRenderer());

        itemsBoxLabel = new JLabel("Choose item to change stocks amount");
        currentStockLabel = new JLabel("");

        currentStockLabel.setText("");
        stocksToAdd = new JTextField();
        stocksToAdd.setSize(200, 50);

        okButton = new JButton("Ok");
        okButton.setSize(200, 50);

        cancelButton = new JButton("Cancel");
        cancelButton.setSize(200, 50);
    }

    private void initPanel() {
        panel = new JPanel(new GridLayout(0, 1, 0, 10));
        Border border = BorderFactory.createEmptyBorder();
        panel.setBorder(border);
        panel.add(itemsBoxLabel);
        panel.add(itemsBox);
        panel.add(currentStockLabel);
        panel.add(stocksToAdd);
        panel.add(okButton);
        panel.add(cancelButton);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JLabel getItemsBoxLabel() {
        return itemsBoxLabel;
    }

    public void setItemsBoxLabel(JLabel itemsBoxLabel) {
        this.itemsBoxLabel = itemsBoxLabel;
    }

    public JLabel getCurrentStockLabel() {
        return currentStockLabel;
    }

    public void setCurrentStockLabel(JLabel currentStockLabel) {
        this.currentStockLabel = currentStockLabel;
    }

    public JComboBox<Product> getItemsBox() {
        return itemsBox;
    }

    public void setItemsBox(JComboBox<Product> itemsBox) {
        this.itemsBox = itemsBox;
    }

    public JTextField getStocksToAdd() {
        return stocksToAdd;
    }

    public void setStocksToAdd(JTextField stocksToAdd) {
        this.stocksToAdd = stocksToAdd;
    }

    public JButton getOkButton() {
        return okButton;
    }

    public void setOkButton(JButton okButton) {
        this.okButton = okButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }
}
