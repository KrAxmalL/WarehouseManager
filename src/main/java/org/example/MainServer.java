package org.example;

import org.example.Databases.CrudCategoryRepository;
import org.example.Databases.CrudProductRepository;
import org.example.Databases.CrudUserRepository;
import org.example.Models.Category;
import org.example.Models.Product;
import org.example.Models.User;
import org.example.Network.ServeTCPClient;
import org.example.Utils.Config;
import org.example.Utils.MyCipher;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {

    public static void main(String[] args) throws IOException {
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
