package org.example.Services;

import org.example.Databases.CrudCategoryRepository;
import org.example.Databases.CrudProductRepository;
import org.example.Models.Category;
import org.example.Models.Product;

public class ProductService {

    private static CrudProductRepository products = new CrudProductRepository();
    private static CrudCategoryRepository categories = new CrudCategoryRepository();

    public static Product parseProductFromJson(String input) throws Exception {
        String[] fields = input.replaceAll("[{}]", "").trim().split(",");
        if(fields.length == 5) {
            String name = fields[0].replace("\"name\":", "").replaceAll("\"", "").trim();
            String producer = fields[1].replace("\"producer\":", "").replaceAll("\"", "").trim();
            String categoryIdStr = fields[2].replace("\"categoryId\":", "").trim();
            int categoryId = Integer.parseInt(categoryIdStr.replaceAll("\"", ""));
            String priceStr = fields[3].replace("\"price\":", "").trim();
            int price = Integer.parseInt(priceStr.replaceAll("\"", ""));
            String amountStr = fields[4].replace("\"amount\":", "").trim();
            int amount = Integer.parseInt(amountStr.replaceAll("\"", ""));

            Product res = new Product(name, producer, categoryId, price, amount);
            return res;
        }
        else {
            throw new Exception("Invalid json of product");
        }
    }

    public static boolean validateProduct(Product product) {
        if(product.getName() == null || product.getName().isEmpty()) {
            return false;
        }
        if(product.getProducer() == null || product.getProducer().isEmpty()) {
            return false;
        }
        if(product.getPrice() <= 0) {
            return false;
        }
        if(product.getAmount() <= 0) {
            return false;
        }

        int categoryId = product.getCategoryId();
        Category category = categories.getCategory(categoryId);
        if(category == null) {
            return false;
        }

        //if id is given this is updating
        if(product.getId() > 0) {
            return validateForUpdating(product);
        }
        //if id is zero adding new product
        else {
            return validateForAdding(product);
        }
    }

    private static boolean validateForAdding(Product product) {
        //check that name of new product is unique
        Product dbProduct = products.getProductByName(product.getName());
        if(dbProduct != null) {
            return false;
        }
        return true;
    }

    private static boolean validateForUpdating(Product product) {
        //check that product to update exists
        Product dbProductById = products.getProduct(product.getId());
        if(dbProductById == null) {
            return false;
        }
        //check that new name of product is unique
        if(!product.getName().equals(dbProductById.getName())) {
            Product dbProductByName = products.getProductByName(product.getName());
            if(dbProductByName != null) {
                return false;
            }
        }
        return true;
    }
}
