import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by geoffrey on 19/06/14.
 */
public class Board extends JPanel implements ActionListener{

    Perso p;
    boolean perdu = false;
    public Image img;
    public Image fond;
    Timer time;
    ArrayList <PipeLine> pipeLines = new ArrayList<PipeLine>();

    public Board(){
        p = new Perso();
        addKeyListener(new AL());
        setFocusable(true);
        ImageIcon i = new ImageIcon(("images/test.jpg"));
        ImageIcon j = new ImageIcon(("images/fondFlappy.jpg"));
        img = i.getImage();
        fond= j.getImage();
        fond	= fond.getScaledInstance(785,625,Image.SCALE_DEFAULT);

        img = img.getScaledInstance(1350,35,Image.SCALE_DEFAULT);
        time = new Timer(4, this);
        time.start();
        pipeLines.add(new PipeLine(new Random()));
        Font f = new Font("Serif", Font.PLAIN, 36);
        this.setFont(f);



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

        if (p.getY() < 0 || p.getY() > 270) {
            p.dx = 0;
            perdu = true;
        }

        else{
              for (int i=0; i<pipeLines.size(); i++){
                  if((75+30>=pipeLines.get(i).x)
                          &&(75<=pipeLines.get(i).x+50)
                          &&(p.getY()>=0)
                          &&(p.getY()<=pipeLines.get(i).y)){
                      perdu=true;
                      break;
                  }
                  if((75+30>=pipeLines.get(i).x)
                          &&(75<=pipeLines.get(i).x+50)
                          &&(p.getY()+30>=pipeLines.get(i).y+130)
                          &&(p.getY()<=365)) {
                      perdu = true;
                      break;
                  }


              }
            /*
            colisions tubes !!!
             */

        }
        if(perdu){
            p.dx=0;
        }

            repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(fond,0, 0, null);
        g2D.setColor(new Color(31, 90, 5));

        if(!perdu) {
            //System.out.println(pipeLines.size() + " avant la boucle");
            for (int i = 0; i < pipeLines.size(); i++) {
                if (pipeLines.get(i).x < -50)
                    pipeLines.remove(i);

                if (75 == pipeLines.get(i).x + 50)
                    p.score++;
                //System.out.println("##################### "+pipeLines.get(i).x+" , "+pipeLines.get(i).y+" ################");
                g2D.fillRect(pipeLines.get(i).x, 0, 50, pipeLines.get(i).y);
                g2D.fillRect(pipeLines.get(i).x, pipeLines.get(i).y + 130, 50, 365);
                pipeLines.get(i).x--;

            }


            if ((p.getX() - 575) % 2400 == 0) {
                p.nx = 0;
            }
            if ((p.getX() - 1775) % 2400 == 0) {
                p.nx2 = 0;
            }
            if (p.getX() % 300 == 0) {
                //System.out.println(pipeLines.size() + "########################################");
                pipeLines.add(new PipeLine(new Random()));

            }
            g2D.drawImage(img, 700 - p.nx2, 300, null);

            if (p.getX() > 575) {
                g2D.drawImage(img, 700 - p.nx, 300, null);
            }

            g2D.drawImage(p.getStill(), 75, p.getY(), null);
            g2D.setColor(new Color(0, 0, 0));
            String s = String.valueOf(p.score);
            g2D.drawString(s, 330, 40);
        }
        else{
            g2D.drawImage(img, 0, 300, null);
            g2D.setColor(new Color(0, 0, 0));
            String s = String.valueOf(p.score);
            g2D.drawString(s, 330, 40);
            g2D.drawImage(img, 700 - p.nx2, 300, null);
            Font font = new Font("Serif", Font.PLAIN, 350);
            g2D.setColor(new Color(250, 47, 47));
            g2D.drawString("Game-Over !",250,175);

        }



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
