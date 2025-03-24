package Funtion;

public class Checkseats {
    public static boolean isValidSeat(String seat) {
        // Biểu thức chính quy kiểm tra cấu trúc của chuỗi
        String regex = "^[A-Z]-\\d{2}$";
        return seat.matches(regex);
    }
    public static boolean isValidSeatClass(String seatClass, String seat) {
        char firstChar = Character.toUpperCase(seat.charAt(0));
        switch (seatClass) {
            case "Phổ thông":
                return firstChar >= 'A' && firstChar <= 'F';
            case "Phổ thông đặc biệt":
                return firstChar >= 'G' && firstChar <= 'L';
            case "Thương gia":
                return firstChar >= 'M' && firstChar <= 'R';
            case "Hạng nhất":
                return firstChar >= 'S' && firstChar <= 'Z';
            default:
                return false;
        }
    }
}
