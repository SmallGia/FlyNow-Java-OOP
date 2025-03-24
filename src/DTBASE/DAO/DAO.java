package DTBASE.DAO;

import DTBASE.Customer.Customer;
import DTBASE.Information.Information;
import DTBASE.flights.Flights;
import DTBASE.Database.Database;
import DTBASE.Account.Account;

import javax.swing.*;
import java.sql.*;
import java.text.Collator;
import java.util.*;

/**
 *
 * @author Tam
 */
public class DAO {
      // ACCOUNT
      public static boolean checkUsernameExists(String username) {
          String sql = "SELECT * FROM account WHERE username = ?";
          try {
              Connection con = Database.openConnection();
              PreparedStatement statement = con.prepareStatement(sql);
              statement.setString(1, username);
              ResultSet rs = statement.executeQuery();

              if (rs.next()) {
                  return true;
              } else {
                  return false;
              }
          } catch (Exception ex) {
              ex.printStackTrace();
          }
          return false;
      }
      public static boolean checkEmailExists(String email) {
          String sql = "SELECT * FROM account WHERE email = ?";
          try {
              Connection con = Database.openConnection();
              PreparedStatement statement = con.prepareStatement(sql);
              statement.setString(1, email);
              ResultSet rs = statement.executeQuery();

              if (rs.next()) {
                  return true;
              } else {
                  return false;
              }
          } catch (Exception ex) {
              ex.printStackTrace();
          }
          return false;
      }

