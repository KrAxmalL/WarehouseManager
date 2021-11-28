package org.ukma.warehouse.UI.Menus.Authorization;

import org.ukma.warehouse.UI.Menus.MainWindow.MainWindow;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class RegistrationMenu extends JFrame {

    private JPanel panel;

    private JLabel loginLabel;
    private JTextField loginField;

    private JLabel passwordLabel;
    private JPasswordField passwordField;

    private JLabel repeatPasswordLabel;
    private JPasswordField repeatPasswordField;

    private JButton registerButton;
    private JButton goToLoginButton;

    public RegistrationMenu() {
        super("Register");

        this.getContentPane().setLayout(new BorderLayout());
        this.setSize(300,400);
        this.setBounds(MainWindow.DIMENSION.width / 2 - this.getWidth() / 2, MainWindow.DIMENSION.height / 2 - this.getHeight() / 2, this.getWidth(), this.getHeight());

        initComponents();
        initPanel();
    }
    private void initComponents() {
        loginLabel = new JLabel("Login");
        loginField = new JTextField();
        loginField.setSize(200, 30);

        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();
        passwordField.setSize(200, 30);

        repeatPasswordLabel = new JLabel("Password");
        repeatPasswordField = new JPasswordField();
        repeatPasswordField.setSize(200, 30);

        registerButton = new JButton("Register");
        registerButton.setSize(200, 30);

        goToLoginButton = new JButton("Go to login");
        goToLoginButton.setSize(200, 30);
    }

    private void initPanel() {
        panel = new JPanel(new GridLayout(8, 1));
        Border border = BorderFactory.createEmptyBorder();
        panel.setBorder(border);

        panel.add(loginLabel);
        panel.add(loginField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(repeatPasswordLabel);
        panel.add(repeatPasswordField);
        panel.add(registerButton);
        panel.add(goToLoginButton);

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

    public JLabel getRepeatPasswordLabel() {
        return repeatPasswordLabel;
    }

    public void setRepeatPasswordLabel(JLabel repeatPasswordLabel) {
        this.repeatPasswordLabel = repeatPasswordLabel;
    }

    public JPasswordField getRepeatPasswordField() {
        return repeatPasswordField;
    }

    public void setRepeatPasswordField(JPasswordField repeatPasswordField) {
        this.repeatPasswordField = repeatPasswordField;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public void setRegisterButton(JButton registerButton) {
        this.registerButton = registerButton;
    }

    public JButton getGoToLoginButton() {
        return goToLoginButton;
    }

    public void setGoToLoginButton(JButton goToLoginButton) {
        this.goToLoginButton = goToLoginButton;
    }
}
