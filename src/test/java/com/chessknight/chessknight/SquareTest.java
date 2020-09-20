package com.chessknight.chessknight;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SquareTest {

    @Test
    void testValidCreation() {
        int row = 4;
        int column = 5;
        Square validSquare = new Square(column,row);
        assertThat(validSquare.getColumn()).isEqualTo(column);
        assertThat(validSquare.getRow()).isEqualTo(row);
    }

    @Test
    void testValidCreationWithLetter() {
        int row = 3;
        String column = "D";
        Square validSquare = new Square(column,row);
        assertThat(validSquare.getColumn()).isEqualTo(4);
        assertThat(validSquare.getRow()).isEqualTo(3);
    }

    @Test
    void testInvalidCreation() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Square(Square.COLUMN_LOWER_LIMIT - 1, Square.ROW_UPPER_LIMIT));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Square(Square.COLUMN_UPPER_LIMIT + 1, Square.ROW_UPPER_LIMIT));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Square(Square.COLUMN_UPPER_LIMIT, Square.ROW_LOWER_LIMIT - 1));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Square(Square.COLUMN_UPPER_LIMIT, Square.ROW_UPPER_LIMIT + 1));
    }

    @Test
    void testInvalidCreationWithLetter() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Square("I" , Square.ROW_UPPER_LIMIT));
    }

    @Test
    void testEquality() {
        assertThat(new Square(1, 2)).isEqualTo(new Square(1, 2));

        assertThat(new Square(1, 2)).isNotEqualTo(new Square(1, 3));

        assertThat(new Square(2, 3)).isNotEqualTo(new Square(1, 3));
    }

    @Test
    void testPossibleMoves_allMovesPossible() {
        Square square = new Square(4, 4);
        List<Square> possibleMoves = square.getPossibleMoves();
        assertThat(possibleMoves).containsExactly(new Square(3,6),
                new Square(5, 6),
                new Square(2, 5),
                new Square(2, 3),
                new Square(6, 5),
                new Square(6, 3),
                new Square(3, 2),
                new Square(5, 2));
    }

    @Test
    void testPossibleMoves_squareInCorner() {
        Square squareInCorner = new Square(1, 1);
        List<Square> possibleMoves = squareInCorner.getPossibleMoves();
        assertThat(possibleMoves).containsExactly(new Square(2, 3),
                new Square(3, 2));
    }

    @Test
    void testPossibleMoves_squareAtEdge() {
        Square squareInCorner = new Square(3, 1);
        List<Square> possibleMoves = squareInCorner.getPossibleMoves();
        assertThat(possibleMoves).containsExactly(new Square(2, 3),
                new Square(4, 3),
                new Square(1, 2),
                new Square(5, 2));
    }

}
