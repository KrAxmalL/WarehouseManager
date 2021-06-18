package org.example.UI.Menus;

import javax.swing.*;

public class ExitMenu extends JMenu {

    private JMenuItem exit;

    public ExitMenu() {
        super("Main");
        init();
    }

    private void init() {
        exit = new JMenuItem("Exit");
        exit.addActionListener((e)-> System.exit(0));
        this.add(exit);
    }
}
