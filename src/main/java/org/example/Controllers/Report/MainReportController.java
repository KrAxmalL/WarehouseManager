package org.example.Controllers.Report;

import org.example.Controllers.Product.AddProductController;
import org.example.Controllers.Product.DeleteProductController;
import org.example.Controllers.Product.EditProductController;
import org.example.Models.Category;
import org.example.Models.Product;
import org.example.Network.GlobalContext;
import org.example.UI.Menus.Category.CategoryTable;
import org.example.UI.Menus.Product.*;
import org.example.UI.Menus.Report.FilterProductMenu;
import org.example.UI.Menus.Report.ReportMenu;
import org.example.UI.Menus.Report.StatisticsMenu;

import javax.swing.*;
import java.util.List;

public class MainReportController {

    private ReportMenu reportMenu;
    private JScrollPane tableView;
    private FilterProductMenu filterProductMenu;
    private StatisticsMenu statisticsMenu;

    private FilterProductController filterProductController;
    private StatisticsController statisticsController;

    private List<Product> products;
    private List<Category> categories;

    public MainReportController(ReportMenu reportMenu, JScrollPane tableView) {
        this.reportMenu = reportMenu;
        this.tableView = tableView;
        products = GlobalContext.productCache;
        categories = GlobalContext.categoryCache;
        initView();
        initControllers();
    }

    private void initView() {
        reportMenu.getAllProducts().addActionListener(e -> showAllProducts());
        reportMenu.getFilterProducts().addActionListener(e -> showFilterProducts());
        reportMenu.getAllCategories().addActionListener(e -> showAllCategories());
        reportMenu.getStatistics().addActionListener(e -> showStatistics());

        filterProductMenu = new FilterProductMenu();
        statisticsMenu = new StatisticsMenu();
    }

    private void initControllers() {
        filterProductController = new FilterProductController(filterProductMenu, tableView);
        statisticsController = new StatisticsController(statisticsMenu);
    }

    public void showAllProducts() {
        tableView.setViewportView(new ProductTable(products.toArray(new Product[0])));
    }

    public void showFilterProducts() {
        filterProductController.showView();
    }

    public void showAllCategories() {
        tableView.setViewportView(new CategoryTable(categories.toArray(new Category[0])));
    }

    public void showStatistics() {
        statisticsController.showView();
    }
}
