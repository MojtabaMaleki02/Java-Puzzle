Logic Puzzle Project
====================

This project implements a state-space representation for a logic puzzle. The puzzle state class implements the `puzzle.State` interface provided by the instructors.

## Game Description

The game board consists of 10 squares arranged in 3 rows and 5 columns. 
The game is played with 3 red and 3 blue stones. Initially, the stones are arranged on the board as follows:

![image](https://i.ibb.co/0Dr80XB/image-1.png)


In a move, one of the stones must be moved to a neighboring empty square (consider the squares that are to the left, right, top, or bottom of the stone chosen).

The goal of the game is to swap the red and the blue stones, i.e., to obtain the following board configuration:

![image](https://i.ibb.co/XWV3jg1/image-3.png)

## PuzzleState Implementation

The `PuzzleState` class represents the state of the puzzle game board. It initializes a board with predefined `Square` values and provides methods to make moves, check the legality of moves, and determine if the puzzle is solved.

### Key Methods

- `isSolved()`: Checks if the game is solved.
- `isLegalMove(Object move)`: Checks if a given move is legal.
- `makeMove(Object move)`: Executes a given move on the board.
- `getLegalMoves()`: Returns a set of all possible legal moves.
- `clone()`: Creates a deep copy of the current puzzle state.

## One of the Solutions

Below is a sequence of moves that represents a solution and can be used to solve the puzzle:

1. Move stone from row 1, column 4 to row 1, column 3.
2. Move stone from row 1, column 3 to row 1, column 2.
3. Move stone from row 1, column 2 to row 0, column 2.
4. Move stone from row 1, column 0 to row 1, column 1.
5. Move stone from row 1, column 1 to row 1, column 2.
6. Move stone from row 1, column 2 to row 1, column 3.
7. Move stone from row 1, column 3 to row 1, column 4.
8. Move stone from row 0, column 0 to row 1, column 0.
9. Move stone from row 1, column 0 to row 1, column 1.
10. Move stone from row 1, column 1 to row 1, column 2.
11. Move stone from row 1, column 2 to row 1, column 3.
12. Move stone from row 0, column 2 to row 1, column 2.
13. Move stone from row 1, column 2 to row 1, column 1.
14. Move stone from row 1, column 1 to row 1, column 0.
15. Move stone from row 1, column 0 to row 0, column 0.
16. Move stone from row 1, column 3 to row 1, column 2.
17. Move stone from row 1, column 2 to row 1, column 1.
18. Move stone from row 1, column 1 to row 1, column 0.
19. Move stone from row 1, column 4 to row 1, column 3.
20. Move stone from row 1, column 3 to row 1, column 2.
21. Move stone from row 1, column 2 to row 1, column 1.
22. Move stone from row 0, column 4 to row 1, column 4.
23. Move stone from row 1, column 4 to row 1, column 3.
24. Move stone from row 1, column 3 to row 1, column 2.
25. Move stone from row 1, column 2 to row 0, column 2.
26. Move stone from row 1, column 1 to row 1, column 2.
27. Move stone from row 1, column 2 to row 1, column 3.
28. Move stone from row 1, column 3 to row 1, column 4.
29. Move stone from row 1, column 4 to row 0, column 4.
30. Move stone from row 1, column 0 to row 1, column 1.
31. Move stone from row 1, column 1 to row 1, column 2.
32. Move stone from row 1, column 2 to row 1, column 3.
33. Move stone from row 1, column 3 to row 1, column 4.
34. Move stone from row 2, column 0 to row 1, column 0.
35. Move stone from row 1, column 0 to row 1, column 1.
36. Move stone from row 1, column 1 to row 1, column 2.
37. Move stone from row 1, column 2 to row 1, column 3.
38. Move stone from row 0, column 2 to row 1, column 2.
39. Move stone from row 1, column 2 to row 1, column 1.
40. Move stone from row 1, column 1 to row 1, column 0.
41. Move stone from row 1, column 0 to row 2, column 0.
42. Move stone from row 1, column 3 to row 1, column 2.
43. Move stone from row 1, column 2 to row 1, column 1.
44. Move stone from row 1, column 1 to row 1, column 0.
45. Move stone from row 1, column 4 to row 1, column 3.
46. Move stone from row 1, column 3 to row 1, column 2.
47. Move stone from row 1, column 2 to row 1, column 1.
48. Move stone from row 2, column 4 to row 1, column 4.
49. Move stone from row 1, column 4 to row 1, column 3.
50. Move stone from row 1, column 3 to row 1, column 2.
51. Move stone from row 1, column 2 to row 0, column 2.
52. Move stone from row 1, column 1 to row 1, column 2.
53. Move stone from row 1, column 2 to row 1, column 3.
54. Move stone from row 1, column 3 to row 1, column 4.
55. Move stone from row 1, column 4 to row 2, column 4.
56. Move stone from row 1, column 0 to row 1, column 1.
57. Move stone from row 1, column 1 to row 1, column 2.
58. Move stone from row 1, column 2 to row 1, column 3.
59. Move stone from row 1, column 3 to row 1, column 4.
60. Move stone from row 0, column 2 to row 1, column 2.
61. Move stone from row 1, column 2 to row 1, column 1.
62. Move stone from row 1, column 1 to row 1, column 0.
