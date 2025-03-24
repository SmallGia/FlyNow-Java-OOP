/*
 * Created by JFormDesigner on Tue May 14 08:25:42 ICT 2024
 */

package Contact;

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
import javax.swing.SwingConstants;

import MenuUI.Menu_UI;
import com.k33ptoo.components.KButton;

/**
 * @author Thai Ho Phu Gia
 */
public class ContactUI extends JFrame {
    private Menu_UI menuui;
    public ContactUI(Menu_UI menuui) {
        initComponents();
        this.menuui = menuui;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void kButton4(ActionEvent e) {
        // TODO add your code here
        this.setVisible(false);
        menuui.setVisible(true);
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        kButton1 = new KButton();
        label1 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        kButton2 = new KButton();
        label8 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        label11 = new JLabel();
        kButton3 = new KButton();
        label12 = new JLabel();
        label13 = new JLabel();
        label14 = new JLabel();
        label15 = new JLabel();
        kButton4 = new KButton();

        //======== this ========
        setMaximumSize(new Dimension(960, 600));
        setMinimumSize(new Dimension(960, 600));
        setPreferredSize(new Dimension(960, 600));
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/Icon/Logo/plane_whitebackground.png")).getImage()); //NON-NLS
        setTitle("Fly Now  Official 1.0"); //NON-NLS
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label2 ----
        label2.setIcon(new ImageIcon(getClass().getResource("/Icon/Logo/siu.png"))); //NON-NLS
        contentPane.add(label2);
        label2.setBounds(260, 5, 160, 165);

        //---- label3 ----
        label3.setIcon(new ImageIcon(getClass().getResource("/Icon/Logo/plane_300x300.png"))); //NON-NLS
        contentPane.add(label3);
        label3.setBounds(480, 5, 310, 165);

        //---- label4 ----
        label4.setIcon(new ImageIcon(getClass().getResource("/Icon/dev/3.png"))); //NON-NLS
        contentPane.add(label4);
        label4.setBounds(105, 225, 100, 110);

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
        contentPane.add(kButton1);
        kButton1.setBounds(100, 225, 110, 110);

        //---- label1 ----
        label1.setText("Th\u00e1i H\u1ed3 Ph\u00fa Gia"); //NON-NLS
        label1.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label1);
        label1.setBounds(70, 345, 170, label1.getPreferredSize().height);

        //---- label5 ----
        label5.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
        label5.setText("11012302891"); //NON-NLS
        label5.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label5);
        label5.setBounds(70, 400, 170, 25);

        //---- label6 ----
        label6.setText("thaihophugiak16@siu.edu.vn"); //NON-NLS
        label6.setHorizontalAlignment(SwingConstants.CENTER);
        label6.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
        contentPane.add(label6);
        label6.setBounds(new Rectangle(new Point(15, 455), label6.getPreferredSize()));

        //---- label7 ----
        label7.setIcon(new ImageIcon(getClass().getResource("/Icon/dev/2.png"))); //NON-NLS
        contentPane.add(label7);
        label7.setBounds(420, 225, 100, 110);

        //---- kButton2 ----
        kButton2.setText("text"); //NON-NLS
        kButton2.setkBorderRadius(200);
        kButton2.setkBackGroundColor(Color.gray);
        kButton2.setkEndColor(Color.white);
        kButton2.setkHoverEndColor(Color.gray);
        kButton2.setkHoverForeGround(Color.darkGray);
        kButton2.setkHoverStartColor(Color.black);
        kButton2.setkIndicatorColor(Color.lightGray);
        kButton2.setkSelectedColor(Color.gray);
        kButton2.setkStartColor(Color.white);
        kButton2.setkAllowGradient(false);
        kButton2.setBorder(null);
        contentPane.add(kButton2);
        kButton2.setBounds(415, 225, 110, 110);

        //---- label8 ----
        label8.setText("B\u00f9i L\u00ea H\u1ed3ng T\u00e2m"); //NON-NLS
        label8.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
        label8.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label8);
        label8.setBounds(385, 345, 170, 24);

        //---- label9 ----
        label9.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
        label9.setText("31012302897"); //NON-NLS
        label9.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label9);
        label9.setBounds(385, 400, 170, 25);

        //---- label10 ----
        label10.setText("builehongtamk16@siu.edu.vn"); //NON-NLS
        label10.setHorizontalAlignment(SwingConstants.CENTER);
        label10.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
        contentPane.add(label10);
        label10.setBounds(330, 455, 295, 24);

        //---- label11 ----
        label11.setIcon(new ImageIcon(getClass().getResource("/Icon/dev/1.png"))); //NON-NLS
        contentPane.add(label11);
        label11.setBounds(740, 225, 100, 110);

        //---- kButton3 ----
        kButton3.setText("text"); //NON-NLS
        kButton3.setkBorderRadius(200);
        kButton3.setkBackGroundColor(Color.gray);
        kButton3.setkEndColor(Color.white);
        kButton3.setkHoverEndColor(Color.gray);
        kButton3.setkHoverForeGround(Color.darkGray);
        kButton3.setkHoverStartColor(Color.black);
        kButton3.setkIndicatorColor(Color.lightGray);
        kButton3.setkSelectedColor(Color.gray);
        kButton3.setkStartColor(Color.white);
        kButton3.setkAllowGradient(false);
        kButton3.setBorder(null);
        contentPane.add(kButton3);
        kButton3.setBounds(735, 225, 110, 110);

        //---- label12 ----
        label12.setText("L\u00ea Kh\u1ea3 An"); //NON-NLS
        label12.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
        label12.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label12);
        label12.setBounds(705, 345, 170, 24);

        //---- label13 ----
        label13.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
        label13.setText("31012302893"); //NON-NLS
        label13.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label13);
        label13.setBounds(705, 400, 170, 25);

        //---- label14 ----
        label14.setText("lekhaank16@siu.edu.vn"); //NON-NLS
        label14.setHorizontalAlignment(SwingConstants.CENTER);
        label14.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
        contentPane.add(label14);
        label14.setBounds(650, 455, 286, 24);

        //---- label15 ----
        label15.setText(" Ph\u1ea7n m\u1ec1m \u0111\u1eb7t v\u00e9 m\u00e1y bay "); //NON-NLS
        label15.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 30)); //NON-NLS
        label15.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label15);
        label15.setBounds(0, 165, 955, 39);

        //---- kButton4 ----
        kButton4.setText("QUAY L\u1ea0I"); //NON-NLS
        kButton4.setFont(new Font("Consolas", Font.BOLD, 20)); //NON-NLS
        kButton4.setkStartColor(Color.yellow);
        kButton4.setkEndColor(Color.orange);
        kButton4.setForeground(Color.darkGray);
        kButton4.setkForeGround(Color.darkGray);
        kButton4.addActionListener(e -> kButton4(e));
        contentPane.add(kButton4);
        kButton4.setBounds(new Rectangle(new Point(385, 510), kButton4.getPreferredSize()));

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
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private KButton kButton1;
    private JLabel label1;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private KButton kButton2;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JLabel label11;
    private KButton kButton3;
    private JLabel label12;
    private JLabel label13;
    private JLabel label14;
    private JLabel label15;
    private KButton kButton4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
