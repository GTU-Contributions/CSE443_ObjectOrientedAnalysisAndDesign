/**
 * Created by Mehmed Mustafa on 28-Oct-17.
 */

import javax.swing.*;
import java.awt.*;

public class SnakeToolsAbstraction {
    Snake theSnake;

    int staminaCounter = 0;

    // The smaller the rate, the faster the stamina depletes
    private final static int STAMINA_DEPLETEMENT_RATE = 1;

    // Key input
    public static boolean up = false, down = false, right = false, left = false;

    // Snake Movement Direction
    public static int dx = 0, dy = 0;

    SnakeToolsAbstraction(){}
    SnakeToolsAbstraction(Snake snake){ theSnake = snake; }

    // Pop-up screen which allows the player to choose snake character
    private Snake snakeSelection(){
        Snake selectedSnake = null;

        JPanel panel = new JPanel();
        panel.add(new JLabel("Please select your snake: "));

        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("Python");
        model.addElement("Anaconda");

        JComboBox comboBox = new JComboBox(model);
        panel.add(comboBox);

        JOptionPane.showConfirmDialog(null, panel, "Snake Selector", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE);

        StringBuilder resultStr = new StringBuilder();
        resultStr.append(comboBox.getSelectedItem());

        if(resultStr.toString().equals("Python"))
            selectedSnake = new Python();

        if(resultStr.toString().equals("Anaconda"))
            selectedSnake = new Anaconda();

        return selectedSnake;
    }

    // Pop-up screen which allows the player to choose snake decoration
    public Snake decoratorSelection(Snake snakeToDecorate){
        Snake decoratedSnake = null;

        JPanel panel = new JPanel();
        panel.add(new JLabel("Please select your decoration: "));

        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("Internship: +1.5 Multiplier");
        model.addElement("Foreign Language: +2 Multiplier");
        model.addElement("Training Certificate: +3 Multiplier");

        JComboBox comboBox = new JComboBox(model);
        panel.add(comboBox);

        JOptionPane.showConfirmDialog(null, panel, "Decorator Selector", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE);

        StringBuilder resultStr = new StringBuilder();
        resultStr.append(comboBox.getSelectedItem());

        if(resultStr.toString().equals("Internship: +1.5 Multiplier"))
            decoratedSnake = new Internship(snakeToDecorate);

        if(resultStr.toString().equals("Foreign Language: +2 Multiplier"))
            decoratedSnake = new ForeignLanguage(snakeToDecorate);

        if(resultStr.toString().equals("Training Certificate: +3 Multiplier"))
            decoratedSnake = new TrainingCertificate(snakeToDecorate);

        return decoratedSnake;
    }

    public Snake getSnake(){
        return theSnake;
    }

    public void setSnake(Snake newSnake) { this.theSnake = newSnake; }

    // Create and initialize the snake
    public void initializeSnake(int BoardWidth, int BoardHeight){

        // Select the snake type with pop-up
        theSnake = snakeSelection();

        // Create the head of the snake and set it's position
        theSnake.getHead().setPosition(BoardWidth/2, BoardHeight/2);

        // Create the tail of the snake and set it's position
        theSnake.getTail().add(theSnake.getHead());
        for(int i = 1; i<theSnake.getSnakeLength(); ++i){
            mRectangle tailPiece = new mRectangle();
            tailPiece.setPosition(theSnake.getHead().getX() + (mRectangle.BIT_SIZE * i), theSnake.getHead().getY());
            theSnake.getTail().add(tailPiece);
        }
    }

    // Is snake Board or Self crashed methods
    public boolean BoardCrashed(int boardWidth, int boardHeight){
        return (theSnake.getHead().getX() < 0 || theSnake.getHead().getX() >= boardWidth ||
                theSnake.getHead().getY() < 0 || theSnake.getHead().getY() >= boardHeight);
    }
    public boolean SelfCrashed(){
        for(mRectangle rectangles : theSnake.getTail())
            if(rectangles.isCollision(theSnake.getHead()))
                return true;

        return false;
    }

    // Did the snake eat an object
    public boolean isEaten(mRectangle object){
        return theSnake.getHead().isCollision(object);
    }

    // Get snake movement
    public void getSnakeMovement(){
        // Get movement
        if(up && dy == 0){
            dy = -mRectangle.BIT_SIZE;
            dx = 0;
        }
        else if(down && dy == 0){
            dy = mRectangle.BIT_SIZE;
            dx = 0;
        }
        else if(left && dx == 0){
            dy = 0;
            dx = -mRectangle.BIT_SIZE;
        }
        else if(right && dx == 0){
            dy = 0;
            dx = mRectangle.BIT_SIZE;
        }
    }

    // Stop snake movement
    public void stopSnakeMovement(){
        up = false;
        down = false;
        left = false;
        right = false;
        dx = 0;
        dy = 0;
    }

    // Is snake stopped
    public boolean isSnakeStopped(){
        return (dx == 0 && dy == 0);
    }

    // Is snake moving
    public boolean isSnakeMoving(){
        return !isSnakeStopped();
    }

    // Update snake movement
    public void updateSnakeMovement(){

        // Update the snake if the snake is moving
        if(dx != 0 || dy != 0){
            for(int i = theSnake.getTail().size()-1; i > 0; --i){
                theSnake.getTail().get(i).setPosition(
                        theSnake.getTail().get(i-1).getX(),
                        theSnake.getTail().get(i-1).getY()
                );
            }
            theSnake.getHead().move(dx, dy);

            ++staminaCounter;
            if(staminaCounter == STAMINA_DEPLETEMENT_RATE){
                theSnake.decreaseStamina();
                staminaCounter = 0;
            }
        }
    }

    // To check if the snake's stamina is depleted
    public boolean isStaminaDepleted(){
        return (theSnake.getStamina() <= 0);
    }

    // Refresh the snake object
    public void refreshTheSnake(Graphics2D graphics2D){
        // Render the snake's head
        theSnake.getHead().refreshRectangle(graphics2D, Color.BLUE);

        // Render  the snake's tail
        for(int i = 1; i < theSnake.getTail().size(); ++i){
            theSnake.getTail().get(i).refreshRectangle(graphics2D, Color.GREEN);
        }
    }

}
