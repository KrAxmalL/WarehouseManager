package org.example.Controllers.Main;

import org.example.Controllers.Category.MainCategoryController;
import org.example.Controllers.Product.MainProductController;
import org.example.UI.MainWindow;

public class MainController {

    private MainWindow mainWindow;

    private MainProductController mainProductController;
    private MainCategoryController mainCategoryController;

    public MainController() {
        mainWindow = new MainWindow();
        initControllers();
    }

    private void initControllers() {
        mainProductController = new MainProductController(mainWindow.getProductMenu());
        mainCategoryController = new MainCategoryController(mainWindow.getCategoryMenu());
    }

    public void showProducts() {

    }

    public void showCategories() {

    }
}
