package ua.ivan.provider.dao;


import ua.ivan.provider.model.Role;
import ua.ivan.provider.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : Pavel Ravvich.
 * Created : 29/10/2017.
 * <p>
 * UserDAO
 */
public class UserDAO {

    private final List<User> store = new ArrayList<>();

    public User getById(int id) {

        User result = new User();
        result.setId(-1L);

        for (User user : store) {
            if (user.getId() == id) {
                result = user;
            }
        }

        return result;
    }

    public User getUserByLoginPassword(final String login, final String password) {

        User result = new User();
        result.setId(-1L);

        for (User user : store) {
            if (user.getEmail().equals(login) && user.getPassword().equals(password)) {
                result = user;
            }
        }

        return result;
    }

    public boolean add(final User user) {
        return store.add(user);
    }

    public Role getRoleByLoginPassword(final String login, final String password) {
        Role result = Role.UNKNOWN;

        for (User user : store) {
            if (user.getEmail().equals(login) && user.getPassword().equals(password)) {
                result = user.getRole();
            }
        }

        return result;
    }

    public boolean userIsExist(final String login, final String password) {

        boolean result = false;

        for (User user : store) {
            if (user.getEmail().equals(login) && user.getPassword().equals(password)) {
                result = true;
                break;
            }
        }

        return result;
    }
}
