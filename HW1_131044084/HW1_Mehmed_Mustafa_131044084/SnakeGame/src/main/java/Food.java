/**
 * Created by Mehmed on 25-Oct-17.
 */

import java.util.List;

public class Food extends ImprovementDecorator {
    Snake snake;

    public Food(Snake snake){
        this.snake = snake;
    }

    @Override
    public String getDecoratorInfo() {
        return "Food: Increases the snake's stamina by 50";
    }

    @Override
    public mRectangle getHead(){ return snake.getHead(); }

    @Override
    public List<mRectangle> getTail(){ return snake.getTail(); }

    @Override
    public double getScore(){ return snake.getScore(); }

    @Override
    public double getScoreMultiplier() {
        return snake.getScoreMultiplier();
    }

    @Override
    public int getStamina() {
        return snake.getStamina() + 50;
    }

    @Override
    public int decreaseStamina(){ return snake.decreaseStamina(); }
}
