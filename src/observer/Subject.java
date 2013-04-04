package observer;

public interface Subject {

	//Basic Methods for every Observer
	public void register(Observer o);
	public void unregister(Observer o);
	public void notifyObserver();
}