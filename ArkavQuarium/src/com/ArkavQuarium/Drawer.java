package com.ArkavQuarium;
import java.awt.*;
import java.awt.event.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.*;

public class Drawer {

    protected JFrame frame;
    protected DrawPanel drawPanel;
    protected Aquarium aquarium;
    protected long time;
    protected boolean menuState;

    protected MouseListener mouseListener;
    protected KeyListener keyListener;

    protected boolean jalan;
    protected boolean savingFile;

    public Drawer() {

        menuState = true;
        jalan = true;
        savingFile = false;

        aquarium = new Aquarium(1080, 720);

        frame = new JFrame("ArkavQuarium");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawPanel = new DrawPanel();

        mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (menuState) {

                    if ((e.getX() >= 600 && e.getX() <= 965) && (e.getY() >= 70 && e.getY() <= 170))
                        menuState = false;
                        aquarium.createGuppy();
                    if ((e.getX() >= 600 && e.getX() <= 965) && (e.getY() >= 212 && e.getY() <= 283)) {
                        String loadFilename = JOptionPane.showInputDialog("Please input file name for load: ");
                        System.out.println("Load Game");
                        aquarium.loadGame((loadFilename));
                        menuState = false;
                    }

                } else {

                    if (e.getY() > 140) {
                        aquarium.createFood(new Point(e.getX(), 150));
                    }

                    if ((e.getY() >= 37 && e.getY() <= 69) && (e.getX() >= 931 && e.getX() <= 1041)){
                        savingFile = true;

                    }


                }

                // System.out.println(e.getX() + " " + e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };

        keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (!menuState) {
                    if (e.getKeyChar() == 'g') {
                        aquarium.createGuppy();
                        //System.out.println("GUPP = " + aquarium.getListGuppy().getSize());
                    } else if (e.getKeyChar() == 'p') {
                        aquarium.createPiranha();
                    } else if (e.getKeyChar() == 'e') {
                        // add egg
                    } else if (e.getKeyChar() == 'x') {
                        JDialog.setDefaultLookAndFeelDecorated(true);
                        int response = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Exit",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (response == JOptionPane.NO_OPTION) {
                        } else if (response == JOptionPane.YES_OPTION) {
                            jalan = false;
                        } else if (response == JOptionPane.CLOSED_OPTION) {
                        }
                    }


                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };

        drawPanel.addMouseListener(mouseListener);
        drawPanel.addKeyListener(keyListener);

        drawPanel.setFocusable(true);
        drawPanel.requestFocusInWindow();

