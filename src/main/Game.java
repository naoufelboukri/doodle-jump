package main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

public class Game extends Canvas implements Runnable{
    public static int WIDTH=800;
    public static int HEIGHT=1100;
    public static long DELTA=20;
    public int FLOOR = HEIGHT - 150;
    public int LIMIT = (int) (HEIGHT * .2);
    public int score = 1;
    public boolean pause = false;
    public boolean menu = false;
    public Hero hero;
    Window window;
    Thread thread;
    public Game() {
        Image doodle = new ImageIcon("doodle.png").getImage();
        hero = new Hero(WIDTH / 2 - Hero.WIDTH / 2, FLOOR - Hero.HEIGHT, doodle);
        Platform.platforms.add(new Platform(WIDTH / 2 - Platform.WIDTH / 2, FLOOR));
        for (int i = 0; i < 5; i++) {
            generatePlatforms();
        }
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
                if (!pause && !menu)
                    Tick();
                Render();
            }
        }
    }
    public void Tick() {
        // Move to the right
        if (KeyInput.Right) {
            if (hero.x >= WIDTH) hero.x = 0;
            hero.moveRight();
        }

        // Move to the left
        if (KeyInput.Left) {
            if (hero.x <= 0) hero.x = WIDTH;
            hero.moveLeft();
        }

        // Gravity for the player
        hero.ySpeed -= 2;
        hero.y -= (int)hero.ySpeed;

        // Reset if player fall
        if (hero.y > HEIGHT + 100) {
            score = 0;
            menu = true;
            hero.y = 0;
            hero.ySpeed = 0;
        }

        // Control the limit to improve your score
        if (hero.y < LIMIT) {
            hero.y = LIMIT;
            score += 1;
            if (score == 500 || score == 750 || score == 1000) {
                Platform.limitPlatforms -= 1;
            }
            Platform.progress(Platform.platforms);
        }


        for (int i = 0; i < Platform.platforms.size() - 1; i++) {
            // Make player jump during collision with platform
            Platform platform = Platform.platforms.get(i);
            if (platform.collision(hero)) {
                hero.jump();
            }

            // Delete a platform
            if (platform.position.y > HEIGHT + 50) {
                Platform.platforms.remove(platform);
            }
        }

        // Add new platform
        if (Platform.platforms.size() < Platform.limitPlatforms) {
            generatePlatforms();
        }
    }

    public void Render(){
        BufferStrategy bs = this.getBufferStrategy();
        Font titleFont = new Font("Arial", 1, 32);
        if(bs == null) {
            this.createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2g =(Graphics2D)g;
        
        g2g.setColor(Color.LIGHT_GRAY);
        g2g.fillRect(0, 0, WIDTH, HEIGHT);
        
        g2g.setColor(Color.GRAY);
        int squareSize = 30;
        for (int i = 0; i < WIDTH; i += squareSize) {
            for (int j = - squareSize * 150; j < HEIGHT; j+= squareSize) {
                g2g.drawRect(i, j + (score * 5), squareSize, squareSize);
            }
        }
        
        g2g.setColor(Color.DARK_GRAY);

        for (int i = 0; i < Platform.platforms.size(); i++) {
            Platform platform = Platform.platforms.get(i);
            g2g.drawImage(platform.platformImg, platform.position.x, platform.position.y, Platform.WIDTH, Platform.HEIGHT,null);
        }

        g2g.drawImage(hero.image,hero.x,hero.y,Hero.WIDTH,Hero.HEIGHT,null);

        g2g.setColor(Color.BLACK);
        g2g.setFont(new Font("Arial", 1, 24));
        g2g.drawString(score + "", WIDTH / 2, 100);

        if (pause || menu) {
            // Create background
            g2g.setColor(new Color(1f,1f,1f,.5f));
            g2g.fillRect(0, 0, WIDTH, HEIGHT);

            g2g.setColor(Color.BLACK);
            g2g.setFont(titleFont);

            if (pause) {
                g2g.drawString("PAUSE", WIDTH / 2, HEIGHT / 2);
            } else if (menu) {
                g2g.drawString("Recommencer ? Appuyer sur entrée", 0, HEIGHT / 2);
            }
        }

        g2g.dispose();
        bs.show();
    }


    public void Touche(String S) {
        if (S.equals(" ")) pause = !pause;
        if (S.equals("\n")) menu = !menu;
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

    private void generatePlatforms() {
        Random random = new Random();
        int minHeight = score > 750 ? 150 : 100;
        int randomHeightInterval = random.nextInt(250) + minHeight;
        int randomX = (int)(Math.random()*(WIDTH - Platform.WIDTH));

        ArrayList<Platform> platforms = Platform.platforms;
        Platform lastPlatform = platforms.get(platforms.size() - 1);
        Platform platform = new Platform(randomX, lastPlatform.position.y - randomHeightInterval);
        platforms.add(platform);
    }
    public static void main(String args[]){
        new Game();
    }

}