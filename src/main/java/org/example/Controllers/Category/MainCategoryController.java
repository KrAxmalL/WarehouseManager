package org.example.Controllers.Category;

import org.example.Controllers.Category.AddCategoryController;
import org.example.Controllers.Category.DeleteCategoryController;
import org.example.Controllers.Category.EditCategoryController;
import org.example.UI.Menus.Category.AddCategoryMenu;
import org.example.UI.Menus.Category.DeleteCategoryMenu;
import org.example.UI.Menus.Category.EditCategoryMenu;
import org.example.UI.Menus.Category.CategoryMenu;

public class MainCategoryController {

    private CategoryMenu categoryMenu;
    private AddCategoryMenu addCategoryMenu;
    private EditCategoryMenu editCategoryMenu;
    private DeleteCategoryMenu deleteCategoryMenu;

    private AddCategoryController addCategoryController;
    private EditCategoryController editCategoryController;
    private DeleteCategoryController deleteCategoryController;

    public MainCategoryController(CategoryMenu categoryMenu) {
        this.categoryMenu = categoryMenu;
        initView();
        initControllers();
    }

    private void initView() {
        this.addCategoryMenu = new AddCategoryMenu();
        this.editCategoryMenu = new EditCategoryMenu();
        this.deleteCategoryMenu = new DeleteCategoryMenu();

        categoryMenu.getNewCategory().addActionListener(e -> showAddCategoryMenu());
        categoryMenu.getEditCategory().addActionListener(e -> showEditCategoryMenu());
        categoryMenu.getDeleteCategory().addActionListener(e -> showDeleteCategoryMenu());
    }

    private void initControllers() {
        addCategoryController = new AddCategoryController(addCategoryMenu);
        editCategoryController = new EditCategoryController(editCategoryMenu);
        deleteCategoryController = new DeleteCategoryController(deleteCategoryMenu);
    }

    public void showAddCategoryMenu() {
        if(addCategoryMenu == null) {
            addCategoryMenu = new AddCategoryMenu();
        }
        addCategoryMenu.setVisible(true);
    }

    public void showEditCategoryMenu() {
        if(editCategoryMenu == null) {
            editCategoryMenu = new EditCategoryMenu();
        }
        editCategoryMenu.setVisible(true);
    }

    public void showDeleteCategoryMenu() {
        if(deleteCategoryMenu == null) {
            deleteCategoryMenu = new DeleteCategoryMenu();
        }
        deleteCategoryMenu.setVisible(true);
    }
}
