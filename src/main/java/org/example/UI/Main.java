package org.example.UI;

import org.example.Controllers.Main.MainController;
import org.example.Databases.CrudCategoryRepository;
import org.example.Databases.CrudProductRepository;
import org.example.Databases.CrudUserRepository;
import org.example.Models.Category;
import org.example.Models.Product;
import org.example.Models.User;
import org.example.Utils.Config;
import org.example.Utils.MyCipher;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {

		initUsers();
		initCategories();
		initProducts();

		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				new MainController();
				//new MainWindow();
			}
		});
	}

	private static void initUsers() {
		CrudUserRepository users = new CrudUserRepository();
		MyCipher cipher = new MyCipher();
		User user = new User(Config.TEST_LOGIN, cipher.encode(Config.TEST_PASSWORD));
		users.addUser(user);
	}

	private static void initCategories() {
		CrudCategoryRepository categories = new CrudCategoryRepository();
		for(int i = 1; i <= 5; i++) {
			String name = "category number " + i;
			String description = "description number " + i;
			categories.addCategory(new Category(name, description));
		}
	}

	private static void initProducts() {
		CrudProductRepository products = new CrudProductRepository();
		for(int i = 1; i <= 10; i++) {
			String name = "product number " + i;
			String description = "description " + i;
			String producer = "producer number " + i;
			int categoryId = i % 5;
			if(categoryId == 0) {
				categoryId = 1;
			}
			int price = 100 * i;
			int amount = 10 * i;
			products.addProduct(new Product(name, description, producer, categoryId, price, amount));
		}
	}

}
