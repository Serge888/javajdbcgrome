package hibernate.lesson4.controller;

import hibernate.lesson4.model.User;
import hibernate.lesson4.service.UserService;
import jdbc.lesson4.hw.exception.BadRequestException;

public class UserController {
    UserService userService = new UserService();

    public User registerUser(User user) {
        return userService.registerUser(user);
    }

    public void login(String userName, String password) throws BadRequestException {
        userService.login(userName, password);
    }

    public void logout() {
        userService.logout();
    }

    public User update(User user) {
        return userService.update(user);
    }

    public void delete(long id) {
        userService.delete(id);
    }

    public User findById(long id) {
        return userService.findById(id);
    }

    public User findByUserName(String userName) {
        return userService.findByUserName(userName);
    }
}
