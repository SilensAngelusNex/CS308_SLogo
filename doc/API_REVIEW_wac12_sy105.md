# API Review
wac12 and sy105

### Part 1
---
1. **Flexibility**
	* Allow for easy adding of commands
	* Allow for easy command overloading
	* Leave room to add turtles/turtle selection
2. **Encapsulation**
	* Other parts of the program don't need to know how commands are handled
	* Put a string in, a string and changes to the turtle and lines come out
3. **Errors**
	* The parse method could fail if the string isn't a valid command
		* If it does, it should return a string to the view with an error
		message in it, so no error is thrown, but the user is still notified.
		* The message should depend on the type of error, so users know what's
		going wrong.
4. **Success**
	* I think my API is good because it keeps most of the internal operations
	hidden and therefore separate from the other parts of the program.
	* Making events that the View has to listen to seems to break this a bit,
	but it's probably better than the view having to ask "What should I draw?"
	every time the view updates.

### Part 2
---
1. **Use Cases**
	1. Encounter an undefined function
	2. Encounter a function with incorrect arguments
	3. Turtle moves off screen
	4. Multiple commands on the same line
	5. Incomplete command on a line, followed by the rest on the next line.
2. ***Advanced Techniques**
	* Reflection will be useful to actually execute the parsed command
3. **Exciting Feature**
	* I'm most excited to try to implement overloading commands.
4. **Scary Feature**
	* Getting the command into a reflectable format is most intimidating.