package Model;

public interface Observable {
	
	abstract public void addListener(EnclosureObserver v);
	
	abstract public void removeListener(EnclosureObserver v);
}
