
### Changes to API

### Front end
Our API has dramatically changed because we underestimated the communication between the front end and back end. For example, we created TurtleView interface as one of our external APIs. Whenever there is a change in the turtle model, model will call one the methods in this interface to update the view of the turtle in front end. 

We have also added a class for the method list display page. This class' function is to link to the CS308 page that list all the methods the user should be able to use in the basic implementation. 

We have the UIFactory class that creates all the buttons that are being used in our UI and displays the exception when a bad command is imputed into the console.

We have added a TurtlePane class that implements the TurtleView interface. This class displays all the turtles and lines for the user. It listens to the back end to know when to update the turtles and display.

We have also added multiple classes that manage a different section of our BorderPane and display of the GUI. These classes extend the class that they are using within the BorderPane and control all the buttons, consoles, and displays of our GUI. 

### Back end
### Discussion




if those changes are major or minor (justify your distinction based on how much they affected your team mate's code)
if those changes are for better or worse (if for the worse, is there a way to improve it or was the original API overly optimistic)
if your foresee any significant changes coming in the next few days as you finish the project (based on your experience and the fact that you now know all the features to be implemented)