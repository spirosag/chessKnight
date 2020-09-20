# Chess Knight App

#### Exercise Description

> Create a Java console application that should represent an empty chessboard where the user will be able to enter a 
>starting position and an ending position. The application should then calculate a list of all possible paths that one 
>knight (αλογάκι) piece in the starting position could take to reach the ending position in 3 moves. 
>Some inputs might not have a solution, in this case the program should display a message that no solution has been found.
> Otherwise, the shortest path (if that exists) should be returned.

## Solution Approach

### Assumptions

The application needs to calculate a list of **all possible paths** between a starting and an ending square on a 
chessboard that are possible in 3 moves.

It is not specified that ***only*** simple paths (i.e. paths with only distinct squares) are valid. As a result *cyclic*
paths are assumed to be valid since it is not mentioned otherwise. For example for a *starting square of B2* and an 
*ending square of  C4* the path `B2 -> A4 -> B2 -> C4` is accepted to be valid.

In the implementation it is possible to calculate the solutions (paths) either with or without these **cyclic** paths. 
This can be modified through the `acyclic` parameter in the solvers. In the running command line app the solver uses 
`acyclic = false` meaning that cyclic paths will be added do the list.

Although all possible paths are calculated, in the end **only the shortest** is returned to the user as the description specifies.

### Implementation

The chess square is represented by the `Square` class which functions on numerical column and row coordinates so that it
can be extended to work in boards bigger than the 8x8 limit of the chessboard. The `Square` can calculate the valid 
possible moves according the knight's rules.

The possible path calculation is done by  the `Solver`. The solver tackles the problem as an unweighted graph where the 
possible moves are the vertices. Two different implementations of the `Solver ` interface are implemented. 
A `DfsSolver` which tackles the problem **depth-first** and a `BfsSolver` which works in a **breadth-first** approach. 
In the running command line application the `DfsSolver` is used.

The **DfsSolver** works recursively and limits itself to the 3-move limit. It can calculate possible solutions either 
including or excluding cyclic paths using the `acyclic` parameter. It follows first paths that take it far away and then
continues to the other nearby paths.

The **BfsSolver** also limits itself to the 3 move limit and can also exclude/include cyclic paths through the `acyclic`
parameter. It explores the graph in "levels" exploring first nearby and then continuing further away.

Unit tests are also implemented for `Square`, `DfsSolver` & `BfsSolver`.

## How to run

The project can be imported as a **gradle** app. It can be built with:

```bash
gradlew build
```

and then execute the jar produced:

```bash
java -jar build/libs/chessknight-0.0.1-SNAPSHOT.jar
```

A pre-build jar is available in the projects [project's releases page](https://github.com/spirosag/chessKnight/releases)
