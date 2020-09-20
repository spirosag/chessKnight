package com.chessknight.chessknight;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class BfsSolverTest extends DfsSolverTest{
    @Override
    @BeforeEach
    void setUp() {
        cyclicSolver = new BfsSolver(false);
        acyclicSolver = new BfsSolver(true);
    }

    @Test
    void testBfsSolver_bfsVSdfs() {
        Square startingSquare = new Square(2, 2);
        Square endingSquare = new Square(3, 4);

        Solver bfsSolver = new BfsSolver(false);
        List<List<Square>> bfsResults = bfsSolver.solve(startingSquare, endingSquare);

        Solver dfsSolver = new DfsSolver(false);
        List<List<Square>> dfsResults = dfsSolver.solve(startingSquare, endingSquare);

        Assertions.assertThat(bfsResults).containsExactlyInAnyOrderElementsOf(dfsResults);
    }
}
