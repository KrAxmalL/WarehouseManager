package org.ukma.warehouse.Controllers.Product;

import org.ukma.warehouse.Models.Category;
import org.ukma.warehouse.Models.Message;
import org.ukma.warehouse.Models.Product;
import org.ukma.warehouse.Network.Context.GlobalContext;
import org.ukma.warehouse.Services.ProductService;
import org.ukma.warehouse.UI.Menus.Product.AddProductMenu;
import org.ukma.warehouse.Utils.CommandTypeEncoder;

import javax.swing.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

public class AddProductController {

    private AddProductMenu addProductMenu;

    private static final int ADD_COMMAND = CommandTypeEncoder.PRODUCT ^ CommandTypeEncoder.CREATE;

    private static final String BIG_DECIMAL_PATTERN = "-?(?:\\d+(?:\\.\\d+)?|\\.\\d+)";

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
        addProductMenu.getProductPriceField().setText("");
    }

    private void accepted() {
        String priceStr = addProductMenu.getProductPriceField().getText();
        if(priceStr.matches(BIG_DECIMAL_PATTERN)) {
            Product pr = new Product();
            pr.setName(addProductMenu.getProductNameField().getText());
            pr.setDescription(addProductMenu.getProductDescriptionField().getText());
            pr.setProducer(addProductMenu.getProductSupplierField().getText());
            pr.setPrice(new BigDecimal(priceStr));
            pr.setAmount(0);
            System.out.println("Product price: " + pr.getPrice());
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
                    addProductMenu.setVisible(false);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Wrong input!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Wrong input!");
        }
    }

    private void cancel() {
        addProductMenu.setVisible(false);
    }
}
