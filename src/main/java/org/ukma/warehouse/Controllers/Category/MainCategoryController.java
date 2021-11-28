package org.ukma.warehouse.Controllers.Category;

import org.ukma.warehouse.UI.Menus.Category.AddCategoryMenu;
import org.ukma.warehouse.UI.Menus.Category.DeleteCategoryMenu;
import org.ukma.warehouse.UI.Menus.Category.EditCategoryMenu;
import org.ukma.warehouse.UI.Menus.Category.CategoryMenu;

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
        addCategoryController.showView();
    }

    public void showEditCategoryMenu() {
        editCategoryController.showView();
    }

    public void showDeleteCategoryMenu() {
        deleteCategoryController.showView();
    }
}
