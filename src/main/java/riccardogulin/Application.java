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
        // 1. Raggruppiamo gli users per città
        Map<String, List<User> > usersByCity = usersList.stream().filter(user -> user.getAge() <18).collect(Collectors.groupingBy(user -> user.getCity()));
        usersByCity.forEach((città, listaUtenti) -> System.out.println("Città: " + città + ", " + listaUtenti.stream().map(user -> user.getName()).toList()));

        // 2. Raggruppiamo gli users per età
        Map<Integer, List<User> > usersByAge = usersList.stream().collect(Collectors.groupingBy(user -> user.getAge()));
        usersByAge.forEach((età, listaUtenti) -> System.out.println("Età: " + età + ", " + listaUtenti));

        // 3. Concateniamo tutti i nomi degli user per ottenere una stringa tipo "nome cognome, nome1 cognome1, ...."
        String nomiConcatenati = usersList.stream().map(user -> user.getName() + " " + user.getSurname()).collect(Collectors.joining(", "));
        System.out.println(nomiConcatenati);

        // 4. Concateniamo tutti i nomi degli user per ottenere una stringa tipo "nome cognome, nome1 cognome1, ...."
        String etàConcatenate = usersList.stream().map(user -> String.valueOf(user.getAge())).collect(Collectors.joining(", "));
        System.out.println(etàConcatenate);

        // 5. Calcolo la media delle età
        double average = usersList.stream().collect(Collectors.averagingInt(user -> user.getAge()));
        System.out.println("Media delle età: " + average);

        // 6. Calcolare la media delle età raggruppando per città
        Map<String, Double> mediaEtàPerCittà = usersList.stream().collect(Collectors.groupingBy(
                User::getCity,
                Collectors.averagingInt(User::getAge)));

        mediaEtàPerCittà.forEach((città, etàMedia) -> System.out.println("Città: " + città + ", " + etàMedia));

        // 7. Raggruppiamo per città con informazioni su età più bassa, più alta, media, ecc..

        Map<String, IntSummaryStatistics> raggruppatiPerCittàConStatistiche = usersList.stream().collect(Collectors.groupingBy(
                User::getCity,
                Collectors.summarizingInt(User::getAge)));

        raggruppatiPerCittàConStatistiche.forEach((città, stats) -> System.out.println("Città: " + città + ", " + stats));


        // *********************************** COMPARATORS *************************************
        // 1. Ordinare in base all'età (ordine crescente)
        List<User> usersSortedByAge = usersList.stream().sorted(Comparator.comparingInt(user-> user.getAge())).toList();
        usersSortedByAge.forEach(System.out::println);

        // 2. Ordinare in base all'età (ordine decrescente)
        List<User> usersSortedByAgeDesc = usersList.stream().sorted(Comparator.comparingInt(User::getAge).reversed()).toList();
        usersSortedByAgeDesc.forEach(System.out::println);

        // 3. Ordinare in base al cognome
        List<User> usersSortedBySurname = usersList.stream().sorted(Comparator.comparing(User::getSurname)).toList();
        usersSortedBySurname.forEach(System.out::println);


        // *********************************** LIMIT *************************************
        System.out.println(" -------------------- LIMIT ------------------------ ");
        List<User> threeUsersSortedBySurname = usersList.stream().sorted(Comparator.comparing(User::getSurname)).skip(3).limit(3).toList();
        threeUsersSortedBySurname.forEach(System.out::println);

        // *********************************** MAP TO *************************************
        System.out.println(" -------------------- MAP TO ------------------------ ");

        // 1. Calcolo della somma totale delle età (con map e reduce)
        int totalAge = usersList.stream().map(User::getAge).reduce(0, (partialSum, current) -> partialSum + current);
        System.out.println("Età totale: " + totalAge);


        // 2. Calcolo della somma totale delle età (con mapToInt)
        int totalAge2 = usersList.stream().mapToInt(User::getAge).sum();
        System.out.println("Età totale 2: " + totalAge2);

        // 3. Calcolo della somma totale delle età (con mapToInt)
        double average2 = usersList.stream().mapToInt(User::getAge).average().getAsDouble();
        System.out.println("Media totale 2: " + average2);

        // 4. Restituisce l'età massima
        int etàMassima = usersList.stream().mapToInt(User::getAge).max().getAsInt();
        System.out.println("Età massima: " + etàMassima);

    }
}
