import java.util.List;

/**
 * Created by Mehmed Mustafa on 25-Oct-17.
 */

public class TrainingCertificate extends ImprovementDecorator {
    Snake snake;

    public TrainingCertificate(Snake snake){
        this.snake = snake;
    }

    @Override
    public String getDecoratorInfo() {
        return "Training Certificate: Increases the snake's score multiplier by 3";
    }

    @Override
    public mRectangle getHead(){ return snake.getHead(); }

    @Override
    public List<mRectangle> getTail(){ return snake.getTail(); }

    @Override
    public double getScoreMultiplier() {
        return 3.0 * snake.getScoreMultiplier();
    }

    @Override
    public double getScore(){ return snake.getScore(); }

    @Override
    public int getStamina() { return snake.getStamina(); }

    @Override
    public int decreaseStamina(){ return snake.decreaseStamina(); }
}
