/*
 * Created by JFormDesigner on Sun May 05 16:10:31 ICT 2024
 */

package CreateAccUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

import Funtion.EmailValidator;
import Login02UI.Login_Form_UI;
import MenuUI.Menu_UI;
import com.k33ptoo.components.KButton;
import com.k33ptoo.components.KGradientPanel;

import static DTBASE.DAO.DAO.*;
import static Funtion.GenerateRandomString.generateRandomString;
import static Funtion.GenerateRandomString.generateRandomStringshort;

/**
 * @author Thai Ho Phu Gia
 */
public class CreateAcc_UI extends JFrame {
    public CreateAcc_UI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        BindButton1();
        BindButton2();
    }
    private void BindButton2(){
        getRootPane().setDefaultButton(Button_createacc);
    }
    private void BindButton1() {
        KeyAdapter keyAdapter = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (e.getSource() == Text_User) {
                        Text_pass.requestFocus();
                    } else if (e.getSource() == Text_pass) {
                        Text_Mail.requestFocus();
                    } else if (e.getSource() == Text_Mail) {
                        Text_User.requestFocus();
                    }
                }
            }
        };

// Gán KeyAdapter cho các trường văn bản
        Text_User.addKeyListener(keyAdapter);
        Text_pass.addKeyListener(keyAdapter);
        Text_Mail.addKeyListener(keyAdapter);
    }
    private void Button_BackLogin(ActionEvent e) {
        // TODO add your code here
        this.dispose();
        new Login_Form_UI().setVisible(true);
    }
    private void create()
    {
        String user,pass,mail;
        user = Text_User.getText();
        pass = Text_pass.getText();
        mail = Text_Mail.getText();
        if(user.isEmpty() || pass.isEmpty() || mail.isEmpty() || !EmailValidator.validate(mail)){
            Label_Notice.setVisible(true);
            return;
        }
        if (checkUsernameExists(user)) {
            JOptionPane.showMessageDialog(null, "Tài khoản đã tồn tại, hãy chọn tên đăng nhập khác", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (checkEmailExists(mail)) {
            JOptionPane.showMessageDialog(null, "Email đã tồn tại, hãy chọn email khác", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String code = generateRandomStringshort();
        new CheckMail(code,this,mail,user,pass).setVisible(true);
//        addNewAccount(user,pass,mail);
//        JOptionPane.showMessageDialog(null, "Tạo tài khoản thành công, hãy quay về màn hình đăng nhập", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
    private void Button_createacc(ActionEvent e) {
        // TODO add your code here
        // Show the loading label
        label5.setVisible(true);

        // Create a SwingWorker
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Perform the heavy task
                setEnabled(false);
                create();
                setEnabled(true);
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
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label5 = new JLabel();
        Label_Guide1 = new JLabel();
        Label_Title = new JLabel();
        Text_User = new JFormattedTextField();
        Label_User = new JLabel();
        Text_pass = new JPasswordField();
        Label_Pass = new JLabel();
        Text_Mail = new JFormattedTextField();
        Label_mail = new JLabel();
        Button_createacc = new KButton();
        Button_BackLogin = new KButton();
        Label_Notice = new JLabel();
        kGradientPanel1 = new KGradientPanel();

        //======== this ========
        setMaximumSize(new Dimension(1005, 500));
        setMinimumSize(new Dimension(1005, 500));
        setPreferredSize(new Dimension(1005, 500));
        setResizable(false);
        setTitle("Fly Now  Official 1.0"); //NON-NLS
        setIconImage(new ImageIcon(getClass().getResource("/Icon/Logo/plane_whitebackground.png")).getImage()); //NON-NLS
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label5 ----
        label5.setIcon(new ImageIcon(getClass().getResource("/Icon/Icon/superminiloading.gif"))); //NON-NLS
        label5.setVisible(false);
        contentPane.add(label5);
        label5.setBounds(460, 180, 105, 135);

        //---- Label_Guide1 ----
        Label_Guide1.setIcon(new ImageIcon(getClass().getResource("/Icon/Logo/plane_300x300.png"))); //NON-NLS
        Label_Guide1.setBorder(null);
        contentPane.add(Label_Guide1);
        Label_Guide1.setBounds(-60, -25, 275, 195);

        //---- Label_Title ----
        Label_Title.setText("T\u1ea0O T\u00c0I KHO\u1ea2N M\u1edaI"); //NON-NLS
        Label_Title.setHorizontalAlignment(SwingConstants.CENTER);
        Label_Title.setFont(new Font("Consolas", Font.BOLD, 28)); //NON-NLS
        contentPane.add(Label_Title);
        Label_Title.setBounds(330, 20, 345, 50);

        //---- Text_User ----
        Text_User.setFont(new Font("Consolas", Font.PLAIN, 16)); //NON-NLS
        contentPane.add(Text_User);
        Text_User.setBounds(250, 150, 655, 40);

        //---- Label_User ----
        Label_User.setText("T\u00ean \u0111\u0103ng nh\u1eadp:"); //NON-NLS
        Label_User.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
        Label_User.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(Label_User);
        Label_User.setBounds(80, 150, 145, 40);

        //---- Text_pass ----
        Text_pass.setFont(new Font("Consolas", Font.PLAIN, 16)); //NON-NLS
        contentPane.add(Text_pass);
        Text_pass.setBounds(250, 225, 655, 39);

        //---- Label_Pass ----
        Label_Pass.setText("M\u1eadt kh\u1ea9u:"); //NON-NLS
        Label_Pass.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
        Label_Pass.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(Label_Pass);
        Label_Pass.setBounds(80, 225, 145, 40);

        //---- Text_Mail ----
        Text_Mail.setFont(new Font("Consolas", Font.PLAIN, 16)); //NON-NLS
        contentPane.add(Text_Mail);
        Text_Mail.setBounds(250, 295, 655, 40);

        //---- Label_mail ----
        Label_mail.setText("Email:"); //NON-NLS
        Label_mail.setFont(new Font("Consolas", Font.BOLD, 18)); //NON-NLS
        Label_mail.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(Label_mail);
        Label_mail.setBounds(80, 295, 145, 40);

        //---- Button_createacc ----
        Button_createacc.setText("T\u1ea0O NGAY"); //NON-NLS
        Button_createacc.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
        Button_createacc.setkBorderRadius(30);
        Button_createacc.setBorder(null);
        Button_createacc.setIcon(null);
        Button_createacc.addActionListener(e -> Button_createacc(e));
        contentPane.add(Button_createacc);
        Button_createacc.setBounds(new Rectangle(new Point(230, 385), Button_createacc.getPreferredSize()));

        //---- Button_BackLogin ----
        Button_BackLogin.setText("Quay l\u1ea1i m\u00e0n h\u00ecnh \u0111\u0103ng nh\u1eadp"); //NON-NLS
        Button_BackLogin.setkBorderRadius(30);
        Button_BackLogin.setBorder(null);
        Button_BackLogin.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
        Button_BackLogin.addActionListener(e -> Button_BackLogin(e));
        contentPane.add(Button_BackLogin);
        Button_BackLogin.setBounds(485, 385, 335, 45);

        //---- Label_Notice ----
        Label_Notice.setText("B\u1ea0N \u0110\u00c3 \u0110\u1ec2 TR\u1ed0NG HO\u1eb6C \u0110\u1ec2 NH\u1eacP SAI Y\u1ec0U C\u1ea6U, H\u00c3Y KI\u1ec2M TRA TH\u00d4NG TIN L\u1ea0I"); //NON-NLS
        Label_Notice.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 20)); //NON-NLS
        Label_Notice.setForeground(Color.red);
        Label_Notice.setHorizontalAlignment(SwingConstants.CENTER);
        Label_Notice.setVisible(false);
        contentPane.add(Label_Notice);
        Label_Notice.setBounds(95, 345, 855, 32);

        //---- kGradientPanel1 ----
        kGradientPanel1.setkEndColor(Color.white);
        kGradientPanel1.setkStartColor(Color.white);
        kGradientPanel1.setkGradientFocus(0);
        contentPane.add(kGradientPanel1);
        kGradientPanel1.setBounds(0, 0, 1005, 470);

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
    private JLabel Label_Guide1;
    private JLabel Label_Title;
    private JFormattedTextField Text_User;
    private JLabel Label_User;
    private JPasswordField Text_pass;
    private JLabel Label_Pass;
    private JFormattedTextField Text_Mail;
    private JLabel Label_mail;
    private KButton Button_createacc;
    private KButton Button_BackLogin;
    private JLabel Label_Notice;
    private KGradientPanel kGradientPanel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
