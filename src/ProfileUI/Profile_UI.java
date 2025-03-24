/*
 * Created by JFormDesigner on Thu May 09 15:00:00 ICT 2024
 */

package ProfileUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import MenuUI.Menu_UI;
import com.k33ptoo.components.KButton;

import static DTBASE.DAO.DAO.getEmailByUsername;

/**
 * @author Thai Ho Phu Gia
 */
public class Profile_UI extends JFrame {
    private String username,password,email;
    private Menu_UI menuui;
    public Profile_UI(String username, String password, Menu_UI menuui) {
        initComponents();
        this.username =username;
        this.password = password;
        this.menuui= menuui;
        this.email = getEmailByUsername(username);
        textField1.setText(username);
        textField2.setText(password);
        textField3.setText(email);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
       // label6.setText("<html><div style='font-family: Arial; font-size: 24px; color: #ff0000; text-shadow: 2px 2px 4px #000000;'>THONG TIN KHÁCH HÀNG</div></html>");
    }

    private void kButton2(ActionEvent e) {
        // TODO add your code here
        this.setVisible(false);
        menuui.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        kButton1 = new KButton();
        label3 = new JLabel();
        textField1 = new JTextField();
        label4 = new JLabel();
        textField2 = new JTextField();
        label5 = new JLabel();
        textField3 = new JTextField();
        label6 = new JLabel();
        label7 = new JLabel();
        textField4 = new JTextField();
        kButton2 = new KButton();

        //======== this ========
        setMaximumSize(new Dimension(500, 585));
        setMinimumSize(new Dimension(500, 585));
        setPreferredSize(new Dimension(500, 585));
        setTitle("Fly Now  Official 1.0"); //NON-NLS
        setIconImage(new ImageIcon(getClass().getResource("/Icon/Logo/plane_whitebackground.png")).getImage()); //NON-NLS
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setLayout(null);

            //---- label1 ----
            label1.setText("text"); //NON-NLS
            label1.setIcon(new ImageIcon(getClass().getResource("/Icon/Logo/plane_300x300.png"))); //NON-NLS
            panel1.add(label1);
            label1.setBounds(0, 10, 300, 150);

            //---- label2 ----
            label2.setIcon(new ImageIcon(getClass().getResource("/Icon/Icon/Graphicloads-Colorful-Long-Shadow-User.96.png"))); //NON-NLS
            panel1.add(label2);
            label2.setBounds(300, 25, 100, 110);

            //---- kButton1 ----
            kButton1.setText("text"); //NON-NLS
            kButton1.setkBorderRadius(200);
            kButton1.setkBackGroundColor(Color.gray);
            kButton1.setkEndColor(Color.white);
            kButton1.setkHoverEndColor(Color.gray);
            kButton1.setkHoverForeGround(Color.darkGray);
            kButton1.setkHoverStartColor(Color.black);
            kButton1.setkIndicatorColor(Color.lightGray);
            kButton1.setkSelectedColor(Color.gray);
            kButton1.setkStartColor(Color.white);
            kButton1.setkAllowGradient(false);
            kButton1.setBorder(null);
            panel1.add(kButton1);
            kButton1.setBounds(295, 25, 105, 110);

            //---- label3 ----
            label3.setText("T\u00ean \u0111\u0103ng nh\u1eadp"); //NON-NLS
            label3.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            panel1.add(label3);
            label3.setBounds(35, 270, 148, 29);

            //---- textField1 ----
            textField1.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            textField1.setEditable(false);
            panel1.add(textField1);
            textField1.setBounds(195, 270, 280, 30);

            //---- label4 ----
            label4.setText("M\u1eadt kh\u1ea9u"); //NON-NLS
            label4.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            panel1.add(label4);
            label4.setBounds(40, 330, 143, 29);

            //---- textField2 ----
            textField2.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            textField2.setEditable(false);
            panel1.add(textField2);
            textField2.setBounds(195, 330, 280, 30);

            //---- label5 ----
            label5.setText("Email"); //NON-NLS
            label5.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            panel1.add(label5);
            label5.setBounds(40, 395, 143, 24);

            //---- textField3 ----
            textField3.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            textField3.setEditable(false);
            panel1.add(textField3);
            textField3.setBounds(195, 390, 280, 30);

            //---- label6 ----
            label6.setText("TH\u00d4NG TIN T\u00c0I KHO\u1ea2N"); //NON-NLS
            label6.setFont(new Font("Consolas", Font.BOLD, 30)); //NON-NLS
            label6.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(label6);
            label6.setBounds(0, 170, 500, 66);

            //---- label7 ----
            label7.setText("S\u1ed1 \u0111i\u1ec7n tho\u1ea1i"); //NON-NLS
            label7.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            label7.setVisible(false);
            panel1.add(label7);
            label7.setBounds(40, 485, 143, 24);

            //---- textField4 ----
            textField4.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
            textField4.setEditable(false);
            textField4.setVisible(false);
            panel1.add(textField4);
            textField4.setBounds(195, 480, 280, 30);

            //---- kButton2 ----
            kButton2.setText("TR\u1ede V\u1ec0 M\u00c0N H\u00ccNH CH\u00cdNH"); //NON-NLS
            kButton2.setBorder(null);
            kButton2.setkBorderRadius(30);
            kButton2.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 20)); //NON-NLS
            kButton2.addActionListener(e -> kButton2(e));
            panel1.add(kButton2);
            kButton2.setBounds(110, 490, 280, kButton2.getPreferredSize().height);

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
        panel1.setBounds(0, 0, 500, 650);

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
    private JLabel label1;
    private JLabel label2;
    private KButton kButton1;
    private JLabel label3;
    private JTextField textField1;
    private JLabel label4;
    private JTextField textField2;
    private JLabel label5;
    private JTextField textField3;
    private JLabel label6;
    private JLabel label7;
    private JTextField textField4;
    private KButton kButton2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
