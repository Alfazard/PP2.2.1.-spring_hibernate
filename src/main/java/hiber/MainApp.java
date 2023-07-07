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

        userService.add(new User("User1", "Lastname1", "user1@mail.ru",
                new Car("Car1", 12345)));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru",
                new Car("Car2", 23456)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru",
                new Car("Car3", 34567)));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru",
                new Car("Car4", 45678)));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.printf("""
                    Id = %d
                    First Name = %s
                    Last Name = %s
                    Email = %s
                    Car = %s
                    
                    """, user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getCar());
        }
        List<User> usersByCar = userService.listUsers();
        for (User user : usersByCar) {
            System.out.println(userService.getUserByCar(user.getCar().getModel(), user.getCar().getSeries()));
        }
        context.close();
    }
}
