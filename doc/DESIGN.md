# Design for SLOGO

## Introduction



## Design Overview

The project will use a Model-View-Controller architecture. The big picture overview of the architecture is that the View is the frontend, the Model is the 
backend, and the Controller passes data from the View to the Model. The View contains all the UI components that need to be displayed, such as the canvas, 
turtle, and input box for commands. The Controller is the interface between the View and the Model, so all commands that are entered will first be passed 
from the View into the Controller. The Controller then parses the command into an expression tree, which is passed to the Model. If the command is invalid, 
the Controller throws an exception rather that creating the expression tree, and the View will handle the exception appropriately. The Model will update 
the states of the objects according to the expression tree. In order for the View to be updated whenever the Model changes the states of the objects, the 
View will contain listeners for the objects. This way, the View can be updated automatically whenever the Model changes the state of any of the objects.

The most important classes for this project are:
- **MainView**: This class is the main entry point for the frontend. 
- **TurtleView**: This class contains information needed to generate the turtle in the frontend.
- **TurtleModel**: This class updates the state of the turtle according to an expression tree.
- **PathView**: This class contains information needed to generate the turtle's path in the frontend.
- **PathModel**: This class updates the state of the turtle's path according to an expression tree.
- **CommandController**: This class takes in a String as the command from the frontend and parses the String into an expression tree. The expression tree
is then passed to the backend.

## User Interface 



## API Details

### Model/Back End API

#### External

void exec(expression tree)
* Runs the SLogo command given the expression tree.
Model_Observable that front end listens to. If model changes, the front end will change based on the changes: PathModel, TurtleModel

#### Internal

void updatePath()
* change the state of the path, add more lines, changes color ....

void updateTurtleState()
* change the state of the turtle, for example, color, location ...

### Controller API

#### External

void tree createExpressionTree(String)
* update the expression tree based on the new command

#### Internal

void addnode(x)
* add a node to the expression tree rooted at x

void removenode()
* remove a node from the expression tree

### View/Front End  API

#### External

none
* nothing calls the view

#### Internal

void go()
* Executes the command in the command entry box.
* lets controller generate the expression tree based on the command 

updateEnclosure()
* Updates what is being drawn to reflect the current state of the Model
* listen to changes in back end

void changePenColor(Color newColor)
* Changes the color of the pen the turtle is currently drawing with.

void changeTurtleImage(ImageView newTurtle)
* Changes the image being used to represent the turtle.
Model_listener that listens the back end changes: PathView, TurtleView.

## API Example Code



## Design Considerations

We discussed at length how the Model will update the View. For example, if the Model changes the turtle's position, how will the View know to correctly
update the turtle's position in the frontend? At first, we considered having the Mdodel return a Collection of objects to the View whenever the states
are updated. However, the bad thing about this design is that every object needs to be passed to the View whenever even one small aspect of the Model
is updated, leading to a lot of unnecessary work. We then decided on the current design, which will be implemented with Observables/Listeners so that 
the View can automatically track when the Model updates states. This design is better because objects are only updated if they are needed to, instead of
all the objects being updates every time no matter what change was made.

There are still design considerations that need to be considered further. The Controller needs to throw an exception if the command that it receives 
is invalid. We still need to figure out the details of the exception and how the View will handle the exception. The Controller also needs to parse
the command into an an expression tree. Right now, we are just treating the expression tree as a black box and have not considered its details yet.
We might have to implement our own expression tree class in the future, as it is probably not a built-in Java class.

## Team Responsibilities

- **Weston**: Backend 
- **Daniel**: Backend (and frontend-backend interface)
- **Owen**: Frontend (and frontend-backend interface)
- **Blake**: Frontend