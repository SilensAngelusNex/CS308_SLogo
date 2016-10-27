
## Changes to API

## Front end
Our API has dramatically changed because we underestimated the communication between the front end and back end. For example, we created TurtleView interface as one of our external APIs. Whenever there is a change in the turtle model, model will call one the methods in this interface to update the view of the turtle in front end. 


## Back end


## Discussion


###Major changes
We did not really understand the idea of Model_View_Controller when we created the APIs. As a result, we did not include the APIs for the controller. We modified our API with implementations of controller to avoid dependencies and direct access between model and view.  


###Minor changes

### Changes coming
Front end: we will probably change the UIFactory to be more specific in handling UI components. As for right now, it could handle the exception, create buttons and choiceboxes. We would probably want to extract the promptalert method to a different class so that UIFactory is only responsible for making new UI elements. 
