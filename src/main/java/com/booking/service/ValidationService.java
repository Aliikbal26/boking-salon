package com.booking.service;

import java.util.Scanner;

public class ValidationService {
    // Buatlah function sesuai dengan kebutuhan
    // public static void validateInput() {

    // }

    // public static void validateCustomerId() {

    // }

    private static boolean checkInput(String input, String regex) {
        return input.matches(regex);
    }

    public static int getValidatedNumberInput(Scanner input, String regex, String errMessage) {
        int validatedNumber;
        while (true) {
            String inputNumber = input.nextLine();
            if (checkInput(inputNumber, regex)) {
                validatedNumber = Integer.parseInt(inputNumber);
                return validatedNumber;
            } else {
                System.out.println(errMessage);
                System.out.println("Silakan masukkan kembali input yang benar di bawah ini: ");
            }
        }
    }

    public static String getValidatedStringInput(Scanner input, String regex, String errMessage) {
        String validatedString;
        while (true) {
            String inputString = input.nextLine();
            if (checkInput(inputString, regex)) {
                validatedString = inputString;
                return validatedString;
            } else {
                System.out.println(errMessage);
                System.out.println("Silakan masukkan kembali input yang benar di bawah ini: ");
            }
        }
    }
}
