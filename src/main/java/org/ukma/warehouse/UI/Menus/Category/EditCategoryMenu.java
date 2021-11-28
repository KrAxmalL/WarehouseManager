package org.ukma.warehouse.UI.Menus.Category;

import org.ukma.warehouse.Models.Category;
import org.ukma.warehouse.UI.Menus.MainWindow.MainWindow;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class EditCategoryMenu extends JFrame {

    private JPanel panel;

    private JLabel categoryLabel;
    private JComboBox<Category> categoryField;
    private JTextField categoryNameField;
    private JLabel categoryNameLabel;
    private JTextField categoryDescriptionField;
    private JLabel categoryDescriptionLabel;
    private JButton editButton;
    private JButton cancelButton;

    public EditCategoryMenu() {
        super("Edit Category");
        initComponents();
        initPanel();

        this.getContentPane().setLayout(new FlowLayout());
        this.setSize(400,500);
        this.setBounds(MainWindow.DIMENSION.width / 2 - this.getWidth() / 2, MainWindow.DIMENSION.height / 2 - this.getHeight() / 2, this.getWidth(), this.getHeight());
        this.add(panel);
        //this.setVisible(true);
    }

    private void initComponents() {

        categoryLabel = new JLabel("Choose category to edit");
        categoryField = new JComboBox<>();
        categoryField.setRenderer(new CategoryRenderer());
        categoryNameField = new JTextField();

        categoryNameField.setSize(200, 50);
        categoryNameLabel = new JLabel("New category name");

        //categoryDescriptionField = new JTextField(selectedCategory.getDescription());
        //Khrystya selectedCategory.getDescription() -> ((Category) categoriesField.getSelectedItem()).getDescription()
        categoryDescriptionField = new JTextField();
        categoryDescriptionField.setSize(200, 50);
        categoryDescriptionLabel = new JLabel("New category description");

        editButton = new JButton("Edit");
        editButton.setSize(200, 50);

        cancelButton = new JButton("Cancel");
        cancelButton.setSize(200, 50);
        //button.addActionListener(new MainWindow.ChangeCategoryListener.CategoryEditActionListener.CategoryUpdateButtonListener());
    }

    private void initPanel() {
        panel = new JPanel(new GridLayout(0, 1, 0, 10));
        Border border = BorderFactory.createEmptyBorder();
        panel.setBorder(border);
        panel.add(categoryLabel);
        panel.add(categoryField);
        panel.add(categoryNameLabel);
        panel.add(categoryNameField);
        panel.add(categoryDescriptionLabel);
        panel.add(categoryDescriptionField);
        panel.add(editButton);
        panel.add(cancelButton);
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
