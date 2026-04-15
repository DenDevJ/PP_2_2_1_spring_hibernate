package hiber.dao;

import hiber.model.User;
import java.util.List;

public interface UserDao {
   void saveAll(List<User> users);
   List<User> findAll();
   void updateAll(List<User> users);
}