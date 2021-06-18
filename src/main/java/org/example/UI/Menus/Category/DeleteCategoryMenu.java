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
        this.setVisible(true);
    }

    private void initComponents() {
        categoriesField = new JComboBox(/*new DefaultComboBoxModel(warehouse.getAllCategories().toArray())*/);
        //categoriesField.setRenderer(new MainWindow.CategoryRenderer());
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
}
