package hiber.dao;

import hiber.model.User;
import java.util.List;
import java.util.Optional;

public interface UserDao {
   void add(User user);
   void addAll(List<User> users);
   List<User> listUsers();
   Optional<User> getUserById(Long id);
   Optional<User> getUserByCar(String model, int series);
}