package entities;

import observer.Subject;

/**
 * Moveable extends Entity with the moevment Strategy Pattern
 * */
public abstract class Moveable extends Entity {
	protected Move movement;
	
	public Moveable(double newPosX, double newPosY, Subject getdeltaUpdater) {
		super(newPosX, newPosY, getdeltaUpdater);
	}
	
	/**
	 * with the movment Strategy Pattern the Entity can move itself on the screen, and exchange this behaviour with other Movement Patterns
	 * to sync the movement the update() method is upgraded to include movement
	 * */
	public void update(double delta) {
		super.update(delta);
		movement.move();
	}
}
