package main;

public class Hero {
    public static int WIDTH = 40;
    public static int HEIGHT = 60;
    public static int SPEED = 10;
    public int x;
    public int y;
    public double ySpeed = 0;
    
    public void setX(int posX) {
        x = posX;
    }
    
    public void setY(int posY) {
        y = posY;
    }
    
    public void moveRight() {
        x += Hero.SPEED;
    }
    
    public void moveLeft() {
        x -= Hero.SPEED;
    }
    
    public void jump() {
        ySpeed = 20;
        y -= (int)ySpeed;
    }
}
