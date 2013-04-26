package behaviours;

import java.util.Random;

import movementV2.*;
import entities.*;
import graphics.GS;

public class RandomFlying extends Behave {
	private boolean once = false;
	double staytime = 1000;
	double timer = 1000;
	double range = 0;
	int limitX = 0;
	int limitY = 0;
	int addX = 0;
	int addY = 0;
	int speed = 0;
	
	public RandomFlying(Moveable getOwner, double staytime, double range, int limitX, int addX, int limitY, int addY, int speed) {
		super(getOwner);
		this.staytime = staytime;
		this.range = range;
		this.timer = this.staytime;
		this.limitX = limitX;
		this.limitY = limitY;
		this.addX = addX;
		this.addY = addY;
		this.speed = speed;
	}

	protected void checkMind() {
		timer -= owner.delta;
		if (timer <= 0){
			Random rnd = new Random();
			int rx = rnd.nextInt(1280-limitX);
			int ry = rnd.nextInt(768-limitY);
			rx += addX;
			rx -= addY;
			((TargetPosition)owner.movement).changeTarget(8, rx, ry);
			timer = staytime;			
		}
		if (GS.player1 != null){
			double x = GS.player1.posX - owner.posX;
			double y = GS.player1.posY - owner.posY;
			double distance = Math.sqrt(Math.pow(x,  2)+Math.pow(y, 2));
			if(distance < range){
				((Offensive)owner).fire();
			}
			
		}
		if(owner.posX > 1280 || owner.posX < 0||owner.posY > 768 || owner.posY < 0){
			timer = 0;
		}
	}
}

