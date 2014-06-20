import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by geoffrey on 19/06/14.
 */
public class Board extends JPanel implements ActionListener{

    Dude p;
    public Image img;
    public Image fond;
    Timer time;

    public Board(){
        p = new Dude();
        addKeyListener(new AL());
        setFocusable(true);
        ImageIcon i = new ImageIcon("test.jpg");
        ImageIcon j = new ImageIcon("fondFlappy.jpg");
        img = i.getImage();
        fond= j.getImage();
        fond	= fond.getScaledInstance(785,625,Image.SCALE_DEFAULT);

        img = img.getScaledInstance(1350,35,Image.SCALE_DEFAULT);
        time = new Timer(4, this);
        time.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        p.move();
        p.y+=p.fall;
        if(p.fall<=0){
            p.count--;
            if(p.count==0){
                p.fall=1;
            }
        }
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(fond,0, 0, null);

        if ((p.getX() - 575) % 2400 == 0){
            p.nx=0;
        }
        if ((p.getX()- 1775) % 2400 == 0) {
            p.nx2=0;
        }
        g2D.drawImage(img, 700-p.nx2, 300, null);
        /*
         *Ici mettre le traitement pour la répétition d'image
         */
        //System.out.println(p.getX());
        if (p.getX() > 575){
            g2D.drawImage(img, 700-p.nx, 300, null);
        }

        g2D.drawImage(p.getStill(), 75, p.getY(), null);

    }

    private class AL extends KeyAdapter {
        public void keyRealised(KeyEvent e){
            p.keyReleased(e);

        }

        @Override
        public void keyPressed(KeyEvent e) {
            p.keyPressed(e);
        }
        @Override
        public void keyReleased(KeyEvent e) {
            p.keyReleased(e);
        }
    }
}
