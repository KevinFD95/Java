package src.todolistprogram;

import src.todolistprogram.database.classes.Person;
import src.todolistprogram.database.classes.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class App {
    public static void main(String[] args) {
        User user = new User(1, "juanmg", "111111");
        Person p1 = new Person("Juan", "Martínez Gutiérrez",
                LocalDate.of(1995, 1, 25), "Juan MG", user);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedBirthday = p1.getBirthday().format(formatter);
        System.out.println(formattedBirthday);
    }
}