package Game;

//swing library
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

//awt library
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

//util library
import java.util.Timer;
import java.util.TimerTask;

/*
!!!!WARNING!!! IF YOU USE WINDOWS THEN ADD 'src/' TO ALL URLS!!!!
Example: use "src/imagesGame/2C.png" instead of "imagesGame/2C.png"
 */

class Game extends JFrame{

    //block of image icons
    private ImageIcon YourCards, EnemyCards;

    private JLabel yourStand, hisStand, result;
    private final JLabel [] arrayOfLabels = new JLabel[10];
    private final JLabel [] arrayOfELabels = new JLabel[10];
    private final JLabel [] arrayOfECards = new JLabel[10];

    private JButton buttonContinue, buttonExit;

    int last = 0, total = 0, totalE = 0, newPos = 2, newPosE = 2;

    boolean yourStop = false, hisStop = false;

    //color
    private final Color dGreenColor = new Color(13, 156, 0);

    /**private final String [] arrayOfCards = {"src/imagesGame/2C.png","src/imagesGame/2D.png","src/imagesGame/2H.png","src/imagesGame/2S.png",
            "src/imagesGame/3C.png","src/imagesGame/3D.png","src/imagesGame/3H.png","src/imagesGame/3S.png",
            "src/imagesGame/4C.png","src/imagesGame/4D.png","src/imagesGame/4H.png","src/imagesGame/4S.png",
            "src/imagesGame/5C.png","src/imagesGame/5D.png","src/imagesGame/5H.png","src/imagesGame/5S.png",
            "src/imagesGame/6C.png","src/imagesGame/6D.png","src/imagesGame/6H.png","src/imagesGame/6S.png",
            "src/imagesGame/7C.png","src/imagesGame/7D.png","src/imagesGame/7H.png","src/imagesGame/7S.png",
            "src/imagesGame/8C.png","src/imagesGame/8D.png","src/imagesGame/8H.png","src/imagesGame/8S.png",
            "src/imagesGame/9C.png","src/imagesGame/9D.png","src/imagesGame/9H.png","src/imagesGame/9S.png",
            "src/imagesGame/10C.png","src/imagesGame/10D.png","src/imagesGame/10H.png","src/imagesGame/10S.png",
            "src/imagesGame/JC.png","src/imagesGame/JD.png","src/imagesGame/JH.png","src/imagesGame/JS.png",
            "src/imagesGame/QC.png","src/imagesGame/QD.png","src/imagesGame/QH.png","src/imagesGame/QS.png",
            "src/imagesGame/KC.png","src/imagesGame/KD.png","src/imagesGame/KH.png","src/imagesGame/KS.png",
            "src/imagesGame/AC.png","src/imagesGame/AD.png","src/imagesGame/AH.png","src/imagesGame/AS.png"};*/

    private final String [] arrayOfCards = {"imagesGame/2C.png","imagesGame/2D.png","imagesGame/2H.png","imagesGame/2S.png",
            "imagesGame/3C.png","imagesGame/3D.png","imagesGame/3H.png","imagesGame/3S.png",
            "imagesGame/4C.png","imagesGame/4D.png","imagesGame/4H.png","imagesGame/4S.png",
            "imagesGame/5C.png","imagesGame/5D.png","imagesGame/5H.png","imagesGame/5S.png",
            "imagesGame/6C.png","imagesGame/6D.png","imagesGame/6H.png","imagesGame/6S.png",
            "imagesGame/7C.png","imagesGame/7D.png","imagesGame/7H.png","imagesGame/7S.png",
            "imagesGame/8C.png","imagesGame/8D.png","imagesGame/8H.png","imagesGame/8S.png",
            "imagesGame/9C.png","imagesGame/9D.png","imagesGame/9H.png","imagesGame/9S.png",
            "imagesGame/10C.png","imagesGame/10D.png","imagesGame/10H.png","imagesGame/10S.png",
            "imagesGame/JC.png","imagesGame/JD.png","imagesGame/JH.png","imagesGame/JS.png",
            "imagesGame/QC.png","imagesGame/QD.png","imagesGame/QH.png","imagesGame/QS.png",
            "imagesGame/KC.png","imagesGame/KD.png","imagesGame/KH.png","imagesGame/KS.png",
            "imagesGame/AC.png","imagesGame/AD.png","imagesGame/AH.png","imagesGame/AS.png"};

