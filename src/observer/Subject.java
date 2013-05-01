package observer;

/**
 * Subject is interface for the observer in the observer pattern.
 * With this interface other Observers can be built (like DeltaUpdater)
 * without the need to adjust the methodcalls to the observer (only reference)
 * 
 * 
 * 
 * 
 * Erklärung unklar
 * */
public interface Subject {

	//Basic Methods for every Observer
	public void register(Observer o);
	public void unregister(Observer o);
	public void notifyObserver();
}