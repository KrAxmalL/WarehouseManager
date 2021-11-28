package org.ukma.warehouse.UI.Menus.Category;

import org.ukma.warehouse.UI.Menus.MainWindow.MainWindow;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class AddCategoryMenu extends JFrame {

    private JPanel panel;

    private JTextField categoryNameField;
    private JLabel categoryNameLabel;
    private JTextField categoryDescriptionField;
    private JLabel categoryDescriptionLabel;
    private JButton addButton;
    private JButton cancelButton;

    public AddCategoryMenu() {
        super("New Category");
        initComponents();
        initPanel();

        this.getContentPane().setLayout(new FlowLayout());
        this.setSize(300,300);
        this.setBounds(MainWindow.DIMENSION.width / 2 - this.getWidth() / 2, MainWindow.DIMENSION.height / 2 - this.getHeight() / 2, this.getWidth(), this.getHeight());
        this.add(panel);
        //this.setVisible(true);
    }

    private void initComponents() {
        categoryNameField = new JTextField();
        categoryNameField.setSize(200, 50);
        categoryNameLabel = new JLabel("New category name");

        categoryDescriptionField = new JTextField();
        categoryDescriptionField.setSize(200, 50);
        categoryDescriptionLabel = new JLabel("New category description");

        addButton = new JButton("Add");
        addButton.setSize(200, 50);

        cancelButton = new JButton("Cancel");
        cancelButton.setSize(200, 50);
        //button.addActionListener(new MainWindow.NewCategoryListener.categoryAddButtonListener());
    }

    private void initPanel() {
        panel = new JPanel(new GridLayout(0, 1, 0, 10));
        Border border = BorderFactory.createEmptyBorder();
        panel.setBorder(border);
        panel.add(categoryNameLabel);
        panel.add(categoryNameField);
        panel.add(categoryDescriptionLabel);
        panel.add(categoryDescriptionField);
        panel.add(addButton);
        panel.add(cancelButton);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JTextField getCategoryNameField() {
        return categoryNameField;
    }

    public void setCategoryNameField(JTextField categoryNameField) {
        this.categoryNameField = categoryNameField;
    }

    public JLabel getCategoryNameLabel() {
        return categoryNameLabel;
    }

    public void setCategoryNameLabel(JLabel categoryNameLabel) {
        this.categoryNameLabel = categoryNameLabel;
    }

    public JTextField getCategoryDescriptionField() {
        return categoryDescriptionField;
    }

    public void setCategoryDescriptionField(JTextField categoryDescriptionField) {
        this.categoryDescriptionField = categoryDescriptionField;
    }

    public JLabel getCategoryDescriptionLabel() {
        return categoryDescriptionLabel;
    }

    public void setCategoryDescriptionLabel(JLabel categoryDescriptionLabel) {
        this.categoryDescriptionLabel = categoryDescriptionLabel;
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
