/*
 * Created by JFormDesigner on Sun May 05 23:29:59 ICT 2024
 */

package BookingUI;

import MenuUI.Menu_UI;
import SummitUI.SummitUi;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.demo.FullDemo;
import com.k33ptoo.components.KButton;
import com.k33ptoo.components.KGradientPanel;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.text.Collator;
import java.time.LocalDate;
import java.util.List;
import java.util.*;

import static DTBASE.DAO.DAO.getUniqueFromList;
import static DTBASE.DAO.DAO.getUniqueToLocationList;

/**
 * @author Thai Ho Phu Gia
 */
public class Booking_UI extends JFrame {
    private String username;
    JFrame MAP = new JFrame();
    private Menu_UI menuUI;
    private SummitUi summitUi;

    public Booking_UI(String username, Menu_UI menuUI) {
        initComponents();
        initDatePicker();
        initMap();
        initCombobox();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.username = username;
        this.menuUI = menuUI;
        summitUi = new SummitUi(username, this);
    }

    private void initCombobox() {
        DefaultComboBoxModel<String> model;
        List<String> fromLocationList = null;
        List<String> toLocationList = null;
        try {
            fromLocationList = getUniqueFromList();
            toLocationList = getUniqueToLocationList();
            Collator collator = Collator.getInstance(new Locale("vi", "VN"));
            Collections.sort(fromLocationList, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return collator.compare(o1, o2);
                }
            });
            Collections.sort(toLocationList, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return collator.compare(o1, o2);
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        model = new DefaultComboBoxModel<>(fromLocationList.toArray(new String[0]));
        comboBox1.setModel(model);
        model = new DefaultComboBoxModel<>(toLocationList.toArray(new String[0]));
        comboBox2.setModel(model);
    }

    private void initDatePicker() {
        DatePickerSettings dateSettings = new DatePickerSettings();
        dateSettings.setFontValidDate(new Font("Consolas", 1, 20));
        dateSettings.setColor(DatePickerSettings.DateArea.DatePickerTextValidDate, new Color(0, 100, 0));
        datePicker1.setSettings(dateSettings);
        dateSettings = new DatePickerSettings();
        dateSettings.setFontValidDate(new Font("Consolas", 1, 20));
        dateSettings.setColor(DatePickerSettings.DateArea.DatePickerTextValidDate, new Color(0, 100, 0));
        datePicker2.setSettings(dateSettings);
        JButton button_DatePicker = datePicker1.getComponentToggleCalendarButton();
        URL dateImageURL = FullDemo.class.getResource("/images/datepickerbutton1.png");
        Image dateExampleImage = Toolkit.getDefaultToolkit().getImage(dateImageURL);
        ImageIcon dateExampleIcon = new ImageIcon(dateExampleImage);
        button_DatePicker.setText("");
        button_DatePicker.setIcon(dateExampleIcon);
        button_DatePicker = datePicker2.getComponentToggleCalendarButton();
        button_DatePicker.setText("");
        button_DatePicker.setIcon(dateExampleIcon);
    }

