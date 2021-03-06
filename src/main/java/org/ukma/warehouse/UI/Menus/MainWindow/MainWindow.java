package org.ukma.warehouse.UI.Menus.MainWindow;

import org.ukma.warehouse.UI.Menus.Category.CategoryMenu;
import org.ukma.warehouse.UI.Menus.Exit.ExitMenu;
import org.ukma.warehouse.UI.Menus.Product.ProductMenu;
import org.ukma.warehouse.UI.Menus.Report.ReportMenu;
import org.ukma.warehouse.UI.Menus.Stock.StockMenu;
import org.ukma.warehouse.Utils.Config;

import java.awt.*;

import javax.swing.*;


public class MainWindow extends JFrame
{
    private static final long serialVersionUID = 1L;

	private Font MyFont = new Font("Calibri", Font.BOLD, 16);
    public static final Dimension DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();

    private JMenuBar menuBar;

    private ExitMenu exitMenu;
    private ProductMenu productMenu;
    private CategoryMenu categoryMenu;
    private StockMenu stockMenu;
    private ReportMenu reportMenu;

    private JScrollPane scrollPane;

    public MainWindow()
    {
        super("Warehouse Manager");
        setup();
        initComponents();
    }

    private void initComponents() {
        exitMenu = new ExitMenu();
        productMenu = new ProductMenu();
        categoryMenu = new CategoryMenu();
        stockMenu = new StockMenu();
        reportMenu = new ReportMenu();

        menuBar = new JMenuBar();
        menuBar.add(exitMenu);
        menuBar.add(productMenu);
        menuBar.add(categoryMenu);
        menuBar.add(stockMenu);
        menuBar.add(reportMenu);

        setJMenuBar(menuBar);

        scrollPane = new JScrollPane();
        add(scrollPane, new GridBagConstraints(
                0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0
        ));
    }

    private void setup() {
        setDefaultCloseOperation( EXIT_ON_CLOSE );

        this.setLayout(new GridBagLayout());
        setFont(super.getComponents());

        this.setSize(Config.MAIN_WIDTH, Config.MAIN_HEIGHT);
        this.setLocation(DIMENSION.width / 2 - this.getWidth() / 2, DIMENSION.height / 2 - this.getHeight() / 2);
    }

    private void setFont(Component[] comp) {
        for (Component component : comp) {
            if (component instanceof Container)
                setFont(((Container) component).getComponents());
            try {
                component.setFont(MyFont);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setMenuBar(JMenuBar menuBar) {
        this.menuBar = menuBar;
    }

    public ExitMenu getExitMenu() {
        return exitMenu;
    }

    public void setExitMenu(ExitMenu exitMenu) {
        this.exitMenu = exitMenu;
    }

    public ProductMenu getProductMenu() {
        return productMenu;
    }

    public void setProductMenu(ProductMenu productMenu) {
        this.productMenu = productMenu;
    }

    public CategoryMenu getCategoryMenu() {
        return categoryMenu;
    }

    public void setCategoryMenu(CategoryMenu categoryMenu) {
        this.categoryMenu = categoryMenu;
    }

    public StockMenu getStockMenu() {
        return stockMenu;
    }

    public void setStockMenu(StockMenu stockMenu) {
        this.stockMenu = stockMenu;
    }

    public ReportMenu getReportMenu() {
        return reportMenu;
    }

    public void setReportMenu(ReportMenu reportMenu) {
        this.reportMenu = reportMenu;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

}

   