package hiber.dao;

import hiber.model.User;
import java.util.List;
import java.util.Optional;

public interface UserDao {
   void add(User user);
   void addAll(List<User> users);
   List<User> listUsers();
   void update(User user);
   void updateAll(List<User> users);
   Optional<User> getUserById(Long id);
   Optional<User> getUserByCar(String model, int series);
}