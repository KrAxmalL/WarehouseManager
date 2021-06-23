package org.example.Controllers.Category;

import org.example.Models.Category;
import org.example.Models.Category;
import org.example.Models.Message;
import org.example.Network.GlobalContext;
import org.example.Services.CategoryService;
import org.example.UI.Menus.Category.EditCategoryMenu;
import org.example.Utils.CommandTypeEncoder;

import javax.swing.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class EditCategoryController {

    private EditCategoryMenu editCategoryMenu;

    private List<Category> categories;

    private static final int EDIT_COMMAND = CommandTypeEncoder.CATEGORY ^ CommandTypeEncoder.UPDATE;

    public EditCategoryController(EditCategoryMenu editCategoryMenu) {
        this.editCategoryMenu = editCategoryMenu;
        //this.categoryService = new CategoryService();
        categories = GlobalContext.categoryCache;
        initView();
    }

    public void showView() {
        updateCategories();
        editCategoryMenu.setVisible(true);
    }

    private void initView() {
        editCategoryMenu.getEditButton().addActionListener(e -> accepted());
        editCategoryMenu.getCancelButton().addActionListener(e -> cancel());

        updateCategories();
    }

    private void updateCategories() {
        JComboBox<Category> comboBox = editCategoryMenu.getCategoryField();
        comboBox.removeAllItems();
        Iterator<Category> it = categories.iterator();
        while(it.hasNext()) {
            comboBox.addItem(it.next());
        }
    }

    private void accepted() {
        Category selected = (Category)(editCategoryMenu.getCategoryField().getSelectedItem());
        if(selected != null) {
            Category category = new Category();
            category.setId(selected.getId());
            category.setName(editCategoryMenu.getCategoryNameField().getText());
            category.setDescription(editCategoryMenu.getCategoryDescriptionField().getText());

            System.out.println(category);
            try {
                GlobalContext.client.sendRequest(EDIT_COMMAND, CategoryService.categoryToJson(category));
                Message response = GlobalContext.client.getResponse();
                System.out.println(response);
                if (response.getMessage().equals("ok")) {
                    JOptionPane.showMessageDialog(null, "Category edited successfully!");
                    GlobalContext.updateCategoriesCache();
                    GlobalContext.updateProductsCache();
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
            JOptionPane.showMessageDialog(null, "Choose a category to edit!");
        }
    }

    private void cancel() {
        editCategoryMenu.setVisible(false);
    }
}
