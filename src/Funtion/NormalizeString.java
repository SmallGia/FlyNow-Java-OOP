package Funtion;

public class NormalizeString {
    public static String normalizeString(String input) {
        // Xóa khoảng trắng thừa ở đầu và cuối chuỗi
        input = input.trim();

        // Tách chuỗi thành các từ
        String[] words = input.split("\\s+");

        // Xử lý từng từ
        for (int i = 0; i < words.length; i++) {
            // Chuyển chữ cái đầu tiên của từ thành chữ hoa
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
        }

        // Nối các từ lại với nhau bằng khoảng trắng
        return String.join(" ", words);
    }
}
