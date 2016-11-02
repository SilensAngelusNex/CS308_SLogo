package Model;

public interface CommandObserver {
	public void addCommand(String commandName, int numArgs);
}
