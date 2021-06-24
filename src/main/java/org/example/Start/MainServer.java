package org.example.Start;

import org.example.Databases.CrudCategoryRepository;
import org.example.Databases.CrudProductRepository;
import org.example.Databases.CrudUserRepository;
import org.example.Models.Category;
import org.example.Models.Product;
import org.example.Models.User;
import org.example.Network.Server.ServeTCPClient;
import org.example.Utils.Config;
import org.example.Utils.MyCipher;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {

    public static void main(String[] args) throws IOException {

        initUsers();
        initCategories();
        initProducts();

        ServerSocket server = new ServerSocket(Config.SERVER_PORT);
        try {
            for(int i = 0; i < 1; i++) {
                Socket socket = server.accept();
                new ServeTCPClient(socket);
            }
        }
        finally {
            server.close();
        }
    }

    private static void initUsers() {
        CrudUserRepository users = new CrudUserRepository();
        MyCipher cipher = new MyCipher();
        String login = "login";
        String password = "login";
        users.addUser(new User(login, cipher.encode(password)));

        login = "user";
        password = "user";
        users.addUser(new User(login, cipher.encode(password)));
    }

    private static void initCategories() {
        CrudCategoryRepository categories = new CrudCategoryRepository();
        Category first = new Category("fruits", "fresh fruits");
        Category second = new Category("vegetables", "vegetables from Germany");
        Category third = new Category("bread", "");
        Category fourth = new Category("fish", "");
        Category fifth = new Category("meat", "fresh meat");

        categories.addCategory(first);
        categories.addCategory(second);
        categories.addCategory(third);
        categories.addCategory(fourth);
        categories.addCategory(fifth);
    }

    private static void initProducts() {
        CrudProductRepository products = new CrudProductRepository();

        Product first = new Product("apple", "", "garden makers inc", 1, 10, BigDecimal.valueOf(100));
        Product second = new Product("orange", "oranges from Spain", "garden makers inc", 1, 20, BigDecimal.valueOf(200));
        Product third = new Product("banana", "", "super kitchen", 1, 30, BigDecimal.valueOf(300));
        Product fourth = new Product("cucumber", "", "catchy food", 2, 40, BigDecimal.valueOf(400));
        Product fifth = new Product("tomato", "fresh tomatoes", "import expo", 2, 50, BigDecimal.valueOf(500));
        Product sixth = new Product("onion", "", "import expo", 2, 60, BigDecimal.valueOf(600));
        Product seventh = new Product("teremnivskyi", "", "teremno", 3, 70, BigDecimal.valueOf(700));
        Product eight = new Product("salmon", "tasty salmon", "sea lovers inc", 4, 80, BigDecimal.valueOf(800));
        Product ninth = new Product("tuna", "tuna from the Black Sea", "salt crew", 4, 90, BigDecimal.valueOf(900));
        Product tenth = new Product("chicken", "", "eco inspiration", 5, 100, BigDecimal.valueOf(1000));

        products.addProduct(first);
        products.addProduct(second);
        products.addProduct(third);
        products.addProduct(fourth);
        products.addProduct(fifth);
        products.addProduct(sixth);
        products.addProduct(seventh);
        products.addProduct(eight);
        products.addProduct(ninth);
        products.addProduct(tenth);
    }
}
