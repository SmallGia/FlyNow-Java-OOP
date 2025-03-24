/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Tam
 */
import java.util.ArrayList;
import java.util.List;

public class BookingManager {
    private List<Booking> bookings;

    public BookingManager() {
        this.bookings = new ArrayList<>();
    }

    // Phương thức để thêm một đặt chỗ vào danh sách
    public void addBooking(int flightId, int numberOfTickets, int totalCost, String username) {
        Booking booking = new Booking(flightId, numberOfTickets, totalCost, username);
        bookings.add(booking);
    }

    // Phương thức để trả về danh sách các đặt chỗ
    public List<Booking> getBookings() {
        return bookings;
    }
    
}
