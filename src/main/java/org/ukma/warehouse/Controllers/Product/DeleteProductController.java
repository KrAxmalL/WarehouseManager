package org.ukma.warehouse.Controllers.Product;

import org.ukma.warehouse.Models.Message;
import org.ukma.warehouse.Models.Product;
import org.ukma.warehouse.Network.Context.GlobalContext;
import org.ukma.warehouse.UI.Menus.Product.DeleteProductMenu;
import org.ukma.warehouse.Utils.CommandTypeEncoder;

import javax.swing.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class DeleteProductController {

    private DeleteProductMenu deleteProductMenu;

    private List<Product> products;

    private static final int DELETE_COMMAND = CommandTypeEncoder.PRODUCT ^ CommandTypeEncoder.DELETE;

    public DeleteProductController(DeleteProductMenu deleteProductMenu) {
        this.deleteProductMenu = deleteProductMenu;
        //this.productService = new ProductService();
        products = GlobalContext.productCache;
        initView();
    }

    public void showView() {
        updateProducts();
        deleteProductMenu.setVisible(true);
    }

    private void initView() {
        deleteProductMenu.getDeleteButton().addActionListener(e -> accepted());
        deleteProductMenu.getCancelButton().addActionListener(e -> cancel());

        updateProducts();
    }

    private void updateProducts() {
        JComboBox<Product> comboBox = deleteProductMenu.getProductsField();
        comboBox.removeAllItems();
        Iterator<Product> it = products.iterator();
        while(it.hasNext()) {
            comboBox.addItem(it.next());
        }
    }

    private void accepted() {
        Product pr = (Product)(deleteProductMenu.getProductsField().getSelectedItem());
        if(pr != null) {
            try {
                GlobalContext.client.sendRequest(DELETE_COMMAND, String.valueOf(pr.getId()));
                Message response = GlobalContext.client.getResponse();
                System.out.println(response);
                if (response.getMessage().equals("ok")) {
                    JOptionPane.showMessageDialog(null, "Product was successfully deleted!");
                    GlobalContext.updateProductsCache();
                    deleteProductMenu.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Product wasn't deleted!");
                }
                //System.out.println(GlobalContext.productCache);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Choose a product to delete!");
        }
    }

    private void cancel() {
        deleteProductMenu.setVisible(false);
    }
}
