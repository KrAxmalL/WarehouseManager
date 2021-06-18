package org.example.Controllers.Product;

import org.example.Models.Category;
import org.example.Models.Product;
import org.example.Services.ProductService;
import org.example.UI.Menus.Product.DeleteProductMenu;
import org.example.UI.Menus.Product.EditProductMenu;

import javax.swing.*;

public class DeleteProductController {

    private DeleteProductMenu deleteProductMenu;

    public DeleteProductController(DeleteProductMenu deleteProductMenu) {
        this.deleteProductMenu = deleteProductMenu;
        //this.productService = new ProductService();
        initView();
    }

    private void initView() {
        deleteProductMenu.getDeleteButton().addActionListener(e -> accepted());
        deleteProductMenu.getCancelButton().addActionListener(e -> cancel());
        //add categories to choose
    }

    private void accepted() {
        Product pr = (Product)(deleteProductMenu.getProductsField().getSelectedItem());

        System.out.println(pr);
        if(ProductService.validateProduct(pr)) {
            JOptionPane.showMessageDialog(null, "Product deleting accepted!");
        }
        else {
            JOptionPane.showMessageDialog(null, "Product wasn't chosen!");
        }
    }

    private void cancel() {
        deleteProductMenu.setVisible(false);
    }
}
