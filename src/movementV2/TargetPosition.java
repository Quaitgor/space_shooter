package movementV2;

import entities.*;

/**
 * Moves towards the specified location and can rotate appropriately.
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
	
	/**
	 * With this method a new target on the screen can be defined, the object
	 * will move toward that object. The speed can be adjusted
	 * */
	public void changeTarget(double speed, double x, double y){
		this.speed = speed;
		changeTarget(x,y);
	}
	
	/**
	 * With this method a new target on the screen can be defined, the object
	 * will move toward that object
	 * */
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
	
	/**
	 * This method makes nessesary recalulation before moving anything.
	 * */
	protected void calculateMove(){
		xdiff -= Math.abs(nposX);
		ydiff -= Math.abs(nposY);
		if((xdiff <= 0.0)&&(ydiff <= 0.0)){
			targetReached = true;
		}
	}
	
	/**
	 * This method sends the final movement command
	 * */
	protected void makeMove(){
		owner.posX -= nposX;
		owner.posY -= nposY;
	}
}
