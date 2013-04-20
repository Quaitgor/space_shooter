package movementV2;

import java.util.Random;

import entities.Entity;

public class Straight extends Move{
	//private int testdelta = 0;
	public boolean randomRotate = false;
	private double rotation = 0;
	
	public Straight(Entity getOwner, double angle, double speed) {
		super(getOwner);
		nposX = 0;
		nposY = 0;
	}
	protected void calculateMove(){
	}
	public void randomRotate(boolean switcher){
		randomRotate = switcher;
		if (randomRotate){
    		Random r = new Random();
    		rotation = -2.0 + ( 2.0 - -2.0) * r.nextDouble();
			
		}
	}
	protected void makeMove(){
    	if (randomRotate) owner.LayerDatas.get(0).rotation += rotation;
		owner.posX -= nposX;
		owner.posY -= nposY;
	}
}
