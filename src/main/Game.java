package main;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Game extends Canvas implements Runnable{
    public static int WIDTH=600;
    public static int HEIGHT=1100;
    public static long DELTA=20;
    public int FLOOR = HEIGHT - 150;
    public Hero hero;
    public ArrayList<Platform> platforms = new ArrayList<Platform>();
    Window window;
    Thread thread;
    public Game() {
        hero = new Hero();
        hero.setX(WIDTH / 2 - Hero.WIDTH / 2);
        hero.setY(FLOOR - Hero.HEIGHT);
        platforms.add(new Platform(WIDTH, 30, Color.DARK_GRAY));
        platforms.add(new Platform());
        this.addKeyListener(new KeyInput(this));
        this.addMouseListener(new MouseInput(this));
        this.addMouseWheelListener(new MouseInput(this));
        this.addMouseMotionListener(new MouseInput(this));
        this.setFocusable(true);
        window=new Window(this);
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        long timer = System.currentTimeMillis();
        while(true) {
            if(System.currentTimeMillis()>timer+DELTA) {
                timer+=DELTA;
                Tick();
                Render();
            }
        }
    }
    public void Tick() {
        if (KeyInput.Right) {
            if (hero.x >= WIDTH) hero.x = 0;
            hero.moveRight();
        }
        if (KeyInput.Left) {
            if (hero.x <= 0) hero.x = WIDTH;
            hero.moveLeft();
        }
        
        if (hero.y + Hero.HEIGHT >= FLOOR) {
            hero.jump();
        } else {
            hero.ySpeed -= 1;
            hero.y -= (int)hero.ySpeed;
        }
        
        Platform platform = platforms.get(1);
        if (hero.y >= platform.position.y + 5 && hero.y <= platform.position.y - 5) {
            System.out.println(hero.ySpeed);
            hero.jump();
        }
        
    }
    public void Render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(2);
            return;
        }


        Graphics g = bs.getDrawGraphics();
        Graphics2D g2g =(Graphics2D)g;
        
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        g.setColor(Color.GRAY);
        int squareSize = 30;
        for (int i = 0; i < WIDTH; i += squareSize) {
            for (int j = 0; j < HEIGHT; j+= squareSize) {
                g.drawRect(i, j, squareSize, squareSize);
            }
        }
        
        g.setColor(Color.DARK_GRAY);
        Platform floor = platforms.get(0);
        g.fillRect(0, FLOOR, floor.width, floor.height);
        
        for (int i = 1; i < platforms.size(); i++) {
            Platform platform = platforms.get(i);
            g.setColor(platform.color);
            g.fillRect(
                    platform.position.x,
                    platform.position.y,
                    platform.width,
                    platform.height
            );
        }
        
        
        g.setColor(new Color(0x17AA19));
        g.fillRect(hero.x, hero.y, Hero.WIDTH, Hero.HEIGHT);
        
        g.dispose();
        bs.show();
    }


    public void Touche(String S) {
        //System.out.println("Touche "+S);
    }
    public void Click(Point P) {
        //System.out.println("Click "+(P.x+","+P.y));
    }
    public void Hover(Point P) {
        //System.out.println("Hover "+(P.x+","+P.y));
    }
    public void Scroll(double V) {
        //System.out.println("Scroll "+V);
    }


    //démarrage
    public static void main(String args[]){
        new Game();
    }

}