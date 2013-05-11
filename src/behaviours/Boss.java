package behaviours;

import movementV2.Move;
import movementV2.StraightTrigonometry;
import ent_c.Mine;
import entities.Moveable;
import entities.Offensive;

/**
 * Makes the owner move on an E-shaped path and shoot rockets.
 */
public class Boss extends Behave{
	private double timeTillNextAction = 1500;
	private int nextMovement = 0;
	private final double speed = 7;
	private Move[] Movements = {
		new StraightTrigonometry(owner, speed, 0),
		new StraightTrigonometry(owner, speed, -90),
		new StraightTrigonometry(owner, speed, 180),
		new StraightTrigonometry(owner, speed, 0),
		new StraightTrigonometry(owner, speed, 90),
		new StraightTrigonometry(owner, speed, 180),
		new StraightTrigonometry(owner, speed, 0),
		new StraightTrigonometry(owner, speed, -90),
		new StraightTrigonometry(owner, speed, 180),
	};
	private double[] timePerMovement = {
			1000*(5/speed),
			1000*(5/speed),
			1000*(5/speed),
			1000*(5/speed),
			2000*(5/speed),
			1000*(5/speed),
			1000*(5/speed),
			1000*(5/speed),
			1000*(5/speed)
	};
	public Boss(Moveable getOwner) {
		super(getOwner);
	}

	public void checkMind() {
		timeTillNextAction -= owner.delta;
		if(timeTillNextAction < 0){
			owner.movement = Movements[nextMovement];
			timeTillNextAction = timePerMovement[nextMovement++];
			new Mine(owner.posX, owner.posY);
			if(nextMovement==Movements.length){
				nextMovement = 0;
			}
		}
		((Offensive)owner).fire();
	}

}
