/**
 * Created by Mehmed Mustafa on 25-Oct-17.
 */

import java.util.List;

public class ForeignLanguage extends ImprovementDecorator {
    Snake snake;

    public ForeignLanguage(Snake snake){
        this.snake = snake;
    }

    @Override
    public mRectangle getHead(){ return snake.getHead(); }

    @Override
    public List<mRectangle> getTail(){ return snake.getTail(); }

    @Override
    public String getDecoratorInfo() {
        return "Foreign Language: Increases the snake's score multiplier by 2";
    }

    @Override
    public double getScore(){ return snake.getScore(); }

    @Override
    public double getScoreMultiplier() {
        return 2.0 * snake.getScoreMultiplier();
    }

    @Override
    public int getStamina() { return snake.getStamina(); }

    @Override
    public int decreaseStamina(){ return snake.decreaseStamina(); }
}
