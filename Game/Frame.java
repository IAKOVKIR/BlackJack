package Game;

//awt library
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;

//util library
import java.io.*;
import java.sql.*;
import java.util.TimerTask;
import java.util.Timer;

//swing library
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JRootPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Frame extends JFrame{

    private Game game = new Game();
    private User user;

    //block of labels
    private JLabel [] labels;

    //block of buttons
    private JButton [] buttons;

    //block of borders
    private Border buttonHoverPlay;
    private Border buttonBorder;

    //block of text fields
    private JTextField nameField;

    //fonts
    private Font textFont = new Font(Font.SANS_SERIF, Font.BOLD, 38);
    private Font labelFont = new Font(Font.SANS_SERIF, Font.BOLD, 62);

    //color
    private Color orangeColor = new Color(255, 128, 0);

    private String userName = "Admin";
    private String clickedLang = "";

    //dimension of the screen
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    //width and height for frame
    private int frameWidth = (int) (screenSize.getWidth() - screenSize.getWidth() / 3.5);
    private int frameHeight = (int) (screenSize.getHeight() - screenSize.getHeight() / 4);

    void fFrame() {

        BufferedReader objReader = null;
        int count = 0;
        try {
            objReader = new BufferedReader(new FileReader("users.txt"));

            int id = 0;
            if ((userName = objReader.readLine()) != null) id = Integer.parseInt(userName);

            String [] array = new String[5];
            while ((userName = objReader.readLine()) != null && count < 5) {
                array[count] = userName;
                count++;
            }
            user = new User(id, array[0], array[1], array[2], array[3], array[4]);

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {
                if (objReader != null)
                    objReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        // buttons
        buttons = new JButton[18];
        for (int i = 0; i < 18; i++) buttons[i] = new JButton();
        setLanguage(user.getLanguage());

        //labels (Header)
        labels = new JLabel[7];
        String [] labText = {"WELCOME", "GAME", "SETTINGS", "LANGUAGE", "DETAILS", "SAVED", "ARE YOU SURE?"};
        for (int i = 0; i < 7; i++) labels[i] = new JLabel(labText[i]);

        //text fields
        nameField = new JTextField(user.getFirstName());

        //borders for buttons
        buttonBorder = new LineBorder(Color.DARK_GRAY,3);
        buttonHoverPlay = new LineBorder(orangeColor,3);

        System.out.println(user.getFirstName() + " " + user.getLastName());

        //title for main page
        setTitle("Main Menu");

        //bounds for main page
        setBounds((int)(screenSize.getWidth() / 10),(int)(screenSize.getHeight() / 10), frameWidth, frameHeight);
        game.setBounds(getBounds());

        //400
        int butX = (int)(frameWidth / 2.75);

        int labWelcome = (int)(butX / 40 * 42.8);
        int labGame = butX / 40 * 50;
        int labSettings = (int)(butX / 40 * 43.5);
        int labLanguage = (int)(butX / 40 * 41.5);
        int labDetails = butX / 40 * 46;
        int labX = butX / 40 * 38;

        //300
        int butWidth = (int)(frameHeight / 2.15);

        int labWidth = butX / 4 * 5;

        //210
        int butY = (int)(frameHeight / 3.09);

        int butY1 = (int)(butY / 7 * 9.4);
        int butY2 = (int)(butY / 7 * 11.55);
        int butY3 = butY / 7 * 5;

        int labY = butWidth / 15 * 2 ;

        //50
        int butHeight = (int)(frameHeight / 12.5);

        //add all labels(header) to frame
        setLabel(labels[0], labWelcome, labY, labWidth, labY * 2, labelFont);
        setLabel(labels[1], labGame, labY, labWidth, labY * 2, labelFont);
        setLabel(labels[2], labSettings, labY, labWidth, labY * 2, labelFont);
        setLabel(labels[3], labLanguage, labY, labWidth, labY * 2, labelFont);
        setLabel(labels[4], labDetails, labY, labWidth, labY * 2, labelFont);
        setLabel(labels[5], (int)(butHeight * 0.3), 0, butHeight * 3, butHeight, textFont);
        setLabel(labels[6], butX / 40 * 39, butX / 2, butWidth / 10 * 11, butHeight, textFont);

        //add all buttons to frame
        setButton(buttons[0], butX, butY3, butWidth, butHeight);
        setButton(buttons[1], butX, butY, butWidth, butHeight);
        setButton(buttons[2], butX, butY1, butWidth, butHeight);
        setButton(buttons[3], butX, butY3, butWidth, butHeight);
        setButton(buttons[4], butX, butY, butWidth, butHeight);
        setButton(buttons[5], butX, butY1, butWidth, butHeight);
        setButton(buttons[6], butX, butY2, butWidth, butHeight);
        setButton(buttons[7], butX, butY3, butWidth, butHeight);
        setButton(buttons[8], butX, butY, butWidth, butHeight);
        setButton(buttons[9], butX, butY1, butWidth, butHeight);
        setButton(buttons[10], butX, butY3, butWidth, butHeight);
        setButton(buttons[11], butX, butY, butWidth, butHeight);
        setButton(buttons[12], butX, butY2, butWidth, butHeight);
        setButton(buttons[13], butX, butY1, butWidth, butHeight);
        setButton(buttons[14], butX, butY1, butWidth, butHeight);
        setButton(buttons[15], butX, butY, butWidth, butHeight);
        setButton(buttons[16], butX / 10 * 11, butY / 21 * 31, butWidth / 3, butHeight);
        setButton(buttons[17], butX / 10 * 14, butY / 21 * 31, butWidth / 3, butHeight);

        //add all text fields
        nameField.setBounds(butX,butY - 60, butWidth, butHeight);
        nameField.setBackground(Color.BLACK);
        nameField.setForeground(orangeColor);
        nameField.setBorder(buttonBorder);
        nameField.setFont(textFont);
        nameField.setCaretColor(orangeColor);

        //set position of all things as absolute
        setLayout(null);

        //icon for application
        setIconImage(new ImageIcon("src/imagesMenu/PlayIcon.png").getImage());

        //menu's background
        getContentPane().setBackground(Color.GRAY);

        //add all buttons to frame
        //make buttons (3 - 17) invisible
        for (int i = 0; i < 18; i++) getContentPane().add(buttons[i]);
        for (int i = 3; i < 18; i++) buttons[i].setVisible(false);

        //add all labels to frame
        //make labels (1 - 6) invisible
        for (int i = 0; i < 7; i++) getContentPane().add(labels[i]);
        for (int i = 1; i < 7; i++) labels[i].setVisible(false);

        //field (details page)
        getContentPane().add(nameField);
        nameField.setVisible(false);

        //block of Actions
        buttons[0].addActionListener(e -> play());
        buttons[3].addActionListener(e1 -> newGame());
        buttons[1].addActionListener(e2 -> settings());
        buttons[2].addActionListener(e3 -> {

            File f;

            try {
                // create new file
                f = new File("users.txt");
                boolean bool = f.delete();

            } catch(Exception e) {
                // if any error occurs
                e.printStackTrace();
            }

            System.exit(0);
        });
        buttons[7].addActionListener(e4 -> language());
        buttons[6].addActionListener(e5 -> ReturnToMainMenu());
        buttons[9].addActionListener(e6 -> ReturnToMainMenu());
        buttons[12].addActionListener(e7 -> ReturnToSettings());
        buttons[8].addActionListener(e8 -> details());
        buttons[10].addActionListener(e9 -> {
            clickedLang = "eng";
            changeLanguage();
        });
        buttons[11].addActionListener(e10 -> {
            clickedLang = "rus";
            changeLanguage();
        });
        buttons[13].addActionListener(e11 -> {
            clickedLang = "nor";
            changeLanguage();
        });
        buttons[14].addActionListener(e12 -> closeDetails());
        buttons[15].addActionListener(e13 -> saveDetails());

        game.addWindowListener(new WindowAdapter() {
            public  void windowClosed(WindowEvent e) {
                setVisible(true);
            }
        });

        game.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                setVisible(false);
            }
        });

        game.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                setVisible(true);
            }
        });

        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }


    public void paint(Graphics g) {
        super.paint(g);
    }

    public void setLanguage(String language) {
        switch (language) {
            case "eng":
                String [] butText = {"PLAY", "SETTINGS", "EXIT", "NEW GAME", "LOAD GAME", "SAVE GAME", "RETURN", "LANGUAGE", "DETAILS", "RETURN", "ENGLISH",
                        "РУССКИЙ", "RETURN", "NORSK", "RETURN", "SAVE", "YES", "NO"};
                for (int i = 0; i < 18; i++) buttons[i].setText(butText[i]);
                break;
            case "rus":
                String [] butTextRus = {"ИГРАТЬ", "НАСТРОЙКИ", "ВЫХОД", "НАЧАТЬ ИГРУ", "ПРОДОЛЖИТЬ", "СОХРАНИТЬ", "ВЕРНУТЬСЯ", "ЯЗЫК", "ДЕТАЛИ", "ВЕРНУТЬСЯ",
                        "ENGLISH", "РУССКИЙ", "ВЕРНУТЬСЯ", "NORSK", "ВЕРНУТЬСЯ", "СОХРАНИТЬ", "ДА", "НЕТ"};
                for (int i = 0; i < 18; i++) buttons[i].setText(butTextRus[i]);
                break;
            case "nor":
                String [] butTextNor = {"SPILLE", "INNSTILLINGER", "AVSLUTTE", "NYTT SPILL", "LOAD SPILL", "LAGRE SPILLET", "RETURNERE", "SPRÅK", "DETALJER", "RETURNERE", "ENGLISH",
                        "РУССКИЙ", "RETURNERE", "NORSK", "RETURNERE", "LAGRE", "JA", "NEI"};
                for (int i = 0; i < 18; i++) buttons[i].setText(butTextNor[i]);
                break;
        }
    }

    //method to set new label
    private void setLabel(JLabel label, int x, int y, int width, int height, Font font) {

        label.setBounds(x, y, width, height);
        label.setFont(font);
        label.setForeground(orangeColor);

    }

    private void setButton(JButton button, int x, int y, int width, int height) {
        button.setBounds(x, y, width, height);
        button.setBackground(orangeColor);
        button.setForeground(Color.DARK_GRAY);
        button.setFont(textFont);
        button.setBorder(buttonBorder);
        button.setBorderPainted(true);
        button.setFocusPainted(false);
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setForeground(orangeColor);
                button.setBackground(Color.DARK_GRAY);
                button.setBorder(buttonHoverPlay);
            }
            public void mouseExited(MouseEvent evt) {
                button.setForeground(Color.DARK_GRAY);
                button.setBackground(orangeColor);
                button.setBorder(buttonBorder);
            }
        });

    }

    //method to open play page
    private void play() {

        setTitle("Game");

        labels[0].setVisible(false);
        labels[1].setVisible(true);

        buttons[0].setVisible(false);
        buttons[1].setVisible(false);
        buttons[2].setVisible(false);

        buttons[3].setVisible(true);
        buttons[4].setVisible(true);
        buttons[5].setVisible(true);
        buttons[6].setVisible(true);

    }

    //method to start new game

    private void newGame() {

        setVisible(false);

        game.last = 0;

        game.newPos = 2;
        game.newPosE = 2;

        game.total = 0;
        game.totalE = 0;

        game.yourStop = false;
        game.hisStop = false;

        game.fGame();

    }

    //method to open settings page
    private void settings() {

        setTitle("Settings");

        labels[0].setVisible(false);
        labels[2].setVisible(true);

        buttons[0].setVisible(false);
        buttons[1].setVisible(false);
        buttons[2].setVisible(false);

        buttons[7].setVisible(true);
        buttons[8].setVisible(true);
        buttons[9].setVisible(true);

    }

    //method to return to main menu
    private void ReturnToMainMenu() {

        setTitle("Main Menu");

        labels[1].setVisible(false);
        labels[2].setVisible(false);
        labels[0].setVisible(true);

        buttons[3].setVisible(false);
        buttons[4].setVisible(false);
        buttons[5].setVisible(false);
        buttons[6].setVisible(false);

        buttons[7].setVisible(false);
        buttons[8].setVisible(false);
        buttons[9].setVisible(false);

        buttons[0].setVisible(true);
        buttons[1].setVisible(true);
        buttons[2].setVisible(true);

    }

    //method to open language page
    private void language() {

        setTitle("Language");

        labels[2].setVisible(false);
        labels[3].setVisible(true);

        buttons[7].setVisible(false);
        buttons[8].setVisible(false);
        buttons[9].setVisible(false);

        buttons[10].setVisible(true);
        buttons[11].setVisible(true);
        buttons[12].setVisible(true);
        buttons[13].setVisible(true);

    }

    private void details() {

        setTitle("Details");

        labels[2].setVisible(false);
        buttons[7].setVisible(false);
        buttons[8].setVisible(false);
        buttons[9].setVisible(false);

        labels[4].setVisible(true);
        nameField.setVisible(true);
        buttons[14].setVisible(true);
        buttons[15].setVisible(true);


    }

    //method to return to settings page
    private void closeDetails() {

        setTitle("Settings");

        labels[4].setVisible(false);
        nameField.setVisible(false);
        buttons[14].setVisible(false);
        buttons[15].setVisible(false);

        labels[2].setVisible(true);
        buttons[7].setVisible(true);
        buttons[8].setVisible(true);
        buttons[9].setVisible(true);

    }

    private void ReturnToSettings() {

        setTitle("Settings");

        labels[3].setVisible(false);
        buttons[10].setVisible(false);
        buttons[11].setVisible(false);
        buttons[13].setVisible(false);
        buttons[12].setVisible(false);

        labels[2].setVisible(true);
        buttons[7].setVisible(true);
        buttons[8].setVisible(true);
        buttons[9].setVisible(true);

    }

    private void saveDetails() {

        labels[5].setVisible(true);

        String newName = nameField.getText();

        if (!newName.equals(userName)) {
            userName = newName;
            user.setFirstName(userName);
        }

        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        labels[5].setVisible(false);
                    }
                },
                2300
        );

    }

    private void changeLanguage() {

        buttons[16].setVisible(true);
        buttons[17].setVisible(true);
        labels[6].setVisible(true);

        buttons[10].setVisible(false);
        buttons[11].setVisible(false);
        buttons[12].setVisible(false);
        buttons[13].setVisible(false);

        buttons[17].addActionListener(e15 -> {
            buttons[16].setVisible(false);
            buttons[17].setVisible(false);
            labels[6].setVisible(false);

            buttons[10].setVisible(true);
            buttons[11].setVisible(true);
            buttons[12].setVisible(true);
            buttons[13].setVisible(true);
        });

        buttons[16].addActionListener(e14 -> {

            user.setLanguage(clickedLang);
            setLanguage(user.getLanguage());

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "");
                String update = "update user_table set lang = ? where userid = ?";
                PreparedStatement preparedStmt = con.prepareStatement(update);
                preparedStmt.setString(1, user.getLanguage());
                preparedStmt.setInt(2, user.getId());
                preparedStmt.executeUpdate();
                con.close();
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }

            buttons[16].setVisible(false);
            buttons[17].setVisible(false);
            labels[6].setVisible(false);
            labels[3].setVisible(false);

            ReturnToMainMenu();

            labels[5].setVisible(true);

            new Timer().schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            labels[5].setVisible(false);
                        }
                        },
                    2300
                );

        });

    }

}