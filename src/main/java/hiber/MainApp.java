package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      ApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      List<Car> cars = new ArrayList<>();
      cars.add(new Car("BMW", 3));
      cars.add(new Car("Audi", 8));
      cars.add(new Car("Mercedes", 500));
      cars.add(new Car("Lada", 7));

      List<User> users = new ArrayList<>();
      users.add(new User("User1", "Lastname1", "user1@mail.ru"));
      users.add(new User("User2", "Lastname2", "user2@mail.ru"));
      users.add(new User("User3", "Lastname3", "user3@mail.ru"));
      users.add(new User("User4", "Lastname4", "user4@mail.ru"));

      for (int i = 0; i < users.size(); i++) {
         users.get(i).setCar(cars.get(i));
      }
      userService.addAll(users);
      List<User> usersFromDb = userService.listUsers();

      System.out.println("=== Все пользователи из БД ===");
      for (User user : usersFromDb) {
         System.out.println(user);
      }
      System.out.println("\n=== Поиск по машине ===");

      userService.getUserByCar("BMW", 3)
              .ifPresentOrElse(
                      user -> System.out.println("Найден: " + user),
                      () -> System.out.println("Пользователь с BMW 3 не найден")
              );
      userService.getUserByCar("Toyota", 2)
              .ifPresentOrElse(
                      user -> System.out.println("Найден: " + user),
                      () -> System.out.println("Пользователь с Toyota 2 не найден")
              );
   }
}