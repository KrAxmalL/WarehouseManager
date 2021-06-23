package org.example.Controllers.Category;

import org.example.Models.Category;
import org.example.Models.Message;
import org.example.Models.Product;
import org.example.Network.GlobalContext;
import org.example.UI.Menus.Category.DeleteCategoryMenu;
import org.example.Utils.CommandTypeEncoder;

import javax.swing.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class DeleteCategoryController {

    private DeleteCategoryMenu deleteCategoryMenu;

    private List<Category> categories;

    private static final int DELETE_COMMAND = CommandTypeEncoder.CATEGORY ^ CommandTypeEncoder.DELETE;

    public DeleteCategoryController(DeleteCategoryMenu deleteCategoryMenu) {
        this.deleteCategoryMenu = deleteCategoryMenu;
        //this.categoryService = new CategoryService();
        categories = GlobalContext.categoryCache;
        initView();
    }

    public void showView() {
        updateCategories();
        deleteCategoryMenu.setVisible(true);
    }

    private void updateCategories() {
        JComboBox<Category> comboBox = deleteCategoryMenu.getCategoriesField();
        comboBox.removeAllItems();
        Iterator<Category> it = categories.iterator();
        while(it.hasNext()) {
            comboBox.addItem(it.next());
        }
    }

    private void initView() {
        deleteCategoryMenu.getDeleteButton().addActionListener(e -> accepted());
        deleteCategoryMenu.getCancelButton().addActionListener(e -> cancel());
        //add categories to choose
    }

    private void accepted() {
        Category category = (Category)(deleteCategoryMenu.getCategoriesField().getSelectedItem());

        System.out.println(category);
        if(category != null) {
            try {
                GlobalContext.client.sendRequest(DELETE_COMMAND, String.valueOf(category.getId()));
                Message response = GlobalContext.client.getResponse();
                System.out.println(response);
                if (response.getMessage().equals("ok")) {
                    JOptionPane.showMessageDialog(null, "Category was successfully deleted!");
                    GlobalContext.updateCategoriesCache();
                    GlobalContext.updateProductsCache();
                    deleteCategoryMenu.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Category wasn't deleted!");
                }
                //System.out.println(GlobalContext.productCache);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Choose a category to delete!");
        }
    }

    private void cancel() {
        deleteCategoryMenu.setVisible(false);
    }
}
