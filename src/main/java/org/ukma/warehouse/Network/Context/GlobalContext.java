package org.ukma.warehouse.Network.Context;

import lombok.SneakyThrows;
import org.ukma.warehouse.Models.Category;
import org.ukma.warehouse.Models.Message;
import org.ukma.warehouse.Models.Product;
import org.ukma.warehouse.Network.Client.StoreClientTCP;
import org.ukma.warehouse.Services.CategoryService;
import org.ukma.warehouse.Services.ProductService;
import org.ukma.warehouse.Utils.CommandTypeEncoder;
import org.ukma.warehouse.Utils.Config;

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
            if(productsAsString[i]!= null && !productsAsString[i].isEmpty()) {
                productCache.add(ProductService.parseProductFromJson(productsAsString[i]));
            }
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
            if(categoriesAsString[i]!= null && !categoriesAsString[i].isEmpty()) {
                categoryCache.add(CategoryService.parseCategoryFromJson(categoriesAsString[i]));
            }
        }
    }
}

