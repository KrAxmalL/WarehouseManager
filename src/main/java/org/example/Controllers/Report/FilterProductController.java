package org.example.Controllers.Report;

import org.example.Models.Category;
import org.example.Models.Message;
import org.example.Models.Product;
import org.example.Network.GlobalContext;
import org.example.Services.ProductService;
import org.example.UI.Menus.Report.FilterProductMenu;
import org.example.Utils.CommandTypeEncoder;

import javax.swing.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class FilterProductController {

    private FilterProductMenu filterProductMenu;

    private JScrollPane tableView;

    private List<Category> categories;

    public FilterProductController(FilterProductMenu filterProductMenu, JScrollPane tableView) {
        this.filterProductMenu = filterProductMenu;
        this.tableView = tableView;
        categories = GlobalContext.categoryCache;
        initView();
    }

    private void initView() {
        filterProductMenu.getFilterButton().addActionListener(e -> accepted());
        filterProductMenu.getCancelButton().addActionListener(e -> cancel());
    }

    public void showView() {
        updateCategories();
        filterProductMenu.setVisible(true);
    }

    private void updateCategories() {
        JComboBox<Category> comboBox = filterProductMenu.getCategoriesField();
        comboBox.removeAllItems();
        Iterator<Category> it = categories.iterator();
        while(it.hasNext()) {
            comboBox.addItem(it.next());
        }
    }

    private void accepted() {
        int entity = CommandTypeEncoder.PRODUCT;
        int command = CommandTypeEncoder.LIST_BY_CRITERIA;
        ButtonModel fieldModel = filterProductMenu.getFieldButtonGroup().getSelection();
        if(fieldModel != null) {
            ButtonModel orderModel = filterProductMenu.getOrderButtonGroup().getSelection();
            if(orderModel != null) {
                String chosenField = fieldModel.getActionCommand();
                int fieldCommand = getFieldCommand(chosenField);
                String chosenOrder = orderModel.getActionCommand();
                int orderCommand = getOrderCommand(chosenOrder);

                int requestCommand = CommandTypeEncoder.createCommand(entity, command, fieldCommand, orderCommand);

                System.out.println(chosenField);
                System.out.println(chosenOrder);
            }

        }

        /*Product pr = new Product();

        int entity = filterProductMenu.getE
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
        }*/
    }

    private int getFieldCommand(String field) {
        switch(field) {
            case "name": return CommandTypeEncoder.CRITERIA_NAME;

            case "producer": return CommandTypeEncoder.CRITERIA_PRODUCER;

            case "category": return CommandTypeEncoder.CRITERIA_CATEGORY_ID;

            case "amount": return CommandTypeEncoder.CRITERIA_AMOUNT;

            case "price": return CommandTypeEncoder.CRITERIA_PRICE;

            default: return CommandTypeEncoder.CRITERIA_NAME;
        }
    }

    private int getOrderCommand(String order) {
        switch(order) {
            case "ascending": return CommandTypeEncoder.ORDER_ASCEND;

            case "descending": return CommandTypeEncoder.ORDER_DESCEND;

            default: return CommandTypeEncoder.ORDER_ASCEND;
        }
    }

    private void cancel() {
        filterProductMenu.setVisible(false);
    }
}
