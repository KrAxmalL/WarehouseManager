package org.ukma.warehouse.UI.Menus.Report;

import javax.swing.*;

public class ReportMenu extends JMenu {

    private JMenuItem allProducts;
    private JMenuItem filterProducts;
    private JMenuItem allCategories;
    private JMenuItem statistics;

    public ReportMenu() {
        super("Report");
        init();
    }

    private void init() {
        allProducts  = new JMenuItem("Show all products");
        filterProducts  = new JMenuItem("Filter products");
        allCategories = new JMenuItem("Show all categories");
        statistics = new JMenuItem("Statistics");

        this.add(allProducts);
        this.add(filterProducts);
        this.add(allCategories);
        this.add(statistics);
    }

    public JMenuItem getAllProducts() {
        return allProducts;
    }

    public void setAllProducts(JMenuItem allProducts) {
        this.allProducts = allProducts;
    }

    public JMenuItem getFilterProducts() {
        return filterProducts;
    }

    public void setFilterProducts(JMenuItem filterProducts) {
        this.filterProducts = filterProducts;
    }

    public JMenuItem getAllCategories() {
        return allCategories;
    }

    public void setAllCategories(JMenuItem allCategories) {
        this.allCategories = allCategories;
    }

    public JMenuItem getStatistics() {
        return statistics;
    }

    public void setStatistics(JMenuItem statistics) {
        this.statistics = statistics;
    }
}
