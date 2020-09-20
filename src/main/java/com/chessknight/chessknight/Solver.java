package com.chessknight.chessknight;

import java.util.List;

public interface Solver {
    int DEPTH_LIMIT = 3;

    List<List<Square>> solve(Square startingSquare, Square endingSquare);
}
