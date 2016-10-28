package Model;

public interface Observable<A> {
	
	abstract public void addListener(A v);
	
	abstract public void removeListener(A v);
}
