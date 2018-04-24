package com.ArkavQuarium;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Drawer {

    protected JFrame frame;
    protected DrawPanel drawPanel;
    protected Aquarium aquarium;
    protected long time;
    protected boolean menuState;

    protected double xFood;
    protected boolean newEgg;
    protected boolean newFood;
    protected boolean newGuppy;
    protected boolean newPiranha;

    protected boolean loadGame;
    protected boolean saveGame;

    public Drawer() {

        menuState = true;
        newFood = false;
        newGuppy = false;
        newPiranha = false;
        newEgg = false;
        loadGame = false;
        saveGame = false;

        aquarium = new Aquarium(1080, 720);

        frame = new JFrame("ArkavQuarium");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawPanel = new DrawPanel();

        MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (menuState) {

                    if ((e.getX() >= 600 && e.getX() <= 965) && (e.getY() >= 70 && e.getY() <= 170))
                        menuState = false;
                    if ((e.getX() >= 600 && e.getX() <= 965) && (e.getY() >= 212 && e.getY() <= 283)) {
                        loadGame = true;
                        menuState = false;
                        // System.out.println("Load Game");
                    }

                } else {

                    if (e.getY() > 140) {
                        aquarium.createFood(new Point(e.getX(), 150));

                        System.out.println("FOOD " + aquarium.listFood.getSize());
                    }

                    if ((e.getY() >= 37 && e.getY() <= 69) && (e.getX() >= 931 && e.getX() <= 1041))
                        saveGame = true;
                    // System.out.println("Game Saved");
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

        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (!menuState) {
                    if (e.getKeyChar() == 'g') {
                        aquarium.createGuppy();
                    } else if (e.getKeyChar() == 'p') {
                        aquarium.createPiranha();
                    } else if (e.getKeyChar() == 'e') {
                        newEgg = true;
                    }
                }

                // System.out.println(e.getKeyChar());
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

        // initialize needed boolean

        while (true) {
            frame.repaint();
        }
    }

    public long getTime() {
        return aquarium.getCurrentTime();
    }

    public int getFrame() {
        long temp = getTime() % 1500000000L;

        if (temp >= 0 && temp < 150000000L) return 0;
        else if (temp >= 150000000L && temp < 300000000L) return 1;
        else if (temp >= 300000000L && temp < 450000000L) return 2;
        else if (temp >= 450000000L && temp < 600000000L) return 3;
        else if (temp >= 600000000L && temp < 750000000L) return 4;
        else if (temp >= 750000000L && temp < 900000000L) return 5;
        else if (temp >= 900000000L && temp < 1050000000L) return 6;
        else if (temp >= 1050000000L && temp < 1200000000L) return 7;
        else if (temp >= 1200000000L && temp < 1350000000L) return 8;
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

            aquarium.run();

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

            int fps = getFrame();
            String filename = "coin" + String.valueOf(fps) + ".png";

            double x = coin.getX();
            double y = coin.getY();
            int level = coin.getValue() / coin.getBaseVal();

            if (level == 1) filename = "src/com/ArkavQuarium/assets/img/b" + filename + ".png";
            else if (level == 2) filename = "src/com/ArkavQuarium/assets/img/g" + filename + ".png";
            else if (level == 3) filename = "src/com/ArkavQuarium/assets/img/s" + filename + ".png";
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

    }

    public void updateAquarium() {
        if (newFood) {
            aquarium.createFood(new Point(xFood, 150));
            System.out.println("FOOD " + aquarium.listFood.getSize());
            newFood = false;
        }

        if (newGuppy) {
            aquarium.createGuppy();
            // System.out.println("GUPPY " + aquarium.listGuppy.getSize());
            newGuppy = false;
        }

        if (newPiranha) {
            aquarium.createPiranha();
            newPiranha = false;
        }

        if (newEgg) {
        }
        if (loadGame) {
        }
        if (saveGame) {
        }

    }
}


