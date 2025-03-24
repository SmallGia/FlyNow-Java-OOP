/*
 * Created by JFormDesigner on Wed May 15 15:07:39 ICT 2024
 */

package CreateAccUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import javax.swing.*;

import com.k33ptoo.components.KButton;

import static DTBASE.CheckMail.CheckMail.checkMail;
import static DTBASE.DAO.DAO.addNewAccount;

/**
 * @author Thai Ho Phu Gia
 */
public class CheckMail extends JFrame {
    private String code;
    CreateAcc_UI createAccUi;
    private String mail,username,pass;
    public CheckMail(String code,CreateAcc_UI createAccUi,String mail,String username,String pass){
        initComponents();
        this.code = code;
        this.createAccUi=createAccUi;
        this.mail=mail;
        this.username=username;
        this.pass=pass;
        checkMail(mail,code);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private void kButton2(ActionEvent e) {
        // TODO add your code here
        if(Text_code.getText().equals(code)){
            addNewAccount(username,pass,mail);
            JOptionPane.showMessageDialog(null, "Tạo tài khoản thành công, hãy quay về màn hình đăng nhập", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "Mã xác nhận không chính xác, hãy kiểm tra lại", "Thông báo", JOptionPane.ERROR_MESSAGE);JOptionPane.showMessageDialog(null, "Mã xác nhận không đúng, vui lòng thử lại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void kButton1(ActionEvent e) {
        // TODO add your code here
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        Text_code = new JFormattedTextField();
        label2 = new JLabel();
        kButton1 = new KButton();
        kButton2 = new KButton();

        //======== this ========
        setMaximumSize(new Dimension(400, 300));
        setMinimumSize(new Dimension(400, 300));
        setPreferredSize(new Dimension(400, 300));
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/Icon/Logo/plane_whitebackground.png")).getImage()); //NON-NLS
        setTitle("Fly Now  Official 1.0"); //NON-NLS
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("M\u00c3 X\u00c1C NH\u1eacN"); //NON-NLS
        label1.setFont(new Font("Consolas", Font.BOLD, 30)); //NON-NLS
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label1);
        label1.setBounds(75, 105, 235, 45);

        //---- Text_code ----
        Text_code.setFont(new Font("Consolas", Font.PLAIN, 16)); //NON-NLS
        contentPane.add(Text_code);
        Text_code.setBounds(35, 160, 330, 40);

        //---- label2 ----
        label2.setIcon(new ImageIcon(getClass().getResource("/Icon/Logo/IconLogo.png"))); //NON-NLS
        contentPane.add(label2);
        label2.setBounds(145, 0, 105, 110);

        //---- kButton1 ----
        kButton1.setText("H\u1ee6Y"); //NON-NLS
        kButton1.setFont(new Font("Consolas", Font.BOLD, 16)); //NON-NLS
        kButton1.addActionListener(e -> kButton1(e));
        contentPane.add(kButton1);
        kButton1.setBounds(215, 225, 100, 35);

        //---- kButton2 ----
        kButton2.setText("X\u00c1C NH\u1eacN"); //NON-NLS
        kButton2.setFont(new Font("Consolas", Font.BOLD, 16)); //NON-NLS
        kButton2.addActionListener(e -> kButton2(e));
        contentPane.add(kButton2);
        kButton2.setBounds(75, 225, 100, 35);

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
    private JLabel label1;
    private JFormattedTextField Text_code;
    private JLabel label2;
    private KButton kButton1;
    private KButton kButton2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
