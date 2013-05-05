package movementV2;

import java.util.Random;

import entities.*;

/**
 * Moves towards the specified location and can rotate appropriately.
 * @author philipp
 *
 */
public class TargetPosition extends Move{
	private boolean rotation = false;
	private double speed;
	private boolean targetReached = false;
	private double xdiff;
	private double ydiff;
	
	public boolean getTargetReached(){return targetReached;}
	public TargetPosition(Entity getOwner, double speed, double x, double y, boolean rotation) {
		super(getOwner);
		this.speed = speed;
		this.rotation = rotation;
		changeTarget(x, y);
		
	}
	public void changeTarget(double speed, double x, double y){
		this.speed = speed;
		changeTarget(x,y);
	}
	public void changeTarget(double x, double y){
		targetReached = false;
		xdiff = owner.posX -x;
		ydiff = owner.posY -y;
		if (!(xdiff == 0 && ydiff == 0)){
			double angle = Math.atan(ydiff/xdiff);
			double reverse = -1;
			if(xdiff > 0)reverse = 1;
			nposX = Math.cos(angle)*this.speed*reverse;
			nposY = Math.sin(angle)*this.speed*reverse;
			
			if(rotation){
				double reverse2 = 0;
				if(xdiff < 0)reverse2 = 180;
				((Offensive)owner).mainTexture.rotation = 180+(angle/Math.PI*180-reverse2);
			}
		}
	}
	protected void calculateMove(){
	}
	protected void makeMove(){
		xdiff -= Math.abs(nposX);
		ydiff -= Math.abs(nposY);
		if((xdiff <= 0.0)&&(ydiff <= 0.0)){
			targetReached = true;
		}
		owner.posX -= nposX;
		owner.posY -= nposY;
	}
}
