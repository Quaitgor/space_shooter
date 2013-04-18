package movement;

import moveables.Movable;

import org.lwjgl.input.Mouse;

public class RandomFlying extends Move{
	//double player_bottom, tiles_bottom, player_right, tiles_right;
	//double b_collision, t_collision, l_collision, r_collision;
	int distance = 20;
	double accelspeed = 0.2;
	double maxspeed = 3.0;
	double newPosX, newPosY, oldPosX, oldPosY;
	boolean tracking = true;
	boolean stopped = false;
	boolean mousefree = true;
	double rX = 1;
	double rY = 1;
	double calcX  = 0;
	double calcY = 0;
	public RandomFlying(Movable getOwner, double moveX, double moveY) {
		super(getOwner);
		//changePosition(500, 600);
	}
	protected void changePosition(double x, double y){
		newPosX = x;
		newPosY = y;
		System.out.println(newPosX+" / " + newPosY);
		oldPosX = owner.posX;
		oldPosY = owner.posY;
		stopped = false;
		tracking = true;
		calculateMove();
	}
	
	protected void calculateMove(){
		calcX = newPosX - owner.posX;
		calcY = newPosY - owner.posY;
		
		if(Mouse.isButtonDown(0) && mousefree){
			mousefree = false;
			changePosition(Mouse.getX(), 768 - Mouse.getY());
			System.out.println(calcX+" / " + calcY);
			
		}
		if (!Mouse.isButtonDown(0)){
			mousefree = true;
		}
		if (calcX < 0) rX = -1; else rX = 1;
		if (calcY < 0) rY = -1; else rY = 1;
		if (Math.abs(calcX) >= distance || Math.abs(calcY) >= distance){
			if (tracking) {
				if (Math.abs(calcX) > Math.abs(calcY)){
					if (nposX < maxspeed && nposX > -1*maxspeed) nposX += rX*accelspeed;
					nposY = nposX*(100/calcX*calcY)/100;
				}
				else if(Math.abs(calcY) > Math.abs(calcX)){
					if (nposY < maxspeed && nposY > -1*maxspeed) nposY += rY*accelspeed;
					nposX = nposY*(100/calcY*calcX)/100;
				}
				else if (Math.abs(calcX) == Math.abs(calcY)){
					if (nposX < maxspeed && nposX > -1*maxspeed) nposX += rX*accelspeed;
					if (nposY < maxspeed && nposY > -1*maxspeed) nposY += rY*accelspeed;
				}
			}
		}else{
			tracking = false;
		}
		if (!tracking && !stopped){
			if (Math.abs(nposX) > Math.abs(nposY)){
				if (Math.abs(nposX) > accelspeed){
					if (nposX > 0) nposX -= accelspeed;
					if (nposX < 0) nposX += accelspeed;
				}else{
					nposX = 0;
				}
				if (calcY != 0 || calcX != 0) nposY = nposX*(100/calcX*calcY)/100;
			}
			else if(Math.abs(nposY) > Math.abs(nposX)){
				if (Math.abs(nposY) > accelspeed){
					if (nposY > 0) nposY -= accelspeed;
					if (nposY < 0) nposY += accelspeed;
				}else{
					nposY = 0;
				}
				if (calcY != 0 || calcX != 0) nposX = nposY*(100/calcY*calcX)/100;
			}
			else if (Math.abs(calcX) == Math.abs(calcY)){
				if (Math.abs(nposX) > accelspeed){
					if (nposX > 0) nposX -= accelspeed;
					if (nposX < 0) nposX += accelspeed;
				}else{
					nposX = 0;
				}
				if (Math.abs(nposY) > accelspeed){
					if (nposY > 0) nposY -= accelspeed;
					if (nposY < 0) nposY += accelspeed;
				}else{
					nposY = 0;
				}
			}
		}
		if (!tracking && nposX == 0 && nposY == 0 && !stopped){
			stopped = true;
		}
	}
	protected void makeMove(){
		owner.posX += nposX;
		owner.posY += nposY;
	}
}
