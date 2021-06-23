package org.example.Network;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.SneakyThrows;
import org.example.Databases.CrudCategoryRepository;
import org.example.Databases.CrudProductRepository;
import org.example.Models.Category;
import org.example.Models.Message;
import org.example.Models.Product;
import org.example.Services.CategoryService;
import org.example.Services.ProductService;
import org.example.UI.Menus.Product.ProductMenu;
import org.example.Utils.CommandTypeEncoder;
import org.example.Utils.Config;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class GlobalContext {

    private static InetAddress address;
    public static StoreClientTCP client;

    public static List<Product> productCache;
    public static List<Category> categoryCache;

    @SneakyThrows
    public GlobalContext() {
        address = InetAddress.getByName(Config.LOCALHOST);
        client = new StoreClientTCP(address);

        productCache = new ArrayList<>();
        categoryCache = new ArrayList<>();

        updateProductsCache();
        updateCategoriesCache();
    }

    @SneakyThrows
    public static void updateProductsCache() {
        productCache.clear();
        client.sendRequest(CommandTypeEncoder.PRODUCT ^ CommandTypeEncoder.LIST_BY_CRITERIA, "");
        Message resp = client.getResponse();
        System.out.println("Context resp: " + resp);
        String data = resp.getMessage();
        String[] productsAsString = data.split("\n");
        for(int i = 0; i < productsAsString.length; i++) {
            productCache.add(ProductService.parseProductFromJson(productsAsString[i]));
        }
    }

    @SneakyThrows
    public static void updateCategoriesCache() {
        categoryCache.clear();
        client.sendRequest(CommandTypeEncoder.CATEGORY ^ CommandTypeEncoder.LIST_BY_CRITERIA, "");
        Message resp = client.getResponse();
        System.out.println("Context resp: " + resp);
        String data = resp.getMessage();
        String[] categoriesAsString = data.split("\n");
        for(int i = 0; i < categoriesAsString.length; i++) {
            categoryCache.add(CategoryService.parseCategoryFromJson(categoriesAsString[i]));
        }
    }
}

