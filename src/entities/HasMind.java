package entities;

import behaviours.Behave;

/**
 * Through the assigned Behave-Object the Offensive can have a more complex,
 * behavior.
 */
public abstract class HasMind extends Offensive{
	public Behave mind;
	protected int defaultLayer = 40;
	
	public HasMind(double newPosX, double newPosY) {
		super(newPosX, newPosY);
	}
	/**
	 * Modified Update to include sending updates to the mind object
	 * */
	public void update(double delta){
		super.update(delta);
		if(mind != null)mind.update();
	}
	/**
	 * modified unsubscribe to remove the reference of the mind object when
	 * unsubscribing (removing the object from the game)
	 * */
	public void unsubscribe(){
		super.unsubscribe();
		mind = null;
	}
}
