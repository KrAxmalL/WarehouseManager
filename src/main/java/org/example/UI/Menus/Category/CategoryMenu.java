package org.example.UI.Menus.Category;

import org.example.UI.UserInterface;

import javax.swing.*;

public class CategoryMenu extends JMenu {

    public CategoryMenu() {
        super("Category");
        init();
    }

    private void init()
    {
        JMenuItem newCategory  = new JMenuItem("New Category");
        JMenuItem changeCategory  = new JMenuItem("Change Category");
        JMenuItem deleteCategory = new JMenuItem("Delete Category");

        //newCategory.addActionListener(new UserInterface.NewCategoryListener());
        //changeCategory.addActionListener(new UserInterface.ChangeCategoryListener());
        //deleteCategory.addActionListener(new UserInterface.DeleteCategoryListener());

        this.add(newCategory);
        this.add(changeCategory);
        this.add(deleteCategory);
    }
}
