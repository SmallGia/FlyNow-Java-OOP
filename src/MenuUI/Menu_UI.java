/*
 * Created by JFormDesigner on Thu May 09 08:39:00 ICT 2024
 */

package MenuUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

import BookingUI.Booking_UI;
import Contact.ContactUI;
import ProfileUI.Profile_UI;
import PurchasedUI.Purchased_UI;
import com.k33ptoo.components.KButton;
import com.k33ptoo.components.KGradientPanel;

/**
 * @author Thai Ho Phu Gia
 */
public class Menu_UI extends JFrame {
    private int xPos,direction;
    private String username;
    private Booking_UI BookingUI;
    private Profile_UI profileUI;
    private Purchased_UI purchasedUI;
    private ContactUI contactUI;
    public Menu_UI(String username,String password) {
        this.username=username;
        initComponents();
        this.BookingUI = new Booking_UI(username,this);
        label8.setText(username);
        this.profileUI = new Profile_UI(username,password,this);
        this.purchasedUI = new Purchased_UI(username,this);
        this.contactUI= new ContactUI(this);
        initlable();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private void initlable(){
        xPos = this.getWidth();
        direction = -2;

        Timer timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xPos += direction;
                if (xPos <= -label4.getWidth()) {
                    xPos = getWidth();
                }
                label4.setLocation(xPos, label4.getLocation().y);
            }
        });
        timer.start();
    }
    private void button3(ActionEvent e) {
        // TODO add your code here
        BookingUI.setVisible(true);
        this.setVisible(false);
    }

    private void button1(ActionEvent e) {
        // TODO add your code here
        profileUI.setVisible(true);
        this.setVisible(false);

    }

    private void button2(ActionEvent e) {
        // TODO add your code here
        purchasedUI.resetComponents();
        purchasedUI.setVisible(true);
        this.setVisible(false);
    }

    private void button4(ActionEvent e) {
        // TODO add your code here
        contactUI.setVisible(true);
        this.setVisible(false);

    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        label8 = new JLabel();
        label2 = new JLabel();
        kGradientPanel1 = new KGradientPanel();
        label1 = new JLabel();
        button1 = new JButton();
        label3 = new JLabel();
        button2 = new JButton();
        label5 = new JLabel();
        button3 = new JButton();
        label6 = new JLabel();
        button4 = new JButton();
        label7 = new JLabel();
        label4 = new JLabel();
        kGradientPanel2 = new KGradientPanel();

        //======== this ========
        setResizable(false);
        setMaximumSize(new Dimension(1280, 720));
        setMinimumSize(new Dimension(1280, 720));
        setPreferredSize(new Dimension(1280, 720));
        setBackground(Color.black);
        setTitle("Fly Now  Official 1.0"); //NON-NLS
        setIconImage(new ImageIcon(getClass().getResource("/Icon/Logo/plane_whitebackground.png")).getImage()); //NON-NLS
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setLayout(null);

            //---- label8 ----
            label8.setText("USERNAME"); //NON-NLS
            label8.setFont(new Font("Consolas", Font.BOLD, 30)); //NON-NLS
            label8.setHorizontalAlignment(SwingConstants.CENTER);
            label8.setForeground(new Color(0xffcccc));
            panel1.add(label8);
            label8.setBounds(0, 90, 1280, 35);

            //---- label2 ----
            label2.setText("CH\u00c0O M\u1eeaNG \u0110\u00c3 TR\u1ede L\u1ea0I"); //NON-NLS
            label2.setFont(new Font("Consolas", Font.BOLD, 40)); //NON-NLS
            label2.setHorizontalAlignment(SwingConstants.CENTER);
            label2.setForeground(new Color(0xffcccc));
            panel1.add(label2);
            label2.setBounds(0, 0, 1280, 90);

            //---- kGradientPanel1 ----
            kGradientPanel1.setkStartColor(Color.cyan);
            kGradientPanel1.setkEndColor(new Color(0x9999ff));
            panel1.add(kGradientPanel1);
            kGradientPanel1.setBounds(0, 0, 1280, 135);

            //---- label1 ----
            label1.setIcon(new ImageIcon(getClass().getResource("/Icon/Logo/plane.png"))); //NON-NLS
            panel1.add(label1);
            label1.setBounds(395, 170, 475, 325);

            //---- button1 ----
            button1.setIcon(new ImageIcon(getClass().getResource("/Icon/Icon/profile3.png"))); //NON-NLS
            button1.setBorder(LineBorder.createBlackLineBorder());
            button1.setBackground(Color.white);
            button1.setToolTipText("profile"); //NON-NLS
            button1.addActionListener(e -> button1(e));
            panel1.add(button1);
            button1.setBounds(220, 200, 100, 100);

            //---- label3 ----
            label3.setText("TH\u00d4NG TIN"); //NON-NLS
            label3.setFont(new Font("Consolas", Font.BOLD, 30)); //NON-NLS
            label3.setBorder(null);
            panel1.add(label3);
            label3.setBounds(335, 230, 160, label3.getPreferredSize().height);

            //---- button2 ----
            button2.setIcon(new ImageIcon(getClass().getResource("/Icon/Icon/Thi\u1ebft k\u1ebf ch\u01b0a c\u00f3 t\u00ean (2)/pur.png"))); //NON-NLS
            button2.setBorder(LineBorder.createBlackLineBorder());
            button2.addActionListener(e -> button2(e));
            panel1.add(button2);
            button2.setBounds(220, 415, 100, 100);

            //---- label5 ----
            label5.setText("V\u00c9 \u0110\u00c3 \u0110\u1eb6T"); //NON-NLS
            label5.setFont(new Font("Consolas", Font.BOLD, 30)); //NON-NLS
            panel1.add(label5);
            label5.setBounds(345, 450, 175, label5.getPreferredSize().height);

            //---- button3 ----
            button3.setFont(new Font("Consolas", Font.BOLD, 30)); //NON-NLS
            button3.setIcon(new ImageIcon(getClass().getResource("/Icon/Icon/Thi\u1ebft k\u1ebf ch\u01b0a c\u00f3 t\u00ean (2)/getticket.png"))); //NON-NLS
            button3.setBorder(LineBorder.createBlackLineBorder());
            button3.addActionListener(e -> button3(e));
            panel1.add(button3);
            button3.setBounds(790, 200, 100, 100);

            //---- label6 ----
            label6.setText("\u0110\u1eb6T V\u00c9"); //NON-NLS
            label6.setFont(new Font("Consolas", Font.BOLD, 30)); //NON-NLS
            panel1.add(label6);
            label6.setBounds(new Rectangle(new Point(915, 235), label6.getPreferredSize()));

            //---- button4 ----
            button4.setBorder(LineBorder.createBlackLineBorder());
            button4.setIcon(new ImageIcon(getClass().getResource("/Icon/Icon/Thi\u1ebft k\u1ebf ch\u01b0a c\u00f3 t\u00ean (2)/contact.png"))); //NON-NLS
            button4.addActionListener(e -> button4(e));
            panel1.add(button4);
            button4.setBounds(795, 415, 100, 100);

            //---- label7 ----
            label7.setText("LI\u00caN H\u1ec6"); //NON-NLS
            label7.setFont(new Font("Consolas", Font.BOLD, 30)); //NON-NLS
            panel1.add(label7);
            label7.setBounds(new Rectangle(new Point(910, 445), label7.getPreferredSize()));

            //---- label4 ----
            label4.setText("T\u1eeb \u0110\u00f4ng Nam \u00c1 \u0110\u1ebfn Th\u1ebf Gi\u1edbi Trong T\u1ea7m Tay B\u1ea1n"); //NON-NLS
            label4.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 30)); //NON-NLS
            label4.setHorizontalAlignment(SwingConstants.CENTER);
            label4.setForeground(new Color(0xffcccc));
            panel1.add(label4);
            label4.setBounds(0, 625, 865, 70);

            //---- kGradientPanel2 ----
            kGradientPanel2.setkStartColor(Color.cyan);
            kGradientPanel2.setkEndColor(new Color(0x6666ff));
            panel1.add(kGradientPanel2);
            kGradientPanel2.setBounds(0, 620, 1285, 75);

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
        panel1.setBounds(0, 0, 1280, 695);

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
    private JLabel label8;
    private JLabel label2;
    private KGradientPanel kGradientPanel1;
    private JLabel label1;
    private JButton button1;
    private JLabel label3;
    private JButton button2;
    private JLabel label5;
    private JButton button3;
    private JLabel label6;
    private JButton button4;
    private JLabel label7;
    private JLabel label4;
    private KGradientPanel kGradientPanel2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
