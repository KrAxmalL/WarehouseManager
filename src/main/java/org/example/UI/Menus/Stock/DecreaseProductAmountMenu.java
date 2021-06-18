package org.example.UI.Menus.Stock;

import org.example.Models.Product;
import org.example.UI.MainWindow;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class DecreaseProductAmountMenu extends JFrame {

    private JPanel panel;

    private JLabel itemsBoxLabel;
    private JLabel clarificationLabel;
    private JLabel currentStockLabel;
    private JComboBox<Product> itemsBox;
    private JSpinner stocksToAdd;
    private JButton okButton;
    private JButton cancelButton;

    public DecreaseProductAmountMenu() {
        super("Decrease product amount");
        initComponents();
        initPanel();
        this.getContentPane().setLayout(new FlowLayout());
        this.setSize(400,500);
        this.setBounds(MainWindow.DIMENSION.width / 2 - this.getWidth() / 2, MainWindow.DIMENSION.height / 2 - this.getHeight() / 2, this.getWidth(), this.getHeight());
        this.add(panel);
        this.setVisible(true);
    }

    private void initComponents() {
        itemsBox = new JComboBox();
        //itemsBox.setRenderer(new MainWindow.ItemRenderer());
        /*itemsBox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                selectedItem = (Item)itemsBox.getSelectedItem();
                currentStockLabel.setText("Current stock qty: " + selectedItem.getStock());
                stocksToAdd.setModel(new SpinnerNumberModel(1, selectedItem.getStock() * -1, selectedItem != null ? 10000 - selectedItem.getStock() : 10000, 1));
            }
        });*/
        itemsBoxLabel = new JLabel("Choose item to change stocks amount");
        clarificationLabel = new JLabel("(positive number adds stocks, negative � writes them off)");
        currentStockLabel = new JLabel("");

        //selectedItem = (Item)itemsBox.getSelectedItem();
        currentStockLabel.setText("Current stock qty: " + 1);
        stocksToAdd = new JSpinner(/*new SpinnerNumberModel(1, selectedItem.getStock() * -1, selectedItem != null ? 10000 - selectedItem.getStock() : 10000, 1)*/);

        okButton = new JButton("Ok");
        okButton.setSize(200, 50);

        cancelButton = new JButton("Cancel");
        cancelButton.setSize(200, 50);
        //button.addActionListener(new MainWindow.ManipulateStocksListener.StocksAddActionListener());
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

    public JLabel getClarificationLabel() {
        return clarificationLabel;
    }

    public void setClarificationLabel(JLabel clarificationLabel) {
        this.clarificationLabel = clarificationLabel;
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

    public JSpinner getStocksToAdd() {
        return stocksToAdd;
    }

    public void setStocksToAdd(JSpinner stocksToAdd) {
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
