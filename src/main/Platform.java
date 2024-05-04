package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Platform {
    public static int WIDTH = 155;
    public static int HEIGHT = 35;
    public Color color = new Color(0x3636A8);
    public Point position = new Point();
    public int score;
    public static ArrayList<Platform> platforms = new ArrayList<Platform>();
    public static int limitPlatforms = 8;
    Image platformImg = new ImageIcon("platform.png").getImage();


    public Platform(int x, int y) {
        position.x = x;
        position.y = y;
        score = 1;
    }

    public boolean collision(Hero hero) {
        if (hero.y + Hero.HEIGHT >= position.y && hero.y + Hero.HEIGHT <= position.y + Platform.HEIGHT) {
            if (hero.x + Hero.WIDTH >= position.x && hero.x <= position.x + WIDTH && hero.ySpeed < 0) {
                return true;
            }
        }
        return false;
    }

    protected void destroy() {
        this.destroy();
    }

    public static void progress(ArrayList<Platform> p) {
        for (int i = 0; i < p.size(); i++) {
            Platform platform = p.get(i);
            platform.position.y += platform.score * 20;
        }
    }
}
