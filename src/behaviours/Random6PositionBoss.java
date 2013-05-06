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
	private boolean throwsMines;
	private double[][] Positions = {
		{902.0, 99.0},
		{902.0, 400.0},
		{902.0, 701.0},
		{601.0, 99.0},
		{601.0, 400.0},
		{601.0, 701.0}
	};
	public Random6PositionBoss(Moveable getOwner, boolean throwsMines) {
		super(getOwner);
		this.throwsMines = throwsMines;
		owner.movement = new TargetPosition(owner, 7, Positions[5][0], Positions[5][1], false);
	}
	public Random6PositionBoss(Moveable getOwner, boolean throwsMines, double[][] Positions) {
		super(getOwner);
		this.Positions = Positions;
		this.throwsMines = throwsMines;
		owner.movement = new TargetPosition(owner, 7, Positions[5][0], Positions[5][1], false);
	}

	public void checkMind() {
		if(((TargetPosition)owner.movement).getTargetReached()){
			int nextposition = RandomInts.nextInt(Positions.length-1);
			((TargetPosition)owner.movement).changeTarget(Positions[nextposition][0], Positions[nextposition][1]);
			if(throwsMines){
				new Mine(owner.posX, owner.posY);
			}
		}
		((Offensive)owner).fire();
	}

}
