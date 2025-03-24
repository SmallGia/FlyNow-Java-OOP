package LoginUI;

import java.awt.event.ActionEvent;

import CreateAccUI.CreateAcc_UI;
import ForgetAccUI.ForgetUI;
import com.k33ptoo.components.KButton;

import javax.imageio.plugins.tiff.ExifTIFFTagSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
/*
 * Created by JFormDesigner on Wed Mar 27 15:29:21 ICT 2024
 */



/**
 * @author Thai Ho Phu Gia
 */
public class Login_Form_UI extends JFrame {
    public Login_Form_UI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        getRootPane().setDefaultButton(Button_Login); // Set Button_Login as the default button
    }

    private void button1MousePressed(MouseEvent e) {
        // TODO add your code here
    }

    private void button1MouseReleased(MouseEvent e) {
        // TODO add your code here
    }

    private void Button_Login(ActionEvent e) {
        // TODO add your code here
        String username = Text_UserName.getText();
        String password = Text_Pass.getText();
        if (username.equals("") || password.equals("")) {
            Notice_ErrorLogin.setVisible(true);
            Notce_ErrorLogin2.setVisible(true);
        } else {
            if (username.equals("admin") && password.equals("admin")) {
       //         new Main_UI().setVisible(true);
                this.dispose();
            } else {
                Notice_ErrorLogin.setVisible(true);
                Notce_ErrorLogin2.setVisible(true);
            }
        }
    }

    private void Button_Register(ActionEvent e) {
        // TODO add your code here
        new CreateAcc_UI().setVisible(true);
        this.dispose();
    }

    private void Button_Forget(ActionEvent e) {
        // TODO add your code here
        new ForgetUI().setVisible(true);
        this.dispose();
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        panel2 = new JPanel();
        label1 = new JLabel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        Label_Notice1 = new JLabel();
        Label_Logo = new JLabel();
        Text_UserName = new JFormattedTextField();
        Label_UserName = new JLabel();
        Text_Pass = new JPasswordField();
        Label_Pass = new JLabel();
        Button_Login = new KButton();
        Label_Notice2 = new JLabel();
        Button_Register = new KButton();
        Notice_ErrorLogin = new JLabel();
        Notce_ErrorLogin2 = new JLabel();
        Button_Forget = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(1005, 500));
        setPreferredSize(new Dimension(1005, 500));
        setMaximumSize(new Dimension(1005, 500));
        setResizable(false);
        setTitle("Fly Now-Pre-alpha"); //NON-NLS
        setIconImage(new ImageIcon(getClass().getResource("/Icon/Logo/plane_whitebackground.png")).getImage()); //NON-NLS
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setPreferredSize(new Dimension(1280, 720));
            panel1.setBackground(Color.white);
            panel1.setLayout(null);

            //======== panel2 ========
            {
                panel2.setLayout(null);

                //---- label1 ----
                label1.setIcon(new ImageIcon(getClass().getResource("/background/plane2.gif"))); //NON-NLS
                panel2.add(label1);
                label1.setBounds(0, 0, 480, 475);

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
            panel2.setBounds(0, 0, 480, 475);

            //======== panel3 ========
            {
                panel3.setBackground(Color.white);
                panel3.setLayout(null);

                //======== panel4 ========
                {
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
                panel3.add(panel4);
                panel4.setBounds(new Rectangle(new Point(165, 75), panel4.getPreferredSize()));

                //---- Label_Notice1 ----
                Label_Notice1.setText("WELCOME"); //NON-NLS
                Label_Notice1.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 26)); //NON-NLS
                Label_Notice1.setHorizontalAlignment(SwingConstants.CENTER);
                Label_Notice1.setVisible(false);
                panel3.add(Label_Notice1);
                Label_Notice1.setBounds(5, 415, 120, 50);

                //---- Label_Logo ----
                Label_Logo.setIcon(new ImageIcon(getClass().getResource("/Icon/Logo/plane_300x300.png"))); //NON-NLS
                panel3.add(Label_Logo);
                Label_Logo.setBounds(110, 0, 290, 180);

                //---- Text_UserName ----
                Text_UserName.setFont(new Font("Consolas", Font.PLAIN, 18)); //NON-NLS
                panel3.add(Text_UserName);
                Text_UserName.setBounds(175, 190, 300, 25);

                //---- Label_UserName ----
                Label_UserName.setText("T\u00ean \u0111\u0103ng nh\u1eadp:"); //NON-NLS
                Label_UserName.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
                panel3.add(Label_UserName);
                Label_UserName.setBounds(new Rectangle(new Point(25, 190), Label_UserName.getPreferredSize()));

                //---- Text_Pass ----
                Text_Pass.setFont(new Font("Consolas", Font.PLAIN, 18)); //NON-NLS
                panel3.add(Text_Pass);
                Text_Pass.setBounds(175, 230, 300, 25);

                //---- Label_Pass ----
                Label_Pass.setText("M\u1eadt kh\u1ea9u:"); //NON-NLS
                Label_Pass.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
                panel3.add(Label_Pass);
                Label_Pass.setBounds(new Rectangle(new Point(75, 230), Label_Pass.getPreferredSize()));

                //---- Button_Login ----
                Button_Login.setText("\u0110\u0102NG NH\u1eacP"); //NON-NLS
                Button_Login.setkBorderRadius(30);
                Button_Login.setBorder(null);
                Button_Login.addActionListener(e -> Button_Login(e));
                panel3.add(Button_Login);
                Button_Login.setBounds(195, 340, 140, 35);

                //---- Label_Notice2 ----
                Label_Notice2.setText("N\u1ebfu b\u1ea1n ch\u01b0a c\u00f3 t\u00e0i kho\u1ea3n, h\u00e3y \u0111\u0103ng k\u00fd \u1edf n\u00fat d\u01b0\u1edbi \u0111\u00e2y"); //NON-NLS
                Label_Notice2.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 12)); //NON-NLS
                Label_Notice2.setHorizontalAlignment(SwingConstants.CENTER);
                panel3.add(Label_Notice2);
                Label_Notice2.setBounds(0, 385, 535, Label_Notice2.getPreferredSize().height);

                //---- Button_Register ----
                Button_Register.setText("\u0110\u0102NG K\u00dd"); //NON-NLS
                Button_Register.setkBorderRadius(30);
                Button_Register.setBorder(null);
                Button_Register.addActionListener(e -> Button_Register(e));
                panel3.add(Button_Register);
                Button_Register.setBounds(195, 415, 140, 35);

                //---- Notice_ErrorLogin ----
                Notice_ErrorLogin.setText("B\u1ea1n \u0111\u00e3 nh\u1eadp sai m\u1eadt kh\u1ea9u ho\u1eb7c t\u00ean \u0111\u0103ng nh\u1eadp, h\u00e3y nh\u1eadp l\u1ea1i!"); //NON-NLS
                Notice_ErrorLogin.setFont(new Font("Consolas", Font.PLAIN, 12)); //NON-NLS
                Notice_ErrorLogin.setForeground(Color.red);
                Notice_ErrorLogin.setVisible(false);
                panel3.add(Notice_ErrorLogin);
                Notice_ErrorLogin.setBounds(60, 290, 415, Notice_ErrorLogin.getPreferredSize().height);

                //---- Notce_ErrorLogin2 ----
                Notce_ErrorLogin2.setText("N\u1ebfu \u0111\u00e2y l\u00e0 l\u1ea7n \u0111\u1ea7u s\u1eed d\u1ee5ng ph\u1ea7n m\u1ec1m, h\u00e3y t\u1ea1o t\u00e0i kho\u1ea3n ngay!"); //NON-NLS
                Notce_ErrorLogin2.setFont(new Font("Consolas", Notce_ErrorLogin2.getFont().getStyle(), Notce_ErrorLogin2.getFont().getSize())); //NON-NLS
                Notce_ErrorLogin2.setForeground(Color.red);
                Notce_ErrorLogin2.setVisible(false);
                panel3.add(Notce_ErrorLogin2);
                Notce_ErrorLogin2.setBounds(60, 310, 455, Notce_ErrorLogin2.getPreferredSize().height);

                //---- Button_Forget ----
                Button_Forget.setText("Qu\u00ean t\u00ean \u0111\u0103ng nh\u1eadp/m\u1eadt kh\u1ea9u ?"); //NON-NLS
                Button_Forget.setBorder(null);
                Button_Forget.setFocusPainted(false);
                Button_Forget.setBackground(SystemColor.window);
                Button_Forget.setFont(new Font("Consolas", Font.ITALIC, 12)); //NON-NLS
                Button_Forget.setForeground(new Color(0x990099));
                Button_Forget.setFocusable(false);
                Button_Forget.setOpaque(true);
                Button_Forget.addActionListener(e -> Button_Forget(e));
                panel3.add(Button_Forget);
                Button_Forget.setBounds(300, 260, 205, 19);

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
            panel1.add(panel3);
            panel3.setBounds(480, 0, 525, 470);

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
        panel1.setBounds(0, 0, 1000, 470);

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
        setSize(1005, 500);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel1;
    private JPanel panel2;
    private JLabel label1;
    private JPanel panel3;
    private JPanel panel4;
    private JLabel Label_Notice1;
    private JLabel Label_Logo;
    private JFormattedTextField Text_UserName;
    private JLabel Label_UserName;
    private JPasswordField Text_Pass;
    private JLabel Label_Pass;
    private KButton Button_Login;
    private JLabel Label_Notice2;
    private KButton Button_Register;
    private JLabel Notice_ErrorLogin;
    private JLabel Notce_ErrorLogin2;
    private JButton Button_Forget;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
