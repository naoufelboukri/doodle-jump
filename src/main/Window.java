package main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

public class Window extends Canvas{

    public JFrame frame;
    Game game;

    public Window(Game G) {

        game=G;

        Dimension screenSize=new Dimension(Game.WIDTH,Game.HEIGHT);
        frame = new JFrame("IA");

        frame.setLocation(new Point(500,100));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(game);
        game.setPreferredSize(screenSize);

        frame.pack();
        frame.setVisible(true);

    }

}