package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            // Таблица будет создана автоматически благодаря hbm2ddl.auto=update
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                String sql = "DROP TABLE Users";
                session.createSQLQuery(sql).executeUpdate();
            } catch (SQLGrammarException e) {

                System.out.println("Таблицы Users не существует.");
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.persist(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User", User.class);
            return query.list();
        }
    }

    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
        }
    }
}
