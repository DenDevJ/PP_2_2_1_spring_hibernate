package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.Arrays;
import java.util.List;

public class MainApp {
   public static void main(String[] args) {

      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      System.out.println("\n=== ШАГ 1: Сохранение юзеров ===");
      List<User> users = Arrays.asList(
              new User("Иван", "Петров", "ivan@mail.com"),
              new User("Мария", "Сидорова", "maria@mail.com"),
              new User("Алексей", "Иванов", "alex@mail.com")
      );
      userService.saveUsers(users);
      System.out.println("✅ Сохранено юзеров: " + users.size());

      System.out.println("\n=== ШАГ 2: Сохранение машин ===");
      List<Car> cars = Arrays.asList(
              new Car("BMW", 5),
              new Car("Audi", 3),
              new Car("Mercedes", 7),
              new Car("Toyota", 2)
      );
      carService.saveCars(cars);
      System.out.println("✅ Сохранено машин: " + cars.size());

      System.out.println("\n=== ШАГ 3: Загрузка из БД ===");
      List<User> usersFromDb = userService.getAllUsers();
      List<Car> carsFromDb = carService.getAllCars();
      System.out.println("📥 Загружено юзеров: " + usersFromDb.size());
      System.out.println("📥 Загружено машин: " + carsFromDb.size());

      System.out.println("\n=== ШАГ 4: Раздача машин юзерам ===");
      for (int i = 0; i < usersFromDb.size(); i++) {
         User user = usersFromDb.get(i);
         Car car = carsFromDb.get(i % carsFromDb.size());
         user.addCar(car);
         System.out.println("🔗 " + user.getFirstName() + " получил " + car.getModel());
      }

      System.out.println("\n=== ШАГ 5: Сохранение юзеров с машинами ===");
      userService.updateUsers(usersFromDb);
      System.out.println("✅ Юзеры с машинами сохранены");

      System.out.println("\n=== ФИНАЛЬНЫЙ РЕЗУЛЬТАТ ===");
      List<User> finalUsers = userService.getAllUsers();
      for (User user : finalUsers) {
         System.out.println(user);
      }

      context.close();
   }
}