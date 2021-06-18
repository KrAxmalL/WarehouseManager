package org.example.Controllers.Category;

import org.example.Models.Category;
import org.example.Models.Category;
import org.example.UI.Menus.Category.AddCategoryMenu;

import javax.swing.*;

public class AddCategoryController {

    private AddCategoryMenu addCategoryMenu;

    public AddCategoryController(AddCategoryMenu addCategoryMenu) {
        this.addCategoryMenu = addCategoryMenu;
        //this.categoryService = new CategoryService();
        initView();
    }

    private void initView() {
        addCategoryMenu.getAddButton().addActionListener(e -> accepted());
        addCategoryMenu.getCancelButton().addActionListener(e -> cancel());
        //add categories to choose
    }

    private void accepted() {
        Category pr = new Category();

        pr.setName(addCategoryMenu.getCategoryNameField().getText());
        pr.setDescription(addCategoryMenu.getCategoryDescriptionField().getText());

        System.out.println(pr);
        //if(CategoryService.validateCategory(pr)) {
            JOptionPane.showMessageDialog(null, "Category accepted!");
        //}
        //else {
            //JOptionPane.showMessageDialog(null, "Wrong input!");
        //}
    }

    private void cancel() {
        addCategoryMenu.setVisible(false);
    }
}
