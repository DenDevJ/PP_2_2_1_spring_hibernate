package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   private final SessionFactory sessionFactory;

   @Autowired
   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void saveAll(List<User> users) {
      Transaction tx = null;
      try (Session session = sessionFactory.openSession()) {
         tx = session.beginTransaction();
         for (User user : users) {
            session.save(user);
         }
         tx.commit();
      } catch (Exception e) {
         if (tx != null) tx.rollback();
         e.printStackTrace();
      }
   }

   @Override
   public List<User> findAll() {
      try (Session session = sessionFactory.openSession()) {
         return session.createQuery("FROM User", User.class).list();
      }
   }

   @Override
   public void updateAll(List<User> users) {
      Transaction tx = null;
      try (Session session = sessionFactory.openSession()) {
         tx = session.beginTransaction();
         for (User user : users) {
            session.merge(user);
         }
         tx.commit();
      } catch (Exception e) {
         if (tx != null) tx.rollback();
         e.printStackTrace();
      }
   }
}