package org.ukma.warehouse.Controllers.Product;

import org.ukma.warehouse.Models.Category;
import org.ukma.warehouse.Models.Message;
import org.ukma.warehouse.Models.Product;
import org.ukma.warehouse.Network.Context.GlobalContext;
import org.ukma.warehouse.Services.ProductService;
import org.ukma.warehouse.UI.Menus.Product.EditProductMenu;
import org.ukma.warehouse.Utils.CommandTypeEncoder;

import javax.swing.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

public class EditProductController {

    private EditProductMenu editProductMenu;

    private List<Product> products;
    private List<Category> categories;

    private static final String BIG_DECIMAL_PATTERN = "-?(?:\\d+(?:\\.\\d+)?|\\.\\d+)";

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

        editProductMenu.getProductField().addActionListener(e -> fillFields());
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

    private void fillFields() {
        if(!products.isEmpty()) {
            Product selectedProduct = (Product)editProductMenu.getProductField().getSelectedItem();
            if(selectedProduct != null) {
                editProductMenu.getProductNameField().setText(selectedProduct.getName());
                editProductMenu.getProductDescriptionField().setText(selectedProduct.getDescription());
                editProductMenu.getProductSupplierField().setText(selectedProduct.getProducer());
                editProductMenu.getProductPriceField().setText(selectedProduct.getPrice().toString());
                for (int i = 0; i < editProductMenu.getCategoryField().getItemCount(); i++) {
                    Category curr = editProductMenu.getCategoryField().getItemAt(i);
                    if (curr.getId() == selectedProduct.getCategoryId()) {
                        editProductMenu.getCategoryField().setSelectedIndex(i);
                        return;
                    }
                }
                return;
            }
        }
        editProductMenu.getProductNameField().setText("");
        editProductMenu.getProductDescriptionField().setText("");
        editProductMenu.getProductSupplierField().setText("");
        editProductMenu.getProductPriceField().setText("");
    }

    private void accepted() {
        Product selected = (Product)(editProductMenu.getProductField().getSelectedItem());
        if(selected != null) {
            String priceStr = editProductMenu.getProductPriceField().getText();
            if(priceStr.matches(BIG_DECIMAL_PATTERN)) {
                Product pr = new Product();
                pr.setId(selected.getId());
                pr.setName(editProductMenu.getProductNameField().getText());
                pr.setDescription(editProductMenu.getProductDescriptionField().getText());
                pr.setProducer(editProductMenu.getProductSupplierField().getText());
                pr.setPrice(new BigDecimal(priceStr));
                System.out.println("Product price: " + pr.getPrice());
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
                JOptionPane.showMessageDialog(null, "Wrong input!");
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
