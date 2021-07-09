/**
 * Created by Mehmed Mustafa on 25-Oct-17.
 */

import java.util.List;

public class JobOffer extends ImprovementDecorator {
    Snake snake;

    public JobOffer(Snake snake){
        this.snake = snake;

        // Increase the snake's size by 1
        mRectangle eTemp = new mRectangle();
        eTemp.setPosition(-999, -999);
        snake.getTail().add(eTemp);
    }

    @Override
    public String getDecoratorInfo() {
        return "Job Offer: Increases the snake's tail by 1";
    }

    @Override
    public mRectangle getHead(){ return snake.getHead(); }

    @Override
    public List<mRectangle> getTail(){ return snake.getTail(); }

    @Override
    public double getScore(){
        return this.snake.getScore() + snake.getScoreMultiplier() * 1.0;
    }

    @Override
    public double getScoreMultiplier() {
        return snake.getScoreMultiplier();
    }

    @Override
    public int getStamina() { return snake.getStamina(); }

    @Override
    public int decreaseStamina(){ return snake.decreaseStamina(); }
}
