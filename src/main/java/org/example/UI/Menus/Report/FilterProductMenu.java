package org.example.UI.Menus.Report;

import org.example.Models.Category;
import org.example.UI.Menus.MainWindow.MainWindow;
import org.example.UI.Menus.Category.CategoryRenderer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FilterProductMenu extends JFrame {

    private JPanel panel;

    private JTextField textField;
    private JLabel textLabel;

    private JTextField minNumber;
    private JLabel minNumberLabel;
    private JTextField maxNumber;
    private JLabel maxNumberLabel;

    private ButtonGroup fieldButtonGroup;
    private JRadioButton nameButton;
    private JRadioButton producerButton;
    private JRadioButton categoryButton;
    private JRadioButton amountButton;
    private JRadioButton priceButton;
    private JLabel fieldLabel;

    private ButtonGroup orderButtonGroup;
    private JRadioButton ascendButton;
    private JRadioButton descendButton;
    private JLabel orderLabel;

    private JComboBox<Category> categoriesField;
    private JLabel categoriesLabel;

    private JButton filterButton;
    private JButton cancelButton;

    public FilterProductMenu() {
        super("Filter");
        initComponents();
        initPanel();
        this.getContentPane().setLayout(new FlowLayout());
        this.setSize(400,800);
        this.setBounds(MainWindow.DIMENSION.width / 2 - this.getWidth() / 2, MainWindow.DIMENSION.height / 2 - this.getHeight() / 2, this.getWidth(), this.getHeight());
        this.add(panel);
    }

    private void initComponents() {

        textLabel = new JLabel("Search for text fields");
        textField = new JTextField();
        textField.setSize(200, 50);

        minNumberLabel = new JLabel("Minimal number");
        minNumber = new JTextField();
        minNumber.setSize(200, 50);
        maxNumberLabel = new JLabel("Maximal number");
        maxNumber = new JTextField();
        maxNumber.setSize(200, 50);

        categoriesLabel = new JLabel("Category");
        categoriesField = new JComboBox();
        categoriesField.setRenderer(new CategoryRenderer());
        categoriesField.setSize(200, 50);

        fieldLabel = new JLabel("Choose field");
        nameButton = new JRadioButton("name");
        nameButton.setActionCommand(nameButton.getText());
        producerButton = new JRadioButton("producer");
        producerButton.setActionCommand(producerButton.getText());
        categoryButton = new JRadioButton("category");
        categoryButton.setActionCommand(categoryButton.getText());
        amountButton = new JRadioButton("amount");
        amountButton.setActionCommand(amountButton.getText());
        priceButton = new JRadioButton("price");
        priceButton.setActionCommand(priceButton.getText());
        fieldButtonGroup = new ButtonGroup();
        fieldButtonGroup.add(nameButton);
        fieldButtonGroup.add(producerButton);
        fieldButtonGroup.add(categoryButton);
        fieldButtonGroup.add(amountButton);
        fieldButtonGroup.add(priceButton);

        orderLabel = new JLabel("Select order");
        ascendButton = new JRadioButton("ascending");
        ascendButton.setActionCommand(ascendButton.getText());
        descendButton = new JRadioButton("descending");
        descendButton.setActionCommand(descendButton.getText());
        orderButtonGroup = new ButtonGroup();
        orderButtonGroup.add(ascendButton);
        orderButtonGroup.add(descendButton);

        filterButton = new JButton("Filter");
        filterButton.setSize(200, 50);

        cancelButton = new JButton("Cancel");
        cancelButton.setSize(200, 50);
        // button.addActionListener(new MainWindow.NewProductListener.productAddButtonListener());
    }

    private void initPanel() {
        panel = new JPanel(new GridLayout(0, 1, 0, 10));
        Border border = BorderFactory.createEmptyBorder();
        panel.setBorder(border);
        panel.add(textLabel);
        panel.add(textField);
        panel.add(minNumberLabel);
        panel.add(minNumber);
        panel.add(maxNumberLabel);
        panel.add(maxNumber);
        panel.add(categoriesLabel);
        panel.add(categoriesField);
        panel.add(fieldLabel);
        panel.add(nameButton);
        panel.add(producerButton);
        panel.add(categoryButton);
        panel.add(amountButton);
        panel.add(priceButton);
        panel.add(orderLabel);
        panel.add(ascendButton);
        panel.add(descendButton);
        panel.add(filterButton);
        panel.add(cancelButton);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JTextField getTextField() {
        return textField;
    }

    public void setTextField(JTextField textField) {
        this.textField = textField;
    }

    public JLabel getTextLabel() {
        return textLabel;
    }

    public void setTextLabel(JLabel textLabel) {
        this.textLabel = textLabel;
    }

    public JTextField getMinNumber() {
        return minNumber;
    }

    public void setMinNumber(JTextField minNumber) {
        this.minNumber = minNumber;
    }

    public JLabel getMinNumberLabel() {
        return minNumberLabel;
    }

    public void setMinNumberLabel(JLabel minNumberLabel) {
        this.minNumberLabel = minNumberLabel;
    }

    public JTextField getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(JTextField maxNumber) {
        this.maxNumber = maxNumber;
    }

    public JLabel getMaxNumberLabel() {
        return maxNumberLabel;
    }

    public void setMaxNumberLabel(JLabel maxNumberLabel) {
        this.maxNumberLabel = maxNumberLabel;
    }

    public ButtonGroup getFieldButtonGroup() {
        return fieldButtonGroup;
    }

    public void setFieldButtonGroup(ButtonGroup fieldButtonGroup) {
        this.fieldButtonGroup = fieldButtonGroup;
    }

    public JRadioButton getNameButton() {
        return nameButton;
    }

    public void setNameButton(JRadioButton nameButton) {
        this.nameButton = nameButton;
    }

    public JRadioButton getProducerButton() {
        return producerButton;
    }

    public void setProducerButton(JRadioButton producerButton) {
        this.producerButton = producerButton;
    }

    public JRadioButton getCategoryButton() {
        return categoryButton;
    }

    public void setCategoryButton(JRadioButton categoryButton) {
        this.categoryButton = categoryButton;
    }

    public JRadioButton getAmountButton() {
        return amountButton;
    }

    public void setAmountButton(JRadioButton amountButton) {
        this.amountButton = amountButton;
    }

    public JRadioButton getPriceButton() {
        return priceButton;
    }

    public void setPriceButton(JRadioButton priceButton) {
        this.priceButton = priceButton;
    }

    public JLabel getFieldLabel() {
        return fieldLabel;
    }

    public void setFieldLabel(JLabel fieldLabel) {
        this.fieldLabel = fieldLabel;
    }

    public ButtonGroup getOrderButtonGroup() {
        return orderButtonGroup;
    }

    public void setOrderButtonGroup(ButtonGroup orderButtonGroup) {
        this.orderButtonGroup = orderButtonGroup;
    }

    public JRadioButton getAscendButton() {
        return ascendButton;
    }

    public void setAscendButton(JRadioButton ascendButton) {
        this.ascendButton = ascendButton;
    }

    public JRadioButton getDescendButton() {
        return descendButton;
    }

    public void setDescendButton(JRadioButton descendButton) {
        this.descendButton = descendButton;
    }

    public JLabel getOrderLabel() {
        return orderLabel;
    }

    public void setOrderLabel(JLabel orderLabel) {
        this.orderLabel = orderLabel;
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

    public JButton getFilterButton() {
        return filterButton;
    }

    public void setFilterButton(JButton filterButton) {
        this.filterButton = filterButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }
}
