package org.example.UI;

import org.example.Databases.CrudProductRepository;
import org.example.Models.Product;
import org.example.UI.Menus.Category.CategoryMenu;
import org.example.UI.Menus.Category.ProductTable;
import org.example.UI.Menus.Product.ProductMenu;
import org.example.UI.Menus.ReportMenu;
import org.example.UI.Menus.Stock.StockMenu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;


public class UserInterface extends JFrame 
{
    private static final long serialVersionUID = 1L;
    /** @param MyFont is installed font */
	private Font MyFont = new Font("Calibri", Font.BOLD, 16);
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private WareHouse warehouse = new WareHouse();

	// TODO: delete?
	public void setFont(Component[] comp) {
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
    
    public UserInterface() 
    {
        super("Menu");
        setDefaultCloseOperation( EXIT_ON_CLOSE );
       
        JMenuBar menuBar = new JMenuBar();
       
        menuBar.add(createFileMenu());
        menuBar.add(new ProductMenu());
        menuBar.add(new CategoryMenu());
        menuBar.add(new StockMenu());
        menuBar.add(new ReportMenu());
        
        setJMenuBar(menuBar);
        CrudProductRepository repo = new CrudProductRepository();
        JScrollPane pane = new JScrollPane();
        pane.setViewportView(new ProductTable(repo.getAll().toArray(new Product[0])));
        //setContentPane(new ProductTable());
        this.setLayout(new GridBagLayout());
        add(pane, new GridBagConstraints(
                0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0
        ));
        this.setSize(500, 650);
		setFont(super.getComponents());
		/* Put in the middle of the screen */
		this.setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
		openFileDefaultDB();
		setVisible(true);
    }


	private JMenu createFileMenu()
    {
      
        JMenu file = new JMenu("main");
 
        JMenuItem open = new JMenuItem("Open",new ImageIcon("images/open.png"));

        //Khrystya: new button "Save"
        JMenuItem save = new JMenuItem("Save",new ImageIcon("images/save.png"));
        
        JMenuItem about = new JMenuItem("About");
        JMenuItem exit = new JMenuItem(new ExitAction());

        open.addActionListener(new openFileListener());
       
        exit.setIcon(new ImageIcon("images/exit.png"));
        file.add(open);
        
        //Khrystya: 
        file.add(save);
        
        file.addSeparator();
        file.add(about);
        file.add(exit);

        return file;
    }

    static class ExitAction extends AbstractAction
    {
        private static final long serialVersionUID = 1L;
        ExitAction() {
            putValue(NAME, "Exit");
        }
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private class openFileListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Text files .txt", "txt"));
            fileChooser.setDialogTitle("Choose a file");
            
            //Khrystya 
            fileChooser.setApproveButtonText("Select file");
            //ileChooser.setCurrentDirectory(new File(warehouse.getDBPath()));
            
            //Khrystya filse -> Folders
            //fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            
            int result = fileChooser.showOpenDialog(UserInterface.this);
            if (result == JFileChooser.APPROVE_OPTION) {
            	
            	String r1 = warehouse.readAllCategoriesFromFile(fileChooser.getSelectedFile().getPath());
            	if (!r1.equals("")) 
            	{
         		   JOptionPane.showMessageDialog(UserInterface.this, r1);    		
             	}; 
            	
             	String r2 = warehouse.readAllItemsFromFile(fileChooser.getSelectedFile().getPath());
               	if (!r2.equals("")) 
               	{
          		   JOptionPane.showMessageDialog(UserInterface.this, r2);    		
               	}; 
               	if (r1.equals("")&&r2.equals(""))
                JOptionPane.showMessageDialog(UserInterface.this, "Database is succesfully loaded from folder: " + fileChooser.getSelectedFile());
            }
        }
    }
    
    public void openFileDefaultDB()
    {
    	String r = warehouse.readAllCategoriesFromFile(warehouse.getDBPath());
    	if (!r.equals("")) {
    		   JOptionPane.showMessageDialog(UserInterface.this, r);    		
    	}; 
    	r = warehouse.readAllItemsFromFile(warehouse.getDBPath());
    	if (!r.equals("")) {
 		   JOptionPane.showMessageDialog(UserInterface.this, r);    		
    	};
    }

