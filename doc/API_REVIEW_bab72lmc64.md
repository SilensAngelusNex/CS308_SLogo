###API Review 
####Blake Becerra & Larissa Cox
####Teams 14 & 3

####Part 1
-----
Larissa Cox
* Flexible
	* Model View controller to completely split front end and back end
	* Logic manager to determine what type of command is inputted and only communicate with that part of the back end
* Encapsulating Implementation 
	* The API encapsulates the View Model Controller Split
	* There will be a view model input and view model output to reflect split in GUI
* Error
	* Will check for bad input in a class attached to the controller and it will determine bad input based on regular expressions from the backend
* Good?
	* It should be good in making the program extendable and communication between front end and back end. 
	* Splitting it completely and having only a controller between the two is what makes it a "good" design

Blake Becerra
* Flexible 
	* Front end and back end completely split with only a controller in the middle
* Encapsulating Implementation
	* Classes in the front end will be encapsulated to their specific purpose to the front end
* Error
	* The goal of our design is to have an error class that will catch an error thrown from the back end and display an message in the front end
* Good?
	* Our design goal is to be flexible and easily be able to implement new features. 
	* One way our design should be good is we are separating the front end and back end as much as possible and only relying on specific information being passed back and forth. 



####Part 2
-----

* Use Cases
	* User input Forward 5
		* So the GUI would send that information to the controller and parse it and send it to the controller and checks for error. 
		* it will then be sent to the turtle class and the turtle will be updated and then information will be sent back the the GUI and displayed
	* User Clicks on Previous Command
		* That command is found in the controller map of previous commands which then sends all the data from that command to the logic class and is executed then updated


This is as far as we got during recitation. 