        frame.setResizable(false);
        frame.setSize(1080, 720);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        frame.add(drawPanel);
    }

    public void run() {

//        while (menuState){}
        while (jalan) {
            frame.repaint();
            if (savingFile){
                String saveFilename= JOptionPane.showInputDialog("Please input file name for save: ");
                System.out.println("Game Saved to"+saveFilename);
                aquarium.saveGame(saveFilename);
                savingFile = false;
            }
        }

        //Exit window
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    public double getTime() {
        return aquarium.getCurrentTime();
    }

    public int getFrame() {
        double temp = getTime() % 1.5;

        if (temp >= 0 && temp < 0.15) return 0;
        else if (temp >= 0.15 && temp < 0.3) return 1;
        else if (temp >= 0.3 && temp < 0.45) return 2;
        else if (temp >= 0.45 && temp < 0.6) return 3;
        else if (temp >= 0.6 && temp < 0.75) return 4;
        else if (temp >= 0.75 && temp < 0.9) return 5;
        else if (temp >= 0.9 && temp < 1.05) return 6;
        else if (temp >= 1.05 && temp < 1.2) return 7;
        else if (temp >= 1.2 && temp < 0.135) return 8;
        else return 9;
    }

    class DrawPanel extends JPanel {

        private Image backgroundImage;
        private Image mainMenu;

        public DrawPanel() {
            super();
            loadMenu();
            loadBackground();
        }

        public void loadBackground() {
            ImageIcon temp = new ImageIcon("src/com/ArkavQuarium/assets/img/aquarium.png");
            backgroundImage = temp.getImage();
        }

        public void loadMenu() {
            ImageIcon temp = new ImageIcon("src/com/ArkavQuarium/assets/img/mainmenu.png");
            mainMenu = temp.getImage();
        }

        public void paintComponent(Graphics g) {

            aquarium.moveGuppy();
            aquarium.movePiranha();
            aquarium.moveCoin();
            aquarium.moveFood();
            aquarium.moveSnail();

            super.paintComponent(g);
            if (menuState) {
                g.drawImage(mainMenu, 0, 0, this);
            } else {
                g.drawImage(backgroundImage, 0, 0, this);

                if (!aquarium.getListGuppy().isEmpty()) {
                    for (int i = 0; i < aquarium.getListGuppy().getSize(); i++) {
                        drawGuppy(aquarium.getListGuppy().get(i), g);
                    }
                }

                if (!aquarium.getListPiranha().isEmpty()) {
                    for (int i = 0; i < aquarium.getListPiranha().getSize(); i++) {
                        drawPiranha(aquarium.getListPiranha().get(i), g);
                    }
                }

                for (int i = 0; i < aquarium.getListFood().getSize(); i++) {
                    drawFood(aquarium.getListFood().get(i), g);
                }

                for (int i = 0; i < aquarium.getListCoin().getSize(); i++) {
                    drawCoin(aquarium.getListCoin().get(i), g);
                }

                drawSnail(aquarium.getGarry(), g);

            }
        }

        public void drawSnail(Snail snail, Graphics g) {
            int fps = getFrame();
            double x = snail.getX();
            double y = snail.getY();
            int direction = snail.getDirection();
            String filename = "snail" + String.valueOf(fps);

            if (direction == 1) filename = "src/com/ArkavQuarium/assets/img/r" + filename + ".png";
            else filename = "src/com/ArkavQuarium/assets/img/" + filename + ".png";

            ImageIcon temp = new ImageIcon(filename);
            Image snailImage = temp.getImage();

            g.drawImage(snailImage, (int) x, (int) y, this);
        }

        public void drawFood(Food food, Graphics g) {
            int fps = getFrame();
            double x = food.getX();
            double y = food.getY();
            String filename = "src/com/ArkavQuarium/assets/img/food" + String.valueOf(fps) + ".png";

            ImageIcon temp = new ImageIcon(filename);
            Image foodImage = temp.getImage();

            g.drawImage(foodImage, (int) x, (int) y, this);
        }

        public void drawCoin(Coin coin, Graphics g) {
//            System.out.println("Gambar coin");
            int fps = getFrame();
//            System.out.println("Gambar coin " + fps);
            String filename = "coin" + String.valueOf(fps) + ".png";

            double x = coin.getX();
            double y = coin.getY();
            int level = coin.getValue() / coin.getBaseVal();

            if (level == 1) filename = "src/com/ArkavQuarium/assets/img/b" + filename;
            else if (level == 2) filename = "src/com/ArkavQuarium/assets/img/g" + filename;
            else if (level == 3) filename = "src/com/ArkavQuarium/assets/img/s" + filename;
            else filename = "src/com/ArkavQuarium/assets/img/dcoin.png";

            ImageIcon temp = new ImageIcon(filename);
            Image coinImage = temp.getImage();

            g.drawImage(coinImage, (int) x, (int) y, this);
        }

        public void drawGuppy(Guppy guppy, Graphics g) {

            String filename;
            int fps = getFrame();
            int level = guppy.getGrowthStep();
            int direction = guppy.getDirection();
            double x = guppy.getX();
            double y = guppy.getY();

            if (direction == 1) filename = "src/com/ArkavQuarium/assets/img/rguppy" + String.valueOf(fps);
            else filename = "src/com/ArkavQuarium/assets/img/guppy" + String.valueOf(fps);

            if (level == 1) filename += ".png";
            else if (level == 2) filename += "1.png";
            else if (level == 3) filename += "2.png";

            ImageIcon temp = new ImageIcon(filename);
            Image GuppyImage = temp.getImage();

            g.drawImage(GuppyImage, (int) x, (int) y, this);
        }

        public void drawPiranha(Piranha piranha, Graphics g) {
            String filename;
            int fps = getFrame();
            int direction = piranha.getDirection();
            double x = piranha.getX();
            double y = piranha.getY();

            if (direction == 1) filename = "src/com/ArkavQuarium/assets/img/rpiranha" + String.valueOf(fps) + ".png";
            else filename = "src/com/ArkavQuarium/assets/img/piranha" + String.valueOf(fps) + ".png";

            ImageIcon temp = new ImageIcon(filename);
            Image piranhaImage = temp.getImage();

            g.drawImage(piranhaImage, (int) x, (int) y, this);
        }

        public void updateAquarium() {

        }

    }
}


