/**
 * Created by Mehmed Mustafa on 25-Oct-17.
 */

import java.util.List;

public class Internship extends ImprovementDecorator {
    Snake snake;

    public Internship(Snake snake){
        this.snake = snake;
    }

    @Override
    public String getDecoratorInfo() {
        return "Internship: Increases the snake's score multiplier by 1.5";
    }

    @Override
    public mRectangle getHead(){ return snake.getHead(); }

    @Override
    public List<mRectangle> getTail(){ return snake.getTail(); }

    @Override
    public double getScore(){ return snake.getScore(); }

    @Override
    public double getScoreMultiplier() {
        return 1.5 * snake.getScoreMultiplier();
    }

    @Override
    public int getStamina() { return snake.getStamina(); }

    @Override
    public int decreaseStamina(){ return snake.decreaseStamina(); }
}