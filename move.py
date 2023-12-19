import turtle

angelo = turtle.Turtle()
angelo.color("red")  # Change the color to red
angelo.speed(1)  # Set the speed of angelo to the slowest

# Move to a location
angelo.penup()  # Lift the pen so it doesn't draw a line when moving
angelo.goto(-100, 0)  # Move the turtle to the position (-100, 0)
angelo.pendown()  # Put the pen down to start drawing

# Draw an "F"
angelo.left(90)
angelo.forward(100)
angelo.right(90)
angelo.forward(50)
angelo.backward(50)
angelo.right(90)
angelo.forward(50)
angelo.left(90)
angelo.forward(50)

turtle.mainloop()