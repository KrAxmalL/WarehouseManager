package org.example.Services;

import org.example.Databases.CrudCategoryRepository;
import org.example.Databases.CrudProductRepository;
import org.example.Models.Category;
import org.example.Models.Product;
import org.example.Utils.CommandTypeEncoder;

import java.util.Iterator;
import java.util.List;

public class CategoryService {

    private static CrudCategoryRepository categories = new CrudCategoryRepository();

    public static Category parseCategoryFromJson(String input) throws Exception {
        String[] fields = input.replaceAll("[{}]", "").trim().split(",");
        if(fields.length == 3) {
            String idStr = fields[0].replace("\"id\":", "").trim();
            int id = Integer.parseInt(idStr.replaceAll("\"", ""));
            String name = fields[1].replace("\"name\":", "").replaceAll("\"", "").trim();
            String description = fields[2].replace("\"description\":", "").replaceAll("\"", "").trim();

            Category res = new Category(name, description);
            res.setId(id);
            return res;
        }
        else {
            throw new Exception("Invalid json of category");
        }
    }

    public static String categoryToJson(Category category) {
        return "{" +
                "\"id\": " + category.getId() + ',' +
                "\"name\": " + '"' + category.getName() + '"' + ',' +
                "\"description\": " + '"' + category.getDescription() + '"' +
                '}';
    }

    public static String processRequest(int command, String data) {
        if((command & CommandTypeEncoder.CREATE) == CommandTypeEncoder.CREATE) {
            try {
                System.out.println("In adding category service");
                Category toAdd = parseCategoryFromJson(data);
                boolean isValid = validateCategory(toAdd);
                System.out.println("Is valid: " + isValid);
                if(isValid) {
                    boolean added = categories.addCategory(toAdd);
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
                Category toUpdate = parseCategoryFromJson(data);
                boolean isValid = validateCategory(toUpdate);
                if(isValid) {
                    boolean updated = categories.updateCategory(toUpdate);
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
                boolean deleted = categories.deleteCategory(toDelete);
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
            List<Category> res = categories.listByCriteria(command, data.split(","));
            Iterator<Category> it = res.iterator();
            StringBuilder stringBuilder = new StringBuilder();
            while(it.hasNext()) {
                stringBuilder.append(categoryToJson(it.next()));
                stringBuilder.append('\n');
            }
            return stringBuilder.toString();
        }
        else {
            return "error";
        }
    }

    public static boolean validateCategory(Category category) {
        System.out.println("Category to validate: " + category);
        if(category == null) {
            return false;
        }
        if(category.getName() == null || category.getName().isEmpty()) {
            return false;
        }
        if(category.getDescription() == null) {
            return false;
        }

        System.out.println("Server Correct fields: true");

        if(category.getId() > 0) {
            return validateForUpdating(category);
        }
        else {
            System.out.println("Server Validate for adding: " + validateForAdding(category));
            return validateForAdding(category);
        }
    }

    private static boolean validateForAdding(Category category) {
        Category dbCategory = categories.getCategoryByName(category.getName());
        System.out.println("dbCategory: " + dbCategory);

        return (dbCategory == null);
    }

    private static boolean validateForUpdating(Category category) {
        Category dbCategoryById = categories.getCategory(category.getId());
        if(dbCategoryById == null) {
            return false;
        }

        if(!category.getName().equals(dbCategoryById.getName())) {
            Category dbCategoryByName = categories.getCategoryByName(category.getName());
            if(dbCategoryByName != null) {
                return false;
            }
        }
        return true;
    }
}
