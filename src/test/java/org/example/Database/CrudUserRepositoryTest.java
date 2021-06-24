package org.example.Database;

import org.example.Databases.CrudCategoryRepository;
import org.example.Databases.CrudUserRepository;
import org.example.Models.Category;
import org.example.Models.User;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class CrudUserRepositoryTest {

    @Test
    public void testAddingUser() {
        CrudUserRepository repo = new CrudUserRepository();
        repo.clearUsers();

        User added = new User("login", "login");
        repo.addUser(added);

        List<User> all = repo.getAll();
        User readed = all.get(0);

        added.setId(readed.getId());

        Assertions.assertEquals(added, readed);
    }

    @Test
    public void testReadingUserById() {
        CrudUserRepository repo = new CrudUserRepository();
        repo.clearUsers();

        User added = new User("login", "login");
        repo.addUser(added);

        List<User> all = repo.getAll();
        User readed = all.get(0);

        added.setId(readed.getId());

        Assertions.assertEquals(added, readed);

        User readedById = repo.getUser(readed.getId());
        added.setId(readedById.getId());

        Assertions.assertEquals(added, readedById);
    }

    @Test
    public void testReadingUserByLogin() {
        CrudUserRepository repo = new CrudUserRepository();
        repo.clearUsers();

        User added = new User("login", "login");
        repo.addUser(added);

        User readed = repo.getUserByLogin(added.getLogin());

        added.setId(readed.getId());

        Assertions.assertEquals(added, readed);
    }
}
