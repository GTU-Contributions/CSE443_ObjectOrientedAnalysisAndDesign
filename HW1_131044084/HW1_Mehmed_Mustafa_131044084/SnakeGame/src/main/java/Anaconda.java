/**
 * Created by Mehmed Mustafa on 25-Oct-17.
 */

import java.util.ArrayList;
import java.util.List;

public class Anaconda extends Snake {

    Anaconda(){
        head = new mRectangle();
        tail = new ArrayList<mRectangle>();
        scoreMultiplier = 2.0;
        snakeStamina = 100;
        snakeLength = snakeLength * 2;
    }

    @Override
    public mRectangle getHead(){ return head; }

    @Override
    public List<mRectangle> getTail(){ return tail; }

    @Override
    public double getScore() { return this.score; }

    @Override
    public double getScoreMultiplier() { return scoreMultiplier;}

    @Override
    public int getStamina() { return snakeStamina; }

    @Override
    public int decreaseStamina(){ return snakeStamina--; }
}
