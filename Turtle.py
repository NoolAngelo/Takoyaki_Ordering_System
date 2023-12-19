import turtle
import random
import math

angelo = turtle.Turtle()
angelo.color("dark green")

for _ in range(1000):  # Increase the range for smoother curve
    # Move forward a consistent distance
    next_move = 10
    # Turn a small random angle between -30 and 30 degrees
    angle = random.randint(-30, 30)

    # Predict the next position
    next_x = angelo.xcor() + next_move * math.cos(math.radians(angelo.heading() + angle))
    next_y = angelo.ycor() + next_move * math.sin(math.radians(angelo.heading() + angle))

    # Check if the next position is out of bounds
    if abs(next_x) > turtle.window_width() / 2 or abs(next_y) > turtle.window_height() / 2:
        # If out of bounds, turn around
        angelo.left(180)
    else:
        # If not out of bounds, proceed with the move
        angelo.forward(next_move)
        angelo.left(angle)

turtle.mainloop()