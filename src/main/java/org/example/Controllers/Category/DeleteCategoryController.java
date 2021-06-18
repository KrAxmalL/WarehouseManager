package org.example.Controllers.Category;

import org.example.Models.Category;
import org.example.UI.Menus.Category.DeleteCategoryMenu;

import javax.swing.*;

public class DeleteCategoryController {

    private DeleteCategoryMenu deleteCategoryMenu;

    public DeleteCategoryController(DeleteCategoryMenu deleteCategoryMenu) {
        this.deleteCategoryMenu = deleteCategoryMenu;
        //this.categoryService = new CategoryService();
        initView();
    }

    private void initView() {
        deleteCategoryMenu.getDeleteButton().addActionListener(e -> accepted());
        deleteCategoryMenu.getCancelButton().addActionListener(e -> cancel());
        //add categories to choose
    }

    private void accepted() {
        Category pr = (Category)(deleteCategoryMenu.getCategoriesField().getSelectedItem());

        System.out.println(pr);
        //if(CategoryService.validateCategory(pr)) {
            JOptionPane.showMessageDialog(null, "Category deleting accepted!");
        //}
        //else {
        //    JOptionPane.showMessageDialog(null, "Category wasn't chosen!");
        //}
    }

    private void cancel() {
        deleteCategoryMenu.setVisible(false);
    }
}
