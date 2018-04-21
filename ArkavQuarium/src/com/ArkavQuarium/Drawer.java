package com.ArkavQuarium;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Drawer {

    protected JFrame frame;
    protected DrawPanel drawPanel;
    protected Aquarium aquarium;
    protected long time;

    public void run() {
        aquarium = new Aquarium(1080, 720);

        frame = new JFrame("ArkavQuarium");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawPanel = new DrawPanel();

        frame.add(drawPanel);

        drawPanel.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                aquarium.createGuppy();
                System.out.println(e.getX() + " " + e.getY());
            }
        });

        frame.setResizable(false);
        frame.setSize(1080, 720);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        while (true) {
            aquarium.run();

            System.out.println(aquarium.getListGuppy().getSize());
            aquarium.getListGuppy().get(0).print();
            moveAll();
        }
    }

    public long getTime() {
        return aquarium.getCurrentTime();
    }

    public int getFrame() {
        long temp = getTime() % 2500;

        if (temp >= 0 && temp < 250) return 0;
        else if (temp >= 250 && temp < 500) return 1;
        else if (temp >= 500 && temp < 750) return 2;
        else if (temp >= 750 && temp < 1000) return 3;
        else if (temp >= 1000 && temp < 1250) return 4;
        else if (temp >= 1250 && temp < 1500) return 5;
        else if (temp >= 1500 && temp < 1750) return 6;
        else if (temp >= 1750 && temp < 2000) return 7;
        else if (temp >= 2000 && temp < 2250) return 8;
        else return 9;
    }

    class DrawPanel extends JPanel {

        private Image backgroundImage;

        public DrawPanel() {
            super();
            loadBackground();
        }

        public void loadBackground() {
            ImageIcon pp = new ImageIcon("src/com/ArkavQuarium/assets/img/aquarium.png");
            backgroundImage = pp.getImage();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
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

        public void drawSnail(Snail snail, Graphics g) {
            int fps = getFrame();
            double x = snail.getX();
            double y = snail.getY();
            int direction = snail.getDirection();
            String filename = "snail" + String.valueOf(fps);

            if (direction == 1) filename = "src/com/ArkavQuarium/assets/img/r" + filename;
            else filename = "src/com/ArkavQuarium/assets/img/" + filename;

            ImageIcon temp = new ImageIcon(filename);
            Image snailImage = temp.getImage();

            g.drawImage(snailImage, (int)x, (int)y, this);
        }

        public void drawFood(Food food, Graphics g) {
            int fps = getFrame();
            double x = food.getX();
            double y = food.getY();
            String filename = "src/com/ArkavQuarium/assets/img/food" + String.valueOf(fps);

            ImageIcon temp = new ImageIcon(filename);
            Image foodImage = temp.getImage();

            g.drawImage(foodImage, (int)x, (int)y, this);
        }

        public void drawCoin(Coin coin, Graphics g) {

            int fps = getFrame();
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

            g.drawImage(coinImage, (int)x, (int)y, this);
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

            g.drawImage(GuppyImage, (int)x, (int)y, this);
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

            g.drawImage(piranhaImage, (int)x, (int)y, this);
        }

    }

    public void moveAll() {
        frame.repaint();
    }

/*
    public static void main(String... args) {
        new Drawer().run();
    }
*/

}


