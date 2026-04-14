package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      ApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      Car car1 = new Car("BMW", 3);
      Car car2 = new Car("Audi", 8);
      Car car3 = new Car("Mercedes", 500);
      Car car4 = new Car("Lada", 7);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      user1.setCar(car1);

      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      user2.setCar(car2);

      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      user3.setCar(car3);

      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      user4.setCar(car4);

      if (userService.getUserByCar("BMW", 3) == null) {
         userService.add(user1);
      }

      if (userService.getUserByCar("Audi", 8) == null) {
         userService.add(user2);
      }

      if (userService.getUserByCar("Mercedes", 500) == null) {
         userService.add(user3);
      }

      if (userService.getUserByCar("Lada", 7) == null) {
         userService.add(user4);
      }

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }
      User found = userService.getUserByCar("BMW", 3);
      System.out.println("Пользователь с машиной BMW 3: " + found);
   }
}