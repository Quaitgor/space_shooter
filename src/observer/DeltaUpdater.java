package observer;

import java.util.Vector;


/**
 * @author  vmadmin
 */
public class DeltaUpdater implements Subject{
	
	private static Vector<Observer> observers;
	private double delta;
	
	public DeltaUpdater(){
		//create ArrayList for saving Subscribers;
		observers = new Vector<Observer>();
	}
	
	// Register Subscriber
	public void register(Observer newObserver) {
		observers.add(newObserver);
	}

	// Remove Subscriber
	public void unregister(Observer deleteObserver) {
		int observerIndex = observers.indexOf(deleteObserver);
		observers.remove(observerIndex);
	}

	// Send Delta to every Subscriber
	public void notifyObserver() {
		for(int i=0;i<observers.size();i++){
			 observers.elementAt(i).update(delta);
      }
	}
	
	// Set Delta to given Value, Changing Delta fires a notify
	/**
	 * @param delta
	 * @uml.property  name="delta"
	 */
	public void setDelta(double delta){
		this.delta = delta;
		notifyObserver();
	}
	public int getObserverNumber(){
		return observers.size();
	}
}