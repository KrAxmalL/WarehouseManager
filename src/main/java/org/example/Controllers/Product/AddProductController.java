package org.example.Controllers.Product;

import org.example.Databases.CrudCategoryRepository;
import org.example.Databases.CrudProductRepository;
import org.example.Models.Category;
import org.example.Models.Message;
import org.example.Models.Packet;
import org.example.Models.Product;
import org.example.Network.GlobalContext;
import org.example.Services.ProductService;
import org.example.UI.Menus.Product.AddProductMenu;
import org.example.Utils.CommandTypeEncoder;

import javax.swing.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

public class AddProductController {

    private AddProductMenu addProductMenu;

    private static final int ADD_COMMAND = CommandTypeEncoder.PRODUCT ^ CommandTypeEncoder.CREATE;

    private List<Category> categories;

    public AddProductController(AddProductMenu addProductMenu) {
        this.addProductMenu = addProductMenu;
        //this.productService = new ProductService();
        categories = GlobalContext.categoryCache;
        initView();
    }

    public void showView() {
        updateCategories();
        fillFields();
        addProductMenu.setVisible(true);
    }

    private void initView() {
        addProductMenu.getAddButton().addActionListener(e -> accepted());
        addProductMenu.getCancelButton().addActionListener(e -> cancel());

        updateCategories();
    }

    private void updateCategories() {
        JComboBox<Category> comboBox = addProductMenu.getCategoriesField();
        comboBox.removeAllItems();
        Iterator<Category> it = categories.iterator();
        while(it.hasNext()) {
            comboBox.addItem(it.next());
        }
    }

    private void fillFields() {
        addProductMenu.getProductNameField().setText("");
        addProductMenu.getProductDescriptionField().setText("");
        addProductMenu.getProductSupplierField().setText("");
        addProductMenu.getProductPriceField().setValue(Integer.valueOf(5));
    }

    private void accepted() {
        Product pr = new Product();

        pr.setName(addProductMenu.getProductNameField().getText());
        pr.setDescription(addProductMenu.getProductDescriptionField().getText());
        pr.setProducer(addProductMenu.getProductSupplierField().getText());
        //check parsing
        double price = (Double)(addProductMenu.getProductPriceField().getValue());
        pr.setPrice((int) price);
        pr.setAmount(0);
        Category category = (Category)(addProductMenu.getCategoriesField().getSelectedItem());
        if(category == null) {
            JOptionPane.showMessageDialog(null, "Choose a category!");
        }
        else {
            pr.setCategoryId(category.getId());
        }

        System.out.println(pr);
        try {
            GlobalContext.client.sendRequest(ADD_COMMAND, ProductService.productToJson(pr));
            Message response = GlobalContext.client.getResponse();
            System.out.println(response);
            if(response.getMessage().equals("ok")) {
                JOptionPane.showMessageDialog(null, "Product accepted!");
                GlobalContext.updateProductsCache();
            }
            else {
                JOptionPane.showMessageDialog(null, "Wrong input!");
            }
            //System.out.println(GlobalContext.productCache);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cancel() {
        addProductMenu.setVisible(false);
    }
}