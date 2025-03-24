/*
 * Created by JFormDesigner on Sat May 11 21:10:54 ICT 2024
 */

package PurInfoUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import DTBASE.Customer.Customer;
import PurchasedUI.Purchased_UI;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.demo.FullDemo;
import com.k33ptoo.components.KButton;
import com.k33ptoo.components.KGradientPanel;

import static DTBASE.DAO.DAO.getCustomer;
import javax.swing.*;


/**
 * @author Thai Ho Phu Gia
 */
public class PurchInfoUI extends JFrame {
    private int total;
    private String ticketCode;
    private Purchased_UI purchased_ui;
    public PurchInfoUI(String ticketCode, int total, Purchased_UI purchased_ui){
        initComponents();
        this.ticketCode = ticketCode;
        this.total = total;
        this.purchased_ui = purchased_ui;
        populateComboBox(total);
        initComboBox();
        populateFields(ticketCode);
        initDatePicker();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        label5.setText("Mã vé  : " + ticketCode);
    }
    public void populateComboBox(int number) {
        // Xóa tất cả các mục hiện có trong comboBox1
        comboBox1.removeAllItems();

        // Thêm các số nguyên từ 1 đến number vào comboBox1
        for (int i = 1 ; i <= number ; i++) {
            comboBox1.addItem(Integer.toString(i));
        }
    }
    private void initComboBox(){
        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    populateFields(ticketCode);
                }
            }
        });
    }
    public void populateFields(String ticketCode) {
        int selectedId = Integer.parseInt((String) comboBox1.getSelectedItem());
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
    }
    private void kButton1(ActionEvent e) {
        // TODO add your code here
        dispose();
        purchased_ui.setEnabled(true);

    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        label3 = new JLabel();
        label2 = new JLabel();
        label1 = new JLabel();
        comboBox1 = new JComboBox<>();
        kGradientPanel1 = new KGradientPanel();
        label4 = new JLabel();
        textField1 = new JTextField();
        label6 = new JLabel();
        textField3 = new JTextField();
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
        comboBox2 = new JComboBox<>();
        kButton1 = new KButton();
        label5 = new JLabel();
        label13 = new JLabel();
        textField8 = new JTextField();

        //======== this ========
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

            //---- label3 ----
            label3.setIcon(new ImageIcon(getClass().getResource("/Icon/Logo/plane_175x135.png"))); //NON-NLS
            panel1.add(label3);
            label3.setBounds(0, 0, 180, 80);

            //---- label2 ----
            label2.setText("TH\u00d4NG TIN KH\u00c1CH H\u00c0NG"); //NON-NLS
            label2.setFont(new Font("Consolas", Font.BOLD, 30)); //NON-NLS
            label2.setForeground(Color.white);
            label2.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(label2);
            label2.setBounds(0, 20, 965, label2.getPreferredSize().height);

            //---- label1 ----
            label1.setText("NG\u01af\u1edcI TH\u1ee8"); //NON-NLS
            label1.setFont(new Font("Consolas", Font.BOLD, 30)); //NON-NLS
            panel1.add(label1);
            label1.setBounds(360, 100, 185, 54);

            //---- comboBox1 ----
            comboBox1.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
                "1", //NON-NLS
                "23", //NON-NLS
                "4", //NON-NLS
                "55" //NON-NLS
            }));
            panel1.add(comboBox1);
            comboBox1.setBounds(530, 110, 75, 35);

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
            textField1.setEditable(false);
            textField1.setFont(new Font("Consolas", textField1.getFont().getStyle() | Font.BOLD, textField1.getFont().getSize() + 6)); //NON-NLS
            panel1.add(textField1);
            textField1.setBounds(175, 190, 240, 30);

            //---- label6 ----
            label6.setText("NH\u00d3M TU\u1ed4I"); //NON-NLS
            label6.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            label6.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(label6);
            label6.setBounds(50, 300, 115, 29);

            //---- textField3 ----
            textField3.setEditable(false);
            textField3.setFont(new Font("Consolas", textField3.getFont().getStyle() | Font.BOLD, textField3.getFont().getSize() + 6)); //NON-NLS
            panel1.add(textField3);
            textField3.setBounds(175, 300, 240, 30);

            //---- label7 ----
            label7.setText("NG\u00c0Y SINH"); //NON-NLS
            label7.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            label7.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(label7);
            label7.setBounds(50, 245, 120, 29);

            //---- datePicker1 ----
            datePicker1.setFont(new Font("Consolas", Font.PLAIN, 20)); //NON-NLS
            datePicker1.setEnabled(false);
            panel1.add(datePicker1);
            datePicker1.setBounds(175, 245, 270, 30);

            //---- label8 ----
            label8.setText("S\u1ed0 \u0110I\u1ec6N THO\u1ea0I"); //NON-NLS
            label8.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            label8.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(label8);
            label8.setBounds(500, 190, 160, 29);

            //---- textField4 ----
            textField4.setEditable(false);
            textField4.setFont(new Font("Consolas", textField4.getFont().getStyle() | Font.BOLD, textField4.getFont().getSize() + 6)); //NON-NLS
            panel1.add(textField4);
            textField4.setBounds(660, 190, 270, 30);

            //---- label9 ----
            label9.setText("GI\u1edaI T\u00cdNH"); //NON-NLS
            label9.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            label9.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(label9);
            label9.setBounds(500, 300, 160, 29);

            //---- label10 ----
            label10.setText("EMAIL"); //NON-NLS
            label10.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            label10.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(label10);
            label10.setBounds(55, 355, 120, 29);

            //---- textField6 ----
            textField6.setEditable(false);
            textField6.setFont(new Font("Consolas", textField6.getFont().getStyle() | Font.BOLD, textField6.getFont().getSize() + 6)); //NON-NLS
            panel1.add(textField6);
            textField6.setBounds(175, 355, 240, 30);

            //---- label11 ----
            label11.setText("\u0110\u1ecaA CH\u1ec8 NH\u00c0"); //NON-NLS
            label11.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            label11.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(label11);
            label11.setBounds(500, 245, 160, 29);

            //---- textField7 ----
            textField7.setEditable(false);
            textField7.setFont(new Font("Consolas", textField7.getFont().getStyle() | Font.BOLD, textField7.getFont().getSize() + 6)); //NON-NLS
            panel1.add(textField7);
            textField7.setBounds(660, 245, 270, 30);

            //======== panel2 ========
            {
                panel2.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.cyan, Color.blue, Color.cyan, Color.blue));
                panel2.setLayout(null);

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
            panel2.setBounds(325, 90, 320, 75);

            //---- comboBox2 ----
            comboBox2.setFont(new Font("Consolas", Font.BOLD, 16)); //NON-NLS
            comboBox2.setModel(new DefaultComboBoxModel<>(new String[] {
                "Nam", //NON-NLS
                "N\u1eef", //NON-NLS
                "Kh\u00e1c" //NON-NLS
            }));
            comboBox2.setEnabled(false);
            panel1.add(comboBox2);
            comboBox2.setBounds(660, 300, comboBox2.getPreferredSize().width, 29);

            //---- kButton1 ----
            kButton1.setText("QUAY V\u1ec0"); //NON-NLS
            kButton1.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            kButton1.setkBorderRadius(30);
            kButton1.setBorder(null);
            kButton1.addActionListener(e -> kButton1(e));
            panel1.add(kButton1);
            kButton1.setBounds(new Rectangle(new Point(760, 420), kButton1.getPreferredSize()));

            //---- label5 ----
            label5.setFont(new Font("Consolas", label5.getFont().getStyle() | Font.BOLD, label5.getFont().getSize() + 8)); //NON-NLS
            label5.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(label5);
            label5.setBounds(335, 400, 320, 65);

            //---- label13 ----
            label13.setText("GH\u1ebe"); //NON-NLS
            label13.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            label13.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(label13);
            label13.setBounds(530, 355, 105, 30);

            //---- textField8 ----
            textField8.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
            textField8.setEditable(false);
            panel1.add(textField8);
            textField8.setBounds(660, 355, 125, 30);

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
    private JLabel label3;
    private JLabel label2;
    private JLabel label1;
    private JComboBox<String> comboBox1;
    private KGradientPanel kGradientPanel1;
    private JLabel label4;
    private JTextField textField1;
    private JLabel label6;
    private JTextField textField3;
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
    private JComboBox<String> comboBox2;
    private KButton kButton1;
    private JLabel label5;
    private JLabel label13;
    private JTextField textField8;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
