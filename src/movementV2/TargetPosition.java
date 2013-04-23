package movementV2;

import java.util.Random;

import entities.Entity;

public class TargetPosition extends Move{
	//private int testdelta = 0;
	public boolean randomRotate = false;
	private double rotation = 0;
	private double tx;
	private double ty;
	
	public TargetPosition(Entity getOwner, double speed, double x, double y) {
		super(getOwner);
		changeTarget(speed, x, y);
	}
	public void changeTarget(double speed, double x, double y){
		double xdiff = x - owner.posX;
		double ydiff = y - owner.posY;
		speed = speed/(ydiff+xdiff);
		nposX = xdiff*speed;
		nposY = ydiff*speed;
	}
	protected void calculateMove(){
	}
	protected void makeMove(){
    	if (randomRotate) owner.LayerDatas.get(0).rotation += rotation;
		owner.posX -= nposX;
		owner.posY -= nposY;
	}
}
