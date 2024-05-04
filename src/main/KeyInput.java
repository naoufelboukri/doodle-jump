package main;


import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class KeyInput extends KeyAdapter{


    public static boolean Enter=false;
    public static boolean Shift=false;
    public static boolean Space=false;

    public static boolean Up=false;
    public static boolean Down=false;
    public static boolean Left=false;
    public static boolean Right=false;

    public static boolean W=false;
    public static boolean A=false;
    public static boolean S=false;
    public static boolean D=false;


    Game game;
    public KeyInput(Game G) {
        game=G;
    }
    @Override
    public void keyTyped(KeyEvent e) {

        String texte=String.valueOf(e.getKeyChar());
        game.Touche(texte);

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int C=e.getKeyCode();

        if(C == KeyEvent.VK_ENTER) Enter=true;
        if(C == KeyEvent.VK_SHIFT) Shift=true;
        if(C == KeyEvent.VK_SPACE) Space=true;

        if(C == KeyEvent.VK_UP) Up=true;
        if(C == KeyEvent.VK_DOWN) Down=true;
        if(C == KeyEvent.VK_LEFT) Left=true;
        if(C == KeyEvent.VK_RIGHT) Right=true;

        if(C == KeyEvent.VK_W) W=true;
        if(C == KeyEvent.VK_A) A=true;
        if(C == KeyEvent.VK_S) S=true;
        if(C == KeyEvent.VK_D) D=true;

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int C=e.getKeyCode();

        if(C == KeyEvent.VK_ENTER) Enter=false;
        if(C == KeyEvent.VK_SHIFT) Shift=false;
        if(C == KeyEvent.VK_SPACE) Space=false;

        if(C == KeyEvent.VK_UP) Up=false;
        if(C == KeyEvent.VK_DOWN) Down=false;
        if(C == KeyEvent.VK_LEFT) Left=false;
        if(C == KeyEvent.VK_RIGHT) Right=false;

        if(C == KeyEvent.VK_W) W=false;
        if(C == KeyEvent.VK_A) A=false;
        if(C == KeyEvent.VK_S) S=false;
        if(C == KeyEvent.VK_D) D=false;

    }

}