package entities;

import behaviours.Behave;

public abstract class HasMind extends Offensive{
	public Behave mind;
	protected int defaultLayer = 40;
	
	public HasMind(double newPosX, double newPosY) {
		super(newPosX, newPosY);
	}

	public void update(double delta){
		super.update(delta);
		if(mind != null)mind.update();
	}
	public void unsubscribe(){
		super.unsubscribe();
		mind = null;
	}
}
