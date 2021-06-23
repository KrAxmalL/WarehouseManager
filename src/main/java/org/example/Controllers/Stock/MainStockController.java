package org.example.Controllers.Stock;

import org.example.Controllers.Product.AddProductController;
import org.example.Controllers.Product.DeleteProductController;
import org.example.Controllers.Product.EditProductController;
import org.example.UI.Menus.Product.AddProductMenu;
import org.example.UI.Menus.Product.DeleteProductMenu;
import org.example.UI.Menus.Product.EditProductMenu;
import org.example.UI.Menus.Product.ProductMenu;
import org.example.UI.Menus.Stock.DecreaseProductAmountMenu;
import org.example.UI.Menus.Stock.IncreaseProductAmountMenu;
import org.example.UI.Menus.Stock.StockMenu;

public class MainStockController {

    private StockMenu stockMenu;
    private IncreaseProductAmountMenu increaseProductAmountMenu;
    private DecreaseProductAmountMenu decreaseProductAmountMenu;

    private IncreaseProductAmountController increaseProductAmountController;
    private DecreaseProductAmountController decreaseProductAmountController;

    public MainStockController(StockMenu stockMenu) {
        this.stockMenu = stockMenu;
        initView();
        initControllers();
    }

    private void initView() {
        this.increaseProductAmountMenu = new IncreaseProductAmountMenu();
        this.decreaseProductAmountMenu = new DecreaseProductAmountMenu();

        stockMenu.getIncreaseProductAmount().addActionListener(e -> showIncreaseProductAmountMenu());
        stockMenu.getDecreaseProductAmount().addActionListener(e -> showDecreaseProductAmountMenu());
    }

    private void initControllers() {
        increaseProductAmountController = new IncreaseProductAmountController(increaseProductAmountMenu);
        decreaseProductAmountController = new DecreaseProductAmountController(decreaseProductAmountMenu);
    }

    public void showIncreaseProductAmountMenu() {
        increaseProductAmountController.showView();
    }

    public void showDecreaseProductAmountMenu() {
        decreaseProductAmountController.showView();
    }
}
