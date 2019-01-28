package Game;

//swing library
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

//awt library
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

//io library
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;

//sql library
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class LogIn extends JFrame {

    //dimension of the screen
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Frame fr = new Frame();

    //width and height for frame
    private int frameWidth = (int) (screenSize.getWidth() - screenSize.getWidth() / 3.5);
    private int frameHeight = (int) (screenSize.getHeight() - screenSize.getHeight() / 4);

    void start() {
        //title for login page
        setTitle("Black Jack");

        Color orangeColor = new Color(255, 128, 0);

        //font
        Font textFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        Font labelFont = new Font(Font.SANS_SERIF, Font.BOLD, 21);

        //borders for buttons
        LineBorder buttonBorder = new LineBorder(Color.DARK_GRAY,3);
        LineBorder buttonHoverPlay = new LineBorder(orangeColor,3);

        JPanel welPan = new JPanel();
        JPanel logPan = new JPanel();

        JButton logBut = new JButton("log in");
        logBut.setBounds(248, 315, 180, 38);
        logBut.setBackground(orangeColor);
        logBut.setFont(labelFont);
        logBut.setForeground(Color.DARK_GRAY);
        logBut.setFocusPainted(false);
        logBut.setBorder(buttonBorder);
        logBut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                logBut.setForeground(orangeColor);
                logBut.setBackground(Color.DARK_GRAY);
                logBut.setBorder(buttonHoverPlay);
            }
            public void mouseExited(MouseEvent evt) {
                logBut.setForeground(Color.DARK_GRAY);
                logBut.setBackground(orangeColor);
                logBut.setBorder(buttonBorder);
            }
        });

        JLabel logLabel = new JLabel();
        logLabel.setBounds(220,130,240, 40);
        logLabel.setText("Enter your username: ");
        logLabel.setFont(labelFont);
        logLabel.setBackground(Color.GRAY);
        logLabel.setForeground(Color.DARK_GRAY);

        JLabel pasLabel = new JLabel();
        pasLabel.setBounds(220,228,240, 40);
        pasLabel.setText("Enter your password: ");
        pasLabel.setFont(labelFont);
        pasLabel.setBackground(Color.GRAY);
        pasLabel.setForeground(Color.DARK_GRAY);

        JTextField logField = new JTextField(20);
        JPasswordField pasField = new JPasswordField(20);

        welPan.setBackground(Color.GRAY);
        logPan.setBackground(orangeColor);

        welPan.setLayout(null);
        logPan.setLayout(null);

        setBounds((int)(screenSize.getWidth() / 10),(int)(screenSize.getHeight() / 10), frameWidth, frameHeight);

        welPan.setBounds((int)(screenSize.getWidth() / 4), 0, (int)(frameWidth / 1.5), frameHeight);
        logPan.setBounds((int)(screenSize.getWidth() / 10),(int)(screenSize.getHeight() / 10), frameWidth, frameHeight);

        add(welPan);
        add(logPan);

        logField.setBounds(217,170,250,35);
        logField.setBackground(Color.DARK_GRAY);
        logField.setForeground(orangeColor);
        logField.setBorder(buttonHoverPlay);
        logField.setFont(textFont);
        logField.setCaretColor(orangeColor);

        pasField.setBounds(217,270,250,35);
        pasField.setBackground(Color.DARK_GRAY);
        pasField.setForeground(orangeColor);
        pasField.setBorder(buttonHoverPlay);
        pasField.setFont(textFont);
        pasField.setCaretColor(orangeColor);

        welPan.add(logLabel);
        welPan.add(logField);
        welPan.add(pasLabel);
        welPan.add(pasField);
        welPan.add(logBut);

        logBut.addActionListener(e -> {
            String loginLine = logField.getText(), passLine = new String(pasField.getPassword());

            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from user_table");

                boolean next = false;

                while (rs.next()) {
                    if (rs.getString(4).equals(loginLine)) {
                        if (rs.getString(5).equals(passLine)) {

                            String str = rs.getInt(1) + "\n" + rs.getString(2) + "\n" + rs.getString(3) + "\n" + rs.getString(4) + "\n" + rs.getString(5) + "\n" + rs.getString(6);

                            BufferedWriter bw = null;
                            FileWriter fw = null;

                            try {

                                fw = new FileWriter("users.txt");
                                bw = new BufferedWriter(fw);
                                bw.write(str);

                            } catch (IOException es) {

                                es.printStackTrace();

                            } finally {

                                try {

                                    if (bw != null)
                                        bw.close();

                                    if (fw != null)
                                        fw.close();

                                } catch (IOException ex) {

                                    ex.printStackTrace();

                                }

                            }

                            fr.fFrame();
                            next = true;

                            break;
                        }
                    }
                }
                con.close();
            }catch(Exception ex){
                System.out.println(e.toString());
            }

        });

        fr.addWindowListener(new WindowAdapter() {

            public void windowOpened(WindowEvent e) {
                setVisible(false);
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }

}