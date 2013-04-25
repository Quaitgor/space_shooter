package behaviours;

import entities.*;
import graphics.GS;

public class TestDetectRange extends Behave {
	private boolean once = false;
	
	public TestDetectRange(Moveable getOwner) {
		super(getOwner);
	}

	protected void checkMind() {
		if (GS.player1 != null){
			double x = GS.player1.posX - owner.posX;
			double y = GS.player1.posY - owner.posY;
			double distance = Math.sqrt(Math.pow(x,  2)+Math.pow(y, 2));
			if(distance < 300){
				if (!once){
					((Offensive)owner).fire();
					System.out.println("sada");
				}
			}
			
		}
		
	}

}
