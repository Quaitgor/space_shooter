package movementV2;

import java.util.Random;

import entities.*;

/**
 * Moves towards a randomly determined direction not exceeding a certain angle
 * and rotates with a random speed.
 * @author philipp
 *
 */
public class RandomStraightTrigRotate extends Move{
	private double rotation = 0;

	public RandomStraightTrigRotate(Entity getOwner, int randomRange) {
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
		rotation = 2.0 + 4 * r.nextDouble();
	}
	protected void calculateMove(){
	}
	protected void makeMove(){
		owner.LayerDatas.firstElement().rotation += rotation;
		owner.posX += nposX;
		owner.posY += nposY;
	}
}
