package org.example.UI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class WareHouse {

	private static Collection<Item> items;
	private static Collection<Category> categories;
	/*
	 * selectedItem is one object from items collection, selected in user
	 * interface and should be used for manipulation with this item (update any
	 * item parameters)
	 */
	public static Item selectedItem;
	/*
	 * selectedCategory is one object from categories collection, selected in
	 * user interface and should be used for manipulation with this category
	 * (update any category parameters)
	 */
	public static Category selectedCategory;
	
	private static String pathToDBFile = "C:\\temp\\lab2\\";
	
	/* Path to file with info about items */
	private static String pathToItemsFile = "";
	
	private static String itemsFileName = "lab2_items_db.txt";
	
	/* Path to file with info about categories */
	private static String pathToCategoriesFile = "";
	
	private static String categoriesFileName = "lab2_categories_db.txt";
	/* Constructor */
	public WareHouse() {
		/* Maybe ArrayList */
		items = new LinkedList<>();
		/* Maybe ArrayList */
		categories = new LinkedList<>();
		selectedItem = null;
		selectedCategory = null;
	}

	public String getitemsFileName(){
		return this.itemsFileName;
	}

	public String getCategoriesFileName () {
		return this.categoriesFileName;
	}

	
	public String getDBPath () {
		return this.pathToDBFile;
	}
	
	public boolean checkForUniqueNameForItem(String newName) {
		for (Item item : items) {
			if (item.getName().equals(newName)) {
				return false;
			}
		}
		return true;
	}

	public boolean checkForUniqueNameForCategory(String newName) {
		for (Category category : categories) {
			if (category.getName().equals(newName)) {
				return false;
			}
		}
		return true;
	}

	public void addCategory(String name, String description/* ? */) throws WarehouseException {
		if (checkForUniqueNameForCategory(name)) {
			categories.add(new Category(name, description));
		} else {
			throw new WarehouseException.NotUniqueNameException("category");
		}
	}

	public void updateCategoryName(String newName /* ? */) throws WarehouseException {
		if (checkForUniqueNameForCategory(newName)) {
			selectedCategory.setName(newName);
		} else {
			throw new WarehouseException.NotUniqueNameException("category");
		}
	}

	public void updateCategoryDescription(String newDescription/* ? */) {
		selectedCategory.setDescription(newDescription);
	}

	public Category findCategory(String categoryName /* ? */) {
		for (Category category : categories) {
			if (category.getName().equals(categoryName)) {
				return category;
			}
		}
		return null;
	}
	

	public void deleteAllCategories() 
	{
		categories.removeAll(categories);
		items.removeAll(items);
		
	}
	

	public void deleteCategory(Category categoryToDelete /* ? */) {

		for (Category category : categories) {
			if (category.equals(categoryToDelete)) /* ??? */
			{
				deleteAllItemsFromCategory(category);
				categories.remove(category);
				break;
			}
		}
	}

	public void deleteAllItemsFromCategory(Category categoryToDelete) {
		ListIterator<Item> it = (ListIterator<Item>) items.iterator();
		Item item;
	    while(it.hasNext()) 
	    {		
	    	item = it.next();
			if (item.getCategoryId() == categoryToDelete.getCategoryId()) {	    	
				it.remove();
			}
		}
	}

	public void addItem(String newName, String newDescription, String newSupplier, double newPrice,
			int newCategory_id /* ? */) throws WarehouseException {
		if (checkForUniqueNameForItem(newName)) {
			items.add(new Item(newName, newDescription, newSupplier, newPrice, newCategory_id));
		} else {
			throw new WarehouseException.NotUniqueNameException("item");
		}
	}

	public void updateItemName(String newName) throws WarehouseException {
		if (checkForUniqueNameForItem(newName)) {
			selectedItem.setName(newName);
		} else {
			throw new WarehouseException.NotUniqueNameException("item");
		}
	}

	public void updateItemDescription(String newDescription /* ? */) {
		selectedItem.setDescription(newDescription);
	}

	public void updateItemSupplier(String newSupplier /* ? */) {
		selectedItem.setSupplier(newSupplier);
	}

	public void updateItemPrice(double newPrice /* ? */) {
		selectedItem.setPrice(newPrice);
	}

	public void updateItemStock(int newStock_qty /* ? */) throws WarehouseException {
		if (newStock_qty > 0) {
			selectedItem.addStock(newStock_qty);
		} else {
			if(!selectedItem.writeOffStock(-newStock_qty))
			{
				throw new WarehouseException.WriteOffQualityExceedingException();
			}
			
		}
	}

	public Item findItem(String itemName /* ? */) {
		for (Item item : items) {
			if (item.getName().equals(itemName)) {
				return item;
			}
		}
		return null;
	}

	public void deleteItem(Item itemToDelete /* ? */) {
		for (Item item : items) {
			if (item.equals(itemToDelete)) {
				items.remove(item);
				break;
			}
		}
	}

	public String getCategoryNameById(int c_Id) {
		for (Category category : categories) {
			if (category.getCategoryId() == c_Id) {
				return category.getName();
			}
		}
		return "No categories with such id!";
	}


	public Collection<Item> getAllItems() {
		return items;
	}

	public Collection<Category> getAllCategories() {
		return categories;
	}

	public String[][] getTotalInformation() {
		return null;
	}

	//writes all the categories with descriptions to the file with the given path
	public void writeAllCategoriesToFile(String fileName) {
		
        //Khrystya selected_folder_path + fixed file_name
		pathToCategoriesFile = fileName + "\\" + this.categoriesFileName;
		
		BufferedWriter wr;
		try {
			wr = new BufferedWriter(new FileWriter(pathToCategoriesFile));
			wr.write(this.allCategoriesToString());
			wr.close();
		} catch(IOException e) {
			System.err.println("Problems with file saving");
		}
	}

	//return the string which contains all text from the file about categories
	public String readAllCategoriesFromFile(String fileName) {
		
        //Khrystya selected_folder_path + fixed file_name
		pathToCategoriesFile = fileName + "\\" + this.categoriesFileName;

		Scanner rd;
		String result = "";
		try {
			rd = new Scanner(new File(pathToCategoriesFile));
			deleteAllCategories();
			int line_id = 0;
			while(rd.hasNextLine()) {
				String str = rd.nextLine();
				line_id++;
				if (line_id>2) 
				{
				//Krystya:  "|" is a symbol used to separate fields in saved file 
				String [] record = str.split("[|]");
	             //(int newID, String newName, String newDescription)
				categories.add(new Category(Integer.parseInt(record[0]), record[1], record[2]));
				}
//				result += str;				
			}
			rd.close();
		} catch(IOException e) {
			result = "Data file lab2_items_db.txt is not found in folder "+fileName+"\n"
					 +"You may open data file from another location.";
			//System.err.println("Problems with file opening");
		}
		
		return result;
	}

	//writes all the items with descriptions to the file with the given path
	public void writeAllItemsToFile(String fileName) {

		//Khrystya selected_folder_path + fixed file_name		
		pathToItemsFile = fileName + "\\" + this.itemsFileName;
		BufferedWriter wr;
		try {
			wr = new BufferedWriter(new FileWriter(pathToItemsFile));
			wr.write(this.allItemsToString());
			wr.close();
		} catch(IOException e) {
			System.err.println("Problems with file saving");
		} 
	}

	
	//return the string which contains all text from the file about items
	public String readAllItemsFromFile(String fileName) {

		//Khrystya selected_folder_path + fixed file_name		
		pathToItemsFile = fileName + "\\" + this.itemsFileName;
		Scanner rd;
		String result = "";
		try {
			rd = new Scanner(new File(pathToItemsFile));
			int line_id = 0;
			while(rd.hasNextLine()) {
				String str = rd.nextLine();
				line_id++;
				if (line_id>2) 
				{
				//Krystya:  "|" is a symbol used to separate fields in saved file 
				String [] record = str.split("[|]");
	             //(int newID, String newName, String newDescription, String newSupplier, double newPrice, int newCategory_id)
				items.add(new Item(Integer.parseInt(record[0]), record[1], record[2],  record[3], Integer.parseInt(record[4]), Double.parseDouble(record[5]), Integer.parseInt(record[6])));
				}
	//			result += str;				
			}
			rd.close();
		} catch(IOException e) {
			//System.err.println("Problems with file opening");
			result = "Data file lab2_items_db.txt is not found in folder "+fileName+"\n"
					 +"You may open data file from another location.";
		}
		return result;
	}
	
	//returns the desription of all items on the warehouse
	public String allItemsToString() {
		String result = "The list of all available items: " + "\n";
		result += "Item id | Item Name | Item Description | Item Supplier | Item Stock_qty  | Item Price | Item Category id" + "\n";
		for(Item item: items) {
			result += item.toString() + "\n";
		}
		return result;
	}
	
	//returns the desription of the items of the given category
	public String onlyCategoryItemsToString(Category category) {
		String result = "All items of the " +category.getName()+ " category:" + "\n";
		for(Item item: items) {
			if(item.getCategoryId() == category.getCategoryId()) {
				result += item.toString() + "\n";
			}
		}
		return result;
	}
	
	//returns total price of all the items on the warehouse
	public double totalPrice() {
		double result = 0;
		for(Item item: items) {
			result += item.getPrice();
		}
		return result;
	}
	
	//returns the total price of the items of the given category
	public double totalCategoryPrice(Category category) {
		double result = 0;
		for(Item item: items) {
			if(item.getCategoryId() == category.getCategoryId()) {
				result += item.getPrice();
			}
		}
		return result;
	}
	
	//Return the description of the all categories
	public String allCategoriesToString() {
		String result = "The list of all available categories: " + "\n";
		result += "Category id | Category Name | Category Description" + "\n";
		for(Category category: categories) {
			result += category.toString() + "\n";
		}
		return result;
	}
	
	//Returns the item with the given name
	public Item searchItem(String name) {
		for(Item current: items) {
			if(current.getName().equals(name)) {
				return current;
			}
		}
		return null;
	}
}
