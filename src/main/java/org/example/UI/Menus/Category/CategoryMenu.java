package org.example.UI.Menus.Category;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryMenu extends JMenu {

    private JMenuItem newCategory;
    private JMenuItem editCategory;
    private JMenuItem deleteCategory;

    public CategoryMenu() {
        super("Category");
        init();
    }

    private void init() {
        newCategory  = new JMenuItem("New Category");
        editCategory  = new JMenuItem("Change Category");
        deleteCategory = new JMenuItem("Delete Category");

        this.add(newCategory);
        this.add(editCategory);
        this.add(deleteCategory);
    }

    public JMenuItem getNewCategory() {
        return newCategory;
    }

    public void setNewCategory(JMenuItem newCategory) {
        this.newCategory = newCategory;
    }

    public JMenuItem getEditCategory() {
        return editCategory;
    }

    public void setEditCategory(JMenuItem editCategory) {
        this.editCategory = editCategory;
    }

    public JMenuItem getDeleteCategory() {
        return deleteCategory;
    }

    public void setDeleteCategory(JMenuItem deleteCategory) {
        this.deleteCategory = deleteCategory;
    }
}
