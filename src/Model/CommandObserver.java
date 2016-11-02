package Model;

import java.util.List;

public interface CommandObserver {
	public void addCommand(String commandName, List<String> args, int numArgs);
}
