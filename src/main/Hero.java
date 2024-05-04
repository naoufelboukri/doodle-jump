package main;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Hero {
    public static int WIDTH = 60;
    public static int HEIGHT = (204 * WIDTH) / 186;
    public static int SPEED = 10;
    public Image image;
    public Image imageNormal;
    public Image imageReverse;
    public int x;
    public int y;
    public double ySpeed = 0;

    public Hero (int posX, int posY, Image img) {
        x = posX;
        y = posY;
        image = img;
        imageNormal = image;
        imageReverse = invertImage(image);
    }
    
    public void moveRight() {
        x += Hero.SPEED;
        image = imageNormal;
    }
    
    public void moveLeft() {
        x -= Hero.SPEED;
        image = imageReverse;
    }

    public void jump() {
        ySpeed = 50;
        y -= (int)ySpeed;
    }

    private static BufferedImage invertImage(Image original) {
        BufferedImage bufferedImage = new BufferedImage(original.getWidth(null), original.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-original.getWidth(null), 0);

        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.drawImage(original, tx, null);
        graphics2D.dispose();

        return bufferedImage;
    }
}
