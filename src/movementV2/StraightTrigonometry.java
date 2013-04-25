package movementV2;

import java.util.Random;

import entities.Entity;

public class StraightTrigonometry extends Move{
	//private int testdelta = 0;
	public boolean randomRotate = false;
	private double rotation = 0;
	
	public StraightTrigonometry(Entity getOwner, double speed, double angle) {
		super(getOwner);
		angle = (angle/180)*Math.PI;
		nposX = Math.cos(angle)*speed;
		nposY = Math.sin(angle)*speed;
	}
	protected void calculateMove(){
	}
	protected void makeMove(){
    	if (randomRotate) owner.LayerDatas.get(0).rotation += rotation;
		owner.posX += nposX;
		owner.posY += nposY;
	}
}
