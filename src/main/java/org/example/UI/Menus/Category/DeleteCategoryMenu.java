package org.example.UI.Menus.Category;

import org.example.Models.Category;
import org.example.UI.MainWindow;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class DeleteCategoryMenu extends JFrame {

    private JPanel panel;

    private JComboBox<Category> categoriesField;
    private JLabel categoriesLabel;
    private JButton deleteButton;
    private JButton cancelButton;

    public DeleteCategoryMenu() {
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
        categoriesField = new JComboBox();
        categoriesField.setRenderer(new CategoryRenderer());
        categoriesLabel = new JLabel("Choose category to delete");

        deleteButton = new JButton("Delete");
        deleteButton.setSize(200, 50);

        cancelButton = new JButton("Cancel");
        cancelButton.setSize(200, 50);
        //button.addActionListener(new MainWindow.DeleteCategoryListener.CategoryDeleteActionListener());
    }

    private void initPanel() {
        panel = new JPanel(new GridLayout(0, 1, 0, 10));
        Border border = BorderFactory.createEmptyBorder();
        panel.setBorder(border);
        panel.add(categoriesLabel);
        panel.add(categoriesField);
        panel.add(deleteButton);
        panel.add(cancelButton);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
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
