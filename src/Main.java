import BookingUI.Booking_UI;
import Login02UI.Login_Form_UI;
import MenuUI.Menu_UI;
import ProfileUI.Profile_UI;

public class Main {
    public static void main(String[] args) {
//            new Booking_UI().setVisible(true);
//            new Menu_UI().setVisible(true);
//            new Profile_UI().setVisible(true);
            Login_Form_UI login  = new Login_Form_UI();
            login.setVisible(true);
            login.setLocationRelativeTo(null);
            login.setDefaultCloseOperation(login.EXIT_ON_CLOSE);
    }
}