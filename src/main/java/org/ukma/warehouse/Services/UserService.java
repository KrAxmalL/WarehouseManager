package org.ukma.warehouse.Services;

import org.ukma.warehouse.Databases.CrudUserRepository;
import org.ukma.warehouse.Models.User;
import org.ukma.warehouse.Utils.CommandTypeEncoder;
import org.ukma.warehouse.Utils.MyCipher;

public class UserService {

    private static CrudUserRepository users = new CrudUserRepository();
    private static MyCipher cipher = new MyCipher();

    public static User parseUserFromJson(String input) throws Exception {
        String[] fields = input.replaceAll("[{}]", "").trim().split(",");
        if(fields.length == 3) {
            String idStr = fields[0].replace("\"id\":", "").trim();
            int id = Integer.parseInt(idStr.replaceAll("\"", ""));

            String login = fields[1].replace("\"login\":",  "").replaceAll("\"", "").trim();
            String password = fields[2].replace("\"password\":", "").replaceAll("\"", "").trim();

            User user = new User(login, password);
            user.setId(id);
            System.out.println("Parsed user: " + user);
            return user;
        }
        else {
            throw new Exception("Invalid json of user");
        }
    }

    public static String userToJson(User user) {
        return "{" +
                "\"id\": " + user.getId() + ',' +
                "\"login\": " + '"' + user.getLogin() + '"' + ',' +
                "\"password\": " + '"' + user.getPassword() + '"' +
                '}';
    }

    public static String processRequest(int command, String data) {
        if((command & CommandTypeEncoder.CREATE) == CommandTypeEncoder.CREATE) {
            try {
                User toRegister = parseUserFromJson(data);
                if(registerUser(toRegister)) {
                    return "ok";
                }
                return "error";
            } catch (Exception e) {
                e.printStackTrace();
                return "error";
            }
        }
        else if((command & CommandTypeEncoder.READ) == CommandTypeEncoder.READ) {
            try {
                User toLogin = parseUserFromJson(data);
                if(loginUser(toLogin)) {
                    return "ok";
                }
                return "error";
            } catch (Exception e) {
                e.printStackTrace();
                return "error";
            }
        }
        else {
            return "error";
        }
    }

    public static boolean registerUser(User user) {
        boolean validUser = validateUser(user);
        if(validUser) {
            user.setPassword(cipher.encode(user.getPassword()));
            User dbUser = users.getUserByLogin(user.getLogin());
            if (dbUser == null) {
                return users.addUser(user);
            }
        }
        return false;
    }

    public static boolean loginUser(User user) {
        boolean validUser = validateUser(user);
        if(validUser) {
            user.setPassword(cipher.encode(user.getPassword()));
            User dbUser = users.getUserByLogin(user.getLogin());
            if (dbUser != null) {
                return user.getPassword().equals(dbUser.getPassword());
            }
        }
        return false;
    }

    public static boolean validateUser(User user) {
        if(user == null) {
            return false;
        }
        if(user.getLogin() == null || user.getLogin().isEmpty()) {
            return false;
        }
        if(user.getPassword() == null || user.getPassword().isEmpty()) {
            return false;
        }

        return true;
    }
}
