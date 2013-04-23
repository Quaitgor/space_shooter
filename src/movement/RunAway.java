package movement;

import moveables.Movable;
import graphics.GS;

public class RunAway extends Move{
	double player_bottom, tiles_bottom, player_right, tiles_right;
	double b_collision, t_collision, l_collision, r_collision;
	int distance = 150;
	double accelspeed = 0.2;
	double maxspeed = 3.0;
	
	public RunAway(Movable getOwner, double moveX, double moveY) {
		super(getOwner);
	}
	protected void calculateMove(){
		player_bottom = GS.player.posY + GS.player.spriteHeight;
		tiles_bottom = owner.posY + owner.spriteHeight;
		player_right = GS.player.posX + GS.player.spriteWidth;
		tiles_right = owner.posX + owner.spriteWidth;

		b_collision = tiles_bottom - GS.player.posY;
		t_collision = player_bottom - owner.posY;
		l_collision = player_right - owner.posX;
		r_collision = tiles_right - GS.player.posX;
		
		
		if (b_collision >= -distance && t_collision >= -distance && l_collision >= -distance && r_collision >= -distance){
			

			if (nposX < maxspeed) nposX -=(accelspeed*(100/(tiles_right/2)*(player_right - tiles_right))/100);
			if (nposY < maxspeed) nposY -=(accelspeed*(100/(tiles_bottom/2)*(player_bottom - tiles_bottom))/100);
			/*
			if (t_collision < b_collision && t_collision < l_collision && t_collision < r_collision ){      
				//System.out.println("Top crash");
				if (nposY < maxspeed) nposY +=accelspeed;
			}
			if (b_collision < t_collision && b_collision < l_collision && b_collision < r_collision){
				//System.out.println("Bottom Crash");
				if (nposY < maxspeed) nposY -=accelspeed;
				double temp = player_right - tiles_right;
				double percent = 100/(tiles_right/2)*temp;
				if (nposX < maxspeed) nposX -=(accelspeed*percent/100);
			}
			if (l_collision < r_collision && l_collision < t_collision && l_collision < b_collision){
				//System.out.println("Left Crash");
				if (nposX < maxspeed) nposX +=accelspeed;
			}
			if (r_collision < l_collision && r_collision < t_collision && r_collision < b_collision){
				//System.out.println("Right Crash");
				if (nposX < maxspeed) nposX -=accelspeed;
			}*/
		}else{
			if (nposX > 0.0) nposX -= accelspeed;
			if (nposY > 0.0) nposY -= accelspeed;
			if (nposX <= accelspeed) nposX = 0;
			if (nposY <= accelspeed) nposY = 0;
		}
		
		
	}
	protected void makeMove(){
		owner.posX += nposX;
		owner.posY += nposY;
	}
}
