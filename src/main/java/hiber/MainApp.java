package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
      Car car1 = new Car("Tesla", 321);
      Car car2 = new Car("Toyota", 121);
      Car car3 = new Car("Chevrolet", 433);
      userService.add(car1);
      userService.add(car2);
      userService.add(car3);
      userService.add(new User("Elon", "Musk", "elonmusk@gmail.ru").setCar(car1));
      userService.add(new User("Paul", "Walker", "supra@mail.ru").setCar(car2));
      userService.add(new User("Dominic", "Toretto", "family@mail.ru").setCar(car3));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         if (user.getCar() != null) {
            System.out.println("Car = " + user.getCar().getModel()
                    + " " + user.getCar().getSeries());
         }
         System.out.println();
      }

      System.out.println(userService.getUserByCar("Tesla", 321));

      context.close();
   }
}
