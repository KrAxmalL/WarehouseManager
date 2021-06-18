package org.example.Controllers.Product;

import org.example.Models.Category;
import org.example.Models.Product;
import org.example.Services.ProductService;
import org.example.UI.Menus.Product.AddProductMenu;
import org.example.UI.Menus.Product.EditProductMenu;

import javax.swing.*;

public class EditProductController {

    private EditProductMenu editProductMenu;

    public EditProductController(EditProductMenu editProductMenu) {
        this.editProductMenu = editProductMenu;
        //this.productService = new ProductService();
        initView();
    }

    private void initView() {
        editProductMenu.getEditButton().addActionListener(e -> accepted());
        editProductMenu.getCancelButton().addActionListener(e -> cancel());
        //add categories to choose
    }

    private void accepted() {
        Product pr = new Product();

        pr.setId(((Product)(editProductMenu.getProductField().getSelectedItem())).getId());
        pr.setName(editProductMenu.getProductNameField().getText());
        pr.setProducer(editProductMenu.getProductDescriptionField().getText());
        double price = (Double)(editProductMenu.getProductPriceField().getValue());
        pr.setPrice((int) price);
        pr.setAmount(0);
        Category category = (Category)(editProductMenu.getCategoryField().getSelectedItem());
        if(category == null) {
            pr.setCategoryId(1);
        }
        else {
            pr.setCategoryId(category.getId());
        }

        System.out.println(pr);
        if(ProductService.validateProduct(pr)) {
            JOptionPane.showMessageDialog(null, "Product edition accepted!");
        }
        else {
            JOptionPane.showMessageDialog(null, "Wrong input!");
        }
    }

    private void cancel() {
        editProductMenu.setVisible(false);
    }
}
