package Funtion;

public class ValidPhoneNumber {
    public static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "\\d{10,11}";
        return phoneNumber.matches(regex);
    }
}
