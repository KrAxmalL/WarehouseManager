package org.example.Controllers.Stock;

import org.example.Models.Message;
import org.example.Models.Product;
import org.example.Network.GlobalContext;
import org.example.Services.ProductService;
import org.example.UI.Menus.Stock.DecreaseProductAmountMenu;
import org.example.UI.Menus.Stock.IncreaseProductAmountMenu;
import org.example.Utils.CommandTypeEncoder;

import javax.swing.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class DecreaseProductAmountController {

    private DecreaseProductAmountMenu decreaseProductAmountMenu;

    private List<Product> products;

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
    }

    public void showView() {
        updateProducts();
        decreaseProductAmountMenu.setVisible(true);
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
            Product pr = new Product();
            pr.setId(selected.getId());
            pr.setName(selected.getName());
            pr.setDescription(selected.getDescription());
            pr.setProducer(selected.getProducer());
            //fix all spinners
            pr.setPrice(selected.getPrice());
            int amountToTake = (Integer)(decreaseProductAmountMenu.getStocksToRemove().getValue());
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
        decreaseProductAmountMenu.setVisible(false);
    }
}
