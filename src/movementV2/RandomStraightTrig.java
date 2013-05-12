package movementV2;

import java.util.Random;

import entities.Entity;

/**
 * Moves towards a randomly determined direction not exceeding the specified angle.
 */
public class RandomStraightTrig extends Move{


	public RandomStraightTrig(Entity getOwner, int randomRange) {
		super(getOwner);
		Random r = new Random();
		double randomSpeed = 2 + 4* r.nextDouble();
		double ramdomAngle = 180+r.nextInt(randomRange);
		if(r.nextBoolean()){
			ramdomAngle *= -1;
		}
		ramdomAngle = (ramdomAngle/180)*Math.PI;
		nposX = Math.cos(ramdomAngle)*randomSpeed;
		nposY = Math.sin(ramdomAngle)*randomSpeed;
	}
	
	/**
	 * Not needed in this movement.
	 * */
	protected void calculateMove(){
	}
	
	/**
	 * @see Move.makeMove()
	 * */
	protected void makeMove(){
		owner.posX += nposX;
		owner.posY += nposY;
	}
}
