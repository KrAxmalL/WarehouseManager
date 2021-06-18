package org.example.Controllers.Category;

import org.example.Models.Category;
import org.example.Models.Category;
import org.example.UI.Menus.Category.EditCategoryMenu;

import javax.swing.*;

public class EditCategoryController {

    private EditCategoryMenu editCategoryMenu;

    public EditCategoryController(EditCategoryMenu editCategoryMenu) {
        this.editCategoryMenu = editCategoryMenu;
        //this.categoryService = new CategoryService();
        initView();
    }

    private void initView() {
        editCategoryMenu.getEditButton().addActionListener(e -> accepted());
        editCategoryMenu.getCancelButton().addActionListener(e -> cancel());
        //add categories to choose
    }

    private void accepted() {
        Category pr = new Category();

        pr.setId((((Category)(editCategoryMenu.getCategoryField().getSelectedItem())).getId()));
        pr.setName(editCategoryMenu.getCategoryNameField().getText());
        pr.setDescription(editCategoryMenu.getCategoryDescriptionField().getText());

        System.out.println(pr);
        //if(CategoryService.validateCategory(pr)) {
            JOptionPane.showMessageDialog(null, "Category edition accepted!");
        //}
        //else {
            //JOptionPane.showMessageDialog(null, "Wrong input!");
        //}
    }

    private void cancel() {
        editCategoryMenu.setVisible(false);
    }
}