    private class DeleteItemsListener implements ActionListener {
        JFrame frame;
        JComboBox<Item> itemsField;
        JLabel itemsLabel;
        JButton button;
        public void actionPerformed(ActionEvent e) {
                frame = new JFrame();
                frame.setTitle("Delete item");
                JPanel panel = new JPanel(new GridLayout(0, 1, 0, 10));
                Border border = BorderFactory.createEmptyBorder();
                panel.setBorder(border);
                init();
                panel.add(itemsLabel);
                panel.add(itemsField);
                panel.add(button);
                frame.getContentPane().setLayout(new FlowLayout());
                frame.setSize(400,500);
                frame.setBounds(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getHeight() / 2, frame.getWidth(), frame.getHeight());
                frame.add(panel);
                frame.setVisible(true);
            }

            private void init() {
                itemsField = new JComboBox(new DefaultComboBoxModel(warehouse.getAllItems().toArray()));
                itemsField.setRenderer(new ItemRenderer());
                itemsLabel = new JLabel("Choose item to delete");

                button = new JButton("Delete");
                button.setSize(200, 50);
                button.addActionListener(new ItemDeleteActionListener());
            }

        private class ItemDeleteActionListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                if (itemsField.getSelectedItem() != null){
                int confirmation = JOptionPane.showConfirmDialog(frame, "Are you sure?");
                if (confirmation == JOptionPane.YES_OPTION) {
                    warehouse.deleteItem((Item) itemsField.getSelectedItem());
                    JOptionPane.showMessageDialog(frame, "Deleted successfully");
                    itemsField.setModel(new DefaultComboBoxModel(warehouse.getAllItems().toArray()));
                    frame.setVisible(true);
                }
                }
                else
                {JOptionPane.showMessageDialog(frame, "No Items to delete!");}
            }
        }
    }

    private class DeleteCategoryListener implements ActionListener {

        JFrame frame;
        JComboBox<Category> categoriesField;
        JLabel categoriesLabel;
        JButton button;

        public void actionPerformed(ActionEvent e) {
            frame = new JFrame();
            frame.setTitle("Delete category");
            JPanel panel = new JPanel(new GridLayout(0, 1, 0, 10));
            Border border = BorderFactory.createEmptyBorder();
            panel.setBorder(border);
            init();
            panel.add(categoriesLabel);
            panel.add(categoriesField);
            panel.add(button);
            frame.getContentPane().setLayout(new FlowLayout());
            frame.setSize(400,500);
            frame.setBounds(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getHeight() / 2, frame.getWidth(), frame.getHeight());
            frame.add(panel);
            frame.setVisible(true);
        }

        private void init() {
            categoriesField = new JComboBox(new DefaultComboBoxModel(warehouse.getAllCategories().toArray()));
            categoriesField.setRenderer(new CategoryRenderer());
            categoriesLabel = new JLabel("Choose category to delete");
            
            button = new JButton("Delete");
            button.setSize(200, 50);
            button.addActionListener(new CategoryDeleteActionListener());
        }

        private class CategoryDeleteActionListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                if (categoriesField.getSelectedItem()!= null) {
                int confirmation = JOptionPane.showConfirmDialog(frame, "Are you sure?");
                if (confirmation == JOptionPane.YES_OPTION) {
                    warehouse.deleteCategory((Category) categoriesField.getSelectedItem());
                    JOptionPane.showMessageDialog(frame, "Deleted successfully");
                    categoriesField.setModel(new DefaultComboBoxModel(warehouse.getAllCategories().toArray()));
                    frame.setVisible(true);
                }
                } 
                else
                {JOptionPane.showMessageDialog(frame, "No Category to delete!");}
            }
        }

    }

    public static class CategoryRenderer extends DefaultListCellRenderer {
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value != null) setText(((Category) value).getName());
            return this;
        }
    }

    private static class ItemRenderer extends DefaultListCellRenderer {
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value != null) setText(((Item) value).getName());
            return this;
        }
    }

    private class ManipulateStocksListener implements ActionListener {

	    JFrame frame;
        JLabel itemsBoxLabel;
        JLabel clarificationLabel;
        JLabel currentStockLabel;
	    JComboBox<Item> itemsBox;
	    JSpinner stocksToAdd;
	    JButton button;

	    Item selectedItem;

        public void actionPerformed(ActionEvent e) {
            frame = new JFrame();
            frame.setTitle("Manipulate stocks");
            JPanel panel = new JPanel(new GridLayout(0, 1, 0, 10));
            Border border = BorderFactory.createEmptyBorder();
            panel.setBorder(border);

        //Krystya: check for empty list
        if ( warehouse.getAllItems().size()>0 )
        {
            init();
            panel.add(itemsBoxLabel);
            panel.add(clarificationLabel);
            panel.add(itemsBox);
            panel.add(currentStockLabel);
            panel.add(stocksToAdd);
            panel.add(button);
            frame.getContentPane().setLayout(new FlowLayout());
            frame.setSize(400,500);
            frame.setBounds(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getHeight() / 2, frame.getWidth(), frame.getHeight());
            frame.add(panel);
            frame.setVisible(true);
        }	
        else 
        {
        	JOptionPane.showMessageDialog(frame, "No items found. Stock can not be changed.");
        }
        }

        private void init() {
            itemsBox = new JComboBox(warehouse.getAllItems().toArray());
            itemsBox.setRenderer(new ItemRenderer());
            itemsBox.addActionListener(new ActionListener() {
            	
                public void actionPerformed(ActionEvent e) {
                    selectedItem = (Item)itemsBox.getSelectedItem();
                    currentStockLabel.setText("Current stock qty: " + selectedItem.getStock());
                    stocksToAdd.setModel(new SpinnerNumberModel(1, selectedItem.getStock() * -1, selectedItem != null ? 10000 - selectedItem.getStock() : 10000, 1));
                }
            });
            itemsBoxLabel = new JLabel("Choose item to change stocks amount");
            clarificationLabel = new JLabel("(positive number adds stocks, negative � writes them off)");
            currentStockLabel = new JLabel("");
            
            selectedItem = (Item)itemsBox.getSelectedItem();
            currentStockLabel.setText("Current stock qty: " + selectedItem.getStock());
            stocksToAdd = new JSpinner(new SpinnerNumberModel(1, selectedItem.getStock() * -1, selectedItem != null ? 10000 - selectedItem.getStock() : 10000, 1));

            button = new JButton("Add / Write off");
            button.setSize(200, 50);
            button.addActionListener(new StocksAddActionListener());
        }

        private class StocksAddActionListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                warehouse.selectedItem = selectedItem;
                try {
                    warehouse.updateItemStock((int)stocksToAdd.getValue());
                    JOptionPane.showMessageDialog(frame, "Added, " + selectedItem.getName() + " now has " + selectedItem.getStock() + " stocks.");
                    currentStockLabel.setText("Current stock qty: " + selectedItem.getStock());
                    stocksToAdd.setModel(new SpinnerNumberModel(1, selectedItem.getStock() * -1, selectedItem != null ? 10000 - selectedItem.getStock() : 10000, 1));
                } catch (WarehouseException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    //Shows info about item with the given name. Shows the special message if such item doesn't exist
    private class SearchItemsListener implements ActionListener{
	    
	    public void actionPerformed(ActionEvent e) {
           
        //Victor:: check for empty list
        if ( warehouse.getAllItems().size()>0 )
        {
        	String itemToSearch = JOptionPane.showInputDialog(null, "Enter the name of the item.");
        	if (itemToSearch == null || itemToSearch.matches("\\s*")) {
				JOptionPane.showMessageDialog(null, "Uncorrect input!");
			} else {
				String searchResult = "";
				Item finded = warehouse.searchItem(itemToSearch);
				if(finded == null) {
					searchResult = "Nothing was found on the warehouse.";
				}
				else {
					searchResult = "Search result: " + "\n" + finded.detailedInfo();
				}
				JOptionPane.showMessageDialog(null, searchResult);
			}
        }	
        else 
        {
        	JOptionPane.showMessageDialog(null, "No items found. You cant search for the items if there is nothing.");
        }
        }
    }
    
    //Shows the detailed info about every category, item and total price of the items on the warehose
    private class DetailedReportInfo implements ActionListener {

        JFrame frame;
        ProductTable table;
        JTextArea text;

        public void actionPerformed(ActionEvent e) {
          

            //init();
            frame = new JFrame();
            frame.setTitle("Information about items.");
            frame.setSize(400,500);
            frame.setBounds(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getHeight() / 2, frame.getWidth(), frame.getHeight());

            JScrollPane pane = new JScrollPane();
            //table = new ProductTable();
            pane.setViewportView(table);
            frame.add(pane);
            frame.setVisible(true);
        }
        
        public void init(){
        	frame = new JFrame();
            frame.setTitle("Information about items.");
            frame.setSize(400,500);
            frame.setBounds(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getHeight() / 2, frame.getWidth(), frame.getHeight());
            
            text = new JTextArea();
            String 
            allInfo  = "===========================================\n";
            allInfo += "Detailed info about the warehouse: \n" ;
            allInfo += "===========================================\n\n";
            
            int counter = 0;
            int totalPrice = 0;
            for(Category categ: warehouse.getAllCategories()) {
            counter++;
            double categPrice = 0;
            allInfo+= counter + ". " + categ.detailedInfo();
         
                 int i = 0;
                 double itemPrice = 0;
                 for(Item current: warehouse.getAllItems()) {
                	 if(current.getCategoryId() == categ.getCategoryId()) {
            	 i++;
            	 itemPrice = (current.getStock()*current.getPrice());
            	 allInfo += "-------------------------------------------\n";
            	 allInfo += counter + "." + i + ") " + current.detailedInfo() + "Total item value: " + itemPrice +"\n";
            	 
            	 categPrice += itemPrice;
                 }
                 }
            allInfo += "===========================================\n";                 
            allInfo += "Total value of the all items in the category: " + categPrice + "\n" + "\n";
            
            totalPrice += categPrice; 
            }
            allInfo += "===========================================\n";
            allInfo +=  "Total value of the all items on the warehouse: " + totalPrice +".\n";
            allInfo += "===========================================\n";

            	text.setText(allInfo);
            	text.setSize(400, 500);
            	text.setEditable(false);
            	//Khrystya
        		text.setSelectionStart(0);
        		text.setSelectionEnd(0);
        		
            	text.setVisible(true);
            	JPanel panel = new JPanel(new BorderLayout());
            	panel.add(new JScrollPane(text), BorderLayout.CENTER);
            	panel.setVisible(true);
            	frame.add(panel);
            
        }
            
    }
    
    //Shows all the info about all the items on the warehouse
    private class ShowAllItemsInfo implements ActionListener {

        JFrame frame;
        JTextArea text;

        public void actionPerformed(ActionEvent e) {
          
        //Victor: check for empty list
        if ( warehouse.getAllItems().size()>0 )
        {
            init();        
            frame.setVisible(true);
        }	
        else 
        {
        	JOptionPane.showMessageDialog(frame, "No items found. Nothing can be shown about the warehouse.");
        }
        
        }
        
        public void init(){
        	frame = new JFrame();
            frame.setTitle("Detailed report");
            frame.setSize(400,500);
            frame.setBounds(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getHeight() / 2, frame.getWidth(), frame.getHeight());
            
            text = new JTextArea();
            String allInfo = "Information about all the items on the warehouse: " + "\n" + "\n";
            int i = 0;
            for(Item current: warehouse.getAllItems()) {
            	i++;
            	allInfo += i + ". " + current.detailedInfo() + "\n";
            }
            	text.setText(allInfo);
            	text.setSize(400, 500);
            	text.setEditable(false);
      //Khrystya
        		text.setSelectionStart(0);
        		text.setSelectionEnd(0);
        		
            	text.setVisible(true);
            	JPanel panel = new JPanel(new BorderLayout());
            	panel.add(new JScrollPane(text), BorderLayout.CENTER);
            	panel.setVisible(true);
            	frame.add(panel);
            }    
    }
}

   