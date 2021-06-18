package org.example.Controllers.Product;

import org.example.Models.Category;
import org.example.Models.Product;
import org.example.Services.ProductService;
import org.example.UI.Menus.Product.AddProductMenu;

import javax.swing.*;

public class AddProductController {

    private AddProductMenu addProductMenu;

    public AddProductController(AddProductMenu addProductMenu) {
        this.addProductMenu = addProductMenu;
        //this.productService = new ProductService();
        initView();
    }

    private void initView() {
        addProductMenu.getAddButton().addActionListener(e -> accepted());
        addProductMenu.getCancelButton().addActionListener(e -> cancel());
        //add categories to choose
    }

    private void accepted() {
        Product pr = new Product();

        pr.setName(addProductMenu.getProductNameField().getText());
        pr.setProducer(addProductMenu.getProductDescriptionField().getText());
        double price = (Double)(addProductMenu.getProductPriceField().getValue());
        pr.setPrice((int) price);
        pr.setAmount(0);
        Category category = (Category)(addProductMenu.getCategoriesField().getSelectedItem());
        if(category == null) {
            pr.setCategoryId(1);
        }
        else {
            pr.setCategoryId(category.getId());
        }

        System.out.println(pr);
        if(ProductService.validateProduct(pr)) {
            JOptionPane.showMessageDialog(null, "Product accepted!");
        }
        else {
            JOptionPane.showMessageDialog(null, "Wrong input!");
        }
    }

    private void cancel() {
        addProductMenu.setVisible(false);
    }
}
