package Login02UI;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import BookingUI.Booking_UI;
import CreateAccUI.CreateAcc_UI;
import ForgetAccUI.ForgetUI;
import MenuUI.Menu_UI;
import com.k33ptoo.components.KButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static DTBASE.DAO.DAO.checkLogin;
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
        initcom();
        getRootPane().setDefaultButton(Button_Login); // Set Button_Login as the default button
    }
    private void initcom(){
        Text_UserName.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    Text_Pass.requestFocus(); // Move focus to Text_Pass when the DOWN arrow key is pressed
                }
            }
        });

        Text_Pass.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    Text_UserName.requestFocus(); // Move focus to Text_UserName when the UP arrow key is pressed
                }
            }
        });
    }
    private void button1MousePressed(MouseEvent e) {
        // TODO add your code here
    }

    private void button1MouseReleased(MouseEvent e) {
        // TODO add your code here
    }

    private void Button_Login(ActionEvent e) {
        // TODO add your code here

        // Show the loading label
        label5.setVisible(true);

        // Create a SwingWorker
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Perform the heavy task
                String username = Text_UserName.getText();
                String password = Text_Pass.getText();

                if (username.equals("") || password.equals("")) {
                    Notice_ErrorLogin.setVisible(true);
                    Notce_ErrorLogin2.setVisible(true);
                } else {
                    if (checkLogin(username, password)) {
                        new Menu_UI(username,password).setVisible(true);
                        dispose();
                    } else {
                        Notice_ErrorLogin.setVisible(true);
                        Notce_ErrorLogin2.setVisible(true);
                    }
                }

                return null;
            }

            @Override
            protected void done() {
                // Hide the loading label when the task is done
                label5.setVisible(false);
            }
        };

        // Start the SwingWorker
        worker.execute();
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
        label3 = new JLabel();
        label2 = new JLabel();
        label5 = new JLabel();
        panel3 = new JPanel();
        Text_UserName = new JFormattedTextField();
        Label_UserName = new JLabel();
        Text_Pass = new JPasswordField();
        Label_Pass = new JLabel();
        Button_Login = new KButton();
        Button_Register = new KButton();
        Notice_ErrorLogin = new JLabel();
        Label_Notice1 = new JLabel();
        Notce_ErrorLogin2 = new JLabel();
        Button_Forget = new JButton();
        scrollPane1 = new JScrollPane();
        Label_Notice2 = new JTextArea();
        label4 = new JLabel();
        Label_Logo = new JLabel();
        label1 = new JLabel();

        //======== this ========
        setMinimumSize(new Dimension(1000, 500));
        setPreferredSize(new Dimension(1000, 500));
        setMaximumSize(new Dimension(1000, 500));
        setResizable(false);
        setTitle("Fly Now  Official 1.0"); //NON-NLS
        setIconImage(new ImageIcon(getClass().getResource("/Icon/Logo/plane_whitebackground.png")).getImage()); //NON-NLS
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setPreferredSize(new Dimension(1280, 720));
            panel1.setBackground(Color.white);
            panel1.setLayout(null);

            //---- label3 ----
            label3.setText("Trong T\u1ea7m Tay B\u1ea1n."); //NON-NLS
            label3.setFont(new Font("Consolas", Font.BOLD, 26)); //NON-NLS
            label3.setForeground(Color.white);
            panel1.add(label3);
            label3.setBounds(new Rectangle(new Point(15, 245), label3.getPreferredSize()));

            //---- label2 ----
            label2.setText("T\u1eeb \u0110\u00f4ng Nam \u00c1 \u0110\u1ebfn Th\u1ebf Gi\u1edbi,"); //NON-NLS
            label2.setFont(new Font("Consolas", Font.BOLD, 26)); //NON-NLS
            label2.setForeground(Color.white);
            panel1.add(label2);
            label2.setBounds(15, 185, 405, 70);

            //---- label5 ----
            label5.setIcon(new ImageIcon(getClass().getResource("/Icon/Icon/superminiloading.gif"))); //NON-NLS
            label5.setVisible(false);
            panel1.add(label5);
            label5.setBounds(405, 170, 105, 135);

            //======== panel3 ========
            {
                panel3.setBackground(Color.white);
                panel3.setBorder(LineBorder.createBlackLineBorder());
                panel3.setLayout(null);

                //---- Text_UserName ----
                Text_UserName.setFont(new Font("Consolas", Font.PLAIN, 18)); //NON-NLS
                panel3.add(Text_UserName);
                Text_UserName.setBounds(170, 155, 300, 25);

                //---- Label_UserName ----
                Label_UserName.setText("T\u00ean \u0111\u0103ng nh\u1eadp:"); //NON-NLS
                Label_UserName.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
                panel3.add(Label_UserName);
                Label_UserName.setBounds(10, 155, 150, Label_UserName.getPreferredSize().height);

                //---- Text_Pass ----
                Text_Pass.setFont(new Font("Consolas", Font.PLAIN, 18)); //NON-NLS
                panel3.add(Text_Pass);
                Text_Pass.setBounds(170, 195, 300, 25);

                //---- Label_Pass ----
                Label_Pass.setText("M\u1eadt kh\u1ea9u:"); //NON-NLS
                Label_Pass.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
                panel3.add(Label_Pass);
                Label_Pass.setBounds(60, 195, 100, Label_Pass.getPreferredSize().height);

                //---- Button_Login ----
                Button_Login.setText("\u0110\u0102NG NH\u1eacP"); //NON-NLS
                Button_Login.setkBorderRadius(30);
                Button_Login.setBorder(null);
                Button_Login.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
                Button_Login.addActionListener(e -> Button_Login(e));
                panel3.add(Button_Login);
                Button_Login.setBounds(100, 310, 140, 35);

                //---- Button_Register ----
                Button_Register.setText("\u0110\u0102NG K\u00dd"); //NON-NLS
                Button_Register.setkBorderRadius(30);
                Button_Register.setBorder(null);
                Button_Register.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
                Button_Register.addActionListener(e -> Button_Register(e));
                panel3.add(Button_Register);
                Button_Register.setBounds(300, 310, 140, 35);

                //---- Notice_ErrorLogin ----
                Notice_ErrorLogin.setText("B\u1ea1n \u0111\u00e3 nh\u1eadp sai m\u1eadt kh\u1ea9u ho\u1eb7c t\u00ean \u0111\u0103ng nh\u1eadp, h\u00e3y nh\u1eadp l\u1ea1i!"); //NON-NLS
                Notice_ErrorLogin.setFont(new Font("Consolas", Font.PLAIN, 12)); //NON-NLS
                Notice_ErrorLogin.setForeground(Color.red);
                Notice_ErrorLogin.setVisible(false);
                panel3.add(Notice_ErrorLogin);
                Notice_ErrorLogin.setBounds(60, 260, 415, Notice_ErrorLogin.getPreferredSize().height);

                //---- Label_Notice1 ----
                Label_Notice1.setText("WELCOME"); //NON-NLS
                Label_Notice1.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 26)); //NON-NLS
                Label_Notice1.setHorizontalAlignment(SwingConstants.CENTER);
                Label_Notice1.setVisible(false);
                panel3.add(Label_Notice1);
                Label_Notice1.setBounds(70, 275, 120, 50);

                //---- Notce_ErrorLogin2 ----
                Notce_ErrorLogin2.setText("N\u1ebfu \u0111\u00e2y l\u00e0 l\u1ea7n \u0111\u1ea7u s\u1eed d\u1ee5ng ph\u1ea7n m\u1ec1m, h\u00e3y t\u1ea1o t\u00e0i kho\u1ea3n ngay!"); //NON-NLS
                Notce_ErrorLogin2.setFont(new Font("Consolas", Notce_ErrorLogin2.getFont().getStyle(), Notce_ErrorLogin2.getFont().getSize())); //NON-NLS
                Notce_ErrorLogin2.setForeground(Color.red);
                Notce_ErrorLogin2.setVisible(false);
                panel3.add(Notce_ErrorLogin2);
                Notce_ErrorLogin2.setBounds(60, 280, 455, Notce_ErrorLogin2.getPreferredSize().height);

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
                Button_Forget.setBounds(295, 225, 205, 19);

                //======== scrollPane1 ========
                {
                    scrollPane1.setBorder(null);

                    //---- Label_Notice2 ----
                    Label_Notice2.setText("B\u1eb1ng c\u00e1ch \u0111\u0103ng k\u00fd, b\u1ea1n \u0111\u1ed3ng \u00fd v\u1edbi \u0110i\u1ec1u kho\u1ea3n & \u0110i\u1ec1u ki\u1ec7n c\u1ee7a ch\u00fang t\u00f4i \n      v\u00e0 b\u1ea1n \u0111\u00e3 \u0111\u1ecdc Ch\u00ednh S\u00e1ch Quy\u1ec1n Ri\u00eang T\u01b0 C\u1ee7a c\u1ee7a ch\u00fang t\u00f4i."); //NON-NLS
                    Label_Notice2.setFont(new Font("Consolas", Font.ITALIC, 12)); //NON-NLS
                    Label_Notice2.setBorder(null);
                    Label_Notice2.setEditable(false);
                    Label_Notice2.setBackground(Color.white);
                    scrollPane1.setViewportView(Label_Notice2);
                }
                panel3.add(scrollPane1);
                scrollPane1.setBounds(15, 360, 505, 40);

                //---- label4 ----
                label4.setText("FLY NOW "); //NON-NLS
                label4.setFont(new Font("Agency FB", Font.BOLD, 36)); //NON-NLS
                label4.setHorizontalAlignment(SwingConstants.CENTER);
                label4.setVisible(false);
                panel3.add(label4);
                label4.setBounds(170, 20, 305, 70);

                //---- Label_Logo ----
                Label_Logo.setIcon(new ImageIcon(getClass().getResource("/Icon/Logo/plane_300x300.png"))); //NON-NLS
                panel3.add(Label_Logo);
                Label_Logo.setBounds(125, 15, 295, 135);
            }
            panel1.add(panel3);
            panel3.setBounds(440, 20, 525, 415);

            //---- label1 ----
            label1.setIcon(new ImageIcon(getClass().getResource("/background/screen.jpg"))); //NON-NLS
            panel1.add(label1);
            label1.setBounds(0, 0, 1000, 470);

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
        setSize(1000, 500);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel1;
    private JLabel label3;
    private JLabel label2;
    private JLabel label5;
    private JPanel panel3;
    private JFormattedTextField Text_UserName;
    private JLabel Label_UserName;
    private JPasswordField Text_Pass;
    private JLabel Label_Pass;
    private KButton Button_Login;
    private KButton Button_Register;
    private JLabel Notice_ErrorLogin;
    private JLabel Label_Notice1;
    private JLabel Notce_ErrorLogin2;
    private JButton Button_Forget;
    private JScrollPane scrollPane1;
    private JTextArea Label_Notice2;
    private JLabel label4;
    private JLabel Label_Logo;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
