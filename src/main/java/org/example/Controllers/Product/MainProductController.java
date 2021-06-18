package org.example.Controllers.Product;

import org.example.UI.Menus.Product.AddProductMenu;
import org.example.UI.Menus.Product.DeleteProductMenu;
import org.example.UI.Menus.Product.EditProductMenu;
import org.example.UI.Menus.Product.ProductMenu;

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
    }

    private void initView() {
        this.addProductMenu = new AddProductMenu();
        this.editProductMenu = new EditProductMenu();
        this.deleteProductMenu = new DeleteProductMenu();

        productMenu.getNewProduct().addActionListener(e -> showAddProductMenu());
        productMenu.getEditProduct().addActionListener(e -> showEditProductMenu());
        productMenu.getDeleteProduct().addActionListener(e -> showDeleteProductMenu());
    }

    private void initControllers() {
        addProductController = new AddProductController(addProductMenu);
        editProductController = new EditProductController(editProductMenu);
        deleteProductController = new DeleteProductController(deleteProductMenu);
    }

    public void showAddProductMenu() {
        if(addProductMenu == null) {
            addProductMenu = new AddProductMenu();
        }
        addProductMenu.setVisible(true);
    }

    public void showEditProductMenu() {
        if(editProductMenu == null) {
            editProductMenu = new EditProductMenu();
        }
        editProductMenu.setVisible(true);
    }

    public void showDeleteProductMenu() {
        if(deleteProductMenu == null) {
            deleteProductMenu = new DeleteProductMenu();
        }
        deleteProductMenu.setVisible(true);
    }
}
