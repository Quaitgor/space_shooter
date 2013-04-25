package behaviours;

import movementV2.*;
import entities.*;
import graphics.GS;

public class RandomFlying extends Behave {
	private boolean once = false;
	double staytime = 0;
	double timer = 0;
	double range = 0;
	
	public RandomFlying(Moveable getOwner, double staytime, double range) {
		super(getOwner);
		this.staytime = staytime*1000;
		this.range = range;
		this.timer = 0+this.staytime;
	}

	protected void checkMind() {
		timer -= owner.delta;
		if (timer <= 0){
			System.out.println(timer + " / " + owner.delta);
			((TargetPosition)owner.movement).changeTarget(1, 200, 200);
			timer = staytime;
			
		}/*
		if (GS.player1 != null){
			double x = GS.player1.posX - owner.posX;
			double y = GS.player1.posY - owner.posY;
			double distance = Math.sqrt(Math.pow(x,  2)+Math.pow(y, 2));
			if(distance < range){
					((Offensive)owner).fire();
			}
		}*/
	}
}

