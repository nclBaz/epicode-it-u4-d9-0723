package riccardogulin;

import com.github.javafaker.Faker;
import riccardogulin.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.function.Supplier;

public class Application {
    public static void main(String[] args) {
        Supplier<Integer> randomNumbersSupplier = () -> {
            Random rndm = new Random();
            return rndm.nextInt(1, 101);
        };

        Supplier<User> userSupplier = () -> {
            Faker faker = new Faker(Locale.ITALY);
            return new User(faker.lordOfTheRings().character(), faker.name().lastName(), randomNumbersSupplier.get());
        };

        List<User> usersList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            usersList.add(userSupplier.get());
        }

        usersList.forEach(user -> System.out.println(user));
    }
}
