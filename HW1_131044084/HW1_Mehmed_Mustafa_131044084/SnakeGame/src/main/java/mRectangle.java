/**
 * Created by Mehmed Mustafa on 26-Oct-17.
 */

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics2D;

public class mRectangle {

    // The size of the pixels
    public static final int BIT_SIZE = 20;
    private int xCoord, yCoord;

    public int getX(){ return xCoord; }
    public int getY(){ return yCoord; }

    public void setPosition(int xCoord, int yCoord){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    // For generation of random items
    public void setRandomPosition(int maxX, int maxY){
        int randomX = (int)(Math.random() * (maxX - BIT_SIZE));
        randomX = randomX - (randomX % BIT_SIZE);

        int randomY = (int)(Math.random() * (maxY - BIT_SIZE));
        randomY = randomY - (randomY % BIT_SIZE);

        this.setPosition(randomX, randomY);
    }

    // For movement of the snake rectangles
    public void move(int dx, int dy){
        xCoord += dx;
        yCoord += dy;
    }

    public Rectangle getBound(){
        return new Rectangle(xCoord, yCoord, BIT_SIZE, BIT_SIZE);
    }

    public boolean isCollision(mRectangle otherMRectangle){
        if(otherMRectangle == this)
            return false;

        return getBound().intersects(otherMRectangle.getBound());
    }

    public void refreshRectangle(Graphics2D g2d, Color color){
        g2d.setColor(color);
        g2d.fillRect(xCoord + 1, yCoord + 1, BIT_SIZE - 2, BIT_SIZE - 2);
    }
}


