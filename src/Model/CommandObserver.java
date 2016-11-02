package Model;

public interface CommandObserver {
	public void addCommand(String commandName, int numArgs);
	
	public void changeCommand(String commandName, int numArgs);
	
	public void deleteVariable(String commandName);
}
