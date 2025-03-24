package Funtion;

import java.util.Random;

public class GenerateRandomString {
    public static String generateRandomStringshort() {
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(10);

        // Add one random uppercase letter
        sb.append(upperCaseLetters.charAt(rnd.nextInt(upperCaseLetters.length())));

        // Add one random lowercase letter
        sb.append(lowerCaseLetters.charAt(rnd.nextInt(lowerCaseLetters.length())));

        // Add one random number
        sb.append(numbers.charAt(rnd.nextInt(numbers.length())));

        // Add remaining random characters from all possible characters
        String allPossibleCharacters = upperCaseLetters + lowerCaseLetters + numbers;
        for (int i = 3; i < 6; i++) {
            sb.append(allPossibleCharacters.charAt(rnd.nextInt(allPossibleCharacters.length())));
        }

        return sb.toString();
    }
    public static String generateRandomString() {
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(10);

        // Add one random uppercase letter
        sb.append(upperCaseLetters.charAt(rnd.nextInt(upperCaseLetters.length())));

        // Add one random lowercase letter
        sb.append(lowerCaseLetters.charAt(rnd.nextInt(lowerCaseLetters.length())));

        // Add one random number
        sb.append(numbers.charAt(rnd.nextInt(numbers.length())));

        // Add remaining random characters from all possible characters
        String allPossibleCharacters = upperCaseLetters + lowerCaseLetters + numbers;
        for (int i = 3; i < 10; i++) {
            sb.append(allPossibleCharacters.charAt(rnd.nextInt(allPossibleCharacters.length())));
        }

        return sb.toString();
    }
}
