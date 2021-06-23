package org.example.Controllers.Product;

import org.example.Models.Category;
import org.example.Models.Message;
import org.example.Models.Product;
import org.example.Network.GlobalContext;
import org.example.Services.ProductService;
import org.example.UI.Menus.Product.AddProductMenu;
import org.example.UI.Menus.Product.EditProductMenu;
import org.example.Utils.CommandTypeEncoder;

import javax.swing.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class EditProductController {

    private EditProductMenu editProductMenu;

    private List<Product> products;
    private List<Category> categories;

    private static final int EDIT_COMMAND = CommandTypeEncoder.PRODUCT ^ CommandTypeEncoder.UPDATE;

    public EditProductController(EditProductMenu editProductMenu) {
        this.editProductMenu = editProductMenu;
        //this.productService = new ProductService();
        products = GlobalContext.productCache;
        categories = GlobalContext.categoryCache;
        initView();
    }

    public void showView() {
        updateProducts();
        updateCategories();
        //fillFields();
        editProductMenu.setVisible(true);
    }

    private void initView() {
        editProductMenu.getEditButton().addActionListener(e -> accepted());
        editProductMenu.getCancelButton().addActionListener(e -> cancel());

        updateProducts();
        updateCategories();

        //editProductMenu.getProductField().addItemListener(e -> fillFields(e.getStateChange()));
    }

    private void updateProducts() {
        JComboBox<Product> comboBox = editProductMenu.getProductField();
        comboBox.removeAllItems();
        Iterator<Product> it = products.iterator();
        while(it.hasNext()) {
            comboBox.addItem(it.next());
        }
    }

    private void updateCategories() {
        JComboBox<Category> comboBox = editProductMenu.getCategoryField();
        comboBox.removeAllItems();
        Iterator<Category> it = categories.iterator();
        while(it.hasNext()) {
            comboBox.addItem(it.next());
        }
    }

    private void fillFields(int index) {
        if(!products.isEmpty()) {
            editProductMenu.getProductField().setSelectedIndex(index);
            Product selectedProduct = (Product)editProductMenu.getProductField().getSelectedItem();
            editProductMenu.getProductNameField().setText(selectedProduct.getName());
            editProductMenu.getProductDescriptionField().setText(selectedProduct.getDescription());
            editProductMenu.getProductSupplierField().setText(selectedProduct.getProducer());
            editProductMenu.getProductPriceField().setValue(Double.valueOf(selectedProduct.getPrice()));
            for(int i = 0; i < editProductMenu.getCategoryField().getItemCount(); i++) {
                Category curr = editProductMenu.getCategoryField().getItemAt(i);
                if(curr.getId() == selectedProduct.getCategoryId()) {
                    editProductMenu.getCategoryField().setSelectedIndex(i);
                    break;
                }

            }
        }
        else {
            editProductMenu.getProductNameField().setText("");
            editProductMenu.getProductDescriptionField().setText("");
            editProductMenu.getProductSupplierField().setText("");
            editProductMenu.getProductPriceField().setValue(Integer.valueOf(10));
        }
    }

    private void accepted() {
        Product selected = (Product)(editProductMenu.getProductField().getSelectedItem());
        if(selected != null) {
            Product pr = new Product();
            pr.setId(selected.getId());
            pr.setName(editProductMenu.getProductNameField().getText());
            pr.setDescription(editProductMenu.getProductDescriptionField().getText());
            pr.setProducer(editProductMenu.getProductSupplierField().getText());
            double price = (Double) (editProductMenu.getProductPriceField().getValue());
            pr.setPrice((int) price);
            pr.setAmount(((Product) (editProductMenu.getProductField().getSelectedItem())).getAmount());
            Category category = (Category) (editProductMenu.getCategoryField().getSelectedItem());
            if (category == null) {
                JOptionPane.showMessageDialog(null, "Choose a category!");
            } else {
                pr.setCategoryId(category.getId());
            }

            System.out.println(pr);
            try {
                GlobalContext.client.sendRequest(EDIT_COMMAND, ProductService.productToJson(pr));
                Message response = GlobalContext.client.getResponse();
                System.out.println(response);
                if (response.getMessage().equals("ok")) {
                    JOptionPane.showMessageDialog(null, "Product changed!");
                    GlobalContext.updateProductsCache();
                    editProductMenu.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong input!");
                }
                //System.out.println(GlobalContext.productCache);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Choose a product to edit!");
        }
    }

    private void cancel() {
        editProductMenu.setVisible(false);
    }
}
