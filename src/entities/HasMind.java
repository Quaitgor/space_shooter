package entities;

import behaviours.Behave;

/**
 * Through the assigned Behave-Object an Offensive can have a more complex,
 * behavior.
 */
public abstract class HasMind extends Offensive{
	public Behave mind;
	protected int defaultLayer = 40;
	
	public HasMind(double newPosX, double newPosY) {
		super(newPosX, newPosY);
	}
	/**
	 * Receives delta-value and redirects it to the mind.
	 * */
	public void update(double delta){
		super.update(delta);
		if(mind != null)mind.update();
	}
	/**
	 * Removes reference to mind.
	 * */
	public void unsubscribe(){
		super.unsubscribe();
		mind = null;
	}
}
