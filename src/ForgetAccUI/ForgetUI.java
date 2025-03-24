/*
 * Created by JFormDesigner on Sun May 05 18:18:55 ICT 2024
 */

package ForgetAccUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import Funtion.EmailValidator;
import Login02UI.Login_Form_UI;
import MenuUI.Menu_UI;
import com.k33ptoo.components.KButton;
import com.k33ptoo.components.KGradientPanel;

import static DTBASE.DAO.DAO.checkEmailExists;
import static DTBASE.DAO.DAO.checkLogin;

/**
 * @author Thai Ho Phu Gia
 */
public class ForgetUI extends JFrame {
    public ForgetUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        getRootPane().setDefaultButton(Button_Summit);
    }

    private void Button_Summit(ActionEvent e) {
        // TODO add your code here
//        label5.setVisible(true);
//
//        label5.setVisible(false);
//        // Show the loading label
        label5.setVisible(true);

        // Create a SwingWorker
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Perform the heavy task
                String mail = Text_Mail.getText();
                if (mail.equals("") || !EmailValidator.validate(mail)) {
                    Label_Notice.setVisible(true);
                } else if (!checkEmailExists(mail)){
                    JOptionPane.showMessageDialog(null, "Email không tồn tại trong hệ thống, hãy kiểm tra lại", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    Label_Notice.setVisible(false);
                    if (ForgotPass.sendCredentialsByEmail(mail))
                        JOptionPane.showMessageDialog(null, "Đã gửi mật khẩu khôi phục về địa chỉ email, hãy kiểm tra và quay lại màn hình đăng nhập", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(null, "Email không tồn tại trong hệ thống, hãy kiểm tra lại", "Thông báo", JOptionPane.ERROR_MESSAGE);
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

    private void Button_BackLogin(ActionEvent e) {
        // TODO add your code here
        this.dispose();
        new Login_Form_UI().setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label5 = new JLabel();
        Label_Guide3 = new JLabel();
        Label_Title = new JLabel();
        Label_Guide1 = new JLabel();
        Label_User = new JLabel();
        Text_Mail = new JFormattedTextField();
        Button_Summit = new KButton();
        Label_Guide2 = new JLabel();
        Label_Notice = new JLabel();
        Button_BackLogin = new KButton();
        kGradientPanel1 = new KGradientPanel();

        //======== this ========
        setTitle("Fly Now  Official 1.0"); //NON-NLS
        setIconImage(new ImageIcon(getClass().getResource("/Icon/Logo/plane_whitebackground.png")).getImage()); //NON-NLS
        setMaximumSize(new Dimension(1000, 500));
        setMinimumSize(new Dimension(1000, 500));
        setPreferredSize(new Dimension(1000, 500));
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label5 ----
        label5.setIcon(new ImageIcon(getClass().getResource("/Icon/Icon/superminiloading.gif"))); //NON-NLS
        label5.setVisible(false);
        contentPane.add(label5);
        label5.setBounds(455, 185, 105, 135);

        //---- Label_Guide3 ----
        Label_Guide3.setIcon(new ImageIcon(getClass().getResource("/Icon/Icon/icons8-lock-100.png"))); //NON-NLS
        Label_Guide3.setBorder(null);
        contentPane.add(Label_Guide3);
        Label_Guide3.setBounds(455, 70, 95, 100);

        //---- Label_Title ----
        Label_Title.setText("QU\u00caN M\u1eacT KH\u1ea8U / T\u00caN \u0110\u0102NG NH\u1eacP ?"); //NON-NLS
        Label_Title.setHorizontalAlignment(SwingConstants.CENTER);
        Label_Title.setFont(new Font("Consolas", Font.BOLD, 28)); //NON-NLS
        contentPane.add(Label_Title);
        Label_Title.setBounds(245, 10, 535, 50);

        //---- Label_Guide1 ----
        Label_Guide1.setIcon(new ImageIcon(getClass().getResource("/Icon/Logo/plane_300x300.png"))); //NON-NLS
        Label_Guide1.setBorder(null);
        contentPane.add(Label_Guide1);
        Label_Guide1.setBounds(-45, 10, 300, 140);

        //---- Label_User ----
        Label_User.setText("H\u00c3Y NH\u1eacP \u0110\u1ecaA CH\u1ec8 EMAIL C\u1ee6A T\u00c0I KHO\u1ea2N C\u1ee6A B\u1ea0N"); //NON-NLS
        Label_User.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
        Label_User.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(Label_User);
        Label_User.setBounds(275, 170, 470, 40);

        //---- Text_Mail ----
        Text_Mail.setOpaque(true);
        Text_Mail.setFont(new Font("Consolas", Font.ITALIC, 16)); //NON-NLS
        contentPane.add(Text_Mail);
        Text_Mail.setBounds(285, 235, 455, 50);

        //---- Button_Summit ----
        Button_Summit.setText("X\u00c1C NH\u1eacN"); //NON-NLS
        Button_Summit.setkBorderRadius(30);
        Button_Summit.setBorder(null);
        Button_Summit.setFont(new Font("Consolas", Font.BOLD, 16)); //NON-NLS
        Button_Summit.addActionListener(e -> Button_Summit(e));
        contentPane.add(Button_Summit);
        Button_Summit.setBounds(420, 315, 170, 35);

        //---- Label_Guide2 ----
        Label_Guide2.setText("M\u1eacT KH\u1ea8U M\u1edaI S\u1ebc \u0110\u01af\u1ee2C G\u1eecI V\u1ec0 MAIL C\u1ee6A B\u1ea0N"); //NON-NLS
        Label_Guide2.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 18)); //NON-NLS
        Label_Guide2.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(Label_Guide2);
        Label_Guide2.setBounds(300, 360, 435, 40);

        //---- Label_Notice ----
        Label_Notice.setText("\u0110\u00c3 NH\u1eacP SAI EMAIL HO\u1eb6C EMAIL \u0110\u00d3 KH\u00d4NG T\u1ed2N T\u1ea0I"); //NON-NLS
        Label_Notice.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 14)); //NON-NLS
        Label_Notice.setForeground(Color.red);
        Label_Notice.setVisible(false);
        contentPane.add(Label_Notice);
        Label_Notice.setBounds(335, 295, 370, Label_Notice.getPreferredSize().height);

        //---- Button_BackLogin ----
        Button_BackLogin.setText("Quay l\u1ea1i m\u00e0n h\u00ecnh \u0111\u0103ng nh\u1eadp"); //NON-NLS
        Button_BackLogin.setkBorderRadius(30);
        Button_BackLogin.setBorder(null);
        Button_BackLogin.setFont(new Font("Consolas", Font.PLAIN, 16)); //NON-NLS
        Button_BackLogin.addActionListener(e -> Button_BackLogin(e));
        contentPane.add(Button_BackLogin);
        Button_BackLogin.setBounds(370, 400, 290, Button_BackLogin.getPreferredSize().height);

        //---- kGradientPanel1 ----
        kGradientPanel1.setkStartColor(Color.white);
        kGradientPanel1.setkEndColor(Color.white);
        kGradientPanel1.setkGradientFocus(0);
        contentPane.add(kGradientPanel1);
        kGradientPanel1.setBounds(0, 0, 1025, 493);

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
    private JLabel label5;
    private JLabel Label_Guide3;
    private JLabel Label_Title;
    private JLabel Label_Guide1;
    private JLabel Label_User;
    private JFormattedTextField Text_Mail;
    private KButton Button_Summit;
    private JLabel Label_Guide2;
    private JLabel Label_Notice;
    private KButton Button_BackLogin;
    private KGradientPanel kGradientPanel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
