package pongGame;

import java.awt.Color;

public class Sprite {
	private int xPosition, yPosition;
	private int xVelocity, yVelocity;
	private int width, height;
	private Color color;
	
	
	public void setXPosition(int num) {
		this.xPosition = num;
	}
	
	public void setYPosition(int num) {
		this.yPosition = num;
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
