package com.chessknight.chessknight;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Slf4j
public class TerminalApplication {

    static void mainTerminalLoop() {
        System.out.println("");
        System.out.println("*** Chess Knight Solution ***");
        do {
            Square startingSquare = getSquareFromInput("Enter Starting Square in the form of \"D6\":");
            Square endingSquare = getSquareFromInput("Enter Ending Square in the form of \"D6\":");

            Solver solver = new DfsSolver(false);
            List<List<Square>> solutions = solver.solve(startingSquare, endingSquare);

            if (solutions.isEmpty()) {
                System.out.println("No solution found.");
            } else {
                String pathString = concatShortestSolution(solutions);
                System.out.println("shortest path:" + pathString);
            }
        } while (true);
    }

    private static String concatShortestSolution(List<List<Square>> solutions) {
        List<Square> shortestSolution = solutions.stream().min(Comparator.comparingInt(List::size))
                .orElse(Collections.emptyList());
        String pathString = shortestSolution.stream().map(Square::toString).collect(Collectors.joining(" -> "));
        return pathString;
    }
    private static String concatAllSolutions(List<List<Square>> solutions) {
        StringBuilder sb = new StringBuilder();
        solutions.forEach(solution -> {
            sb.append(solution.stream().map(Square::toString).collect(Collectors.joining(" -> ")));
            sb.append("\n");
        });
        return sb.toString();
    }
    private static Square getSquareFromInput(String prompt) {
        String startingSquareStr;
        Scanner scan = new Scanner(System.in);
        Square square;
        do {
            System.out.println(prompt);
            startingSquareStr = scan.nextLine();
            square = validateSquare(startingSquareStr);
        } while (square == null);
        return square;
    }

    private static Square validateSquare(String squareString) {
        try {
            String column = squareString.substring(0,1).toUpperCase();
            Integer row = Integer.valueOf(squareString.substring(1,2));
            return new Square(column, row);
        } catch (Exception e) {
            System.out.println("Error while parsing input \"" + squareString + "\", please try again. Error type: " + e.getClass() + " Message: " + e.getMessage());
            log.debug("Exception while parsing input: " + e);
            return null;
        }
    }

}
