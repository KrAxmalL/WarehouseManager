package org.ukma.warehouse.UI.Menus.Authorization;

import org.ukma.warehouse.UI.Menus.MainWindow.MainWindow;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class LoginMenu extends JFrame{

    private JPanel panel;

    private JLabel loginLabel;
    private JTextField loginField;

    private JLabel passwordLabel;
    private JPasswordField passwordField;

    private JButton loginButton;
    private JButton goToRegistrationButton;

    public LoginMenu() {
        super("Login");

        this.getContentPane().setLayout(new BorderLayout());
        this.setSize(300,300);
        this.setBounds(MainWindow.DIMENSION.width / 2 - this.getWidth() / 2, MainWindow.DIMENSION.height / 2 - this.getHeight() / 2, this.getWidth(), this.getHeight());

        initComponents();
        initPanel();

        this.setVisible(true);
    }
    private void initComponents() {
        loginLabel = new JLabel("Login");
        loginField = new JTextField();
        loginField.setSize(200, 30);

        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();
        passwordField.setSize(200, 30);

        loginButton = new JButton("Login");
        loginButton.setSize(200, 30);

        goToRegistrationButton = new JButton("Go to registration");
        goToRegistrationButton.setSize(200, 30);
    }

    private void initPanel() {
        panel = new JPanel(new GridLayout(6, 1));
        Border border = BorderFactory.createEmptyBorder();
        panel.setBorder(border);

        panel.add(loginLabel);
        panel.add(loginField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(goToRegistrationButton);

        this.add(panel, BorderLayout.CENTER);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JLabel getLoginLabel() {
        return loginLabel;
    }

    public void setLoginLabel(JLabel loginLabel) {
        this.loginLabel = loginLabel;
    }

    public JTextField getLoginField() {
        return loginField;
    }

    public void setLoginField(JTextField loginField) {
        this.loginField = loginField;
    }

    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    public void setPasswordLabel(JLabel passwordLabel) {
        this.passwordLabel = passwordLabel;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(JButton loginButton) {
        this.loginButton = loginButton;
    }

    public JButton getGoToRegistrationButton() {
        return goToRegistrationButton;
    }

    public void setGoToRegistrationButton(JButton goToRegistrationButton) {
        this.goToRegistrationButton = goToRegistrationButton;
    }
}
