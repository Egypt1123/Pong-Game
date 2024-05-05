package pongGame;

import java.awt.Color;
import java.awt.Rectangle;

public class Sprite {
	private int initialXPosition, initialYPosition;

	private int xPosition, yPosition;
	private int xVelocity, yVelocity;
	private int width, height;
	private Color color;
	
	 public void setInitialPosition(int initialX, int initialY) {
	       initialXPosition = initialX;
	       initialYPosition = initialY;
	 }
	       
	 public void resetToInitialPosition() {
	      setXPosition(initialXPosition);
	      setYPosition(initialYPosition);
	 }
	 
	 public Rectangle getRectangle() {
		 
		 	return new Rectangle(getXPosition(), getYPosition(), getWidth(), getHeight());
	 }
	 
	public void setXPosition(int num) {
		this.xPosition = num;
	}
	
	public void setYPosition(int num) {
		this.yPosition = num;
	}
	
	public void setXPosition(int newX, int panelWidth) {
	       xPosition = newX;
	       if(xPosition < 0) {
	           xPosition = 0;
	       } else if(xPosition + width > panelWidth) {
	           xPosition = panelWidth - width;
	       }
	 }
	 public void setYPosition(int newY, int panelHeight) {
	      yPosition = newY;
	      if(yPosition < 0) {
	          yPosition = 0;
	      } else if(yPosition + height > panelHeight) {
	          yPosition = panelHeight - height;
	      }
	 }
	public void setXVelocity(int num) {
		this.xVelocity = num;
	}
	
	public void setYVelocity(int num) {
		this.yVelocity = num;
	}
	
	public void setWidth(int num) {
		this.width = num;
	}
	
	public void setHight(int num) {
		this.height = num;
	}
	
	public int getXPosition() {
		return this.xPosition;
	}
	
	public int getYPosition() {
		return this.yPosition;
	}
	
	public int getXVelocity() {
		return this.xVelocity;
	}
	
	public int getYVelocity() {
		return this.yVelocity;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
	}
}
