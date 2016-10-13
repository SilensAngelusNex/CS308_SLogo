#API Peer Review
Owen Chung and Alan Guo
##Part 1
1. Our API is flexible by only giving the back end the text commands instead of other information like buttons or user defined commands. Our API is flexible to allow for different types of front end views such as drop down boxes, buttons, etc.
2. We encapsulate all the previous, text box, and user defined commands by only telling the back end what command to parse. This way, the back end doesn't have to care about where the command came from.
3. An error may be a bad command. The backend may handle the determination of a bad command, but the front end should somehow display this error to the user directly. Another error may be that the command directs the turtle out of bounds, which may be handled in backend logic. This error must also be sent to the front end to display to the user.
4. Our API is good because we encapsulated the implementation details and only give the backend what it really needs to see or know. Our API also covers all the functionality that the front end needs to handle. The API is also simple enough so that we don't have to worry about parsing. We also don't have to worry about updating if we use binding.
##Part 2
1. Five use cases
	* forward 50 and hit enter
* reset button clicked
* undefined command and hit enter
* turn off color trail button clicked
* hide turtle button clicked
2. Using regular expressions to create a regular expression tree is an advanced Java feature that will help with expression parsing. Another advanced feature is reflection, which will help with determining which subclass to instantiate without knowing beforehand.
3.  Alan is most excited about working on the turtle view and making the turtle move around correctly. Owen is most excited about creating buttons and a good looking user interface.
4. Alan is most worried about making the user interface look and feel nice, and about making the turtle move the correct way. Owen is most worried about the implementation details of an expression tree. We are also worried about the struggles involved with integrating front end with back end.