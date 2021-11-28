package org.ukma.warehouse.Controllers.Main;

import org.ukma.warehouse.Controllers.Authorization.LoginController;
import org.ukma.warehouse.Controllers.Authorization.RegistrationController;
import org.ukma.warehouse.Controllers.MainWindow.MainWindowController;
import org.ukma.warehouse.Network.Context.GlobalContext;
import org.ukma.warehouse.UI.Menus.MainWindow.MainWindow;
import org.ukma.warehouse.UI.Menus.Authorization.LoginMenu;
import org.ukma.warehouse.UI.Menus.Authorization.RegistrationMenu;

public class MainController {

    private LoginController loginController;
    private RegistrationController registrationController;
    private MainWindowController mainWindowController;

    private LoginMenu loginMenu;
    private RegistrationMenu registrationMenu;
    private MainWindow mainWindow;

    public static GlobalContext globalContext;

    public MainController() {
        globalContext = new GlobalContext();
        initView();
        initControllers();
    }

    private void initView() {
        loginMenu = new LoginMenu();
        registrationMenu = new RegistrationMenu();
        mainWindow = new MainWindow();
    }

    private void initControllers() {
       loginController = new LoginController(loginMenu, this::goToMainWindow, this::goToRegistration);
       registrationController = new RegistrationController(registrationMenu, this::goToLogin);
       mainWindowController = new MainWindowController(mainWindow);
    }

    private void goToRegistration() {
        loginMenu.setVisible(false);
        registrationController.showView();
    }

    private void goToLogin() {
        registrationMenu.setVisible(false);
        loginController.showView();
    }

    private void goToMainWindow() {
        loginMenu.setVisible(false);
        registrationMenu.setVisible(false);
        mainWindowController.showView();
    }
}
