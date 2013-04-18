package entities;

import movementV2.Move;
import observer.Subject;

/**
 * @author  vmadmin
 */
public abstract class Moveable extends Entity {
	protected Move movement;
	
	public Moveable(double newPosX, double newPosY, Subject getdeltaUpdater) {
		super(newPosX, newPosY, getdeltaUpdater);
		// TODO Auto-generated constructor stub
	}


	public void update(double delta) {
		this.delta = delta;
		this.draw();
		movement.move();
	}
}
