package org.example.Controllers.Category;

import org.example.Models.Category;
import org.example.Models.Category;
import org.example.Models.Message;
import org.example.Network.GlobalContext;
import org.example.Services.CategoryService;
import org.example.Services.ProductService;
import org.example.UI.Menus.Category.AddCategoryMenu;
import org.example.Utils.CommandTypeEncoder;

import javax.swing.*;
import java.io.IOException;

public class AddCategoryController {

    private AddCategoryMenu addCategoryMenu;

    private static final int ADD_COMMAND = CommandTypeEncoder.CATEGORY ^ CommandTypeEncoder.CREATE;

    public AddCategoryController(AddCategoryMenu addCategoryMenu) {
        this.addCategoryMenu = addCategoryMenu;
        //this.categoryService = new CategoryService();
        initView();
    }

    public void showView() {
        addCategoryMenu.setVisible(true);
    }

    private void initView() {
        addCategoryMenu.getAddButton().addActionListener(e -> accepted());
        addCategoryMenu.getCancelButton().addActionListener(e -> cancel());
        //add categories to choose
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
