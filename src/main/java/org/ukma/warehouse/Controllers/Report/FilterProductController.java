package org.ukma.warehouse.Controllers.Report;

import org.ukma.warehouse.Models.Category;
import org.ukma.warehouse.Models.Message;
import org.ukma.warehouse.Models.Product;
import org.ukma.warehouse.Network.Context.GlobalContext;
import org.ukma.warehouse.Services.ProductService;
import org.ukma.warehouse.UI.Menus.Product.ProductTable;
import org.ukma.warehouse.UI.Menus.Report.FilterProductMenu;
import org.ukma.warehouse.Utils.CommandTypeEncoder;

import javax.swing.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

public class FilterProductController {

    private FilterProductMenu filterProductMenu;

    private JScrollPane tableView;

    private List<Category> categories;

    private static final String BIG_DECIMAL_PATTERN = "-?(?:\\d+(?:\\.\\d+)?|\\.\\d+)";

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

                System.out.println(chosenField);
                System.out.println(chosenOrder);

                String toSend = readData(fieldCommand);
                if(toSend != null) {
                    int requestCommand = CommandTypeEncoder.createCommand(entity, command, fieldCommand, orderCommand);
                    try {
                        GlobalContext.client.sendRequest(requestCommand, toSend);
                        Message response = GlobalContext.client.getResponse();
                        System.out.println("String in filterproduct response: " + response.getMessage());
                        String responseData = response.getMessage().trim();
                        if(responseData != null && !responseData.isEmpty()) {
                            String[] productsAsString = responseData.split("\n");
                            Product[] productsToShow = new Product[productsAsString.length];
                            for (int i = 0; i < productsToShow.length; i++) {
                                if (!productsAsString[i].isEmpty()) {
                                    productsToShow[i] = ProductService.parseProductFromJson(productsAsString[i]);
                                }
                            }
                            tableView.setViewportView(new ProductTable(productsToShow));
                            filterProductMenu.setVisible(false);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "No results found!");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private String readData(int commandType) {
        if(commandType == CommandTypeEncoder.CRITERIA_NAME || commandType == CommandTypeEncoder.CRITERIA_PRODUCER) {
            String data = filterProductMenu.getTextField().getText();
            if(data == null) {
                data = "";
            }
            return data;
        }
        else if(commandType == CommandTypeEncoder.CRITERIA_AMOUNT || commandType == CommandTypeEncoder.CRITERIA_PRICE) {
            String min = filterProductMenu.getMinNumber().getText();
            if(min.matches(BIG_DECIMAL_PATTERN)) {
                BigDecimal minValue = new BigDecimal(min);
                String max = filterProductMenu.getMaxNumber().getText();
                if(max.matches(BIG_DECIMAL_PATTERN)) {
                    BigDecimal maxValue = new BigDecimal(max);
                    if(minValue.compareTo(maxValue) <= 0) {
                        String data = minValue.toString() + "," + maxValue.toString();
                        return data;
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Wrong input");
                        return null;
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Wrong input");
                    return null;
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Wrong input");
                return null;
            }
        }
        else if(commandType == CommandTypeEncoder.CRITERIA_CATEGORY_ID) {
            Category selectedCategory = (Category)filterProductMenu.getCategoriesField().getSelectedItem();
            if(selectedCategory != null) {
                int categoryId = selectedCategory.getId();
                String data = String.valueOf(categoryId) + "," + String.valueOf(categoryId);
                return data;
            }
            else {
                JOptionPane.showMessageDialog(null, "Select category!");
                return null;
            }
        }
        else {
            return null;
        }
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
