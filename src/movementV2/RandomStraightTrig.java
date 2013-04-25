package movementV2;

import java.util.Random;

import entities.Entity;

public class RandomStraightTrig extends Move{
	
	public RandomStraightTrig(Entity getOwner) {
		super(getOwner);
		Random r = new Random();
		double randomSpeed = 2 + 4* r.nextDouble();
		double ramdomAngle = 180+r.nextInt(30);
		if(r.nextBoolean()){
			ramdomAngle *= -1;
		}
		ramdomAngle = (ramdomAngle/180)*Math.PI;
		nposX = Math.cos(ramdomAngle)*randomSpeed;
		nposY = Math.sin(ramdomAngle)*randomSpeed;
	}
	protected void calculateMove(){
	}
	protected void makeMove(){
		owner.posX += nposX;
		owner.posY += nposY;
	}
}
