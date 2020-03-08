package net.wvffle.objectProgramming.lesson02;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileApp {
    public static void main (String[] args) throws IOException {
        System.out.println("Enter file name: ");

        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.next();

        // NOTE: One could use new PrintWriter(new File(fileName)) to overwrite the file. Current approach appends to the end
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));

        // Interactive people adding
        boolean writing = true;
        while (writing) {
            System.out.println("Write new record to the file? (y/n)");

            switch (scanner.next().trim().toLowerCase()) {
                case "y":
                    Person person = Person.readFromScanner(scanner, false);
                    printWriter.println(person.serialize());
                    System.out.println();
                    break;
                case "n":
                    System.out.println("Writing aborted.");
                    writing = false;
                    break;
            }
        }

        printWriter.close();
        scanner.close();

        List<Person> people = Files.lines(Paths.get(fileName))
                .map(Person::deserialize)

                // Filtering correct candidates
                .filter(person -> {
                    if (person.getFirstName().length() < 3) return false;
                    return person.getSurname().endsWith("ski");
                })

                // Conversion to list
                .collect(Collectors.toList());

        // Finding max efficiency
        float maxEfficiency = 0;
        for (Person person : people) {
            maxEfficiency = Math.max(maxEfficiency, person.getEfficiency());
        }

        File maxFile = new File(fileName + ".max");
        PrintWriter maxPrintWriter = new PrintWriter(maxFile);

        // Saving the most efficient people
        for (Person person : people) {
            if (person.getEfficiency() != maxEfficiency) continue;
            maxPrintWriter.println(person.serializeAnonymized());
        }

        maxPrintWriter.close();
    }
}
