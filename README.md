# Algorithm that solves the 8-puzzle


### What is the 8-puzzle game?

It is a game of 8 numbered tiles and one empty tile like this:

| 4 | 7 | 1 |
| - | - | - |
| 3 | 2 |   |
| - | - | - |
| 5 | 8 | 9 |


You can move only the tiles that are very next to the empty tile, Up, Down, Right and Left. So one possible move to the example above is the following:

-------
|4|7|1|
|3| |2|
|5|8|9|
-------

,where you moved the 2 tile to the empty tile in the right, leaving an empty spot behind.


The game ends when every tile is in the correct position like the board bellow:

-------
|1|2|3|
|4|5|6|
|7|8| |
-------
