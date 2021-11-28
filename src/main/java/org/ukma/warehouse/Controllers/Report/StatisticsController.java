package org.ukma.warehouse.Controllers.Report;

import org.ukma.warehouse.Models.Category;
import org.ukma.warehouse.Models.Product;
import org.ukma.warehouse.Network.Context.GlobalContext;
import org.ukma.warehouse.UI.Menus.Report.StatisticsMenu;

import java.util.ArrayList;
import java.util.List;

public class StatisticsController {

    private StatisticsMenu statisticsMenu;

    private List<Product> products;
    private List<Category> categories;

    private double totalPrice;

    public StatisticsController(StatisticsMenu statisticsMenu) {
        this.statisticsMenu = statisticsMenu;

        products = GlobalContext.productCache;
        categories = GlobalContext.categoryCache;

        totalPrice = 0;
    }

    public void showView() {
        calculateStatistics();
        statisticsMenu.setVisible(true);
    }

    private void calculateStatistics() {
        totalPrice = 0;
        StringBuilder fullStatistics = new StringBuilder();
        for(Category category: categories) {
            fullStatistics.append(calculateStatisticsForCategory(category));
        }

        fullStatistics.append("Total warehouse products price: ").append(totalPrice);

        statisticsMenu.getTextArea().setText(fullStatistics.toString());
    }

    private String calculateStatisticsForCategory(Category category) {
        StringBuilder result = new StringBuilder();
        List<Product> productsOfCategory = new ArrayList<>();
        for(Product product: products) {
            if(product.getCategoryId() == category.getId()) {
                productsOfCategory.add(product);
            }
        }

        result.append("Category: ").append(category.getName()).append(":\n");
        double totalCategoryPrice = 0;
        for(Product product: productsOfCategory) {
            result.append(calculateStatisticsForProduct(product));
            totalCategoryPrice += product.getPrice().doubleValue() * product.getAmount();
        }
        totalPrice += totalCategoryPrice;
        result.append("Total category price: ").append(totalCategoryPrice).append(".\n\n");

        return result.toString();
    }

    private String calculateStatisticsForProduct(Product product) {
        StringBuilder result = new StringBuilder();
        result.append("Product: ").append(product.getName()).append(", ");
        result.append("price: ").append(product.getPrice()).append(", ");
        result.append("amount: ").append(product.getAmount()).append(", ");
        result.append("total price: ").append(product.getAmount() * product.getPrice().doubleValue()).append(".\n");

        return result.toString();
    }
}
