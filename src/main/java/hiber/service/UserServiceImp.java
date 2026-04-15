package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService {

   private final UserDao userDao;

   @Autowired
   public UserServiceImp(UserDao userDao) {
      this.userDao = userDao;
   }

   @Override
   public void saveUsers(List<User> users) {
      userDao.saveAll(users);
   }

   @Override
   public List<User> getAllUsers() {
      return userDao.findAll();
   }

   @Override
   public void updateUsers(List<User> users) {
      userDao.updateAll(users);
   }
}