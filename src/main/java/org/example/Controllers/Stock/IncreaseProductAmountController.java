package org.example.Controllers.Stock;

import org.example.Models.Category;
import org.example.Models.Message;
import org.example.Models.Product;
import org.example.Network.GlobalContext;
import org.example.Services.ProductService;
import org.example.UI.Menus.Product.EditProductMenu;
import org.example.UI.Menus.Stock.IncreaseProductAmountMenu;
import org.example.Utils.CommandTypeEncoder;

import javax.swing.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class IncreaseProductAmountController {

    private IncreaseProductAmountMenu increaseProductAmountMenu;

    private List<Product> products;

    private static final String INTEGER_PATTERN = "-?\\d+";

    private static final int EDIT_COMMAND = CommandTypeEncoder.PRODUCT ^ CommandTypeEncoder.UPDATE;

    public IncreaseProductAmountController(IncreaseProductAmountMenu increaseProductAmountMenu) {
        this.increaseProductAmountMenu = increaseProductAmountMenu;
        products = GlobalContext.productCache;
        initView();
    }

    private void initView() {
        increaseProductAmountMenu.getOkButton().addActionListener(e -> accepted());
        increaseProductAmountMenu.getCancelButton().addActionListener(e -> cancel());

        updateProducts();

        increaseProductAmountMenu.getItemsBox().addActionListener(e -> fillFields());
    }

    public void showView() {
        updateProducts();
        increaseProductAmountMenu.setVisible(true);
    }

    private void updateProducts() {
        JComboBox<Product> comboBox = increaseProductAmountMenu.getItemsBox();
        comboBox.removeAllItems();
        Iterator<Product> it = products.iterator();
        while(it.hasNext()) {
            comboBox.addItem(it.next());
        }
    }

    private void fillFields() {
        if(!products.isEmpty()) {
            Product selectedProduct = (Product)increaseProductAmountMenu.getItemsBox().getSelectedItem();
            if(selectedProduct != null) {
                increaseProductAmountMenu.getCurrentStockLabel().setText("Current product amount: " + selectedProduct.getAmount());
                return;
            }
        }
        increaseProductAmountMenu.getCurrentStockLabel().setText("Current product amount: ");
    }

    private void accepted() {
        Product selected = (Product)(increaseProductAmountMenu.getItemsBox().getSelectedItem());
        if(selected != null) {
            String amountStr = increaseProductAmountMenu.getStocksToAdd().getText();
            if(amountStr.matches(INTEGER_PATTERN)) {
                Product pr = new Product();
                pr.setId(selected.getId());
                pr.setName(selected.getName());
                pr.setDescription(selected.getDescription());
                pr.setProducer(selected.getProducer());
                pr.setPrice(selected.getPrice());
                int amountToAdd = Integer.parseInt(amountStr);
                if(amountToAdd > 0) {
                    pr.setAmount(selected.getAmount() + amountToAdd);
                    pr.setCategoryId(selected.getCategoryId());

                    System.out.println(pr);
                    try {
                        GlobalContext.client.sendRequest(EDIT_COMMAND, ProductService.productToJson(pr));
                        Message response = GlobalContext.client.getResponse();
                        System.out.println(response);
                        if (response.getMessage().equals("ok")) {
                            JOptionPane.showMessageDialog(null, "Product changed!");
                            GlobalContext.updateProductsCache();
                            increaseProductAmountMenu.setVisible(false);
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
                JOptionPane.showMessageDialog(null, "Wrong input!");
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Choose a product to edit!");
        }
    }

    private void cancel() {
        increaseProductAmountMenu.setVisible(false);
    }
}
