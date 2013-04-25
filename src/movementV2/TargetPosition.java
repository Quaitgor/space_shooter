package movementV2;

import java.util.Random;

import entities.*;

public class TargetPosition extends Move{
	private boolean rotation = false;
	
	public TargetPosition(Entity getOwner, double speed, double x, double y, boolean rotation) {
		super(getOwner);
		this.rotation = rotation;
		changeTarget(speed, x, y);
		
	}
	public void changeTarget(double speed, double x, double y){
		double xdiff = owner.posX -x;
		double ydiff = owner.posY -y;
		if (xdiff != 0 && ydiff != 0){
			double angle = Math.atan(ydiff/xdiff);
			double reverse = -1;
			if(xdiff > 0)reverse = 1;
			nposX = Math.cos(angle)*speed*reverse;
			nposY = Math.sin(angle)*speed*reverse;
			
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
		owner.posX -= nposX;
		owner.posY -= nposY;
	}
}
