package org.ukma.warehouse.Controllers.Authorization;

import org.ukma.warehouse.Interfaces.IViewChanger;
import org.ukma.warehouse.Models.Message;
import org.ukma.warehouse.Models.User;
import org.ukma.warehouse.Network.Context.GlobalContext;
import org.ukma.warehouse.Services.UserService;
import org.ukma.warehouse.UI.Menus.Authorization.LoginMenu;
import org.ukma.warehouse.Utils.CommandTypeEncoder;

import javax.swing.*;
import java.io.IOException;

public class LoginController {

    private LoginMenu loginMenu;

    private IViewChanger showMainWindow;
    private IViewChanger showRegistrationWindow;

    private static final int LOGIN_COMMAND = CommandTypeEncoder.USER ^ CommandTypeEncoder.READ;

    public LoginController(LoginMenu loginMenu, IViewChanger showMainWindow, IViewChanger showRegistrationWindow) {
        this.loginMenu = loginMenu;
        this.showMainWindow = showMainWindow;
        this.showRegistrationWindow = showRegistrationWindow;
        initView();
    }

    public void showView() {
        fillFields();
        loginMenu.setVisible(true);
    }

    private void initView() {
        loginMenu.getLoginButton().addActionListener(e -> login());
        loginMenu.getGoToRegistrationButton().addActionListener(e -> goToRegistration());
    }

    private void fillFields() {
        loginMenu.getLoginField().setText("");
        loginMenu.getPasswordField().setText("");
    }

    private void login() {
        String login = loginMenu.getLoginField().getText();
        if(login != null && !login.isEmpty()) {
            String password = String.valueOf(loginMenu.getPasswordField().getPassword());
            if(password != null && !password.isEmpty()) {
                User toLogin = new User(login, password);
                System.out.println(toLogin);
                try {
                    GlobalContext.client.sendRequest(LOGIN_COMMAND, UserService.userToJson(toLogin));
                    Message response = GlobalContext.client.getResponse();
                    System.out.println(response);
                    if (response.getMessage().equals("ok")) {
                        JOptionPane.showMessageDialog(null, "Successful login!");
                        showMainWindow.changeView();
                    } else {
                        JOptionPane.showMessageDialog(null, "Can't login user! Check data!");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Wrong input!");
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Wrong input!");
        }
    }

    private void goToRegistration() {
        showRegistrationWindow.changeView();
    }
}
