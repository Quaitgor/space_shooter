package movementV2;

import entities.Entity;

/**
 * This Movementpattern has a swaying motion included to the movement speed.
 * The swaying can changed horizontal or vertical with the axis-boolean.
 * */
public class Sway extends Move{
	//private int testdelta = 0;
	public double sway, originalX, originalY, range, maxSpeed = 5.0, speed;
	public boolean randomRotate = false;
	public boolean reverse = false;
	//private double rotation = 0;
	protected boolean axis = true;
	
	public Sway(Entity getOwner, double range, double speed, double maxSpeed) {
		super(getOwner);
		this.speed = -speed;
		nposY = 0;
		this.range = range;
		originalX = owner.posX;
		originalY = owner.posY;
	}
	
	/**
	 * This method makes nessesary recalulation before moving anything.
	 * Not needed in this Movement
	 * */
	protected void calculateMove(){
		if(!reverse){
			if(sway > -1* maxSpeed)sway -= 0.2;
		}
		if(reverse){
			if(sway < maxSpeed)sway += 0.2;
		}
	}
	
	/**
	 * This method sends the final movement command and checks the swaymotions direction
	 * */
	protected void makeMove(){
		if(axis){
			nposX = speed;
			nposY = sway;
		}else{
			nposX = sway;
			nposY = speed;
		}
		owner.posX += nposX;
		owner.posY += nposY;
		
		double posi, originalPosi;
		if(axis){
			posi = owner.posY;
			originalPosi = originalY;
		}else{
			posi = owner.posX;
			originalPosi = originalX;
			
		}
		if(reverse && posi > originalPosi + range){
			reverse = false;
		}
		if(!reverse && posi < originalPosi - range){
			reverse = true;
		}
	}
}
