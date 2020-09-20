package com.chessknight.chessknight;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.*;

@RequiredArgsConstructor
public class BfsSolver implements Solver{
    private Queue<BfsNode> queue = new LinkedList<>();
    public static final int DEPTH_LIMIT = 3;
    private List<List<Square>> solutions;
    private final boolean acyclic;

    @Value
    class BfsNode {
        Square square;
        List<Square> path;
    }
    @Override
    public List<List<Square>> solve(Square startingSquare, Square endingSquare) {
        solutions = new ArrayList<>();
        queue.add(new BfsNode(startingSquare, Arrays.asList(startingSquare)));
        while (!queue.isEmpty()) {
            BfsNode current = queue.poll();
            if (current.getSquare().equals(endingSquare)) {
                solutions.add(current.getPath());
            }
            current.getSquare().getPossibleMoves().forEach(square -> {
                if (current.getPath().size() <= DEPTH_LIMIT) {
                    List<Square> path = new ArrayList<>(current.getPath());
                    boolean validPath = true;
                    if (acyclic) {
                        if (path.contains(square)) {
                            validPath = false;
                        }
                    }

                    if (validPath) {
                        path.add(square);
                        queue.add(new BfsNode(square, path));
                    }
                }
            });


        }
        return solutions;
    }
}
