package observer;

/**
 * central interface of the Observer-Pattern. Also called Observable.
 * */
public interface Subject {

	//Basic Methods for every Observer
	public void register(Observer o);
	public void unregister(Observer o);
	public void notifyObserver();
}