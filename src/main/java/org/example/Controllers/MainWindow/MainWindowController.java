package org.example.Controllers.MainWindow;

import org.example.Controllers.Category.MainCategoryController;
import org.example.Controllers.Product.MainProductController;
import org.example.Controllers.Report.MainReportController;
import org.example.Controllers.Stock.MainStockController;
import org.example.Models.Category;
import org.example.Models.Product;
import org.example.UI.Menus.MainWindow.MainWindow;

import java.util.List;

public class MainWindowController {

    private MainWindow mainWindow;

    private MainProductController mainProductController;
    private MainCategoryController mainCategoryController;
    private MainStockController mainStockController;
    private MainReportController mainReportController;

    private List<Product> products;
    private List<Category> categories;

    public MainWindowController(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        initControllers();
    }

    private void initControllers() {
        mainProductController = new MainProductController(mainWindow.getProductMenu());
        mainCategoryController = new MainCategoryController(mainWindow.getCategoryMenu());
        mainStockController = new MainStockController(mainWindow.getStockMenu());
        mainReportController = new MainReportController(mainWindow.getReportMenu(), mainWindow.getScrollPane());
    }

    public void showView() {
        mainWindow.setVisible(true);
    }
}
