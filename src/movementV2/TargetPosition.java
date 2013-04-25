package movementV2;

import java.util.Random;

import entities.Entity;

public class TargetPosition extends Move{
	
	public TargetPosition(Entity getOwner, double speed, double x, double y) {
		super(getOwner);
		changeTarget(speed, x, y);
	}
	public void changeTarget(double speed, double x, double y){
		double xdiff = x - owner.posX - x;
		double ydiff = y - owner.posY - x;
		speed = speed/(ydiff+xdiff);
		nposX = xdiff*speed;
		nposY = ydiff*speed;
	}
	protected void calculateMove(){
	}
	protected void makeMove(){
		owner.posX -= nposX;
		owner.posY -= nposY;
	}
}
