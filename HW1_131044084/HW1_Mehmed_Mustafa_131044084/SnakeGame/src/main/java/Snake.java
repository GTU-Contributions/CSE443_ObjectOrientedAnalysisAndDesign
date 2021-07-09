/**
 * Created by Mehmed Mustafa on 25-Oct-17.
 */

import java.util.List;

public abstract class Snake {

    // The snake
    protected mRectangle head;
    protected List<mRectangle> tail;

    // The base length of the snake
    protected int snakeLength = 3;
    // The base stamina of the snake
    protected int snakeStamina = 0;
    // The score multiplier of the snake
    protected double scoreMultiplier = 0.0;
    // The score of the snake
    protected double score = 0;

    public int getSnakeLength(){ return snakeLength; }

    public abstract mRectangle getHead();
    public abstract List<mRectangle> getTail();

    public abstract double getScore();
    public abstract double getScoreMultiplier();
    public abstract int getStamina();
    public abstract int decreaseStamina();
}