    void fGame() {

        YourCards = new ImageIcon("imagesGame/red_back.png");
        EnemyCards = new ImageIcon("imagesGame/purple_back.png");

        //block of labels
        JLabel yourLabel = new JLabel(), enemysLabel = new JLabel();
        JPanel BJBackground = new JPanel();
        yourStand = new JLabel();
        hisStand = new JLabel();
        result = new JLabel();

        for (int i = 0; i < 10; i++) arrayOfECards[i] = new JLabel();

        JButton addCard = new JButton(), stopTurn = new JButton(), doubleSum = new JButton();

        buttonContinue = new JButton();
        buttonExit = new JButton();

        // borders
        Border [] borders = new Border[4];
        borders[0] = new LineBorder(Color.WHITE,2);
        borders[1] = new LineBorder(Color.RED,2);
        borders[2] = new LineBorder(Color.BLACK,3);
        borders[3] = new LineBorder(Color.DARK_GRAY,3);

        yourLabel.setIcon(YourCards);
        enemysLabel.setIcon(EnemyCards);
        BJBackground.setLayout(null);

        setTitle("BlackJack");

        //game's background
        BJBackground.setBackground(dGreenColor);
        getContentPane().setBackground(dGreenColor);
        setContentPane(BJBackground);

        yourStand.setIcon(new ImageIcon("BlackJack/stand.png"));
        yourStand.setBounds(385,98,150,40);
        getContentPane().add(yourStand);
        yourStand.setVisible(false);

        hisStand.setIcon(new ImageIcon("BlackJack/standE.png"));
        hisStand.setBounds(538,98,150,40);
        getContentPane().add(hisStand);
        hisStand.setVisible(false);

        yourLabel.setBounds(386,5,148,226);
        enemysLabel.setBounds(539,5,148,226);

        result.setBounds(311,230,450,75);
        result.setIcon(new ImageIcon("BlackJack/youLose.png"));
        result.setBorder(borders[2]);
        getContentPane().add(result);
        result.setVisible(false);

        setButton(buttonContinue,"BlackJack/buttonContinue.png","BlackJack/buttonContinueHover.png", borders[2], borders[3],311,308,222,50);
        buttonContinue.setVisible(false);

        setButton(buttonExit,"BlackJack/buttonExit.png","BlackJack/buttonExitHover.png", borders[2], borders[3],540,308,222,50);
        buttonExit.setVisible(false);

        getContentPane().add(yourLabel);
        getContentPane().add(enemysLabel);

        arrayElToLabel(arrayOfLabels);


        int cardY = (int)(getBounds().height / 2.55);
        int cardWidth = getBounds().width / 7;
        int cardHeight = (int)(getBounds().height / 2.75);


        for (int i = 0; i < 10; i++) {
            arrayOfLabels[i].setBounds(366 - (30 * i), cardY, cardWidth, cardHeight);
        }

        arrayOfLabels[0].setIcon(YourCards);
        arrayOfLabels[1].setIcon(YourCards);

        arrayOfLabels[0].setVisible(true);
        arrayOfLabels[1].setVisible(true);

        int random1 = randCard(), random2 = randCard();

        total = Count(random1, newPos, total);
        total = Count(random2, newPos, total);

        arrayElToLabel(arrayOfELabels);

        for (int i = 0; i < 10; i++) {
            arrayOfELabels[i].setBounds(560 + (30 * i), cardY, cardWidth, cardHeight);
        }

        arrayOfELabels[0].setIcon(EnemyCards);
        arrayOfELabels[1].setIcon(EnemyCards);

        arrayOfELabels[0].setVisible(true);
        arrayOfELabels[1].setVisible(true);

        int randomE1 = randCard(), randomE2 = randCard();

        totalE = Count(randomE1, newPosE, totalE);
        totalE = Count(randomE2, newPosE, totalE);

        arrayOfECards[0].setIcon(new ImageIcon(arrayOfCards[randomE1]));
        arrayOfECards[1].setIcon(new ImageIcon(arrayOfCards[randomE2]));

        setButton(addCard,"BlackJack/addCard.png","BlackJack/addCardHover.png", borders[0], borders[1],0,535,160,40);
        setButton(stopTurn,"BlackJack/stopTurn.png","BlackJack/stopTurnHover.png", borders[0], borders[1],187,535,160,40);
        setButton(doubleSum,"BlackJack/doubleSum.png","BlackJack/doubleSumHover.png", borders[0], borders[1],374,535,160,40);

        addCard.addActionListener(ev -> AddCard());
        stopTurn.addActionListener(ev1 -> Stop());
        buttonExit.addActionListener(ev2 -> dispose());
        buttonContinue.addActionListener(ev3 -> newGame());

        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        arrayOfLabels[0].setIcon(new ImageIcon(arrayOfCards[random1]));
                        arrayOfLabels[1].setIcon(new ImageIcon(arrayOfCards[random2]));
                    }
                },
                2000
        );

        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }

    private int randCard() {
        return (int)(Math.random() * 52);
    }

    //method to set new button
    private void setButton(JButton button, String FileN1, String FileN2,Border border,Border borderHover,int x, int y, int width,int height) {
        button.setBounds(x,y,width,height);
        button.setIcon(new ImageIcon(FileN1));
        button.setBorder(border);
        button.setBorderPainted(true);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evtt) {
                button.setIcon(new ImageIcon(FileN2));
                button.setBorder(borderHover);
            }
            public void mouseExited(MouseEvent evtt) {
                button.setIcon(new ImageIcon(FileN1));
                button.setBorder(border);
            }
        });

        getContentPane().add(button);

    }

    private void arrayElToLabel(JLabel [] array) {

        for (int i = 0; i < array.length; i++) {
            array[i] = new JLabel();
            getContentPane().add(array[i]);
            array[i].setVisible(false);
        }

    }

    private void AddCard() {

        int randNew = (int)(Math.random() * 52);

        if (!yourStop) {
            total = Count(randNew, newPos, total);

            if (total <= 21) {
                arrayOfLabels[newPos].setIcon(new ImageIcon(arrayOfCards[randNew]));
                arrayOfLabels[newPos].setVisible(true);

                newPos++;
            } else if (last == 0) {
                arrayOfLabels[newPos].setIcon(new ImageIcon(arrayOfCards[randNew]));
                arrayOfLabels[newPos].setVisible(true);

                last++;
                Stop();
            }
        }

            hisTurn();

        }

    private int Count(int num, int pos, int increase) {

        if (pos < 10) {
            if (num >= 0 && num <= 31) {
                increase = increase + (num / 4) + 2;
            } else if (num > 31 && num <= 47) {
                increase = increase + 10;
            } else if (increase + 11 > 21) {
                increase++;
            } else {
                increase = increase + 11;
            }
        }

        return increase;
    }

    private void hisTurn() {

        int randNew2 = (int) (Math.random() * 52);
        int randChoice = (int) (Math.random() * 10);

        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {

                        if (!hisStop) {
                            if (totalE <= 16) {
                                totalE = Count(randNew2, newPosE, totalE);
                                addPhoto(randNew2);
                            } else if (totalE < 21) {
                                int totalELimitNumber = 1 + ((totalE - 17) * 2);
                                if (randChoice > totalELimitNumber) {
                                    totalE = Count(randNew2, newPosE, totalE);
                                    addPhoto(randNew2);
                                } else {
                                    hisStop = true;
                                    StopTurn();
                                }
                            } else {
                                hisStop = true;
                                StopTurn();
                            }

                        }
                    }
                }, 1500);
    }



    private void StopTurn() {

        if (hisStop) {
            hisStand.setVisible(true);
        }

        if (yourStop) {
            yourStand.setVisible(true);
        }

        if (yourStop && hisStop) {

            new Timer().schedule(
                    new TimerTask() {
                        @Override
                        public void run() {

                            if (total <= 21 && (total > totalE || totalE > 21)) {
                                result.setIcon(new ImageIcon("BlackJack/youWin.png"));
                                result.setVisible(true);
                                System.out.println("You win!" + total + " " + totalE);
                            } else if (total == totalE) {
                                result.setIcon(new ImageIcon("BlackJack/standoff.png"));
                                result.setVisible(true);
                                System.out.println("Tied!" + total + " " + totalE);
                            } else {
                                result.setIcon(new ImageIcon("BlackJack/youLose.png"));
                                result.setVisible(true);
                                System.out.println("You lose!" + total + " " + totalE);
                            }

                            buttonContinue.setVisible(true);
                            buttonExit.setVisible(true);

                            for (int i = 0;i < arrayOfELabels.length;i++) {
                                if (arrayOfELabels[i].getIcon() != null) {
                                    arrayOfELabels[i].setIcon(arrayOfECards[i].getIcon());
                                } else {
                                    i = arrayOfELabels.length;
                                }
                            }

                        }
                    },
                    1000
            );

        }

    }

    private void addPhoto(int num) {
        arrayOfECards[newPosE].setIcon(new ImageIcon(arrayOfCards[num]));
        arrayOfELabels[newPosE].setIcon(EnemyCards);
        arrayOfELabels[newPosE].setVisible(true);
        newPosE++;
        if (yourStop) {
            hisTurn();
        }
    }

    private void Stop() {
        yourStop = true;
        StopTurn();
        hisTurn();
    }

    private void newGame() {

        last = 0;

        newPos = 2;
        newPosE = 2;

        total = 0;
        totalE = 0;

        yourStop = false;
        hisStop = false;

        hisStand.setVisible(false);
        yourStand.setVisible(false);

        result.setVisible(false);
        buttonContinue.setVisible(false);
        buttonExit.setVisible(false);

        int random1 = (int)(Math.random() * 52), random2 = (int)(Math.random() * 52);
        int randomE1 = (int)(Math.random() * 52), randomE2 = (int)(Math.random() * 52);

        total = Count(random1, newPos, total);
        total = Count(random2, newPos, total);

        totalE = Count(randomE1, newPosE, totalE);
        totalE = Count(randomE2, newPosE, totalE);

        arrayOfECards[0].setIcon(new ImageIcon(arrayOfCards[randomE1]));
        arrayOfECards[1].setIcon(new ImageIcon(arrayOfCards[randomE2]));

        for (int i = 2;i < 10;i++) {
            arrayOfELabels[i].setVisible(false);
            arrayOfLabels[i].setVisible(false);
        }

        arrayOfLabels[0].setIcon(YourCards);
        arrayOfLabels[1].setIcon(YourCards);

        arrayOfELabels[0].setIcon(EnemyCards);
        arrayOfELabels[1].setIcon(EnemyCards);

        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        arrayOfLabels[0].setIcon(new ImageIcon(arrayOfCards[random1]));
                        arrayOfLabels[1].setIcon(new ImageIcon(arrayOfCards[random2]));
                    }
                },
                2000
        );

    }

}