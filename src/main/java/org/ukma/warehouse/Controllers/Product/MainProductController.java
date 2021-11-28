package org.ukma.warehouse.Controllers.Product;

import org.ukma.warehouse.UI.Menus.Product.AddProductMenu;
import org.ukma.warehouse.UI.Menus.Product.DeleteProductMenu;
import org.ukma.warehouse.UI.Menus.Product.EditProductMenu;
import org.ukma.warehouse.UI.Menus.Product.ProductMenu;

public class MainProductController {

    private ProductMenu productMenu;
    private AddProductMenu addProductMenu;
    private EditProductMenu editProductMenu;
    private DeleteProductMenu deleteProductMenu;

    private AddProductController addProductController;
    private EditProductController editProductController;
    private DeleteProductController deleteProductController;

    public MainProductController(ProductMenu productMenu) {
        this.productMenu = productMenu;
        initView();
        initControllers();
        initActions();
    }

    private void initView() {
        this.addProductMenu = new AddProductMenu();
        this.editProductMenu = new EditProductMenu();
        this.deleteProductMenu = new DeleteProductMenu();
    }

    private void initControllers() {
        addProductController = new AddProductController(addProductMenu);
        editProductController = new EditProductController(editProductMenu);
        deleteProductController = new DeleteProductController(deleteProductMenu);
    }

    private void initActions() {
        productMenu.getNewProduct().addActionListener(e -> showAddProductMenu());
        productMenu.getEditProduct().addActionListener(e -> showEditProductMenu());
        productMenu.getDeleteProduct().addActionListener(e -> showDeleteProductMenu());
    }

    public void showAddProductMenu() {
        addProductController.showView();
    }

    public void showEditProductMenu() {
        editProductController.showView();
    }

    public void showDeleteProductMenu() {
        deleteProductController.showView();
    }
}
