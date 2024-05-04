package main;

import java.awt.*;

public class Platform {
    public int width;
    public int height;
    public Color color;
    public Point position = new Point(100, 800);
    
    public Platform() {
        width = 150;
        height = 20;
        color = new Color(0x3636A8);
    }
    
    public Platform(int w, int h, Color c) {
        width = w;
        height = h;
        color = c;
    }
}
