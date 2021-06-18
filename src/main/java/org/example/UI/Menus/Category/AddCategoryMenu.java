package org.example.UI.Menus.Category;

import org.example.UI.UserInterface;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class AddCategoryMenu extends JFrame {

    private JPanel panel;

    private JTextField categoryNameField;
    private JLabel categoryNameLabel;
    private JTextField categoryDescriptionField;
    private JLabel categoryDescriptionLabel;
    private JButton button;

    public AddCategoryMenu() {
        super("New Category");
        initComponents();
        initPanel();

        this.getContentPane().setLayout(new FlowLayout());
        this.setSize(300,300);
        //this.setBounds(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getHeight() / 2, frame.getWidth(), frame.getHeight());
        this.add(panel);
        this.setVisible(true);
    }

    private void initComponents() {
        categoryNameField = new JTextField();
        categoryNameField.setSize(200, 50);
        categoryNameLabel = new JLabel("New category name");

        categoryDescriptionField = new JTextField();
        categoryDescriptionField.setSize(200, 50);
        categoryDescriptionLabel = new JLabel("New category description");

        button = new JButton("Add");
        button.setSize(200, 50);
        //button.addActionListener(new UserInterface.NewCategoryListener.categoryAddButtonListener());
    }

    private void initPanel() {
        panel = new JPanel(new GridLayout(0, 1, 0, 10));
        Border border = BorderFactory.createEmptyBorder();
        panel.setBorder(border);
        panel.add(categoryNameLabel);
        panel.add(categoryNameField);
        panel.add(categoryDescriptionLabel);
        panel.add(categoryDescriptionField);
        panel.add(button);
    }
}
