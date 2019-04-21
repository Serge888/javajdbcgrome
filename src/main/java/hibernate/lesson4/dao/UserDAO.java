package hibernate.lesson4.dao;

import hibernate.lesson4.model.User;
import hibernate.lesson4.model.UserType;
import jdbc.lesson4.hw.exception.BadRequestException;

public class UserDAO extends GeneralDAO<User> {
    private String hqlFindById = "from User where ID = ";
    private String hqlFindByUserName = "from User where USER_NAME = ";
    public static UserType loggedUserType = null;

    public User registerUser(User user) throws hibernate.lesson4.exception.BadRequestException {
        return saveEntity(user);
    }

    public void login(String userName, String password) throws BadRequestException {
        User user = findByUserName(userName);
        if (user != null && user.getPassword().equals(password)) {
            loggedUserType = user.getUserType();
        } else {
            throw new BadRequestException("Login or / and password is / are incorrect.");
        }

    }

    public void logout() {
        loggedUserType = null;
    }


    public User update(User user) {
        return updateEntity(user);
    }

    public void delete(long id) {
        deleteEntity(findById(id));
    }

    public User findById(long id) {
        return findEntityBy(hqlFindById + id).get(0);
    }

    public User findByUserName(String userName) {
        return findEntityBy(hqlFindByUserName + "'" + userName + "'").get(0);
    }

}
