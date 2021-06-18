package org.example.UI.Menus.Category;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        newCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCategoryMenu();
            }
        });

        changeCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditCategoryMenu();
            }
        });

        deleteCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteCategoryMenu();
            }
        });

        this.add(newCategory);
        this.add(changeCategory);
        this.add(deleteCategory);
    }
}
