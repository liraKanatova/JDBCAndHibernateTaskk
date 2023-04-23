package peaksoft.dao;


import org.hibernate.Query;
import org.hibernate.Session;
import peaksoft.model.User;
import peaksoft.util.Util;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = Util.createSessionFactory().openSession();
        session.beginTransaction();
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void dropUsersTable() {
Session session = Util.createSessionFactory().openSession();
session.beginTransaction();
session.createQuery("drop table users").executeUpdate();
session.getTransaction().commit();
session.close();
        System.out.println("successfully drop table");

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
User user = new User(name,lastName,age);
Session session = Util.createSessionFactory().openSession();
session.save(user);
session.getTransaction().commit();
session.close();
        System.out.println("successfully saved");

    }

    @Override
    public void removeUserById(Long id) {
        Session session = Util.createSessionFactory().openSession();
        session.beginTransaction();
       User user = (User) session.get(User.class, id);
       session.delete(user);
       session.getTransaction().commit();
       session.close();
        System.out.println("successfully deleted user");


    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.createSessionFactory().openSession();
        session.beginTransaction();
        Integer selectUFromUserU = session.createQuery(" from User ")
                .getMaxResults();
        session.getTransaction().commit();
        session.close();
        return null;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.createSessionFactory().openSession();
        session.beginTransaction();
        session.createQuery("delete from User ").executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Table successfully cleaned ");
    }
}
