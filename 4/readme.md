# Assignment 4

### Mockito powerups
Answer the following questions about Mockito. Use code examples in your explanations.
* How do you verify that a mock was called?\
We can use <code>verify</code> to check if a certain method of the mock was called.\
Below, the code checks if <code>snakeMock.collidesWith(Collidable other)</code> is called.
    
        public void mustCheckIfSnakeCollidesWithItself() 
        {
            // Act
            boolean snakeBodyCollision = facade.snakeCollidesWithBody(snakeMock);

            // Assert
            verify(snakeMock).collidesWith(snakeMock);
        }

* How do you verify that a mock was NOT called? 
    * Using <code>verify</code> we can specify that we want 0 invocations, e.g. <code>verify(snakeMock, times(0)).collidesWith(snakeMock)</code>. 
    * Alternatively, we can use <code>verifyNoInteractions(Object... mocks)</code>.
* How do you specify how many times a mock should have been called?\
By specifying the wanted number of invocations, e.g.


        public void mustCheckIfBorderCollisionIsDoneForAllBorders() 
        {
            // Act
            var result = facade.snakeCollidesWithBorder(snakeMock, 1024, 800);

            // Assert
            verify(snakeMock, times(4)).hasHitBorder(any(Border.class), anyInt());
        }
* How do you verify that a mock was called with specific arguments?\
Using <code>verify</code> we can specify exactly what arguments we expect a method to be called with and compare with the actual arguments, e.g.

    
        public void mustWrapSnakeAfterHittingBottomBorder()    
        {
            // Arrange        
            int width = 500, height = 800;        
            when(snakeMock.getHead()).thenReturn(new Point(0, 850)); // head 50 pixels below bottom border.

            // Act
            facade.wrapSnake(snakeMock, Border.BOTTOM, width, height);

            // Assert
            verify(snakeMock).moveBy(Direction.DOWN, -800, true);
        }
* How do you use a predicate to verify the properties of the arguments given to a call to the mock?\
We can combine <code>verify</code>-method with the <code>any</code>-method to check if the provided arguments are of a certain type.\
The code below checks if the arguments provided are of type Border and int:
    
    
        verify(snakeMock, times(4)).hasHitBorder(any(Border.class), anyInt());

---
###At least one
To run the Snake game, you must:
1. clone this repo.
2. Run the main method (duh!) found in ui.GameFrame.
3. Control the snake with the arrows. Eat apples, don't eat yourself.
3. If you are feeling sporty, I dare you to lower the delay and set snakeFrame to false.
4. All other arguments you adjust at your own risk. Results will vary.
5. If (when) you lose, you need to restart by running the main method again. Feeling collegial? I dare you to implement a restart button ;-) 