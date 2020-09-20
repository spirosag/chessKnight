package com.chessknight.chessknight;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DfsSolverTest {

    private Solver acyclicSolver;
    private Solver cyclicSolver;

    @BeforeEach
    void setUp() {
        cyclicSolver = new DfsSolver(false);
        acyclicSolver = new DfsSolver(true);
    }

    @Test
    void testSolver_noSolution() {
        List<List<Square>> solutions = cyclicSolver.solve(new Square(1, 1), new Square(8, 8));
        assertThat(solutions).isEmpty();
    }

    @Test
    void testSolver_oneSolutionWithThreeSteps() {
        Square startingSquare = new Square(2, 2);
        Square endingSquare = new Square(5, 8);
        List<List<Square>> solutions = cyclicSolver.solve(startingSquare, endingSquare);
        assertThat(solutions).containsExactly(List.of(startingSquare,
                new Square(3, 4),
                new Square(4, 6),
                endingSquare));

    }

    @Test
    void testSolver_twoSolutionsWithTwoSteps() {
        Square startingSquare = new Square(2, 2);
        Square endingSquare = new Square(5, 3);
        List<List<Square>> solutions = cyclicSolver.solve(startingSquare, endingSquare);
        assertThat(solutions).containsExactly(List.of(startingSquare,
                                                        new Square(3, 4),
                                                        endingSquare),
                                                List.of(startingSquare,
                                                        new Square(4, 1),
                                                        endingSquare));

    }

    @Test
    void testSolver_multipleSolutionsWithBacktracking() {
        Square startingSquare = new Square(2, 2);
        Square endingSquare = new Square(3, 4);
        List<List<Square>> solutions = cyclicSolver.solve(startingSquare, endingSquare);

        for (List<Square> solution : solutions) {
            assertThat(solution.size()).isLessThanOrEqualTo(DfsSolver.DEPTH_LIMIT + 1);
        }
        assertThat(solutions.size()).isEqualTo(15);
        assertThat(solutions).contains(List.of(startingSquare, endingSquare));
    }

    @Test
    void testSolver_startSquareSameAsEndingSquare() {
        Square startingSquare = new Square(5, 5);
        Square endingSquare = startingSquare;
        List<List<Square>> solutions = cyclicSolver.solve(startingSquare, endingSquare);
        assertThat(solutions.size()).isEqualTo(9);
        assertThat(solutions).contains(List.of(startingSquare));
    }


    @Test
    void testSolver_acyclic() {
        Square startingSquare = new Square(4, 1);
        Square endingSquare = new Square(2, 2);
        List<List<Square>> solutions = acyclicSolver.solve(startingSquare, endingSquare);
        assertThat(solutions.size()).isEqualTo(4);
        assertThat(solutions).contains(List.of(startingSquare, endingSquare));
    }

    @Test
    void testSolver_cyclicResultsMatchAcyclicAfterPrune() {
        Square startingSquare = new Square(2, 2);
        Square endingSquare = new Square(3, 4);

        List<List<Square>> cyclicSolutions = cyclicSolver.solve(startingSquare, endingSquare);

        List<List<Square>> prunedAcyclicSolutions = removeCyclicSolutions(cyclicSolutions);

        List<List<Square>> acyclicSolutions = acyclicSolver.solve(startingSquare, endingSquare);

        assertThat(acyclicSolutions).containsExactlyInAnyOrderElementsOf(prunedAcyclicSolutions);
    }

    private List<List<Square>> removeCyclicSolutions(List<List<Square>> cyclicSolutions) {
        List<List<Square>> acyclicSolutions = new ArrayList<>();
        cyclicSolutions.forEach(solution -> {
            if (solution.stream().distinct().count() == solution.size()){
                acyclicSolutions.add(solution);
            }
        });
        return acyclicSolutions;
    }
}
