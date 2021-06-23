package org.example.UI.Menus.Report;

import org.example.UI.MainWindow;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class StatisticsMenu extends JFrame {

    private JPanel panel;

    private JScrollPane scrollPane;
    private JTextArea textArea;

    public StatisticsMenu() {
        super("Statistics");
        this.getContentPane().setLayout(new BorderLayout());
        this.setSize(500,600);
        this.setBounds(MainWindow.DIMENSION.width / 2 - this.getWidth() / 2, MainWindow.DIMENSION.height / 2 - this.getHeight() / 2, this.getWidth(), this.getHeight());

        initComponents();
        initPanel();
    }

    private void initComponents() {
        textArea = new JTextArea();
        textArea.setEditable(false);

        scrollPane = new JScrollPane();
        scrollPane.setViewportView(textArea);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    }

    private void initPanel() {
        panel = new JPanel(new BorderLayout());
        Border border = BorderFactory.createEmptyBorder();
        panel.setBorder(border);

        panel.add(scrollPane, BorderLayout.CENTER);

        this.add(panel, BorderLayout.CENTER);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }
}
