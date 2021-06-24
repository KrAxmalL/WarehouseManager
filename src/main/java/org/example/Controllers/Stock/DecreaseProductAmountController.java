package org.example.Controllers.Stock;

import org.example.Models.Message;
import org.example.Models.Product;
import org.example.Network.Context.GlobalContext;
import org.example.Services.ProductService;
import org.example.UI.Menus.Stock.DecreaseProductAmountMenu;
import org.example.Utils.CommandTypeEncoder;

import javax.swing.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class DecreaseProductAmountController {

    private DecreaseProductAmountMenu decreaseProductAmountMenu;

    private List<Product> products;

    private static final String INTEGER_PATTERN = "-?\\d+";

    private static final int EDIT_COMMAND = CommandTypeEncoder.PRODUCT ^ CommandTypeEncoder.UPDATE;

    public DecreaseProductAmountController(DecreaseProductAmountMenu decreaseProductAmountMenu) {
        this.decreaseProductAmountMenu = decreaseProductAmountMenu;

        products = GlobalContext.productCache;
        initView();
    }

    private void initView() {
        decreaseProductAmountMenu.getOkButton().addActionListener(e -> accepted());
        decreaseProductAmountMenu.getCancelButton().addActionListener(e -> cancel());

        updateProducts();

        decreaseProductAmountMenu.getItemsBox().addActionListener(e -> fillFields());
    }

    public void showView() {
        updateProducts();
        decreaseProductAmountMenu.setVisible(true);
    }

    private void fillFields() {
        if(!products.isEmpty()) {
            Product selectedProduct = (Product)decreaseProductAmountMenu.getItemsBox().getSelectedItem();
            if(selectedProduct != null) {
                decreaseProductAmountMenu.getCurrentStockLabel().setText("Current product amount: " + selectedProduct.getAmount());
                return;
            }
        }
        decreaseProductAmountMenu.getCurrentStockLabel().setText("Current product amount: ");
    }

    private void updateProducts() {
        JComboBox<Product> comboBox = decreaseProductAmountMenu.getItemsBox();
        comboBox.removeAllItems();
        Iterator<Product> it = products.iterator();
        while(it.hasNext()) {
            comboBox.addItem(it.next());
        }
    }

    private void accepted() {
        Product selected = (Product)(decreaseProductAmountMenu.getItemsBox().getSelectedItem());
        if(selected != null) {
            String amountStr = decreaseProductAmountMenu.getStocksToRemove().getText();
            if(amountStr.matches(INTEGER_PATTERN)) {
                Product pr = new Product();
                pr.setId(selected.getId());
                pr.setName(selected.getName());
                pr.setDescription(selected.getDescription());
                pr.setProducer(selected.getProducer());
                pr.setPrice(selected.getPrice());
                int amountToTake = Integer.parseInt(amountStr);
                if(amountToTake > 0) {
                    pr.setAmount(selected.getAmount() - amountToTake);
                    pr.setCategoryId(selected.getCategoryId());
                    System.out.println(pr);
                    try {
                        GlobalContext.client.sendRequest(EDIT_COMMAND, ProductService.productToJson(pr));
                        Message response = GlobalContext.client.getResponse();
                        System.out.println(response);
                        if (response.getMessage().equals("ok")) {
                            JOptionPane.showMessageDialog(null, "Product changed!");
                            GlobalContext.updateProductsCache();
                            decreaseProductAmountMenu.setVisible(false);
                        } else {
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
            else {
                JOptionPane.showMessageDialog(null, "Wrong input!");
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Choose a product to edit!");
        }
    }

    private void cancel() {
        decreaseProductAmountMenu.setVisible(false);
    }
}
