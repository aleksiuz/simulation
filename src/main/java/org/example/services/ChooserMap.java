package org.example.services;

import java.util.Scanner;

public class ChooserMap {
    final static int MIN_SIZE = 10;
    final static int MAX_SIZE = 15;
    final static String MESSAGE_INCORRECT_VALUE = "You wrote incorrect value. Please write number between 10 and 15.";
    final static String MESSAGE_WRITE_VALUE = "Please write the size of the map. Values must be between 10 and 15.";

    public static int chooseMapSize() {
        int size;
        Scanner scanner = new Scanner(System.in);
        System.out.println(MESSAGE_WRITE_VALUE);
        while (true) {
            while (scanner.hasNextInt()) {
                size = scanner.nextInt();
                if ( size >= MIN_SIZE && size <= MAX_SIZE) {
                    scanner.nextLine();
                    return size;
                }
                System.out.println(MESSAGE_INCORRECT_VALUE);
            }
            System.out.println(MESSAGE_INCORRECT_VALUE);
            scanner.nextLine();
        }
    }
}

