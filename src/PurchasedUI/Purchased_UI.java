/*
 * Created by JFormDesigner on Fri May 10 20:39:53 ICT 2024
 */

package PurchasedUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

import DTBASE.Information.Information;
import DTBASE.flights.Flights;
import MenuUI.Menu_UI;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import PurInfoUI.PurchInfoUI;
import com.k33ptoo.components.KButton;
import com.k33ptoo.components.KGradientPanel;
import static DTBASE.DAO.DAO.*;
import javax.swing.event.ListSelectionEvent;
/**
 * @author Thai Ho Phu Gia
 */
public class Purchased_UI extends JFrame {
    private List<Integer> FlightID;
    private List<Flights> flights;
    private Menu_UI menuui;
    private String username;
    private List<Information> flightinfo;
    public Purchased_UI(String username, Menu_UI menuui) {
        initComponents();
        initTable();
        this.menuui = menuui;
        this.username = username;
        this.flights = new ArrayList<>();
        this.flightinfo = new ArrayList<>();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private void initTable(){
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
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                int viewRow = table1.getSelectedRow();
                if (viewRow < 0) {
                    //Không có lựa chọn nào
                } else {
                    int modelRow = table1.convertRowIndexToModel(viewRow);
                    // Đặt giá trị của các trường văn bản dựa trên giá trị trong hàng được chọn
                    textField1.setText(table1.getModel().getValueAt(modelRow, 2).toString());
                    textField2.setText(table1.getModel().getValueAt(modelRow, 3).toString());
                    textField3.setText(table1.getModel().getValueAt(modelRow, 4).toString());
                    textField4.setText(table1.getModel().getValueAt(modelRow, 5).toString() + " " + table1.getModel().getValueAt(modelRow, 6).toString());
                    textField6.setText(table1.getModel().getValueAt(modelRow, 7).toString() + " " + table1.getModel().getValueAt(modelRow, 8).toString());
                    textField8.setText(table1.getModel().getValueAt(modelRow, 10).toString());
                    textField9.setText(table1.getModel().getValueAt(modelRow, 9).toString());
                    textField10.setText(table1.getModel().getValueAt(modelRow, 11).toString() + " $");
                    textField11.setText("BANKING");
                    textField12.setText(table1.getModel().getValueAt(modelRow,0).toString());
                }
            }
        });
    }
    private void kButton1(ActionEvent e) {
        // TODO add your code here
        this.setVisible(false);
        menuui.setVisible(true);
    }
    private void updateTable(List<Flights> flights, List<Information> informations) {
        String[] columnNames = {"Code","ID", "From", "To", "Airlines", "Departure Date", "Departure Time", "Arrival Date", "Arrival Time", "Ticket","Ticket Class" , "Cost"};

        // Tạo một Map để lưu trữ thông tin dựa trên id
        Map<Integer, Information> infoMap = new HashMap<>();
        for (Information info : informations) {
            infoMap.put(info.getID(), info);
        }

        Object[][] data = new Object[flights.size()][12];
        for (int i = 0 ; i < flights.size() ; i++) {
            Flights flight = flights.get(i);
            Information info = infoMap.get(flight.getId());
            data[i][0] = info != null ? info.getTicketCode() : null; // Nếu không tìm thấy thông tin, đặt giá trị là null
            data[i][1] = flight.getId();
            data[i][2] = flight.getFrom();
            data[i][3] = flight.getTo();
            data[i][4] = flight.getAirlines();
            data[i][5] = flight.getDatedepart();
            data[i][6] = flight.getTimedepart();
            data[i][7] = flight.getDatearrive();
            data[i][8] = flight.getTimearrive();
            data[i][9] = info != null ? info.getTicket() : null; // Nếu không tìm thấy thông tin, đặt giá trị là null
            data[i][10] = info != null ? info.getTicketClass() : null; // Nếu không tìm thấy thông tin, đặt giá trị là null
            data[i][11] = info != null ? info.getCost() : null; // Nếu không tìm thấy thông tin, đặt giá trị là null
        }

        // Tạo một TableModel mới không cho phép chỉnh sửa
        TableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Tất cả các ô không thể chỉnh sửa
                return false;
            }
        };
        table1.setModel(model);

        // Tạo một DefaultTableCellRenderer mới
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Đặt căn giữa cho renderer
        for (int i = 0 ; i<=10 ;i++){
            table1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
    private void reload(){
        flightinfo.clear();
        flights.clear();
        try {
            FlightID = getBookedFlights(username);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        if (!FlightID.isEmpty()) {
            ExecutorService executor = Executors.newFixedThreadPool(10); // Tạo một hồ bơi luồng với 10 luồng
            List<Future<Flights>> futures = new ArrayList<>();

            for (Integer id : FlightID) {
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
            flightinfo = getInformationByUsername(username);
            updateTable(flights,flightinfo);
        } else {
            JOptionPane.showMessageDialog(null, "Bạn chưa từng đặt chuyến bay nào cả, hãy trở lại menu vào phần đặt vé để đặt vé ngay!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Không tìm thấy chuyến bay nào");
        }
    }
    private void button1(ActionEvent e) {
        // TODO add your code here
        reload();
    }
    public void resetComponents() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField6.setText("");
        textField8.setText("");
        textField9.setText("");
        textField10.setText("");
        textField11.setText("");
        textField12.setText("");
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);
    }

    private void kButton2(ActionEvent e) {
        // TODO add your code here
        // Kiểm tra xem có dòng nào được chọn hay không
        if (table1.getSelectedRow() == -1) {
            // Nếu không có dòng nào được chọn, hiển thị cảnh báo
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng trong bảng trước khi nhấn nút này", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        } else {
            // TODO: Thêm mã của bạn ở đây
            int number = Integer.parseInt(textField9.getText());
            new PurchInfoUI(textField12.getText(),number,this).setVisible(true);
            this.setEnabled(false);
        }
    }
    public boolean isTable1Empty() {
        return table1.getModel().getRowCount() <= 0;
    }
    private void kButton3(ActionEvent e) {
        // TODO add your code here
        if (isTable1Empty()){
            JOptionPane.showMessageDialog(null, "Không có chuyến bay nào để sắp xếp", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Enable auto creation of row sorter
        table1.setAutoCreateRowSorter(true);

        // Assuming "Price/Ticket" is the 9th column in the table
        int columnIndex = 11; // Column index starts from 0
        if (columnIndex < table1.getModel().getColumnCount()) {
            table1.getRowSorter().toggleSortOrder(columnIndex);
        } else {
            JOptionPane.showMessageDialog(null, "Cột để sắp xếp không tồn tại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void kButton4(ActionEvent e) {
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
        panel2 = new JPanel();
        kButton1 = new KButton();
        label2 = new JLabel();
        label1 = new JLabel();
        kGradientPanel1 = new KGradientPanel();
        Panel1 = new JPanel();
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
        panel4 = new JPanel();
        panel5 = new JPanel();
        panel6 = new JPanel();
        textField10 = new JTextField();
        label10 = new JLabel();
        label11 = new JLabel();
        textField11 = new JTextField();
        label12 = new JLabel();
        textField12 = new JTextField();
        panel7 = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        kButton2 = new KButton();
        kButton3 = new KButton();
        kButton4 = new KButton();

        //======== this ========
        setResizable(false);
        setMaximumSize(new Dimension(1280, 720));
        setMinimumSize(new Dimension(1280, 720));
        setPreferredSize(new Dimension(1280, 720));
        setTitle("Fly Now  Official 1.0"); //NON-NLS
        setIconImage(new ImageIcon(getClass().getResource("/Icon/Logo/plane_whitebackground.png")).getImage()); //NON-NLS
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setLayout(null);

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
        panel1.setBounds(0, 680, 1280, 690);

        //======== panel2 ========
        {
            panel2.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.cyan, Color.blue, Color.cyan, Color.blue));
            panel2.setBackground(Color.white);
            panel2.setLayout(null);

            //---- kButton1 ----
            kButton1.setText("QUAY L\u1ea0I M\u00c0N H\u00ccNH CH\u00cdNH"); //NON-NLS
            kButton1.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            kButton1.addActionListener(e -> kButton1(e));
            panel2.add(kButton1);
            kButton1.setBounds(25, 10, 300, kButton1.getPreferredSize().height);

            //---- label2 ----
            label2.setIcon(new ImageIcon(getClass().getResource("/Icon/Logo/plane_175x135.png"))); //NON-NLS
            panel2.add(label2);
            label2.setBounds(480, 0, 165, 80);

            //---- label1 ----
            label1.setText("V\u00c9 \u0110\u00c3 \u0110\u1eb6T"); //NON-NLS
            label1.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 30)); //NON-NLS
            label1.setHorizontalAlignment(SwingConstants.TRAILING);
            panel2.add(label1);
            label1.setBounds(740, 15, 490, 45);

            //---- kGradientPanel1 ----
            kGradientPanel1.setkStartColor(Color.white);
            kGradientPanel1.setkEndColor(Color.cyan);
            panel2.add(kGradientPanel1);
            kGradientPanel1.setBounds(0, 0, 1240, 75);

            //======== Panel1 ========
            {
                Panel1.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.cyan, Color.blue, Color.cyan, Color.blue));
                Panel1.setBackground(Color.white);
                Panel1.setLayout(null);

                //---- textField1 ----
                textField1.setEditable(false);
                textField1.setBorder(null);
                textField1.setFont(new Font("Segoe UI", Font.BOLD, 16)); //NON-NLS
                textField1.setHorizontalAlignment(SwingConstants.CENTER);
                textField1.setBackground(Color.white);
                Panel1.add(textField1);
                textField1.setBounds(20, 50, 150, 40);

                //---- textField2 ----
                textField2.setEditable(false);
                textField2.setFont(new Font("Segoe UI", Font.BOLD, 16)); //NON-NLS
                textField2.setBorder(null);
                textField2.setHorizontalAlignment(SwingConstants.CENTER);
                textField2.setBackground(Color.white);
                Panel1.add(textField2);
                textField2.setBounds(280, 45, 150, 45);

                //---- label3 ----
                label3.setFont(new Font("Segoe UI", Font.BOLD, 12)); //NON-NLS
                label3.setIcon(new ImageIcon(getClass().getResource("/Icon/Icon/arrow.png"))); //NON-NLS
                Panel1.add(label3);
                label3.setBounds(205, 45, 60, 45);

                //---- label4 ----
                label4.setText("Chuy\u1ebfn bay"); //NON-NLS
                label4.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
                label4.setHorizontalAlignment(SwingConstants.CENTER);
                Panel1.add(label4);
                label4.setBounds(150, 15, 150, 30);

                //---- label5 ----
                label5.setText("H\u00e3ng bay"); //NON-NLS
                label5.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
                label5.setHorizontalAlignment(SwingConstants.CENTER);
                Panel1.add(label5);
                label5.setBounds(610, 15, 150, 30);

                //---- textField3 ----
                textField3.setEditable(false);
                textField3.setFont(new Font("Segoe UI", Font.BOLD, 16)); //NON-NLS
                textField3.setBorder(null);
                textField3.setHorizontalAlignment(SwingConstants.CENTER);
                textField3.setBackground(Color.white);
                Panel1.add(textField3);
                textField3.setBounds(610, 50, 150, 45);

                //---- label6 ----
                label6.setText("Kh\u1edfi h\u00e0nh l\u00fac"); //NON-NLS
                label6.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
                label6.setHorizontalAlignment(SwingConstants.CENTER);
                Panel1.add(label6);
                label6.setBounds(815, 15, 150, 30);

                //---- textField4 ----
                textField4.setEditable(false);
                textField4.setFont(new Font("Segoe UI", Font.BOLD, 16)); //NON-NLS
                textField4.setBorder(null);
                textField4.setHorizontalAlignment(SwingConstants.CENTER);
                textField4.setBackground(Color.white);
                Panel1.add(textField4);
                textField4.setBounds(805, 50, 175, 45);

                //---- textField6 ----
                textField6.setEditable(false);
                textField6.setFont(new Font("Segoe UI", Font.BOLD, 16)); //NON-NLS
                textField6.setBorder(null);
                textField6.setHorizontalAlignment(SwingConstants.CENTER);
                textField6.setBackground(Color.white);
                Panel1.add(textField6);
                textField6.setBounds(1015, 50, 155, 45);

                //---- label7 ----
                label7.setText("\u0110\u1ebfn l\u00fac"); //NON-NLS
                label7.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
                label7.setHorizontalAlignment(SwingConstants.CENTER);
                Panel1.add(label7);
                label7.setBounds(1015, 15, 150, 30);

                //---- label8 ----
                label8.setText("H\u1ea1ng v\u00e9"); //NON-NLS
                label8.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
                label8.setHorizontalAlignment(SwingConstants.CENTER);
                Panel1.add(label8);
                label8.setBounds(20, 130, 150, 30);

                //---- textField8 ----
                textField8.setEditable(false);
                textField8.setFont(new Font("Segoe UI", Font.BOLD, 16)); //NON-NLS
                textField8.setBorder(null);
                textField8.setHorizontalAlignment(SwingConstants.CENTER);
                textField8.setBackground(Color.white);
                Panel1.add(textField8);
                textField8.setBounds(20, 165, 150, 45);

                //---- textField9 ----
                textField9.setEditable(false);
                textField9.setFont(new Font("Segoe UI", Font.BOLD, 16)); //NON-NLS
                textField9.setBorder(null);
                textField9.setHorizontalAlignment(SwingConstants.CENTER);
                textField9.setBackground(Color.white);
                Panel1.add(textField9);
                textField9.setBounds(280, 165, 150, 45);

                //---- label9 ----
                label9.setText("S\u1ed1 v\u00e9"); //NON-NLS
                label9.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
                label9.setHorizontalAlignment(SwingConstants.CENTER);
                Panel1.add(label9);
                label9.setBounds(280, 130, 150, 30);

                //======== panel4 ========
                {
                    panel4.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.cyan, Color.blue, Color.cyan, Color.blue));
                    panel4.setBackground(Color.white);
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
                Panel1.add(panel4);
                panel4.setBounds(10, 10, 440, 100);

                //======== panel5 ========
                {
                    panel5.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.cyan, Color.blue, Color.cyan, Color.blue));
                    panel5.setBackground(Color.white);
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
                Panel1.add(panel5);
                panel5.setBounds(10, 125, 440, 100);

                //======== panel6 ========
                {
                    panel6.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.cyan, Color.blue, Color.cyan, Color.blue));
                    panel6.setBackground(Color.white);
                    panel6.setLayout(null);

                    //---- textField10 ----
                    textField10.setEditable(false);
                    textField10.setFont(new Font("Segoe UI", Font.BOLD, 16)); //NON-NLS
                    textField10.setBorder(null);
                    textField10.setHorizontalAlignment(SwingConstants.CENTER);
                    textField10.setBackground(Color.white);
                    panel6.add(textField10);
                    textField10.setBounds(205, 45, 150, 45);

                    //---- label10 ----
                    label10.setText("Th\u00e0nh ti\u1ec1n"); //NON-NLS
                    label10.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
                    label10.setHorizontalAlignment(SwingConstants.CENTER);
                    panel6.add(label10);
                    label10.setBounds(205, 10, 150, 30);

                    //---- label11 ----
                    label11.setText("H\u00ecnh th\u1ee9c thanh to\u00e1n"); //NON-NLS
                    label11.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
                    label11.setHorizontalAlignment(SwingConstants.CENTER);
                    panel6.add(label11);
                    label11.setBounds(375, 10, 220, 30);

                    //---- textField11 ----
                    textField11.setEditable(false);
                    textField11.setFont(new Font("Segoe UI", Font.BOLD, 16)); //NON-NLS
                    textField11.setBorder(null);
                    textField11.setHorizontalAlignment(SwingConstants.CENTER);
                    textField11.setBackground(Color.white);
                    panel6.add(textField11);
                    textField11.setBounds(385, 45, 200, 45);

                    //---- label12 ----
                    label12.setText("M\u00e3 v\u00e9"); //NON-NLS
                    label12.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
                    label12.setHorizontalAlignment(SwingConstants.CENTER);
                    panel6.add(label12);
                    label12.setBounds(15, 10, 150, 30);

                    //---- textField12 ----
                    textField12.setEditable(false);
                    textField12.setFont(new Font("Segoe UI", Font.BOLD, 16)); //NON-NLS
                    textField12.setBorder(null);
                    textField12.setHorizontalAlignment(SwingConstants.CENTER);
                    textField12.setBackground(Color.white);
                    panel6.add(textField12);
                    textField12.setBounds(15, 45, 150, 45);

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
                Panel1.add(panel6);
                panel6.setBounds(595, 125, 600, 100);

                //======== panel7 ========
                {
                    panel7.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.cyan, Color.blue, Color.cyan, Color.blue));
                    panel7.setBackground(Color.white);
                    panel7.setLayout(null);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel7.getComponentCount(); i++) {
                            Rectangle bounds = panel7.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel7.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel7.setMinimumSize(preferredSize);
                        panel7.setPreferredSize(preferredSize);
                    }
                }
                Panel1.add(panel7);
                panel7.setBounds(595, 10, 600, 100);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < Panel1.getComponentCount(); i++) {
                        Rectangle bounds = Panel1.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = Panel1.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    Panel1.setMinimumSize(preferredSize);
                    Panel1.setPreferredSize(preferredSize);
                }
            }
            panel2.add(Panel1);
            Panel1.setBounds(10, 90, 1210, 235);

            //======== scrollPane1 ========
            {

                //---- table1 ----
                table1.setFont(new Font("Consolas", Font.BOLD, 14)); //NON-NLS
                scrollPane1.setViewportView(table1);
            }
            panel2.add(scrollPane1);
            scrollPane1.setBounds(5, 390, 1230, 265);

            //---- button1 ----
            button1.setIcon(new ImageIcon(getClass().getResource("/Icon/Icon/reload.png"))); //NON-NLS
            button1.setBorder(null);
            button1.setBackground(Color.white);
            button1.setFocusable(false);
            button1.setFocusPainted(false);
            button1.setBorderPainted(false);
            button1.addActionListener(e -> button1(e));
            panel2.add(button1);
            button1.setBounds(1155, 330, 60, 55);

            //---- kButton2 ----
            kButton2.setText("TH\u00d4NG TIN V\u00c9"); //NON-NLS
            kButton2.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            kButton2.setkBorderRadius(30);
            kButton2.setBorder(null);
            kButton2.addActionListener(e -> kButton2(e));
            panel2.add(kButton2);
            kButton2.setBounds(new Rectangle(new Point(20, 335), kButton2.getPreferredSize()));

            //---- kButton3 ----
            kButton3.setText("S\u1eaeP X\u1ebeP THEO GI\u00c1 TI\u1ec0N"); //NON-NLS
            kButton3.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            kButton3.setkBorderRadius(30);
            kButton3.setBorder(null);
            kButton3.addActionListener(e -> kButton3(e));
            panel2.add(kButton3);
            kButton3.setBounds(380, 335, 275, kButton3.getPreferredSize().height);

            //---- kButton4 ----
            kButton4.setText("S\u1eaeP X\u1ebeP THEO TH\u1edcI GIAN BAY"); //NON-NLS
            kButton4.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            kButton4.setkBorderRadius(30);
            kButton4.setBorder(null);
            kButton4.addActionListener(e -> kButton4(e));
            panel2.add(kButton4);
            kButton4.setBounds(760, 335, 330, kButton4.getPreferredSize().height);

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
        contentPane.add(panel2);
        panel2.setBounds(15, 15, 1240, 660);

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
    private JPanel panel2;
    private KButton kButton1;
    private JLabel label2;
    private JLabel label1;
    private KGradientPanel kGradientPanel1;
    private JPanel Panel1;
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
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel6;
    private JTextField textField10;
    private JLabel label10;
    private JLabel label11;
    private JTextField textField11;
    private JLabel label12;
    private JTextField textField12;
    private JPanel panel7;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button1;
    private KButton kButton2;
    private KButton kButton3;
    private KButton kButton4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
