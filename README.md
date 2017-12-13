# GeneralaGame
(first part)
Write a program that simulates rolling a pair of dice. You can simulate rolling one die
by choosing one of the integers 1, 2, 3, 4, 5, or 6 at random. The number you pick
represents the number on the die after it is rolled. The expression 


(int)(Math.random()*6) + 1 


does the computation you need to select a random integer between 1 and 6.
You can assign this value to a variable to represent one of the dice that are being rolled.
Do this twice and add the results together to get the total roll. 
Your program should report the number showing on each die as well as the total roll.
For example: 
The first die comes up 3 and the second die comes up 5
Your total roll is 8
(Note: The word "dice" is a plural, as in "two dice." The singular is "die.")


(second part)
Modify the DiceRolling program so that now it simulates dice rolling game between a number of players. 
Each game will have a number of rounds and each player will be throwing a number of dice. 
Players will also have names. The user enters all the information 
(number of players, rounds, dice and names of the players) through the console 

Add a method which returns the player with the highest score after the game has finished 
