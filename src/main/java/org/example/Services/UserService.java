package org.example.Services;

import org.example.Databases.CrudUserRepository;
import org.example.Models.User;
import org.example.Utils.MyCipher;

public class UserService {

    private static CrudUserRepository users = new CrudUserRepository();

    public static User parseUserFromJson(String input) throws Exception {
        String[] result = new String[2];

        input = input.replaceAll("[{}]", "").trim();
        String[] splitted = input.split(",");

        result[0] = splitted[0].replace("\"login\":", "").trim();
        result[0] = result[0].substring(1, result[0].length() - 1);

        result[1] = splitted[1].replace("\"password\":", "").trim();
        result[1] = result[1].substring(1, result[1].length() - 1);

        if(result[0] == null || result[0].isEmpty()) {
            throw new Exception("Invalid login!");
        }
        if(result[1] == null || result[1].isEmpty()) {
            throw new Exception("Invalid password!");
        }

        MyCipher cipher = new MyCipher();
        User user = new User(result[0], cipher.encode(result[1]));
        return user;
    }

    public static boolean validateUser(User user) {
        if(user != null) {
            User dbUser = users.getUserByLogin(user.getLogin());
            if (dbUser != null) {
                return user.getPassword().equals(dbUser.getPassword());
            }
        }
        return false;
    }
}
