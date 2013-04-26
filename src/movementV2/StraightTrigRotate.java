package movementV2;

import java.util.Random;

import entities.*;

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
	protected void calculateMove(){
	}
	protected void makeMove(){
    	((Offensive)owner).mainTexture.rotation += rotation;
		owner.posX += nposX;
		owner.posY += nposY;
	}
}
