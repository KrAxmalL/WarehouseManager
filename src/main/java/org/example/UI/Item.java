package org.example.UI;

public class Item {

	private String name = "";
	private String description = "";
	private String supplier = "";
	private int stock_qty = 0;
	private double price = 0;
	private static int MAX_ID = 0;
	private int id = 0;
	private int category_id = 0;

	/* Constructor to import data from a saved file*/
	public Item(int newID, String newName, String newDescription, String newSupplier, int newStock_qty, double newPrice, int newCategory_id) {
		this.id = newID;
		this.name = newName;
		this.description = newDescription;
		this.supplier = newSupplier;
		this.stock_qty = newStock_qty;
		this.price = newPrice;
		this.category_id = newCategory_id;
		if (MAX_ID < newID){MAX_ID = newID;}
	}

	/* Constructor */
	public Item(String newName, String newDescription, String newSupplier, double newPrice, int newCategory_id) {
		this.name = newName;
		this.description = newDescription;
		this.supplier = newSupplier;
		this.stock_qty = 0;
		this.price = newPrice;
		this.category_id = newCategory_id;

		MAX_ID++;
		id = MAX_ID;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String newDescription) {
		this.description = newDescription;
	}

	public String getSupplier() {
		return this.supplier;
	}

	public void setSupplier(String newSupplier) {
		this.supplier = newSupplier;
	}

	public int getStock() {
		return this.stock_qty;
	}

	public void addStock(int qty) {
		this.stock_qty += qty;
	}

	/*true means succesful writeOff, 
	 *false - error, not enough actual stock_qty to writeOff
	 *New stock becomes zero ?????*/
	public boolean writeOffStock(int qty) {
		/* check for negative stock quantity */
		if (qty > this.stock_qty) {
			// Throw an error. TODO implement that in GUI
			System.out.println("Error: you can't write off more items than you have.");
			return false;
		} else {
			this.stock_qty -= qty;
			return true;
		}
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double newPrice) {
		this.price = newPrice;
	}

	public int getCategoryId() {
		return this.category_id;
	}

	public void setCategoryId(int newCategoryId) {
		this.category_id = newCategoryId;
	}

	//Krystya:  "|" is a symbol used to separate fields in saved file
	@Override
	public String toString() {
//		return "Item: " + this.name + "\nDescription: " + this.description + "\nSupplier: " + this.supplier	+ "\nStock_qty: " + this.stock_qty + "\nPrice: " + this.price + "\nItem Id:" + this.id + "\nCategory Id:" + this.category_id ;
		return this.id + "|" + this.name + "|" + this.description + "|" + this.supplier	+ "|" + this.stock_qty + "|" + this.price + "|" + this.category_id ;		
	}
	
	//Returns the detailed info about the item
	public String detailedInfo() {
		return "Item: " + this.name + "\nDescription: " + this.description + "\nSupplier: " + this.supplier	+ "\nStock_qty: " + this.stock_qty + "\nPrice: " + this.price + "\nItem Id:" + this.id + "\nCategory Id:" + this.category_id + "." + "\n";
	}
}
