package movement;

import java.util.Random;

import entities.*;

/**
 * Moves straight into the direction determined by the specified angle and
 * rotates with random speed.
 */
public class StraightTrigRotate extends Move{
	private double rotation = 0;

	public StraightTrigRotate(Entity getOwner, double speed, double angle) {
		super(getOwner);
		angle = (angle/180)*Math.PI;
		nposX = Math.cos(angle)*speed;
		nposY = Math.sin(angle)*speed;
		Random r = new Random();
		rotation = -2.0 + ( 2.0 - -2.0) * r.nextDouble();
	}

	
	/**
	 * This method makes necessary recalulation before moving anything.
	 * Not needed in this Movement
	 * */
	protected void calculateMove(){
	}

	/**
	 * This method sends the final movement command
	 * */
	protected void makeMove(){
    	((Offensive)owner).mainTexture.rotation += rotation;
		owner.posX += nposX;
		owner.posY += nposY;
	}
}
