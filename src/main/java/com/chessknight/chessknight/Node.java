package com.chessknight.chessknight;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Node {
    private Square square;
    private boolean visited;

    public List<Node> getAdjacentNodes() {
        return square.getPossibleMoves().stream().map(square1 -> new Node(square1, false))
                .collect(Collectors
                        .toList());
    }
}
