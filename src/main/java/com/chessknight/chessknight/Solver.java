package com.chessknight.chessknight;

import java.util.List;

public interface Solver {
    List<List<Square>> solve(Square startingSquare, Square endingSquare);
}
