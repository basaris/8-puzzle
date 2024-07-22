# Algorithm that solves the 8-puzzle


### What is the 8-puzzle game?

It is a game of 8 numbered tiles and one empty tile like this:

| 4 | 7 | 1 |
| - | - | - |
| 3 | 2 |   |
| 5 | 8 | 6 |


You can move only the tiles that are very next to the empty tile, Up, Down, Right and Left. So one possible move to the example above is the following:

| 4 | 7 | 1 |
| - | - | - |
| 3 |   | 2 |
| 5 | 8 | 6 |

,where you moved the 2 tile to the empty tile in the right, leaving an empty spot behind.


The game ends when every tile is in the correct position like the board bellow:

| 1 | 2 | 3 |
| - | - | - |
| 4 | 5 | 6 |
| 7 | 8 |   |

### Methods

1. With the Uniform Cost Search Algorithm (UCS) 

We created the class `Board` that describes the board with all its methods (tile movement, goal board and a bunch of return methods). 
The function `uniformCostSearch` is the one that counts the solving sequence from the initial state to the goal state. We define a priority queue 
and a list of all the explored states. It visits all the possible states with a cost priority till it finds out the correct path to the goal state. 

&nbsp;
&nbsp;

2. With the A-star method(A*)

It counts the path the same way as before but it compares the manhattan distance from start to finish too. So the Total Cost is a little bit different because we add up the cost of the state and the heuristic cost which is the manhattan distance.