    public static boolean checkLogin(String username, String password) {
        String sql = "SELECT * FROM account WHERE username = ?";
        try {
            Connection con = Database.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String dbPassword = rs.getString("password");
                if (password.equals(dbPassword)) {
                //    System.out.println("Truy cập thành công.");
                    return true;
                } else {
                    return false;
                //    System.out.println("Sai password.");
                }
            } else {
                return false;
              //  System.out.println("Username không tồn tại.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public static String getEmailByUsername(String username) {
        String sql = "SELECT email FROM account WHERE username = ?";

        try {
            Connection con = Database.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getString("email");
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static void addNewAccount(String username, String password, String email) {
        String sql = "INSERT INTO account (username, password, email) VALUES (?, ?, ?)";
        try {
            Connection con = Database.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Người dùng mới đã được thêm vào cơ sở dữ liệu.");
            } else {
                System.out.println("Không có người dùng nào được thêm vào cơ sở dữ liệu.");
            }
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    // FLIGHTS
    public static List<String> getUniqueFromList() throws Exception  {
        List<String> uniqueFromList = new ArrayList<>();
        try {
            // Kết nối tới cơ sở dữ liệu
            Connection connection  = Database.openConnection();

            // Tạo câu truy vấn SQL
            String sql = "SELECT DISTINCT FromLocation FROM Flight";

            // Tạo đối tượng Statement
            Statement statement = connection.createStatement();

            // Thực thi truy vấn
            ResultSet resultSet = statement.executeQuery(sql);

            // Duyệt qua kết quả và thêm các điểm đi vào danh sách
            while (resultSet.next()) {
                String from = resultSet.getString("FromLocation");
                uniqueFromList.add(from);
            }

            // Đóng kết nối, câu lệnh và kết quả
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return uniqueFromList;
    }
    public static List<String> getUniqueToLocationList() throws Exception  {
        List<String> uniqueToLocationList = new ArrayList<>();
        try {
            // Kết nối tới cơ sở dữ liệu
            Connection connection  = Database.openConnection();

            // Tạo câu truy vấn SQL
            String sql = "SELECT DISTINCT Tolocation FROM Flight";

            // Tạo đối tượng Statement
            Statement statement = connection.createStatement();

            // Thực thi truy vấn
            ResultSet resultSet = statement.executeQuery(sql);

            // Duyệt qua kết quả và thêm các điểm đến vào danh sách
            while (resultSet.next()) {
                String toLocation = resultSet.getString("Tolocation");
                uniqueToLocationList.add(toLocation);
            }

            // Đóng kết nối, câu lệnh và kết quả
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return uniqueToLocationList;
    }
    public static List<Integer> getFlightIds(String fromLocation, String toLocation, String departureDate, int bookedTickets) {
        List<Integer> flightIds = new ArrayList<>();
        String sql = "SELECT id FROM Flight WHERE FromLocation = ? AND ToLocation = ? AND DepartureDate = ? AND Ticket >= ?";

        try {
            Connection con = Database.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, fromLocation);
            statement.setString(2, toLocation);
            statement.setString(3, departureDate);
            statement.setInt(4, bookedTickets);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                flightIds.add(rs.getInt("id"));
            }

            rs.close();
            statement.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return flightIds;
    }
    public static float calPrice(String ticketClass,int numTicket,float price){
            float heso;
            if (ticketClass.equals("Phổ thông"))
                heso=1;
            else if (ticketClass.equals("Phổ thông đặc biệt"))
                heso = 1.25F;
            else if (ticketClass.equals("Thương gia"))
                heso= 1.5F;
            else
                heso= 1.75F;
            return numTicket*heso*price;
    }
    public static Flights getFlightById(int id) {
        Flights flight = null;
        String sql = "SELECT * FROM Flight WHERE id = ?";
        try {
            Connection con = Database.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                flight = new Flights();
                flight.setId(rs.getInt("id"));
                flight.setFrom(rs.getString("FromLocation"));
                flight.setTo(rs.getString("Tolocation"));
                flight.setAirlines(rs.getString("Agent"));
                flight.setDatedepart(rs.getString("DepartureDate"));
                flight.setTimedepart(rs.getString("DepartureTime"));
                flight.setDatearrive(rs.getString("ArrivalDate"));
                flight.setTimearrive(rs.getString("ArrivalTime"));
                flight.setLeftticket(rs.getInt("Ticket"));
                flight.setPrice((float) rs.getDouble("Price"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return flight;
    }
    // Bill - Booking
    public static void addBooking(int flightId, int numberOfTickets, float totalCost, String username, String ticketClass,String ticketCode) throws Exception {
        try (Connection conn = Database.openConnection()) {
            String sql = "INSERT INTO Bookings (username, flight_id, number_of_tickets, total_cost, ticket_class,ticket_code) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setInt(2, flightId);
            pstmt.setInt(3, numberOfTickets);
            pstmt.setFloat(4, totalCost);
            pstmt.setString(5, ticketClass);
            pstmt.setString(6,ticketCode);
            pstmt.executeUpdate();
            // delete ticket
            sql = "UPDATE Flight SET Ticket = Ticket - ? WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, numberOfTickets);
            pstmt.setInt(2, flightId);
            pstmt.executeUpdate();

            System.out.println("Dữ liệu đã được thêm vào bảng Bookings thành công.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static List<Integer> getBookedFlights(String username) throws Exception {
        List<Integer> bookedFlights = new ArrayList<>();
        try (Connection conn = Database.openConnection()) {
            // Chuẩn bị câu lệnh SQL để truy vấn danh sách các chuyến bay đã đặt của username
            String sql = "SELECT flight_id FROM Bookings WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            // Thực hiện truy vấn và lấy kết quả
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int flightId = rs.getInt("flight_id");
                bookedFlights.add(flightId);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return bookedFlights;
    }
    public static List<Information> getInformationByUsername(String username) {
        List<Information> informationList = new ArrayList<>();
        String sql = "SELECT * FROM Bookings WHERE username = ?";

        try (Connection con = Database.openConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("flight_id");
                    float cost = rs.getFloat("total_cost");
                    int ticket = rs.getInt("number_of_tickets");
                    String ticketClass = rs.getString("ticket_class"); // Get the ticket class from the result set
                    String ticketCode = rs.getString("ticket_code");
                    Information info = new Information(username, id, cost, ticket, ticketClass,ticketCode);
                    informationList.add(info);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return informationList;
    }
    // Custumer
    public static void addNewCustomer(String ticketCode, String name, String birthdate, String gender, String phone, String email, String address,int id,String group) {
        try {
            // Mở kết nối với cơ sở dữ liệu
            Connection conn = Database.openConnection();
            String query = "INSERT INTO customer (hovaten, ngay_thang_nam_sinh, gioi_tinh, so_dien_thoai, email, dia_chi, ticket_code,num,old_group) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, birthdate);
            pstmt.setString(3, gender);
            pstmt.setString(4, phone);
            pstmt.setString(5, email);
            pstmt.setString(6, address);
            pstmt.setString(7, ticketCode);
            pstmt.setString(8, String.valueOf(id));
            pstmt.setString(9,group);
          //  pstmt.setString(10,seat);
            pstmt.executeUpdate();

            // Hiển thị thông báo khi khách hàng được thêm thành công
            JOptionPane.showMessageDialog(null, "Đã thêm thông tin khách hàng thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void addNewCustomer(String ticketCode, String name, String birthdate, String gender, String phone, String email, String address,int id,String group,String seat) {
        try {
            // Mở kết nối với cơ sở dữ liệu
            Connection conn = Database.openConnection();
            String query = "INSERT INTO customer (hovaten, ngay_thang_nam_sinh, gioi_tinh, so_dien_thoai, email, dia_chi, ticket_code,num,old_group,seat) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, birthdate);
            pstmt.setString(3, gender);
            pstmt.setString(4, phone);
            pstmt.setString(5, email);
            pstmt.setString(6, address);
            pstmt.setString(7, ticketCode);
            pstmt.setString(8, String.valueOf(id));
            pstmt.setString(9,group);
            pstmt.setString(10,seat);
            //  pstmt.setString(10,seat);
            pstmt.executeUpdate();

            // Hiển thị thông báo khi khách hàng được thêm thành công
            JOptionPane.showMessageDialog(null, "Đã thêm thông tin khách hàng thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void deleteCustomer(String ticketCode, int num) {
        try {
            // Mở kết nối với cơ sở dữ liệu
            Connection conn = Database.openConnection();

            // Tạo câu lệnh SQL để xóa bản ghi
            String sql = "DELETE FROM customer WHERE ticket_code = ? AND num = ?";

            // Tạo đối tượng PreparedStatement
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // Đặt giá trị cho các tham số trong câu lệnh SQL
            pstmt.setString(1, ticketCode);
            pstmt.setInt(2, num);

            // Thực thi câu lệnh SQL
            int rowsAffected = pstmt.executeUpdate();

            // Kiểm tra xem có bản ghi nào được xóa hay không
            if (rowsAffected > 0) {
                System.out.println("Customer has been successfully deleted.");
            } else {
                System.out.println("No customer found with the provided ticketCode and num.");
            }

            // Đóng kết nối
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static String getOldGroup(String ticketCode, int num) {
        String oldGroup = null;
        String sql = "SELECT old_group FROM customer WHERE ticket_code = ? AND num = ?";

        try {
            Connection con = Database.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, ticketCode);
            statement.setInt(2, num);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                oldGroup = rs.getString("old_group");
            }

            rs.close();
            statement.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return oldGroup;
    }
    public static String getClassTicket(String ticketCode) {
        String ticketClass = null;
        String sql = "SELECT * FROM bookings WHERE ticket_code = ?";

        try {
            Connection con = Database.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, ticketCode);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                ticketClass = rs.getString("ticket_class");
            }

            rs.close();
            statement.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ticketClass;
    }
    public static Customer getCustomer(String ticketCode, int id) {
        Customer customer = null;
        String sql = "SELECT * FROM customer WHERE ticket_code = ? AND num = ?";

        try {
            Connection con = Database.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, ticketCode);
            statement.setInt(2, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String name = rs.getString("hovaten");
                String birthdate = rs.getString("ngay_thang_nam_sinh");
                String gender = rs.getString("gioi_tinh");
                String phone = rs.getString("so_dien_thoai");
                String email = rs.getString("email");
                String address = rs.getString("dia_chi");
                String group = rs.getString("old_group");
                String seat = rs.getString("seat");
                customer = new Customer(ticketCode, name, birthdate, gender, phone, email, address, group, id,seat);
            }

            rs.close();
            statement.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return customer;
    }
    public static boolean checkSeatExists(String ticketCode, int num, String seat) {
        String sql = "SELECT * FROM customer WHERE ticket_code = ? AND num = ? AND seat = ?";

        try {
            Connection con = Database.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, ticketCode);
            statement.setInt(2, num);
            statement.setString(3, seat);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return true;
            }

            rs.close();
            statement.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }
    public static void main(String[] args) throws Exception {
//        DAO dao = new DAO();
//        int flightId = 101;  // ID của chuyến bay đã đặt
//        int numberOfTickets = 2;  // Số lượng vé đã đặt
//        int totalCost = 200; // Tổng tiền
//        String username = "user123";  // Tên người dùng
//        dao.addBooking(flightId, numberOfTickets, totalCost, username);
    }
}
    
    

   

 
