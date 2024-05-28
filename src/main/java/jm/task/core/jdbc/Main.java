package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl userDao = new UserDaoHibernateImpl();
        final UserService userService = new UserServiceImpl(userDao);
        userService.createUsersTable();
        for (int i = 0; i < 4; i++) {
            userService.saveUser("name" + i, "lastname" + i, (byte) 25);
            System.out.println("User with name name" + i + " saved");
        }
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
