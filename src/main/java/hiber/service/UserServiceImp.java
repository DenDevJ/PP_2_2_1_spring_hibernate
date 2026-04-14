package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Override
   @Transactional
   public void add(User user) {
      userDao.add(user);
   }

   @Override
   @Transactional
   public void addAll(List<User> users) {
      userDao.addAll(users);
   }

   @Override
   @Transactional(readOnly = true)
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Override
   @Transactional
   public void update(User user) {
      userDao.update(user);
   }

   @Override
   @Transactional
   public void updateAll(List<User> users) {
      userDao.updateAll(users);
   }

   @Override
   @Transactional(readOnly = true)
   public Optional<User> getUserById(Long id) {
      return userDao.getUserById(id);
   }

   @Override
   @Transactional(readOnly = true)
   public Optional<User> getUserByCar(String model, int series) {
      return userDao.getUserByCar(model, series);
   }
}