    void initMap() {
        MAP.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        MAP.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MAP.setVisible(false); // Thay đổi ở đây
            }
        });
        // Phần còn lại của mã...
        MAP.setSize(800, 600); // Set the size of the frame

        // Create a JFXPanel
        final JFXPanel fxPanel = new JFXPanel();

        // Add the JFXPanel to the JFrame
        MAP.add(fxPanel);

        // Create JavaFX scene on the JavaFX application thread
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Create a new WebView every time the button is clicked
                WebView webView = new WebView();
                webView.getEngine().load("https://www.google.com/maps");

                fxPanel.setScene(new Scene(webView));
            }
        });
    }
    public void resetComponents() {
        // Reset all the components to their default values
        comboBox1.setSelectedIndex(0);
        comboBox2.setSelectedIndex(0);
        spinner1.setValue(0);
        spinner2.setValue(0);
        spinner3.setValue(0);
        CheckBox_BackPlane.setSelected(false);
        comboBox3.setSelectedIndex(0);
        datePicker1.setDate(null);
        datePicker2.setDate(null);
        label11.setText("");
    }
    public void resetMap() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Create a new WebView every time the button is clicked
                WebView webView = new WebView();
                webView.getEngine().load("https://www.google.com/maps");

                // Get the JFXPanel from the MAP JFrame
                JFXPanel fxPanel = (JFXPanel) MAP.getContentPane().getComponent(0);

                // Set the new scene to the JFXPanel
                fxPanel.setScene(new Scene(webView));
            }
        });
    }

    private void _BackPlane(ActionEvent e) {
        // TODO add your code here
        if (CheckBox_BackPlane.isSelected()) {
            datePicker2.setVisible(true);
        } else
            datePicker2.setVisible(false);
    }

    private void Button_FindPlane(ActionEvent e) {
        // TODO add your code here
        String from = (String) comboBox1.getSelectedItem();
        String to = (String) comboBox2.getSelectedItem();
        if (Objects.equals(from, to)) {
            label11.setText("Điểm đến và điểm khởi hành không thể trùng nhau");
            return;
        }
        int adult = (int) spinner1.getValue();
        int child = (int) spinner2.getValue();
        int baby = (int) spinner3.getValue();
        if (adult<0 || child < 0 || baby < 0)
        {
            label11.setText("Số lượng khách không thể âm");
            return;
        }
        if (adult == 0 && child == 0 && baby == 0) {
            label11.setText("Số lượng khách không được để trống");
            return;
        }
        if (adult ==0 && (child > 0 || baby > 0))
        {
            label11.setText("Trẻ em và em bé phải đi cùng người lớn");
            return;
        }
        if (datePicker1.getDate() == null)
        {
            label11.setText("Ngày đi không được để trống");
            return;
        }
        if (datePicker1.getDate().isBefore(LocalDate.now())) {
            label11.setText("Ngày đi không thể trước ngày hiện tại");
            return;
        }
        boolean comeback =  CheckBox_BackPlane.isSelected();
        if (comeback) {
            if (datePicker2.getDate() == null) {
                label11.setText("Ngày về không được để trống");
                return;
            }
            if (datePicker2.getDate().compareTo(datePicker1.getDate()) < 0) {
                label11.setText("Ngày về không thể trước ngày đi");
                return;
            }
        }
        this.setVisible(false);
        summitUi.setFrom(from);
        summitUi.setTo(to);
        summitUi.setTicket(adult + child + baby);
        summitUi.setComback(comeback);
        summitUi.setTimearrive(datePicker1.getDate());
        summitUi.setTimeback(datePicker2.getDate());
        summitUi.setTicketClass(comboBox3.getSelectedItem().toString());
        summitUi.setAdult(adult);
        summitUi.setChild(child);
        summitUi.setBaby(baby);
        summitUi.setVisible(true);

    }

    private void kButton1(ActionEvent e) {
        // TODO add your code here
        MAP.setVisible(true);
        // resetMap();
    }

    private void kButton2(ActionEvent e) {
        // TODO add your code here
        this.setVisible(false);
        resetComponents();
        label11.setText("");
        menuUI.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        kButton2 = new KButton();
        kButton1 = new KButton();
        label10 = new JLabel();
        label1 = new JLabel();
        kGradientPanel1 = new KGradientPanel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        comboBox1 = new JComboBox();
        comboBox2 = new JComboBox();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        spinner1 = new JSpinner();
        spinner2 = new JSpinner();
        spinner3 = new JSpinner();
        label8 = new JLabel();
        CheckBox_BackPlane = new JCheckBox();
        label9 = new JLabel();
        comboBox3 = new JComboBox<>();
        Button_FindPlane = new KButton();
        datePicker1 = new DatePicker();
        datePicker2 = new DatePicker();
        label11 = new JLabel();

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
            panel1.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.blue, Color.cyan, Color.cyan, Color.blue));
            panel1.setLayout(null);

            //---- kButton2 ----
            kButton2.setText("TR\u1ede L\u1ea0I M\u00c0N H\u00ccNH CH\u00cdNH"); //NON-NLS
            kButton2.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            kButton2.addActionListener(e -> kButton2(e));
            panel1.add(kButton2);
            kButton2.setBounds(20, 510, 315, kButton2.getPreferredSize().height);

            //---- kButton1 ----
            kButton1.setText("GOOGLE MAP"); //NON-NLS
            kButton1.setFont(new Font("Segoe UI", Font.BOLD, 20)); //NON-NLS
            kButton1.setkBorderRadius(30);
            kButton1.setBorder(null);
            kButton1.addActionListener(e -> kButton1(e));
            panel1.add(kButton1);
            kButton1.setBounds(new Rectangle(new Point(915, 455), kButton1.getPreferredSize()));

            //---- label10 ----
            label10.setIcon(new ImageIcon(getClass().getResource("/Icon/Logo/plane_175x135.png"))); //NON-NLS
            panel1.add(label10);
            label10.setBounds(5, -10, 180, 95);

            //---- label1 ----
            label1.setText("V\u00e9 m\u00e1y bay"); //NON-NLS
            label1.setFont(new Font("Consolas", Font.BOLD, 26)); //NON-NLS
            label1.setForeground(Color.white);
            panel1.add(label1);
            label1.setBounds(905, 15, 190, 35);

            //---- kGradientPanel1 ----
            kGradientPanel1.setkStartColor(Color.cyan);
            kGradientPanel1.setkEndColor(new Color(0x0070ec));
            panel1.add(kGradientPanel1);
            kGradientPanel1.setBounds(0, 0, 1125, 70);

            //---- label2 ----
            label2.setText("T\u1eeb"); //NON-NLS
            label2.setFont(new Font("Consolas", Font.PLAIN, 20)); //NON-NLS
            panel1.add(label2);
            label2.setBounds(35, 95, 75, label2.getPreferredSize().height);

            //---- label3 ----
            label3.setText("\u0110\u1ebfn"); //NON-NLS
            label3.setFont(new Font("Consolas", Font.PLAIN, 20)); //NON-NLS
            panel1.add(label3);
            label3.setBounds(375, 95, 75, 24);

            //---- label4 ----
            label4.setText("S\u1ed1 h\u00e0nh kh\u00e1ch"); //NON-NLS
            label4.setFont(new Font("Consolas", Font.PLAIN, 20)); //NON-NLS
            panel1.add(label4);
            label4.setBounds(745, 95, 150, 24);

            //---- comboBox1 ----
            comboBox1.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
            panel1.add(comboBox1);
            comboBox1.setBounds(30, 130, 300, 60);

            //---- comboBox2 ----
            comboBox2.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
            panel1.add(comboBox2);
            comboBox2.setBounds(370, 130, 300, 60);

            //---- label5 ----
            label5.setText("Ng\u01b0\u1eddi l\u1edbn (t\u1eeb 12 tu\u1ed5i)"); //NON-NLS
            label5.setFont(new Font("Consolas", Font.BOLD, 14)); //NON-NLS
            panel1.add(label5);
            label5.setBounds(745, 140, 195, 24);

            //---- label6 ----
            label6.setText("Tr\u1ebb em (t\u1eeb 2-11 tu\u1ed5i)"); //NON-NLS
            label6.setFont(new Font("Consolas", Font.BOLD, 14)); //NON-NLS
            panel1.add(label6);
            label6.setBounds(745, 180, 195, 24);

            //---- label7 ----
            label7.setText("Em b\u00e9 (d\u01b0\u1edbi 2 tu\u1ed5i)"); //NON-NLS
            label7.setFont(new Font("Consolas", Font.BOLD, 14)); //NON-NLS
            panel1.add(label7);
            label7.setBounds(745, 220, 195, 24);

            //---- spinner1 ----
            spinner1.setFont(new Font("Consolas", Font.BOLD, 14)); //NON-NLS
            panel1.add(spinner1);
            spinner1.setBounds(955, 140, 75, 25);

            //---- spinner2 ----
            spinner2.setFont(new Font("Consolas", Font.BOLD, 14)); //NON-NLS
            panel1.add(spinner2);
            spinner2.setBounds(955, 180, 75, 24);

            //---- spinner3 ----
            spinner3.setFont(new Font("Consolas", Font.BOLD, 14)); //NON-NLS
            panel1.add(spinner3);
            spinner3.setBounds(955, 220, 75, 24);

            //---- label8 ----
            label8.setText("Ng\u00e0y \u0111i"); //NON-NLS
            label8.setFont(new Font("Consolas", Font.PLAIN, 20)); //NON-NLS
            panel1.add(label8);
            label8.setBounds(35, 315, 135, 24);

            //---- CheckBox_BackPlane ----
            CheckBox_BackPlane.setText("Kh\u1ee9 h\u1ed3i"); //NON-NLS
            CheckBox_BackPlane.setFont(new Font("Consolas", Font.PLAIN, 20)); //NON-NLS
            CheckBox_BackPlane.setBorder(null);
            CheckBox_BackPlane.setVisible(false);
            CheckBox_BackPlane.addActionListener(e -> _BackPlane(e));
            panel1.add(CheckBox_BackPlane);
            CheckBox_BackPlane.setBounds(new Rectangle(new Point(380, 315), CheckBox_BackPlane.getPreferredSize()));

            //---- label9 ----
            label9.setText("H\u1ea1ng gh\u1ebf"); //NON-NLS
            label9.setFont(new Font("Consolas", Font.PLAIN, 20)); //NON-NLS
            panel1.add(label9);
            label9.setBounds(745, 315, 135, 24);

            //---- comboBox3 ----
            comboBox3.setFont(new Font("Consolas", Font.PLAIN, 18)); //NON-NLS
            comboBox3.setModel(new DefaultComboBoxModel<>(new String[] {
                "Ph\u1ed5 th\u00f4ng", //NON-NLS
                "Ph\u1ed5 th\u00f4ng \u0111\u1eb7c bi\u1ec7t", //NON-NLS
                "Th\u01b0\u01a1ng gia", //NON-NLS
                "H\u1ea1ng nh\u1ea5t" //NON-NLS
            }));
            panel1.add(comboBox3);
            comboBox3.setBounds(745, 365, 280, 39);

            //---- Button_FindPlane ----
            Button_FindPlane.setText("T\u00ecm chuy\u1ebfn bay"); //NON-NLS
            Button_FindPlane.setkAllowGradient(false);
            Button_FindPlane.setkBackGroundColor(new Color(0xff6464));
            Button_FindPlane.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
            Button_FindPlane.setkBorderRadius(30);
            Button_FindPlane.setBorder(null);
            Button_FindPlane.addActionListener(e -> Button_FindPlane(e));
            panel1.add(Button_FindPlane);
            Button_FindPlane.setBounds(new Rectangle(new Point(915, 520), Button_FindPlane.getPreferredSize()));

            //---- datePicker1 ----
            datePicker1.setFont(new Font("Consolas", Font.PLAIN, 20)); //NON-NLS
            panel1.add(datePicker1);
            datePicker1.setBounds(35, 370, 265, 40);

            //---- datePicker2 ----
            datePicker2.setVisible(false);
            panel1.add(datePicker2);
            datePicker2.setBounds(385, 365, 265, 40);

            //---- label11 ----
            label11.setHorizontalAlignment(SwingConstants.CENTER);
            label11.setFont(new Font("Consolas", Font.BOLD, 30)); //NON-NLS
            label11.setForeground(Color.red);
            panel1.add(label11);
            label11.setBounds(0, 435, 915, 60);

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
        panel1.setBounds(95, 45, 1125, 580);

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
    private KButton kButton2;
    private KButton kButton1;
    private JLabel label10;
    private JLabel label1;
    private KGradientPanel kGradientPanel1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JSpinner spinner3;
    private JLabel label8;
    private JCheckBox CheckBox_BackPlane;
    private JLabel label9;
    private JComboBox<String> comboBox3;
    private KButton Button_FindPlane;
    private DatePicker datePicker1;
    private DatePicker datePicker2;
    private JLabel label11;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
