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

      List<User> users = new ArrayList<>();
      users.add(new User("Иван", "Иванов", "ivan@mail.ru"));
      users.add(new User("Пётр", "Петров", "petr@mail.ru"));
      users.add(new User("Мария", "Сидорова", "maria@mail.ru"));
      users.add(new User("Елена", "Кузнецова", "elena@mail.ru"));
      users.add(new User("Алексей", "Смирнов", "alexey@mail.ru"));

      for (User user : users) {
         userService.add(user);
      }

      List<Car> cars = new ArrayList<>();
      cars.add(new Car("BMW", 3));
      cars.add(new Car("Audi", 8));
      cars.add(new Car("Mercedes", 500));
      cars.add(new Car("Lada", 7));
      cars.add(new Car("Toyota", 2));

      for (Car car : cars) {
         User tempUser = new User();
         tempUser.setCar(car);
         userService.add(tempUser);
      }

      List<User> usersFromDb = userService.listUsers();

      List<Car> carsFromDb = new ArrayList<>();
      for (Car car : cars) {
         userService.getUserByCar(car.getModel(), car.getSeries())
                 .ifPresent(user -> carsFromDb.add(user.getCar()));
      }

      for (User user : usersFromDb) {
         if (user.getFirstName() == null) {
            continue;
         }
      }

      for (int i = 0; i < users.size() && i < carsFromDb.size(); i++) {
         users.get(i).setCar(carsFromDb.get(i));
         userService.add(users.get(i));
      }

      System.out.println("=== Результат ===");
      userService.listUsers().forEach(System.out::println);
   }
}