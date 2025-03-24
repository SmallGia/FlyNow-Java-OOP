/*
 * Created by JFormDesigner on Sat May 11 20:13:52 ICT 2024
 */

package InfoUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.BevelBorder;

import DTBASE.Customer.Customer;
import DTBASE.DAO.DAO;
import Funtion.EmailValidator;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.demo.FullDemo;
import com.k33ptoo.components.KButton;
import com.k33ptoo.components.KGradientPanel;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static DTBASE.DAO.DAO.*;
import static Funtion.Checkseats.isValidSeat;
import static Funtion.Checkseats.isValidSeatClass;
import static Funtion.NormalizeString.normalizeString;
import static Funtion.ValidPhoneNumber.isValidPhoneNumber;

/**
 * @author Thai Ho Phu Gia
 */
public class InfoUI extends JFrame {
    private int number,num;
    private String name, birthdate, gender, phone, email, address;
    private String group;
    private String ticketCode;
    private int age;
    private LocalDate BDate;
    private boolean[] isTicketAddedToDatabase = new boolean[101];
    private int adult,child,baby;
    private String seat;
    public InfoUI(int number,String ticketCode,int adult,int child,int baby){
        initComponents();
        initDatePicker();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.number = number;
        this.ticketCode = ticketCode;
        this.adult = adult;
        this.child = child;
        this.baby = baby;
        populateComboBox(number);
        initComboBox1();
        label5.setText(ticketCode);
        label14.setText(getClassTicket(ticketCode));
        label12.setText(0+"/"+number+" thành công");
        setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
    }
    private int numAC(){
        int count = 0;
        for (int i = 1 ; i <=number ;i++)
            if (isTicketAddedToDatabase[i] == true) count++;
        return count;
    }
    public void resetComponents() {
        // Reset các trường nhập liệu
        textField1.setText("");
        textField3.setText("");
        textField4.setText("");
        textField6.setText("");
        textField7.setText("");
        textField8.setText("");
        // Reset datePicker
        datePicker1.clear();

        // Reset comboBox
     //   comboBox1.setSelectedIndex(0);
        comboBox2.setSelectedIndex(0);
    }
    public void initComboBox1() {
        final String[] previousItem = new String[1]; // Mảng để lưu giá trị trước đó của comboBox1

        ItemListener itemListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.DESELECTED) {
                    previousItem[0] = (String) e.getItem(); // Lưu giá trị trước đó khi một mục bị bỏ chọn
                } else if (e.getStateChange() == ItemEvent.SELECTED) {
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Thông tin hiện tại bạn đã lưu chưa? Nếu lưu rồi thì hãy bấm OK, nếu chưa thì bấm Hủy", "Cảnh báo", JOptionPane.OK_CANCEL_OPTION);
                    if (dialogResult == JOptionPane.CANCEL_OPTION) {
                        comboBox1.removeItemListener(this); // Xóa ItemListener
                        comboBox1.setSelectedItem(previousItem[0]); // Đặt lại giá trị cũ cho comboBox1
                        comboBox1.addItemListener(this); // Thêm lại ItemListener
                    } else {
                        int selectedId = Integer.parseInt((String) comboBox1.getSelectedItem());
                        if (isTicketAddedToDatabase[selectedId]) {
                            Customer customer = getCustomer(ticketCode, selectedId);
                            if (customer != null) {
                                textField1.setText(customer.getName());
                                datePicker1.setDate(LocalDate.parse(customer.getBirthdate()));
                                comboBox2.setSelectedItem(customer.getGender().equals("1") ? "Nam" : "Nữ");
                                textField4.setText(customer.getPhone());
                                textField6.setText(customer.getEmail());
                                textField7.setText(customer.getAddress());
                                textField3.setText(customer.getGroup());
                                textField8.setText(customer.getSeat());
                            }
                        } else {
                            resetComponents();
                        }
                    }
                }
            }
        };

        comboBox1.addItemListener(itemListener);
    }

    public void populateComboBox(int number) {
        // Xóa tất cả các mục hiện có trong comboBox1
        comboBox1.removeAllItems();

        // Thêm các số nguyên từ 1 đến number vào comboBox1
        for (int i = 1 ; i <= number ; i++) {
            comboBox1.addItem(Integer.toString(i));
        }
    }

    private void initDatePicker() {
        DatePickerSettings dateSettings = new DatePickerSettings();
        dateSettings.setFontValidDate(new Font("Consolas", 1, 20));
        dateSettings.setColor(DatePickerSettings.DateArea.DatePickerTextValidDate, new Color(0, 100, 0));
        datePicker1.setSettings(dateSettings);
        JButton button_DatePicker = datePicker1.getComponentToggleCalendarButton();
        URL dateImageURL = FullDemo.class.getResource("/images/datepickerbutton1.png");
        Image dateExampleImage = Toolkit.getDefaultToolkit().getImage(dateImageURL);
        ImageIcon dateExampleIcon = new ImageIcon(dateExampleImage);
        button_DatePicker.setText("");
        button_DatePicker.setIcon(dateExampleIcon);
        datePicker1.addDateChangeListener(e -> {
            LocalDate birthdate = e.getNewDate();
            if (birthdate != null) {
                int age = Period.between(birthdate, LocalDate.now()).getYears();
                if (age < 2) {
                    group = "Em bé";
                } else if (age <= 11) {
                    group = "Trẻ em";
                } else {
                    group = "Người lớn";
                }
                textField3.setText(group);
            }
        });
    }
    private void kButton1(ActionEvent e) {
        // TODO add your code here
        if (datePicker1.getDate() == null)
        {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày sinh", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        seat =  textField8.getText();
        if (!isValidSeat(seat))
        {
            JOptionPane.showMessageDialog(null, "Số ghế không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        name = textField1.getText();
        birthdate = datePicker1.getDate().toString();
        gender = comboBox2.getSelectedItem().toString();
        phone = textField4.getText();
        email = textField6.getText();
        address = textField7.getText();
        BDate = datePicker1.getDate();
        if (!isValidPhoneNumber(phone)){
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (name.equals("") || birthdate.equals("") || gender.equals("") || phone.equals("") || email.equals("") || address.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (BDate.isAfter(LocalDate.now())) {
            JOptionPane.showMessageDialog(null, "Ngày sinh không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (EmailValidator.validate(email) == false) {
            JOptionPane.showMessageDialog(null, "Email không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        name = normalizeString(name);
        address = normalizeString(address);
        gender = String.valueOf((gender == "Nam") ? 1 : 0);
        int id = Integer.parseInt(comboBox1.getSelectedItem().toString());
        if (checkSeatExists(ticketCode,id,seat))
        {
            JOptionPane.showMessageDialog(null, "Số ghế đã tồn tại", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!isValidSeatClass(getClassTicket(ticketCode),seat)){
            JOptionPane.showMessageDialog(null, "Số ghế không hợp lệ với hạng vé", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (isTicketAddedToDatabase[id] == false) {
            if (Objects.equals(group, "Người lớn") && adult-1<0){
                JOptionPane.showMessageDialog(null, "Số lượng người lớn đã đủ", "Thông báo", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (Objects.equals(group, "Trẻ em") && child-1<0){
                JOptionPane.showMessageDialog(null, "Số lượng trẻ em đã đủ", "Thông báo", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (Objects.equals(group, "Em bé") && baby-1<0){
                JOptionPane.showMessageDialog(null, "Số lượng em bé đã đủ", "Thông báo", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (Objects.equals(group, "Người lớn")) adult--;
            else if (Objects.equals(group, "Trẻ em")) child--;
            else baby--;
            isTicketAddedToDatabase[Integer.parseInt(comboBox1.getSelectedItem().toString())] = true;
            addNewCustomer(ticketCode,name,birthdate,gender,phone,email,address,id,group,seat);
        } else {
            String prev_group = getOldGroup(ticketCode,id);
            if (!Objects.equals(prev_group, group))
            {
                if (Objects.equals(group, "Người lớn") && adult-1<0){
                    JOptionPane.showMessageDialog(null, "Số lượng người lớn đã đủ", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (Objects.equals(group, "Trẻ em") && child-1<0){
                    JOptionPane.showMessageDialog(null, "Số lượng trẻ em đã đủ", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (Objects.equals(group, "Em bé") && baby-1<0){
                    JOptionPane.showMessageDialog(null, "Số lượng em bé đã đủ", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (Objects.equals(prev_group, "Người lớn")) adult++;
                else if (Objects.equals(prev_group, "Trẻ em")) child++;
                else baby++;
                if (Objects.equals(group, "Người lớn")) adult--;
                else if (Objects.equals(group, "Trẻ em")) child--;
                else baby--;
            }
            deleteCustomer(ticketCode,id);
            addNewCustomer(ticketCode,name,birthdate,gender,phone,email,address,id,group,seat);
        }
        label12.setText(numAC()+"/"+number+" thành công");
    }

    private void kButton2(ActionEvent e) {
        // TODO add your code here
        if (numAC()!=number) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin các vé trước khi thoát", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        } else{
            JOptionPane.showMessageDialog(null, "<html><font face='consolas' size='5'><center>Bạn đã đặt vé thành công, hãy quay về màn hình chính để xem vé đã đặt!<br/>Mã vẽ của bạn là  <font color='red'>" + ticketCode + "</font></center></font></html>", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }

    private void button1(ActionEvent e) {
        // TODO add your code here
        String message = "<html>"
                + "<h1>Số ghế có cấu trúc : A-00</h1>"
                + "<p>Với A là các chữ cái theo dưới đây và 00 là số có 2 chữ số từ 00 đến 99:</p>"
                + "<ul>"
                + "<li><strong>Hạng Phổ thông (Economy):</strong> A - F</li>"
                + "<li><strong>Hạng Phổ thông đặc biệt (Premium Economy):</strong> G - L</li>"
                + "<li><strong>Hạng Thương gia (Business):</strong> M - R</li>"
                + "<li><strong>Hạng Nhất (First Class):</strong> S - Z</li>"
                + "</ul>"
                + "</html>";
        JOptionPane.showMessageDialog(null, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void button2(ActionEvent e) {
        // TODO add your code here
        String message = "<html>"
                + "<h1 style='text-align: center;'>Thông tin vé cần điền còn lại</h1>"
                + "<ul>"
                + "<li><strong>Người lớn:</strong> " + adult + "</li>"
                + "<li><strong>Trẻ em:</strong> " + child + "</li>"
                + "<li><strong>Em bé:</strong> " + baby + "</li>"
                + "</ul>"
                + "</html>";
        JOptionPane.showMessageDialog(null, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        label14 = new JLabel();
        label5 = new JLabel();
        label3 = new JLabel();
        label2 = new JLabel();
        label1 = new JLabel();
        comboBox1 = new JComboBox<>();
        kGradientPanel1 = new KGradientPanel();
        label4 = new JLabel();
        textField1 = new JTextField();
        label6 = new JLabel();
        textField3 = new JTextField();
        kButton1 = new KButton();
        label7 = new JLabel();
        datePicker1 = new DatePicker();
        label8 = new JLabel();
        textField4 = new JTextField();
        label9 = new JLabel();
        label10 = new JLabel();
        textField6 = new JTextField();
        label11 = new JLabel();
        textField7 = new JTextField();
        panel2 = new JPanel();
        button2 = new JButton();
        comboBox2 = new JComboBox<>();
        kButton2 = new KButton();
        label12 = new JLabel();
        label13 = new JLabel();
        textField8 = new JTextField();
        button1 = new JButton();

        //======== this ========
        setResizable(false);
        setMaximumSize(new Dimension(1000, 530));
        setMinimumSize(new Dimension(1000, 530));
        setPreferredSize(new Dimension(1000, 530));
        setTitle("Fly Now  Official 1.0"); //NON-NLS
        setIconImage(new ImageIcon(getClass().getResource("/Icon/Logo/plane_whitebackground.png")).getImage()); //NON-NLS
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.cyan, Color.blue, Color.cyan, Color.blue));
            panel1.setMaximumSize(new Dimension(1000, 530));
            panel1.setMinimumSize(new Dimension(1000, 530));
            panel1.setPreferredSize(new Dimension(1000, 530));
            panel1.setLayout(null);

            //---- label14 ----
            label14.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            label14.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(label14);
            label14.setBounds(205, 415, 231, 51);

            //---- label5 ----
            label5.setFont(new Font("Consolas", Font.BOLD, 30)); //NON-NLS
            label5.setHorizontalAlignment(SwingConstants.TRAILING);
            label5.setForeground(Color.white);
            panel1.add(label5);
            label5.setBounds(780, 5, 170, 65);

            //---- label3 ----
            label3.setIcon(new ImageIcon(getClass().getResource("/Icon/Logo/plane_175x135.png"))); //NON-NLS
            panel1.add(label3);
            label3.setBounds(0, 0, 180, 80);

            //---- label2 ----
            label2.setText("NH\u1eacP TH\u00d4NG TIN"); //NON-NLS
            label2.setFont(new Font("Consolas", Font.BOLD, 30)); //NON-NLS
            label2.setForeground(Color.white);
            panel1.add(label2);
            label2.setBounds(new Rectangle(new Point(360, 20), label2.getPreferredSize()));

            //---- label1 ----
            label1.setText("NG\u01af\u1edcI TH\u1ee8"); //NON-NLS
            label1.setFont(new Font("Consolas", Font.BOLD, 30)); //NON-NLS
            panel1.add(label1);
            label1.setBounds(345, 100, 185, 54);

            //---- comboBox1 ----
            comboBox1.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
                "1", //NON-NLS
                "23", //NON-NLS
                "4", //NON-NLS
                "55" //NON-NLS
            }));
            panel1.add(comboBox1);
            comboBox1.setBounds(515, 110, 75, 35);

            //---- kGradientPanel1 ----
            kGradientPanel1.setkStartColor(Color.cyan);
            kGradientPanel1.setkEndColor(new Color(0x248df3));
            panel1.add(kGradientPanel1);
            kGradientPanel1.setBounds(0, 0, 965, 75);

            //---- label4 ----
            label4.setText("H\u1ecc V\u00c0 T\u00caN"); //NON-NLS
            label4.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            label4.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(label4);
            label4.setBounds(50, 185, 120, 29);

            //---- textField1 ----
            textField1.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
            panel1.add(textField1);
            textField1.setBounds(175, 185, 240, 30);

            //---- label6 ----
            label6.setText("NH\u00d3M TU\u1ed4I"); //NON-NLS
            label6.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            label6.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(label6);
            label6.setBounds(50, 300, 115, 29);

            //---- textField3 ----
            textField3.setEditable(false);
            textField3.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
            panel1.add(textField3);
            textField3.setBounds(175, 300, 240, 30);

            //---- kButton1 ----
            kButton1.setText("L\u01afU TH\u00d4NG TIN"); //NON-NLS
            kButton1.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            kButton1.setkBorderRadius(30);
            kButton1.setBorder(null);
            kButton1.addActionListener(e -> kButton1(e));
            panel1.add(kButton1);
            kButton1.setBounds(new Rectangle(new Point(740, 415), kButton1.getPreferredSize()));

            //---- label7 ----
            label7.setText("NG\u00c0Y SINH"); //NON-NLS
            label7.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            label7.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(label7);
            label7.setBounds(50, 245, 120, 29);

            //---- datePicker1 ----
            datePicker1.setFont(new Font("Consolas", Font.PLAIN, 20)); //NON-NLS
            panel1.add(datePicker1);
            datePicker1.setBounds(175, 245, 270, 30);

            //---- label8 ----
            label8.setText("S\u1ed0 \u0110I\u1ec6N THO\u1ea0I"); //NON-NLS
            label8.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            label8.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(label8);
            label8.setBounds(500, 185, 160, 29);

            //---- textField4 ----
            textField4.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
            panel1.add(textField4);
            textField4.setBounds(660, 185, 270, 30);

            //---- label9 ----
            label9.setText("GI\u1edaI T\u00cdNH"); //NON-NLS
            label9.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            label9.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(label9);
            label9.setBounds(500, 305, 160, 24);

            //---- label10 ----
            label10.setText("EMAIL"); //NON-NLS
            label10.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            label10.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(label10);
            label10.setBounds(55, 355, 120, 29);

            //---- textField6 ----
            textField6.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
            panel1.add(textField6);
            textField6.setBounds(175, 355, 240, 30);

            //---- label11 ----
            label11.setText("\u0110\u1ecaA CH\u1ec8 NH\u00c0"); //NON-NLS
            label11.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            label11.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(label11);
            label11.setBounds(500, 245, 160, 29);

            //---- textField7 ----
            textField7.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
            panel1.add(textField7);
            textField7.setBounds(660, 245, 265, 30);

            //======== panel2 ========
            {
                panel2.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.cyan, Color.blue, Color.cyan, Color.blue));
                panel2.setLayout(null);

                //---- button2 ----
                button2.setBorder(null);
                button2.setRequestFocusEnabled(false);
                button2.setIcon(new ImageIcon(getClass().getResource("/Icon/Icon/information-button.png"))); //NON-NLS
                button2.setBackground(new Color(0xf5f5f5));
                button2.setFocusable(false);
                button2.addActionListener(e -> button2(e));
                panel2.add(button2);
                button2.setBounds(285, 15, 50, 40);

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
            panel2.setBounds(310, 90, 345, 75);

            //---- comboBox2 ----
            comboBox2.setFont(new Font("Consolas", Font.BOLD, 16)); //NON-NLS
            comboBox2.setModel(new DefaultComboBoxModel<>(new String[] {
                "Nam", //NON-NLS
                "N\u1eef" //NON-NLS
            }));
            panel1.add(comboBox2);
            comboBox2.setBounds(660, 300, comboBox2.getPreferredSize().width, 29);

            //---- kButton2 ----
            kButton2.setText("X\u00c1C NH\u1eacN \u0110\u1eb6T"); //NON-NLS
            kButton2.setkBorderRadius(30);
            kButton2.setBorder(null);
            kButton2.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            kButton2.addActionListener(e -> kButton2(e));
            panel1.add(kButton2);
            kButton2.setBounds(new Rectangle(new Point(15, 415), kButton2.getPreferredSize()));

            //---- label12 ----
            label12.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            label12.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(label12);
            label12.setBounds(455, 415, 260, 50);

            //---- label13 ----
            label13.setText("GH\u1ebe"); //NON-NLS
            label13.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            label13.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(label13);
            label13.setBounds(530, 355, 105, 30);

            //---- textField8 ----
            textField8.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
            panel1.add(textField8);
            textField8.setBounds(660, 355, 125, 30);

            //---- button1 ----
            button1.setIcon(new ImageIcon(getClass().getResource("/Icon/Icon/information-button.png"))); //NON-NLS
            button1.setBorder(null);
            button1.setBackground(new Color(0xf5f5f5));
            button1.setRequestFocusEnabled(false);
            button1.addActionListener(e -> button1(e));
            panel1.add(button1);
            button1.setBounds(805, 350, 40, 35);

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
        panel1.setBounds(10, 10, 965, 475);

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
    private JLabel label14;
    private JLabel label5;
    private JLabel label3;
    private JLabel label2;
    private JLabel label1;
    private JComboBox<String> comboBox1;
    private KGradientPanel kGradientPanel1;
    private JLabel label4;
    private JTextField textField1;
    private JLabel label6;
    private JTextField textField3;
    private KButton kButton1;
    private JLabel label7;
    private DatePicker datePicker1;
    private JLabel label8;
    private JTextField textField4;
    private JLabel label9;
    private JLabel label10;
    private JTextField textField6;
    private JLabel label11;
    private JTextField textField7;
    private JPanel panel2;
    private JButton button2;
    private JComboBox<String> comboBox2;
    private KButton kButton2;
    private JLabel label12;
    private JLabel label13;
    private JTextField textField8;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
