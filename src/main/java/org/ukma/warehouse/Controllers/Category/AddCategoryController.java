package org.ukma.warehouse.Controllers.Category;

import org.ukma.warehouse.Models.Category;
import org.ukma.warehouse.Models.Message;
import org.ukma.warehouse.Network.Context.GlobalContext;
import org.ukma.warehouse.Services.CategoryService;
import org.ukma.warehouse.UI.Menus.Category.AddCategoryMenu;
import org.ukma.warehouse.Utils.CommandTypeEncoder;

import javax.swing.*;
import java.io.IOException;

public class AddCategoryController {

    private AddCategoryMenu addCategoryMenu;

    private static final int ADD_COMMAND = CommandTypeEncoder.CATEGORY ^ CommandTypeEncoder.CREATE;

    public AddCategoryController(AddCategoryMenu addCategoryMenu) {
        this.addCategoryMenu = addCategoryMenu;
        initView();
    }

    public void showView() {
        fillFields();
        addCategoryMenu.setVisible(true);
    }

    private void initView() {
        addCategoryMenu.getAddButton().addActionListener(e -> accepted());
        addCategoryMenu.getCancelButton().addActionListener(e -> cancel());
    }

    private void fillFields() {
        addCategoryMenu.getCategoryNameField().setText("");
        addCategoryMenu.getCategoryDescriptionField().setText("");
    }

    private void accepted() {
        Category category = new Category();

        category.setName(addCategoryMenu.getCategoryNameField().getText());
        category.setDescription(addCategoryMenu.getCategoryDescriptionField().getText());

        System.out.println(category);
        try {
            GlobalContext.client.sendRequest(ADD_COMMAND, CategoryService.categoryToJson(category));
            Message response = GlobalContext.client.getResponse();
            System.out.println(response);
            if(response.getMessage().equals("ok")) {
                JOptionPane.showMessageDialog(null, "Category accepted!");
                GlobalContext.updateCategoriesCache();
                addCategoryMenu.setVisible(false);
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
        addCategoryMenu.setVisible(false);
    }
}
