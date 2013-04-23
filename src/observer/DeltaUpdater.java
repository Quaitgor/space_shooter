package observer;

import graphics.GS;

import java.util.Vector;

/**
 * DeltaUpdater is the Observer for the delta, objects registering with this object get updated delta values, needed to keep this game in sync
 * the observer pattern allows easy access to get the delta and send it out to all objects that need it, and at the same time allows
 * each object registered to the observer to unregister itself
 * */
public class DeltaUpdater implements Subject{
	
	private static Vector<Observer> observers;
	private double delta;
	
	public DeltaUpdater(){
		observers = new Vector<Observer>();
	}

	/**
	 * register() adds the object to the vector and the objects will get updated delta values
	 * */
	public void register(Observer newObserver) {
		observers.add(newObserver);
	}

	/**
	 * unregister() removes the object from the list and stopping the delta update for that object
	 * */
	public void unregister(Observer deleteObserver) {
		int observerIndex = observers.indexOf(deleteObserver);
		observers.remove(observerIndex);
	}

	/**
	 * notifyObserver() sends updated delta values to each registered observer in the vector
	 * */
	public void notifyObserver() {
		for(int i=0;i<observers.size();i++){
			 observers.elementAt(i).update(delta);
      }
	}

	/**
	 * setDelta updates the delta and sends the new value to each observer with the notifyObserver() method
	 * */
	public void setDelta(double delta){
		this.delta = delta;
		notifyObserver();
	}
	/**
	 * getObserverNumber returns the number of observers that are registered to the DeltaUpdater,
	 * used for statistics only
	 * */
	public int getObserverNumber(){
		return observers.size();
	}
}