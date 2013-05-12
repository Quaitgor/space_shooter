package observer;

import java.util.Vector;

/**
 * the observable encapsulating the delta value. It is the central class of the
 * Observer-Pattern and therefore Observer objects can register themselves at
 * DeltaUpdater and will receive the updated delta value during every loop cycle
 * to keep the game synchronized.
*/
public class DeltaUpdater implements Subject{
	
	private static Vector<Observer> observers;
	private double delta;
	
	public DeltaUpdater(){
		observers = new Vector<Observer>();
	}

	/**
	 * adds the Observer to the observers-vector, which means it is registered.
	 * */
	public void register(Observer newObserver) {
		observers.add(newObserver);
	}

	/**
	 * removes the Observer from the observers-vector and thereby unregisters it.
	 * */
	public void unregister(Observer deleteObserver) {
		int observerIndex = observers.indexOf(deleteObserver);
		if(observerIndex >= 0)observers.remove(observerIndex);
	}

	/**
	 * sends updated delta values to each registered observer in the observers-vector.
	 * */
	public void notifyObserver() {
		for(int i=0;i<observers.size();i++){
			observers.elementAt(i).update(delta);
		}
	}

	/**
	 * updates the delta and sends the new value to each Observer.
	 * */
	public void setDelta(double delta){
		this.delta = delta;
		notifyObserver();
	}
	/**
	 * returns the number of registered Observers.
	 * */
	public int getObserverNumber(){
		return observers.size();
	}
	
	/**
	 * resets the observers-vector.
	 * */
	public void clearObserver(){
		observers.clear();
	}
}