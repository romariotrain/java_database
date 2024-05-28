package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import lombok.AllArgsConstructor;
import java.util.List;

//@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDaoJDBCImpl repository;

    public UserServiceImpl(UserDaoJDBCImpl repository) {
        this.repository = repository;
    }


    public void createUsersTable() {
        repository.createUsersTable();
    }

    public void dropUsersTable() {
        repository.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        repository.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        repository.removeUserById(id);

    }

    public List<User> getAllUsers() {
        return repository.getAllUsers();
    }

    public void cleanUsersTable() {
        repository.cleanUsersTable();
    }
}
