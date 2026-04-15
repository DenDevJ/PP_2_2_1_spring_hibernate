package hiber.service;

import hiber.model.User;
import java.util.List;

public interface UserService {
    void saveUsers(List<User> users);
    List<User> getAllUsers();
    void updateUsers(List<User> users);
}