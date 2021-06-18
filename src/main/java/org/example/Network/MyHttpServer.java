package org.example.Network;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import org.example.Databases.CrudCategoryRepository;
import org.example.Databases.CrudProductRepository;
import org.example.Databases.CrudUserRepository;
import org.example.Models.Category;
import org.example.Models.Product;
import org.example.Models.User;
import org.example.Utils.Config;
import org.example.Utils.MyCipher;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MyHttpServer {

    public static void main(String[] args) {
        initUsers();
        initCategories();
        initProducts();

        try {
            HttpServer server = HttpServer.create();
            server.bind(new InetSocketAddress(Config.HTTP_SERVER_PORT), 0);

            HttpContext context = server.createContext(LoginController.LOGIN_PATH);
            context.setHandler(LoginController::serve);

            context = server.createContext(ProductController.PRODUCT_PATH);
            context.setHandler(ProductController::serve);

            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
