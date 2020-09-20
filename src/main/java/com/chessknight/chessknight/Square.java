package com.chessknight.chessknight;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

import static java.util.Map.entry;

@Value
@Slf4j
public class Square {
    public static final int ROW_LOWER_LIMIT = 1;
    public static final int ROW_UPPER_LIMIT = 8;
    public static final int COLUMN_LOWER_LIMIT = 1;
    public static final int COLUMN_UPPER_LIMIT = 8;
    private static final Map<Integer, String> columnLetters = Map.ofEntries(entry(1,  "A"),
                                                                            entry(2, "B"),
                                                                            entry(3, "C"),
                                                                            entry(4, "D"),
                                                                            entry(5, "E"),
                                                                            entry(6, "F"),
                                                                            entry(7, "G"),
                                                                            entry(8, "H"));

    int column;
    int row;

    public Square(String column, int row) {
        this(columnLetterToColumnNumber(column), row);
    }

    public Square(int column, int row) {
        if (coordinatesAreValid(column, row)) {
            this.column = column;
            this.row = row;
        } else {
            throw new IllegalArgumentException("Square coordinates out of bounds");
        }
    }

    private boolean coordinatesAreValid(int column, int row) {
        if (column > COLUMN_UPPER_LIMIT || column < COLUMN_LOWER_LIMIT
                || row > ROW_UPPER_LIMIT || row < ROW_LOWER_LIMIT) {
            return false;
        } else {
            return true;
        }
    }

    public List<Square> getPossibleMoves() {
        List<Integer> newColumns = new ArrayList<>();
        List<Integer> newRows = new ArrayList<>();

        //up - left
        newColumns.add(this.column - 1);
        newRows.add(this.row + 2);

        //up - right
        newColumns.add(this.column + 1);
        newRows.add(this.row + 2);

        // left - up
        newColumns.add(this.column - 2);
        newRows.add(this.row + 1);

        //left - down
        newColumns.add(this.column - 2);
        newRows.add(this.row - 1);

        //right - up
        newColumns.add(this.column + 2);
        newRows.add(this.row + 1);

        //right - down
        newColumns.add(this.column + 2);
        newRows.add(this.row - 1);

        //down - left
        newColumns.add(this.column - 1);
        newRows.add(this.row - 2);

        //down - right
        newColumns.add(this.column + 1);
        newRows.add(this.row - 2);

        List<Square> possibleMoves = new ArrayList<>();
        for (int i = 0; i < newColumns.size(); i++) {
            try {
                Square newMove = new Square(newColumns.get(i), newRows.get(i));
                possibleMoves.add(newMove);
            } catch (IllegalArgumentException e) {
                log.debug("Move to [" + column + "," + row + "not possible");
            }
        }
        return Collections.unmodifiableList(possibleMoves);
    }

    @Override
    public String toString() {
        return "Square{"
                 + columnNumToColumnLetter(column) +
                 + row +
                '}';
    }

    private static String columnNumToColumnLetter(int columnNumber) {
        if (columnLetters.containsKey(columnNumber)) {
            return columnLetters.get(columnNumber);
        } else {
            return String.valueOf(columnNumber);
        }
    }

    private static int columnLetterToColumnNumber(String columnLetter) {
        if (columnLetters.containsValue(columnLetter)) {
            return columnLetters.entrySet().stream()
                    .filter(entry -> entry.getValue().equals(columnLetter))
                    .map(Map.Entry::getKey).findFirst().get();
        } else {
            throw new IllegalArgumentException("No number found for column " + columnLetter);
        }
    }
}
