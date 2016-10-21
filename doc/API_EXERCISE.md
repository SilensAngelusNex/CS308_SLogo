# Model/Back End API

### External

void exec(String command)
* Runs the SLogo command given in the String commmand.

Collection<Drawable>  getToDraw()
* Returns the objects that the View needs to draw.

void setPenColor(Color newColor)
* Sets the pen's color to newColor.

### Internal

void parse(String command)
* Recursivly parses command and calls the right methods to execute it.

void addLine(Line newLine)
* Adds the Line newLine tho the collection of lines to be drawn.

------

# View/Front End API

### External

none
* nothing calls the view

### Internal

void go()
* Executes the command in the command entry box.

updateEnclosure()
* Updates what is being drawn to reflect the current state of the Model

void changePenColor(Color newColor)
* Changes the color of the pen the turtle is currently drawing with.

void changeTurtleImage(ImageView newTurtle)
* Changes the image being used to represent the turtle.