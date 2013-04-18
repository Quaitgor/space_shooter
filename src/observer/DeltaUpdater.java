package observer;

import java.util.Vector;


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
	public void setDelta(double delta){
		this.delta = delta;
		notifyObserver();
	}
}