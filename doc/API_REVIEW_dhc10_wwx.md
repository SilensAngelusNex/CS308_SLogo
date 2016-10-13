Daniel Chai and William Xiong  

## Part 1 

1. The backend can take in any command as a String to parse. Also, any number of states can be updated at once because all the objects are returned at once.
2. The frontend does not need to know about how the commands are parsed. It simply passes the String command to the backend, which figures out how to 
parse the command and update the states.
3. The command might not be a valid command. However, the frontend will pass every command to the backend. So the backend can throw an Exception in this case
and pass it up to the front end.
4. I think our API design is good because the relationship between the view and model is simple and easy to understand. There are no unnecessary levels
of indirection.

## Part 2

1. Use cases
- User enters "FORWARD 50" and the turtle moves forward 50 pixels
- User enters "PENDOWN" and the turtle starts leaving a trail
- User enters "HIDE TURTLE" and the turtle becomes invisible
- User enters FORWARD with too many pixels and an exception is thrown becaues the turtle cannot go out of bounds
- User enters gibberish and an exception is thrown
2. Observables will be used when entering the commands in the front-end. 
3. I am most excited on working on parsing the data and updating the states correctly.
4. I am worried about integrating the front-end and the back-end.
