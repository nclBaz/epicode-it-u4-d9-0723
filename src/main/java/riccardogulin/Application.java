package riccardogulin;

import com.github.javafaker.Faker;
import riccardogulin.entities.User;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        Supplier<Integer> randomNumbersSupplier = () -> {
            Random rndm = new Random();
            return rndm.nextInt(1, 101);
        };

        Supplier<User> userSupplier = () -> {
            Faker faker = new Faker(Locale.ITALY);
            return new User(faker.lordOfTheRings().character(),
                    faker.name().lastName(),
                    randomNumbersSupplier.get(),
                    faker.gameOfThrones().city()
                    );
        };

        List<User> usersList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            usersList.add(userSupplier.get());
        }

        usersList.forEach(user -> System.out.println(user));

        // *********************************** COLLECTORS *************************************
        Map<String, List<User> > usersByCity = usersList.stream().filter(user -> user.getAge() <18).collect(Collectors.groupingBy(user -> user.getCity()));
        usersByCity.forEach((città, listaUtenti) -> System.out.println("Città: " + città + ", " + listaUtenti.stream().map(user -> user.getName()).toList()));

    }
}
