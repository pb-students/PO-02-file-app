// Author: Kasper Seweryn <github@wvffle.net>
// License: Licensed under MIT license
// Source: https://github.com/wvffle/object-programming/

package net.wvffle.objectProgramming.lesson02;

import java.util.Collections;
import java.util.Scanner;

public class Person {
    private String firstName;
    private String surname;
    private int age;
    private float efficiency;

    public Person(String firstName, String surname, int age, float efficiency) {
        this.firstName = firstName;
        this.surname = surname;
        this.age = age;
        this.efficiency = efficiency;
    }

    public static Person readFromScanner (Scanner scanner) {
        return readFromScanner(scanner, true);
    }

    public static Person readFromScanner (Scanner scanner, boolean quiet) {
        if (!quiet) System.out.println("First name:");
        String firstname = scanner.next();

        if (!quiet) System.out.println("Surname:");
        String surname = scanner.next();

        if (!quiet) System.out.println("Age:");
        int age = scanner.nextInt();

        if (!quiet) System.out.println("Efficiency:");
        float efficiency = scanner.nextFloat();

        return new Person(firstname, surname, age, efficiency);
    }

    public static Person deserialize (String serialized) {
        Scanner scanner = new Scanner(serialized);
        Person person = readFromScanner(scanner);

        scanner.close();
        return person;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public float getEfficiency() {
        return efficiency;
    }

    public String serialize () {
        return String.format("%s %s %d %f", firstName, surname, age, efficiency);
    }

    public String serializeAnonymized () {
        return String.format("%s %s %d",
                anonymize(firstName),
                anonymize(surname),
                age
        );
    }

    private static String anonymize(String str) {
        // NOTE: Fix for strings with lengths less than 4 chars
        if (str.length() < 4) {
            return str.charAt(0)
                    // NOTE: One can use "*".repeat(str.length() - 2) when using java 11. I'm using java 8.
                    + String.join("", Collections.nCopies(str.length() - 1, "*"));
        }

        return str.charAt(0)
                + str.substring(1, str.length() - 3).replaceAll(".", "*")
                + str.substring(str.length() - 3);
    }
}
