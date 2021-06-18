package org.example.UI.Menus;

import org.example.UI.UserInterface;

import javax.swing.*;

public class ReportMenu extends JMenu {

    public ReportMenu() {
        super("Report");
        init();
    }

    private void init()
    {
        JMenuItem search  = new JMenuItem("Search");
        //search.addActionListener(new UserInterface.SearchItemsListener());

        JMenuItem showItems  = new JMenuItem("Show all items");
        //showItems.addActionListener(new UserInterface.ShowAllItemsInfo());

        JMenuItem detailedReport = new JMenuItem("Detailed report");
        //detailedReport.addActionListener(new UserInterface.DetailedReportInfo());

        this.add(search);
        this.add(showItems);
        this.add(detailedReport);
    }
}
