package org.example.UI.Menus.Report;

import javax.swing.*;

public class ReportMenu extends JMenu {

    public ReportMenu() {
        super("Report");
        init();
    }

    private void init() {
        JMenuItem allProducts  = new JMenuItem("Show all products");
        //search.addActionListener(new MainWindow.SearchItemsListener());

        JMenuItem filterProducts  = new JMenuItem("Filter products");
        //showItems.addActionListener(new MainWindow.ShowAllItemsInfo());

        JMenuItem allCategories = new JMenuItem("Show all categories");
        //detailedReport.addActionListener(new MainWindow.DetailedReportInfo());

        this.add(allProducts);
        this.add(filterProducts);
        this.add(allCategories);
    }
}
