package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public void addAll(List<User> users) {
      for (User user : users) {
         sessionFactory.getCurrentSession().save(user);
      }
   }

   @Override
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession()
              .createQuery("from User", User.class);
      return query.getResultList();
   }

   @Override
   public void update(User user) {
      sessionFactory.getCurrentSession().update(user);
   }

   @Override
   public void updateAll(List<User> users) {
      for (User user : users) {
         sessionFactory.getCurrentSession().update(user);
      }
   }

   @Override
   public Optional<User> getUserById(Long id) {
      User user = sessionFactory.getCurrentSession().get(User.class, id);
      return Optional.ofNullable(user);
   }

   @Override
   public Optional<User> getUserByCar(String model, int series) {
      String hql = "FROM User u WHERE u.car.model = :model AND u.car.series = :series";
      TypedQuery<User> query = sessionFactory.getCurrentSession()
              .createQuery(hql, User.class);
      query.setParameter("model", model);
      query.setParameter("series", series);

      List<User> results = query.getResultList();
      return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
   }
}