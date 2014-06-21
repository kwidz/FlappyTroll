import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by geoffrey on 19/06/14.
 */
public class Perso {

    int x, dx=1, y, nx2, nx, fall=1, count=0;
    Image still;
    int score = 0;

    public Perso(){
        ImageIcon i = new ImageIcon("perso.png");
        still = i.getImage();
        still	= still.getScaledInstance(30,30,Image.SCALE_DEFAULT);
        x = 75 ; y=172; nx2 = 700;



    }

    public void move(){
        x = x + dx;
        nx2 = nx2+dx;
        nx = nx+dx;

    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Image getStill(){
        return still;
    }

    public int getDx() {
        return dx;
    }

    public int getNx2() {
        return nx2;
    }

    public int getNx() {
        return nx;
    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        /*if (key == KeyEvent.VK_LEFT) {
            dx = -1;
            //still = l.getImage();
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
            //still = l.getImage();
        } */
        if (key == KeyEvent.VK_UP) {
            fall=-3;
            count+=30;
            //still = l.getImage();
        }

    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
            dx = 0;

        if (key == KeyEvent.VK_RIGHT)
            dx = 0;


    }


}
