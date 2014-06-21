import java.util.ArrayList;
import java.util.Random;

/**
 * Created by geoffrey on 20/06/14.
 */
public class PipeLine {
    public int x,y;

    public PipeLine(Random rand){
        x = 700;
        y = (rand.nextInt(300-120-20+1) + 20);
    }

}
