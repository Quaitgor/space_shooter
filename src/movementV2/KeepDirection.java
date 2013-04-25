package movementV2;

import java.util.Random;

import entities.Entity;

public class KeepDirection extends Move{
	//private int testdelta = 0;
	public boolean randomRotate = false;
	private double rotation = 0;
	
	public KeepDirection(Entity getOwner, double nposX, double nposY) {
		super(getOwner);
		nposX = nposX;
		nposY = nposY;
	}
	protected void calculateMove(){
	}
	protected void makeMove(){
//    	if (randomRotate) owner.LayerDatas.get(0).rotation += rotation;
		owner.posX -= nposX;
		owner.posY -= nposY;
	}
}
