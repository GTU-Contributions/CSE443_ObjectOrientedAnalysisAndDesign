/**
 * Created by Mehmed Mustafa on 25-Oct-17.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class GameWindow extends JPanel implements Runnable {

    // Window properties
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;

    // Graphics
    private Graphics2D graphics2D;
    private BufferedImage image;

    // Game Loop Variables
    private Thread thread;
    private boolean running;
    private long desiredTime;

    // Game Stuff
    SnakeToolsAbstraction snakeController = new SnakeToolsAbstraction();
    // Current game's level
    private int level = 1;
    // For game over
    private static boolean gameover;
    // To know when to increase the level
    private int eatenObjects = 0;

    // Objects which will be generated on the game screen
    private mRectangle food;

    private mRectangle jobOffer;
    // How often the job offers will change
    private int jobOfferTimer = 0;
    // The lower the rate, the faster is the change
    private final int JOB_CHANGE_RATE = 50;

    // Adapter for key events
    private class M_Adapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            int pressedKey = e.getKeyCode();

            if ((pressedKey == KeyEvent.VK_UP) && (!snakeController.down)) {
                snakeController.up = true;
                snakeController.left = false;
                snakeController.right = false;
            }

            if ((pressedKey == KeyEvent.VK_DOWN) && (!snakeController.up)) {
                snakeController.down = true;
                snakeController.left = false;
                snakeController.right = false;
            }

            if((pressedKey == KeyEvent.VK_LEFT) && (!snakeController.right)){
                snakeController.left = true;
                snakeController.down = false;
                snakeController.up = false;
            }

            if ((pressedKey == KeyEvent.VK_RIGHT) && (!snakeController.left)) {
                snakeController.right = true;
                snakeController.down = false;
                snakeController.up = false;
            }
        }
    }

    // Game Window
    public GameWindow(){
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setFocusable(true);
        requestFocus();
        addKeyListener(new M_Adapter());
    }

    // Create new game window
    public void create(){
        JFrame frame = new JFrame("Job Hunting Snake");
        frame.setContentPane(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void addNotify(){
        super.addNotify();
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run(){

        image = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        graphics2D = image.createGraphics();
        running = true;

        // Start the game
        newGame();

        // Timer counters
        long startTime, waitTime, elapsedTime;

        while(running){
            startTime = System.nanoTime();

            update();
            requestScreenRefresh();

            elapsedTime = System.nanoTime() - startTime;
            waitTime = desiredTime - elapsedTime / 1000000;
            if(waitTime > 0){
                try{
                    Thread.sleep(waitTime);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void setFPS(int fps){
        desiredTime = 1000 / fps;
    }
    private void increaseFPS(int level) { desiredTime = 1000 / (level + 5); }

    private void newGame(){

        // Create the snake
        snakeController.initializeSnake(WINDOW_WIDTH, WINDOW_HEIGHT);

        // Stop snake movement from previous games
        snakeController.stopSnakeMovement();

        // Reset game variables
        gameover = false;

        // To know when to increase the level
        eatenObjects = 0;

        // Reset the game level
        level = 1;

        // Set the FPS to 5
        setFPS(5);

        // Create and set food
        food = new mRectangle();
        food.setRandomPosition(WINDOW_WIDTH, WINDOW_HEIGHT);

        // Create and set job offer
        jobOffer = new mRectangle();
        jobOffer.setRandomPosition(WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    private void update(){
        // Check if the game is over
        if(gameover){
            // Start a new game
            newGame();
            return;
        }

        // WARNING THE STAMINA IS DECREASING VERY FAST AFTER EVERY LEVEL
        if(!gameover && snakeController.isSnakeMoving())
            //snakeController.getSnake().decreaseStamina();
            if(snakeController.isStaminaDepleted())
                gameover = true;

        snakeController.getSnakeMovement();
        snakeController.updateSnakeMovement();
        checkCollisionsState();
    }

    private void checkCollisionsState(){
        // Check if the snake crashed on the borders of the window
        // Check if the snake crashed on itself
        if(snakeController.BoardCrashed(WINDOW_WIDTH, WINDOW_HEIGHT) || snakeController.SelfCrashed()){
            gameover = true;
            return;
        }

        checkFoodCollision();

        checkOfferCollision();
    }

    // Check if the snake ate the food
    private void checkFoodCollision(){

        if(snakeController.isEaten(food)){
            // Decorate the snake with the food
            snakeController.setSnake(new Food(snakeController.getSnake()));
            // Generate new food object
            food.setRandomPosition(WINDOW_WIDTH, WINDOW_HEIGHT);
        }
    }

    private void checkOfferCollision(){
        ++jobOfferTimer;

        if(jobOfferTimer == JOB_CHANGE_RATE){
            // Set new job offer
            jobOffer.setRandomPosition(WINDOW_WIDTH, WINDOW_HEIGHT);
            // Reset the job offer timer
            jobOfferTimer = 0;
        }

        // Check if the snake ate the job offer
        if(snakeController.isEaten(jobOffer)){

            // Count the eaten job offers
            ++eatenObjects;

            // Decorate the snake with the job offer
            snakeController.setSnake(new JobOffer(snakeController.getSnake()));

            // After 5 eaten job offers
            if(eatenObjects % 5 == 0){
                jobOfferTimer = 0;
                eatenObjects = 0;

                // Give a pause chance to the player after the decoration
                snakeController.stopSnakeMovement();

                // Pop-up screen giving the player to select a decoration
                snakeController.setSnake(snakeController.decoratorSelection(snakeController.getSnake()));

                // The maximum possible game level is 10
                if(level < 10){
                    // Increase the game level
                    ++level;
                }

                // Increase the game speed according to the game level
                increaseFPS(level);
            }

            jobOffer.setPosition(-99, -99);
        }
    }

    private void requestScreenRefresh(){
        refreshScreen(graphics2D);
        Graphics g = getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
    }

    private void refreshScreen(Graphics2D graphics2D){
        // Clear the screen
        graphics2D.clearRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Render the food
        food.refreshRectangle(graphics2D, Color.YELLOW);
        graphics2D.drawString("Food", food.getX()-food.BIT_SIZE/4, food.getY()-food.BIT_SIZE/4);

        // Render the job offer if it's available
        jobOffer.refreshRectangle(graphics2D, Color.RED);
        graphics2D.drawString("Job Offer", jobOffer.getX()-jobOffer.BIT_SIZE/2, jobOffer.getY()-jobOffer.BIT_SIZE/4);

        snakeController.refreshTheSnake(graphics2D);

        if(snakeController.isSnakeStopped()){
            // Print the movement info
            graphics2D.setColor(Color.BLUE);
            graphics2D.drawString("Press your direction arrow key to continue.", 10, 25);
        }

        // Print the game info
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString("Score: " + snakeController.getSnake().getScore() + "   Level: " + level +
                       "    Multiplier: " + snakeController.getSnake().getScoreMultiplier() +
                        "   Stamina: " + snakeController.getSnake().getStamina(), 10, 10);

        if(gameover){
            // Print the game over text
            graphics2D.setColor(Color.RED);
            if(snakeController.getSnake().getStamina() == 0)
                graphics2D.drawString("You run out of stamina! GAME OVER!", 10, 25);
            else
                graphics2D.drawString("GAME OVER!", 10, 25);
        }
    }
}

