package org.ukma.warehouse.Controllers.Authorization;

import org.ukma.warehouse.Interfaces.IViewChanger;
import org.ukma.warehouse.Models.Message;
import org.ukma.warehouse.Models.User;
import org.ukma.warehouse.Network.Context.GlobalContext;
import org.ukma.warehouse.Services.UserService;
import org.ukma.warehouse.UI.Menus.Authorization.RegistrationMenu;
import org.ukma.warehouse.Utils.CommandTypeEncoder;

import javax.swing.*;
import java.io.IOException;

public class RegistrationController {

    private RegistrationMenu registrationMenu;

    private IViewChanger showLoginWindow;

    private static final int REGISTER_COMMAND = CommandTypeEncoder.USER ^ CommandTypeEncoder.CREATE;

    public RegistrationController(RegistrationMenu registrationMenu, IViewChanger showLoginWindow) {
        this.registrationMenu = registrationMenu;
        this.showLoginWindow = showLoginWindow;
        initView();
    }

    public void showView() {
        fillFields();
        registrationMenu.setVisible(true);
    }

    private void initView() {
        registrationMenu.getRegisterButton().addActionListener(e -> register());
        registrationMenu.getGoToLoginButton().addActionListener(e -> goToLogin());
    }

    private void fillFields() {
        registrationMenu.getLoginField().setText("");
        registrationMenu.getPasswordField().setText("");
        registrationMenu.getRepeatPasswordField().setText("");
    }

    private void register() {
        String login = registrationMenu.getLoginField().getText();
        if(login != null && !login.isEmpty()) {
            String password = String.valueOf(registrationMenu.getPasswordField().getPassword());
            if(password != null && !password.isEmpty()) {
                String repeatedPassword = String.valueOf(registrationMenu.getRepeatPasswordField().getPassword());
                if (repeatedPassword != null && !repeatedPassword.isEmpty() && repeatedPassword.equals(password)) {
                    User toRegister = new User(login, password);
                    System.out.println(toRegister);
                    try {
                        GlobalContext.client.sendRequest(REGISTER_COMMAND, UserService.userToJson(toRegister));
                        Message response = GlobalContext.client.getResponse();
                        System.out.println(response);
                        if (response.getMessage().equals("ok")) {
                            JOptionPane.showMessageDialog(null, "Successful registration! Please, log in!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Can't register user! Check data!");
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
        else {
            JOptionPane.showMessageDialog(null, "Wrong input!");
        }
    }

    private void goToLogin() {
        showLoginWindow.changeView();
    }
}
