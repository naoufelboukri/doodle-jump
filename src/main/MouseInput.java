package main;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseInput implements MouseListener, MouseMotionListener, MouseWheelListener{

    Game game;
    public MouseInput(Game G) {
        game=G;
    }


    public void mouseClicked(MouseEvent e) {
        Point point = e.getPoint();
        game.Click(point);
    }
    public void mousePressed(MouseEvent e) {

    }
    public void mouseReleased(MouseEvent e) {

    }
    public void mouseEntered(MouseEvent e) {

    }
    public void mouseExited(MouseEvent e) {

    }
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        double scroll=e.getPreciseWheelRotation();
        game.Scroll(scroll);
    }
    @Override
    public void mouseDragged(MouseEvent e) {

    }
    @Override
    public void mouseMoved(MouseEvent e) {
        Point point = e.getPoint();
        game.Hover(point);
    }

}