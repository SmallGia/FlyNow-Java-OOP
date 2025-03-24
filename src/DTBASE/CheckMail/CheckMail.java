package DTBASE.CheckMail;

import DTBASE.Database.Database;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class CheckMail {

    private static final String username = "builehongtamk16@siu.edu.vn";
    private static final String password = "lcqabknkrsmukjgz";

    public static void main(String[] args) {
        checkMail("thp.gia@gmail.com","abcada");
    }
    public static boolean checkMail(String Email,String Code) {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username, "\n" + "admin@flynow.siu", "UTF-8"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(Email));
            message.setSubject(MimeUtility.encodeText("Mã Xác Nhận", "utf-8", "B"));
            String htmlContent = "<!DOCTYPE html>\n" +
                    "<html lang=\"vi\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>Email thông báo tài khoản FLYNOW</title>\n" +
                    "    <style>\n" +
                    "        body {\n" +
                    "            font-family: Arial, sans-serif;\n" +
                    "            margin: 10;\n" +
                    "            padding: 10;\n" +
                    "            background-color: #f0f0f0;\n" +
                    "            color: #000;\n" +
                    "        }\n" +
                    "        .container {\n" +
                    "            width: 80%;\n" +
                    "            margin: 0 auto;\n" +
                    "            padding: 20px;\n" +
                    "            border: 1px solid #ccc;\n" +
                    "            background-color: #fff;\n" +
                    "        }\n" +
                    "        .header {\n" +
                    "            text-align: center;\n" +
                    "            margin-bottom: 20px;\n" +
                    "        }\n" +
                    "        .logo {\n" +
                    "            width: 200px;\n" +
                    "            height: 200px;\n" +
                    "        }\n" +
                    "        .content {\n" +
                    "            line-height: 1.5;\n" +
                    "        }\n" +
                    "        .content h2 {\n" +
                    "            color: #f60;\n" +
                    "        }\n" +
                    "        .footer {\n" +
                    "            text-align: center;\n" +
                    "            margin-top: 20px;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div class=\"container\">\n" +
                    "    <div class=\"header\">\n" +
                    "        <img src=\"https://lh3.googleusercontent.com/pw/AP1GczNp6M2UJ4who4J_T4JzBElM1FIPmbA9VEwnow0sIrk71HXRQnQHSKj7cZqFI7wzTib56j35o9dByqsyrYDDd69jnpOQ6dURVKwlv1BY6efqT_hUnZ7pd-3F-zTu0dwPw6AFqlAZmBoHyU52pSGBaldo=w945-h945-s-no-gm?authuser=0\" alt=\"Logo FLYNOW\" class=\"logo\">\n" +
                    "    </div>\n" +
                    "    <div class=\"content\">\n" +
                    "        <h2>Xin chào bạn,</h2>\n" +
                    "        <p>Mã xác nhận của bạn là: " + Code + "</p>\n" +
                    "        <p>Trân trọng,</p>\n" +
                    "        <p>Đội ngũ hỗ trợ FLYNOW</p>\n" +
                    "    </div>\n" +
                    "    <div class=\"footer\">\n" +
                    "        <p>&copy; 2024 FLYNOW</p>\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>";

            message.setContent(htmlContent, "text/html;charset=UTF-8");
            Transport.send(message);
            System.out.println("Gửi email thành công đến : " + Email);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
