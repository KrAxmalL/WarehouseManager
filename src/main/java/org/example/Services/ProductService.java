package org.example.Services;

import org.example.Databases.CrudCategoryRepository;
import org.example.Databases.CrudProductRepository;
import org.example.Models.Category;
import org.example.Models.Product;
import org.example.Utils.CommandTypeEncoder;

import java.util.Iterator;
import java.util.List;

public class ProductService {

    private static CrudProductRepository products = new CrudProductRepository();
    private static CrudCategoryRepository categories = new CrudCategoryRepository();

    public static Product parseProductFromJson(String input) throws Exception {
        String[] fields = input.replaceAll("[{}]", "").trim().split(",");
        if(fields.length == 7) {
            String idStr = fields[0].replace("\"id\":", "").trim();
            int id = Integer.parseInt(idStr.replaceAll("\"", ""));
            String name = fields[1].replace("\"name\":", "").replaceAll("\"", "").trim();
            String description = fields[2].replace("\"description\":", "").replaceAll("\"", "").trim();
            String producer = fields[3].replace("\"producer\":", "").replaceAll("\"", "").trim();
            String categoryIdStr = fields[4].replace("\"categoryId\":", "").trim();
            int categoryId = Integer.parseInt(categoryIdStr.replaceAll("\"", ""));
            String amountStr = fields[5].replace("\"amount\":", "").trim();
            int amount = Integer.parseInt(amountStr.replaceAll("\"", ""));
            String priceStr = fields[6].replace("\"price\":", "").trim();
            double price = Double.parseDouble(priceStr.replaceAll("\"", ""));

            Product res = new Product(name, description, producer, categoryId, amount, price);
            res.setId(id);
            return res;
        }
        else {
            throw new Exception("Invalid json of product");
        }
    }

    public static String productToJson(Product product) {
        return "{" +
                "\"id\": " + product.getId() + ',' +
                "\"name\": " + '"' + product.getName() + '"' + ',' +
                "\"description\": " + '"' + product.getDescription() + '"' + ',' +
                "\"producer\": " + '"' + product.getProducer() + '"' +  ',' +
                "\"categoryId\": " + product.getCategoryId() + ',' +
                "\"amount\": " + product.getAmount() + ',' +
                "\"price\": " + product.getPrice() +
                '}';
    }

    public static String processRequest(int command, String data) {
        if((command & CommandTypeEncoder.CREATE) == CommandTypeEncoder.CREATE) {
            try {
                System.out.println("In adding product service");
                Product toAdd = parseProductFromJson(data);
                boolean isValid = validateProduct(toAdd);
                System.out.println("Is valid: " + isValid);
                if(isValid) {
                    boolean added = products.addProduct(toAdd);
                    System.out.println("Added: " + added);
                    if (added) {
                        return "ok";
                    }
                }
                return "error";
            } catch (Exception e) {
                e.printStackTrace();
                return "error";
            }
        }
        else if((command & CommandTypeEncoder.READ) == CommandTypeEncoder.READ) {
            return "ok";
        }
        else if((command & CommandTypeEncoder.UPDATE) == CommandTypeEncoder.UPDATE) {
            try {
                Product toUpdate = parseProductFromJson(data);
                boolean isValid = validateProduct(toUpdate);
                if(isValid) {
                    boolean updated = products.updateProduct(toUpdate);
                    if (updated) {
                        return "ok";
                    }
                }
                return "error";
            } catch (Exception e) {
                return "error";
            }
        }
        else if((command & CommandTypeEncoder.DELETE) == CommandTypeEncoder.DELETE) {
            try {
                int toDelete = Integer.parseInt(data);
                boolean deleted = products.deleteProduct(toDelete);
                if(deleted) {
                    return "ok";
                }
                else {
                    return "error";
                }
            } catch (Exception e) {
                return "error";
            }
        }
        else if((command & CommandTypeEncoder.LIST_BY_CRITERIA) == CommandTypeEncoder.LIST_BY_CRITERIA) {
            List<Product> res = products.listByCriteria(command, data.split(","));
            Iterator<Product> it = res.iterator();
            StringBuilder stringBuilder = new StringBuilder();
            while(it.hasNext()) {
                stringBuilder.append(productToJson(it.next()));
                stringBuilder.append('\n');
            }
            return stringBuilder.toString();
        }
        else {
            return "error";
        }
    }


    public static boolean validateProduct(Product product) {
        System.out.println("Product to validate: " + product);
        if(product == null) {
            return false;
        }
        if(product.getName() == null || product.getName().isEmpty()) {
            return false;
        }
        if(product.getProducer() == null || product.getProducer().isEmpty()) {
            return false;
        }
        if(product.getDescription() == null) {
            return false;
        }
        if(product.getPrice() <= 0) {
            return false;
        }
        if(product.getAmount() < 0) {
            return false;
        }

        int categoryId = product.getCategoryId();
        Category category = categories.getCategory(categoryId);
        if(category == null) {
            return false;
        }

        System.out.println("Server Correct fields: true");

        //if id is given this is updating
        if(product.getId() > 0) {
            return validateForUpdating(product);
        }
        //if id is zero adding new product
        else {
            System.out.println("Server Validate for addding: " + validateForAdding(product));
            return validateForAdding(product);
        }
    }

    private static boolean validateForAdding(Product product) {
        //check that name of new product is unique
        Product dbProduct = products.getProductByName(product.getName());
        System.out.println("dbProduct: " + dbProduct);
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
