package hibernate.lesson4.service;

import hibernate.lesson4.dao.UserDAO;
import hibernate.lesson4.factory.InstanceFactory;
import hibernate.lesson4.model.User;
import jdbc.lesson4.hw.exception.BadRequestException;

public class UserService {
    private UserDAO userDAO = InstanceFactory.instanceUserDAO;

    public User registerUser(User user) throws BadRequestException, hibernate.lesson4.exception.BadRequestException {
        if (userDAO.findByUserName(user.getUserName()) != null) {
            throw new BadRequestException( "User " + user.getUserName() + " already exists.");
        }
        return userDAO.registerUser(user);
    }

    public void login(String userName, String password) throws BadRequestException {
        userDAO.login(userName, password);
    }

    public void logout() {
        userDAO.logout();
    }


    public User update(User user) {
        return userDAO.update(user);
    }

    public void delete(long id) {
        userDAO.delete(id);
    }

    public User findById(long id) {
        return userDAO.findById(id);
    }

    public User findByUserName(String userName) {
        return userDAO.findByUserName(userName);
    }
}
