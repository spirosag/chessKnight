package com.chessknight.chessknight;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
public class DfsSolver implements Solver {
    private Square endingSquare;
    private final boolean acyclic;

    public static final int DEPTH_LIMIT = 3;

    List<List<Square>> solutions;

    LinkedList<Square> currentPath;

    public void dfs(Square currentSquare, int depth) {
        if (depth > DEPTH_LIMIT) {
            log.debug("Reached depth limit at " + currentSquare);
            return;
        }

        if (acyclic){
            if(currentPath.contains(currentSquare))
                return;
        }
        currentPath.add(currentSquare);

        if (currentSquare.equals(this.endingSquare)) {
            solutions.add(new ArrayList<>(currentPath));
            log.debug("Reached ending square with path " + currentPath);
        }

        List<Square> possibleMoves = currentSquare.getPossibleMoves();
        for (Square s : possibleMoves) {
                dfs(s, depth + 1);
        }
        currentPath.removeLastOccurrence(currentSquare);
    }

    @Override
    public List<List<Square>> solve(Square startingSquare, Square endingSquare) {
        this.endingSquare = endingSquare;
        solutions = new ArrayList<>();
        currentPath = new LinkedList<>();
        dfs(startingSquare, 0);
        return solutions;
    }


}
