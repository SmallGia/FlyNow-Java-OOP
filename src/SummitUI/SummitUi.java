/*
 * Created by JFormDesigner on Mon May 06 22:59:19 ICT 2024
 */

package SummitUI;

import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

import BookingUI.Booking_UI;
import DTBASE.flights.Flights;
import com.k33ptoo.components.KButton;
import com.k33ptoo.components.KGradientPanel;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import java.awt.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static DTBASE.DAO.DAO.*;
import static Funtion.GenerateRandomString.generateRandomString;
import InfoUI.InfoUI;
/**
 * @author Thai Ho Phu Gia
 */
public class SummitUi extends JFrame {
    private String username;
    private Booking_UI bookingUI;
    private int ticket;
    private String to, from;
    private boolean comback;
    private LocalDate timearrive, timeback;
    private String timearr, timebk;
    private List<Integer> flightID;
    private List<Flights> flights;
    private String ticketClass;
    private float price;
    private int adult, child, baby;
    public SummitUi(String username, Booking_UI bookingUI) {
        initComponents();
        initlistentable();
        inittable();
        this.username = username;
        this.bookingUI = bookingUI;
        this.flights = new ArrayList<>();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private void inittable()
    {
        Font newFont = new Font("Consolas", Font.BOLD, 14); // Tạo font mới
        table1.getTableHeader().setFont(newFont); // Đặt font mới cho header của table1
        table1.getTableHeader().setReorderingAllowed(false);
// Nhân đôi chiều cao của hàng
        table1.setRowHeight(table1.getRowHeight() * 2);

// Nhân đôi chiều rộng của mỗi cột
        TableColumnModel columnModel = table1.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setPreferredWidth(column.getPreferredWidth() * 2);
        }
    }
    private void initlistentable()
    {
        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                // Kiểm tra xem sự kiện này có phải là sự kiện chọn hàng cuối cùng không
                if (!event.getValueIsAdjusting()){
                    int selectedRow = table1.getSelectedRow(); // Lấy hàng được chọn

                    if (selectedRow != -1) { // Kiểm tra xem có hàng nào được chọn không
                        // Lấy dữ liệu từ hàng được chọn và đặt vào các JTextField tương ứng
                        textField1.setText(table1.getValueAt(selectedRow, 1).toString()); // From
                        textField2.setText(table1.getValueAt(selectedRow, 2).toString()); // To
                        textField3.setText(table1.getValueAt(selectedRow, 3).toString()); // Airlines
                        textField4.setText(table1.getValueAt(selectedRow, 4).toString() + " " +  table1.getValueAt(selectedRow, 5).toString()); // Departure Date
                    //    textField5.setText(table1.getValueAt(selectedRow, 5).toString()); // Departure Time
                        textField6.setText(table1.getValueAt(selectedRow, 6).toString() + " " + table1.getValueAt(selectedRow, 7).toString()); // Arrival Date
                    //    textField7.setText(table1.getValueAt(selectedRow, 7).toString()); // Arrival Time
                        textField8.setText(ticketClass); // Class Ticket
                        textField9.setText(String.valueOf(ticket)); // Ticket
                        price = calPrice(ticketClass,ticket, (Float) table1.getValueAt(selectedRow,8));
                        textField10.setText(String.valueOf(price) + " $"); // Price
                    }
                }
            }
        });
    }
    private void updateTable(List<Flights> flights) {
        String[] columnNames = {"ID", "From", "To", "Airlines", "Departure Date", "Departure Time", "Arrival Date", "Arrival Time", "Price/Ticket", "Ticket left"};

        Object[][] data = new Object[flights.size()][10];
        for (int i = 0 ; i < flights.size() ; i++) {
            Flights flight = flights.get(i);
            data[i][0] = flight.getId();
            data[i][1] = flight.getFrom();
            data[i][2] = flight.getTo();
            data[i][3] = flight.getAirlines();
            data[i][4] = flight.getDatedepart();
            data[i][6] = flight.getDatearrive();
            data[i][5] = flight.getTimedepart();
            data[i][7] = flight.getTimearrive();
            data[i][8] = flight.getPrice();
            data[i][9] = flight.getLeftticket();
        }

        // Tạo một TableModel mới không cho phép chỉnh sửa
        TableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Tất cả các ô không thể chỉnh sửae
                return false;
            }
        };
        table1.setModel(model);

        table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Đặt căn giữa cho renderer
        for (int i = 0 ; i<=9 ;i++){
            table1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

    }

    private void flight() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        timearr = timearrive.format(formatter);
        if (comback) {
            timebk = timeback.format(formatter);
        }
        try {
            flightID = getFlightIds(from,to,timearr,ticket);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(from + " -> " + to);
        System.out.println(timearr);
        flights.clear();
        if (!flightID.isEmpty()) {
            ExecutorService executor = Executors.newFixedThreadPool(10); // Tạo một hồ bơi luồng với 10 luồng
            List<Future<Flights>> futures = new ArrayList<>();

            for (Integer id : flightID) {
                futures.add(
                        executor.submit(() -> getFlightById(id)) // Gửi một nhiệm vụ mới để thực hiện truy vấn
                );
            }

            for (Future<Flights> future : futures) {
                try {
                    flights.add(future.get()); // Đợi cho đến khi nhiệm vụ hoàn thành và thêm kết quả vào danh sách
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }

            executor.shutdown(); // Đóng hồ bơi luồng
            updateTable(flights);
        } else {
            System.out.println("Không tìm thấy chuyến bay nào");
            JOptionPane.showMessageDialog(null, "Không tìm thấy chuyến bay nào", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public int getAdult() {
        return adult;
    }

    public void setAdult(int adult) {
        this.adult = adult;
    }

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }

    public int getBaby() {
        return baby;
    }

    public void setBaby(int baby) {
        this.baby = baby;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public boolean isComback() {
        return comback;
    }

    public void setComback(boolean comback) {
        this.comback = comback;
    }

    public LocalDate getTimearrive() {
        return timearrive;
    }

    public void setTimearrive(LocalDate timearrive) {
        this.timearrive = timearrive;
    }

    public LocalDate getTimeback() {
        return timeback;
    }

    public void setTimeback(LocalDate timeback) {
        this.timeback = timeback;
    }

    public String getTicketClass() {
        return ticketClass;
    }

    public void setTicketClass(String ticketclass) {
        this.ticketClass = ticketclass;
    }

    private void kButton1(ActionEvent e) {
        // TODO add your code here
        // Show the loading label
        loading.setVisible(true);

        // Create a SwingWorker
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Perform the heavy task
                flight();

                return null;
            }

            @Override
            protected void done() {
                // Hide the loading label when the task is done
                loading.setVisible(false);
            }
        };

        // Start the SwingWorker
        worker.execute();
    }
    public void resetComponents() {
        // Xóa dữ liệu trong các JTextField
        textField1.setText(""); // Xóa dữ liệu trong textField1
        textField2.setText(""); // Xóa dữ liệu trong textField2
        textField3.setText(""); // Xóa dữ liệu trong textField3
        textField4.setText(""); // Xóa dữ liệu trong textField4
        textField5.setText(""); // Xóa dữ liệu trong textField5
        textField6.setText(""); // Xóa dữ liệu trong textField6
        textField7.setText(""); // Xóa dữ liệu trong textField7
        textField8.setText(""); // Xóa dữ liệu trong textField8
        textField9.setText(""); // Xóa dữ liệu trong textField9
        textField10.setText(""); // Xóa dữ liệu trong textField10

        // Xóa dữ liệu trong JTable
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);
    }
    private void kButton2(ActionEvent e) {
        // TODO add your code here
        this.setVisible(false);
        resetComponents();
        bookingUI.resetComponents();
        bookingUI.setVisible(true);
    }

    private void kButton3(ActionEvent e) {
        // TODO add your code here
        String ticket_code = generateRandomString();
        int selectedRow = table1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn chuyến bay nào!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int id = (int) table1.getValueAt(selectedRow, 0);
            // Show a confirmation dialog
            int response = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn đặt vé không?", "Xác nhận",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            // If the user clicks YES, then proceed with the booking
            if (response == JOptionPane.YES_OPTION) {
                setEnabled(false);
                addBooking(id,ticket,price,username,ticketClass,ticket_code);
                setEnabled(true);
                System.out.println("Đã lưu chuyến bay vào " + username + " thành công");
                new InfoUI(ticket,ticket_code,adult,child,baby).setVisible(true);
         //       JOptionPane.showMessageDialog(null, "<html><font face='consolas' size='5'><center>Bạn đã đặt vé thành công, hãy quay về màn hình chính để xem vé đã đặt!<br/>Mã vẽ của bạn là  <font color='red'>" + ticket_code + "</font></center></font></html>", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    public boolean isTable1Empty() {
        return table1.getModel().getRowCount() <= 0;
    }
    private void kButton4(ActionEvent e) {
        // TODO add your code here
        if (isTable1Empty()){
            JOptionPane.showMessageDialog(null, "Không có chuyến bay nào để sắp xếp", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Enable auto creation of row sorter
        table1.setAutoCreateRowSorter(true);

        // Assuming "Price/Ticket" is the 9th column in the table
        int columnIndex = 8; // Column index starts from 0
        if (columnIndex < table1.getModel().getColumnCount()) {
            table1.getRowSorter().toggleSortOrder(columnIndex);
        } else {
            JOptionPane.showMessageDialog(null, "Cột để sắp xếp không tồn tại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void kButton5(ActionEvent e) {
        // TODO add your code here
        if (isTable1Empty()){
            JOptionPane.showMessageDialog(null, "Không có chuyến bay nào để sắp xếp", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table1.getModel());
        table1.setRowSorter(sorter);

        Comparator<String> flightTimeComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                LocalTime departureTime1 = LocalTime.parse(o1.split(" ")[1]);
                LocalTime arrivalTime1 = LocalTime.parse(o1.split(" ")[3]);
                long minutes1 = ChronoUnit.MINUTES.between(departureTime1, arrivalTime1);
                if (minutes1 < 0) {
                    minutes1 += 24 * 60; // add 24 hours if the flight is overnight
                }

                LocalTime departureTime2 = LocalTime.parse(o2.split(" ")[1]);
                LocalTime arrivalTime2 = LocalTime.parse(o2.split(" ")[3]);
                long minutes2 = ChronoUnit.MINUTES.between(departureTime2, arrivalTime2);
                if (minutes2 < 0) {
                    minutes2 += 24 * 60; // add 24 hours if the flight is overnight
                }

                return Long.compare(minutes1, minutes2);
            }
        };

        sorter.setComparator(6, flightTimeComparator); // Assuming "Departure Time" is the 7th column in the table
        sorter.setComparator(8, flightTimeComparator); // Assuming "Arrival Time" is the 9th column in the table
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        loading = new JLabel();
        kButton2 = new KButton();
        kButton1 = new KButton();
        label2 = new JLabel();
        label1 = new JLabel();
        kGradientPanel1 = new KGradientPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        textField7 = new JTextField();
        kButton3 = new KButton();
        panel2 = new JPanel();
        textField1 = new JTextField();
        textField2 = new JTextField();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        textField3 = new JTextField();
        label6 = new JLabel();
        textField4 = new JTextField();
        textField6 = new JTextField();
        label7 = new JLabel();
        label8 = new JLabel();
        textField8 = new JTextField();
        textField9 = new JTextField();
        label9 = new JLabel();
        textField10 = new JTextField();
        label10 = new JLabel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();
        panel6 = new JPanel();
        textField5 = new JTextField();
        kButton4 = new KButton();
        kButton5 = new KButton();

        //======== this ========
        setMaximumSize(new Dimension(1280, 720));
        setMinimumSize(new Dimension(1280, 720));
        setPreferredSize(new Dimension(1280, 720));
        setTitle("Fly Now  Official 1.0"); //NON-NLS
        setIconImage(new ImageIcon(getClass().getResource("/Icon/Logo/plane_whitebackground.png")).getImage()); //NON-NLS
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.cyan, Color.blue, Color.cyan, Color.blue));
            panel1.setLayout(null);

            //---- loading ----
            loading.setIcon(new ImageIcon(getClass().getResource("/Icon/Icon/superminiloading.gif"))); //NON-NLS
            loading.setVisible(false);
            panel1.add(loading);
            loading.setBounds(545, 260, 105, 135);

            //---- kButton2 ----
            kButton2.setText("QUAY V\u1ec0 M\u00c0N H\u00ccNH T\u00ccM KI\u1ebeM"); //NON-NLS
            kButton2.setkBorderRadius(30);
            kButton2.setBorder(null);
            kButton2.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            kButton2.addActionListener(e -> kButton2(e));
            panel1.add(kButton2);
            kButton2.setBounds(30, 15, 310, kButton2.getPreferredSize().height);

            //---- kButton1 ----
            kButton1.setText("HI\u1ec6N TH\u1eca CHUY\u1ebeN BAY"); //NON-NLS
            kButton1.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            kButton1.setkBorderRadius(30);
            kButton1.setBorder(null);
            kButton1.addActionListener(e -> kButton1(e));
            panel1.add(kButton1);
            kButton1.setBounds(915, 15, 310, kButton1.getPreferredSize().height);

            //---- label2 ----
            label2.setIcon(new ImageIcon(getClass().getResource("/Icon/Logo/plane_175x135.png"))); //NON-NLS
            panel1.add(label2);
            label2.setBounds(535, 0, 165, 80);

            //---- label1 ----
            label1.setText("Nh\u1eefng chuy\u1ebfn bay t\u00ecm th\u1ea5y"); //NON-NLS
            label1.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            label1.setVisible(false);
            panel1.add(label1);
            label1.setBounds(670, 15, 300, 45);

            //---- kGradientPanel1 ----
            kGradientPanel1.setkStartColor(Color.white);
            kGradientPanel1.setkEndColor(Color.cyan);
            panel1.add(kGradientPanel1);
            kGradientPanel1.setBounds(0, 0, 1240, 75);

            //======== scrollPane1 ========
            {

                //---- table1 ----
                table1.setBorder(null);
                table1.setFont(new Font("Consolas", Font.BOLD, 14)); //NON-NLS
                scrollPane1.setViewportView(table1);
            }
            panel1.add(scrollPane1);
            scrollPane1.setBounds(5, 370, 1230, 277);

            //---- textField7 ----
            textField7.setEditable(false);
            textField7.setVisible(false);
            panel1.add(textField7);
            textField7.setBounds(820, 295, 150, 60);

            //---- kButton3 ----
            kButton3.setText("\u0110\u1eb6T V\u00c9"); //NON-NLS
            kButton3.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 20)); //NON-NLS
            kButton3.setkBorderRadius(30);
            kButton3.setBorder(null);
            kButton3.addActionListener(e -> kButton3(e));
            panel1.add(kButton3);
            kButton3.setBounds(new Rectangle(new Point(1005, 320), kButton3.getPreferredSize()));

            //======== panel2 ========
            {
                panel2.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.cyan, Color.blue, Color.cyan, Color.blue));
                panel2.setLayout(null);

                //---- textField1 ----
                textField1.setEditable(false);
                textField1.setBorder(null);
                textField1.setFont(new Font("Segoe UI", Font.BOLD, 16)); //NON-NLS
                textField1.setHorizontalAlignment(SwingConstants.CENTER);
                panel2.add(textField1);
                textField1.setBounds(20, 50, 150, 40);

                //---- textField2 ----
                textField2.setEditable(false);
                textField2.setFont(new Font("Segoe UI", Font.BOLD, 16)); //NON-NLS
                textField2.setBorder(null);
                textField2.setHorizontalAlignment(SwingConstants.CENTER);
                panel2.add(textField2);
                textField2.setBounds(280, 45, 150, 45);

                //---- label3 ----
                label3.setFont(new Font("Segoe UI", Font.BOLD, 12)); //NON-NLS
                label3.setIcon(new ImageIcon(getClass().getResource("/Icon/Icon/arrow.png"))); //NON-NLS
                panel2.add(label3);
                label3.setBounds(205, 45, 60, 45);

                //---- label4 ----
                label4.setText("Chuy\u1ebfn bay"); //NON-NLS
                label4.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
                label4.setHorizontalAlignment(SwingConstants.CENTER);
                panel2.add(label4);
                label4.setBounds(150, 15, 150, 30);

                //---- label5 ----
                label5.setText("H\u00e3ng bay"); //NON-NLS
                label5.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
                label5.setHorizontalAlignment(SwingConstants.CENTER);
                panel2.add(label5);
                label5.setBounds(610, 15, 150, 30);

                //---- textField3 ----
                textField3.setEditable(false);
                textField3.setFont(new Font("Segoe UI", Font.BOLD, 16)); //NON-NLS
                textField3.setBorder(null);
                textField3.setHorizontalAlignment(SwingConstants.CENTER);
                panel2.add(textField3);
                textField3.setBounds(610, 50, 150, 45);

                //---- label6 ----
                label6.setText("Kh\u1edfi h\u00e0nh l\u00fac"); //NON-NLS
                label6.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
                label6.setHorizontalAlignment(SwingConstants.CENTER);
                panel2.add(label6);
                label6.setBounds(815, 15, 150, 30);

                //---- textField4 ----
                textField4.setEditable(false);
                textField4.setFont(new Font("Segoe UI", Font.BOLD, 16)); //NON-NLS
                textField4.setBorder(null);
                textField4.setHorizontalAlignment(SwingConstants.CENTER);
                panel2.add(textField4);
                textField4.setBounds(805, 50, 175, 45);

                //---- textField6 ----
                textField6.setEditable(false);
                textField6.setFont(new Font("Segoe UI", Font.BOLD, 16)); //NON-NLS
                textField6.setBorder(null);
                textField6.setHorizontalAlignment(SwingConstants.CENTER);
                panel2.add(textField6);
                textField6.setBounds(1015, 50, 155, 45);

                //---- label7 ----
                label7.setText("\u0110\u1ebfn l\u00fac"); //NON-NLS
                label7.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
                label7.setHorizontalAlignment(SwingConstants.CENTER);
                panel2.add(label7);
                label7.setBounds(1015, 15, 150, 30);

                //---- label8 ----
                label8.setText("H\u1ea1ng v\u00e9"); //NON-NLS
                label8.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
                label8.setHorizontalAlignment(SwingConstants.CENTER);
                panel2.add(label8);
                label8.setBounds(20, 130, 150, 30);

                //---- textField8 ----
                textField8.setEditable(false);
                textField8.setFont(new Font("Segoe UI", Font.BOLD, 16)); //NON-NLS
                textField8.setBorder(null);
                textField8.setHorizontalAlignment(SwingConstants.CENTER);
                panel2.add(textField8);
                textField8.setBounds(20, 165, 150, 45);

                //---- textField9 ----
                textField9.setEditable(false);
                textField9.setFont(new Font("Segoe UI", Font.BOLD, 16)); //NON-NLS
                textField9.setBorder(null);
                textField9.setHorizontalAlignment(SwingConstants.CENTER);
                panel2.add(textField9);
                textField9.setBounds(280, 165, 150, 45);

                //---- label9 ----
                label9.setText("S\u1ed1 v\u00e9"); //NON-NLS
                label9.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
                label9.setHorizontalAlignment(SwingConstants.CENTER);
                panel2.add(label9);
                label9.setBounds(280, 130, 150, 30);

                //---- textField10 ----
                textField10.setEditable(false);
                textField10.setFont(new Font("Segoe UI", Font.BOLD, 16)); //NON-NLS
                textField10.setBorder(null);
                textField10.setHorizontalAlignment(SwingConstants.CENTER);
                panel2.add(textField10);
                textField10.setBounds(820, 165, 150, 45);

                //---- label10 ----
                label10.setText("Th\u00e0nh ti\u1ec1n"); //NON-NLS
                label10.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
                label10.setHorizontalAlignment(SwingConstants.CENTER);
                panel2.add(label10);
                label10.setBounds(820, 130, 150, 30);

                //======== panel3 ========
                {
                    panel3.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.cyan, Color.blue, Color.cyan, Color.blue));
                    panel3.setLayout(null);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel3.getComponentCount(); i++) {
                            Rectangle bounds = panel3.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel3.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel3.setMinimumSize(preferredSize);
                        panel3.setPreferredSize(preferredSize);
                    }
                }
                panel2.add(panel3);
                panel3.setBounds(10, 10, 440, 100);

                //======== panel4 ========
                {
                    panel4.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.cyan, Color.blue, Color.cyan, Color.blue));
                    panel4.setLayout(null);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel4.getComponentCount(); i++) {
                            Rectangle bounds = panel4.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel4.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel4.setMinimumSize(preferredSize);
                        panel4.setPreferredSize(preferredSize);
                    }
                }
                panel2.add(panel4);
                panel4.setBounds(10, 125, 440, 100);

                //======== panel5 ========
                {
                    panel5.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.cyan, Color.blue, Color.cyan, Color.blue));
                    panel5.setLayout(null);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel5.getComponentCount(); i++) {
                            Rectangle bounds = panel5.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel5.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel5.setMinimumSize(preferredSize);
                        panel5.setPreferredSize(preferredSize);
                    }
                }
                panel2.add(panel5);
                panel5.setBounds(595, 125, 600, 100);

                //======== panel6 ========
                {
                    panel6.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.cyan, Color.blue, Color.cyan, Color.blue));
                    panel6.setLayout(null);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel6.getComponentCount(); i++) {
                            Rectangle bounds = panel6.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel6.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel6.setMinimumSize(preferredSize);
                        panel6.setPreferredSize(preferredSize);
                    }
                }
                panel2.add(panel6);
                panel6.setBounds(595, 10, 600, 100);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel2.getComponentCount(); i++) {
                        Rectangle bounds = panel2.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel2.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel2.setMinimumSize(preferredSize);
                    panel2.setPreferredSize(preferredSize);
                }
            }
            panel1.add(panel2);
            panel2.setBounds(15, 80, 1210, 235);

            //---- textField5 ----
            textField5.setEditable(false);
            textField5.setVisible(false);
            panel1.add(textField5);
            textField5.setBounds(1010, 320, 195, 49);

            //---- kButton4 ----
            kButton4.setText("S\u1eaeP X\u1ebeP THEO GI\u00c1 TI\u1ec0N"); //NON-NLS
            kButton4.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 20)); //NON-NLS
            kButton4.setkBorderRadius(30);
            kButton4.setBorder(null);
            kButton4.addActionListener(e -> kButton4(e));
            panel1.add(kButton4);
            kButton4.setBounds(55, 320, 290, kButton4.getPreferredSize().height);

            //---- kButton5 ----
            kButton5.setText("S\u1eaeP X\u1ebeP THEO TH\u1edcI GIAN BAY"); //NON-NLS
            kButton5.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 20)); //NON-NLS
            kButton5.setkBorderRadius(30);
            kButton5.setBorder(null);
            kButton5.addActionListener(e -> kButton5(e));
            panel1.add(kButton5);
            kButton5.setBounds(495, 320, 335, kButton5.getPreferredSize().height);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel1.getComponentCount(); i++) {
                    Rectangle bounds = panel1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel1.setMinimumSize(preferredSize);
                panel1.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel1);
        panel1.setBounds(15, 15, 1240, 650);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel1;
    private JLabel loading;
    private KButton kButton2;
    private KButton kButton1;
    private JLabel label2;
    private JLabel label1;
    private KGradientPanel kGradientPanel1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JTextField textField7;
    private KButton kButton3;
    private JPanel panel2;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JTextField textField3;
    private JLabel label6;
    private JTextField textField4;
    private JTextField textField6;
    private JLabel label7;
    private JLabel label8;
    private JTextField textField8;
    private JTextField textField9;
    private JLabel label9;
    private JTextField textField10;
    private JLabel label10;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel6;
    private JTextField textField5;
    private KButton kButton4;
    private KButton kButton5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
