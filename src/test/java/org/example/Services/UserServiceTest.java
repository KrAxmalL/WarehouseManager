package org.example.Services;

import org.example.Models.User;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class UserServiceTest {

    private static UserService service = new UserService();

    @Test
    public void testCorrectUserValidationSuccess() {
        User valid = new User("login", "login");
        boolean actual = service.validateUser(valid);

        Assertions.assertTrue(actual);
    }

    @Test
    public void testBlankLoginUserValidationFails() {
        User valid = new User("", "login");
        boolean actual = service.validateUser(valid);

        Assertions.assertFalse(actual);
    }

    @Test
    public void testNullLoginUserValidationFails() {
        User valid = new User(null, "login");
        boolean actual = service.validateUser(valid);

        Assertions.assertFalse(actual);
    }

    @Test
    public void testBlankPasswordUserValidationFails() {
        User valid = new User("login", "");
        boolean actual = service.validateUser(valid);

        Assertions.assertFalse(actual);
    }

    @Test
    public void testNullPasswordUserValidationFails() {
        User valid = new User("login", null);
        boolean actual = service.validateUser(valid);

        Assertions.assertFalse(actual);
    }

    @Test
    public void testJsonUserParsing() throws Exception {
        User beforeParsingToJson = new User("login", "login");
        String UserAsJson = service.userToJson(beforeParsingToJson);
        User afterParsingFromJson = service.parseUserFromJson(UserAsJson);

        boolean equal = beforeParsingToJson.equals(afterParsingFromJson);
        Assertions.assertTrue(equal);
    }
}
