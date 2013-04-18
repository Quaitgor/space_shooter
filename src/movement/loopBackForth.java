package movement;

import moveables.Movable;
import graphics.GS;

public class loopBackForth extends Move{
	private int testdelta = 0;
	boolean reverse;
	public loopBackForth(Movable getOwner, double moveX, double moveY) {
		super(getOwner);
		nposX = moveX;
		nposY = moveY;
	}
	protected void calculateMove(){
		testdelta += owner.delta;
		if (testdelta >= 200 && reverse){
			nposX += 0.2;
			testdelta = 0;
		}
		else if (testdelta >= 200 && !reverse){
			nposX -= 0.2;
			testdelta = 0;
		}
		if (nposX >= 2) reverse = false;
		if (nposX <= -2 )reverse = true;

		System.out.println(GS.player.posX);
	}
	protected void makeMove(){
		owner.posX += nposX;
		owner.posY += nposY;
	}
}
