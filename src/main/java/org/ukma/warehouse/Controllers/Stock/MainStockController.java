package org.ukma.warehouse.Controllers.Stock;

import org.ukma.warehouse.UI.Menus.Stock.DecreaseProductAmountMenu;
import org.ukma.warehouse.UI.Menus.Stock.IncreaseProductAmountMenu;
import org.ukma.warehouse.UI.Menus.Stock.StockMenu;

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
