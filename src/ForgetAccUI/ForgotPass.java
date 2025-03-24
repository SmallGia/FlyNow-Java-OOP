package ForgetAccUI;

import DTBASE.Database.Database;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.sql.*;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeUtility;

import javax.swing.*;
import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Tam
 */

public class ForgotPass {

    private static final String username = "builehongtamk16@siu.edu.vn";
    private static final String password = "lcqabknkrsmukjgz";

    public static void main(String[] args) {
//        String Email = "thaihophugiak16@siu.edu.vn";
//        sendCredentialsByEmail(Email);
    }

    public static boolean sendCredentialsByEmail(String Email) {
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
            Connection con = Database.openConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT username, password FROM account WHERE email = ?");
            stmt.setString(1, Email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String recipientUsername = rs.getString("username");
                String recipientPassword = rs.getString("password");

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username, "\n" + "admin@flynow.siu", "UTF-8"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(Email));
                message.setSubject(MimeUtility.encodeText("Forgot Password", "utf-8", "B"));
//                message.setContent(
//                        "<div style='font-family: Arial, sans-serif;'>" +
//                                "<img src='https://lh3.googleusercontent.com/pw/AP1GczNp6M2UJ4who4J_T4JzBElM1FIPmbA9VEwnow0sIrk71HXRQnQHSKj7cZqFI7wzTib56j35o9dByqsyrYDDd69jnpOQ6dURVKwlv1BY6efqT_hUnZ7pd-3F-zTu0dwPw6AFqlAZmBoHyU52pSGBaldo=w945-h945-s-no-gm?authuser=0' alt='FLYNOW Logo' style='display: block; margin: 0 auto; width: 200px; height: auto;' />" +
//                                "<h1 style='color: blue; text-align: center;'>Xin chào bạn,</h1>" +
//                                "<p style='text-align: center;'>Phần mềm đặt vé <strong style='color: #f60;'>FLYNOW</strong> xin gửi lại bạn tài khoản và mật khẩu của bạn:</p>" +
//                                "<p style='text-align: center;'><strong>Username:</strong> " + recipientUsername + "</p>" +
//                                "<p style='text-align: center;'><strong>Password:</strong> " + recipientPassword + "</p>" +
//                                "<p style='text-align: center;'>Trân trọng,</p>" +
//                                "<p style='text-align: center;'>Đội ngũ hỗ trợ FLYNOW</p>" +
//                                "</div>",
//                        "text/html;charset=UTF-8");
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
                        "            color: #000;\n" + // Change text color to black
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
                        "            width: 200px;\n" + // Double the size of the logo
                        "            height: 200px;\n" + // Double the size of the logo
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
                        "        <p>Phần mềm đặt vé FLYNOW xin gửi lại bạn tài khoản và mật khẩu của bạn:</p>\n" +
                        "        <ul>\n" +
                        "            <li>Username: " + recipientUsername + "</li>\n" +
                        "            <li>Password: " + recipientPassword + "</li>\n" +
                        "        </ul>\n" +
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
            } else {
                System.out.println("Email không tồn tại.");
                return false;
            }
//            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}

   
    
    

   

 
