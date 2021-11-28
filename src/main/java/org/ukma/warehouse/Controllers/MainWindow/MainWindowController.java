package org.ukma.warehouse.Controllers.MainWindow;

import org.ukma.warehouse.Controllers.Category.MainCategoryController;
import org.ukma.warehouse.Controllers.Product.MainProductController;
import org.ukma.warehouse.Controllers.Report.MainReportController;
import org.ukma.warehouse.Controllers.Stock.MainStockController;
import org.ukma.warehouse.Models.Category;
import org.ukma.warehouse.Models.Product;
import org.ukma.warehouse.UI.Menus.MainWindow.MainWindow;

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
