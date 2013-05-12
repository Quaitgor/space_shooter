package behaviours;

import java.util.Random;

import movementV2.Move;
import movementV2.StraightTrigonometry;
import movementV2.TargetPosition;
import ent_c.Mine;
import entities.Moveable;
import entities.Offensive;

/**
 * Makes the owner move on an E-shaped path and shoot rockets.
 * @author philipp
 *
 */
public class Random6PositionBoss extends Behave{
	private Random RandomInts = new Random();
	private int lastPos = 0;
	private int nextPos = 0;
	private boolean throwsMines;
	private double[][] Positions = {
			{1100.0, 162.0},
			{1100.0, 604.0},
			{1100.0, 384.0},
			{920.0, 162.0},
			{920.0, 604.0},
			{920.0, 384.0}
	};
	public Random6PositionBoss(Moveable getOwner, boolean throwsMines) {
		super(getOwner);
		this.throwsMines = throwsMines;
		owner.movement = new TargetPosition(owner, 5, Positions[5][0], Positions[5][1], false);
	}
	public Random6PositionBoss(Moveable getOwner, boolean throwsMines, double[][] Positions) {
		super(getOwner);
		this.Positions = Positions;
		this.throwsMines = throwsMines;
		owner.movement = new TargetPosition(owner, 5, Positions[5][0], Positions[5][1], false);
	}

	public void checkMind() {
		if(owner.health > 0){
			if(((TargetPosition)owner.movement).getTargetReached()){
				lastPos = nextPos;
				while(lastPos == nextPos){
					nextPos = RandomInts.nextInt(Positions.length-1);
				}
				((TargetPosition)owner.movement).changeTarget(Positions[nextPos][0], Positions[nextPos][1]);
				if(throwsMines){
					new Mine(owner.posX, owner.posY);
				}
			}
			((Offensive)owner).fire();
		}
	}
}
