package chess;

import java.util.Objects;

public class ChessPositionImplementation implements ChessPosition {
    private final int row;
    private final int column;

    public ChessPositionImplementation(int row, int column) {
        this.row = row;
        this.column = column;
    }
    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ChessPositionImplementation position = (ChessPositionImplementation) obj;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return "ChessPositionImplementation{" +
                "row=" + row +
                ", column=" + column +
                "}\n";
    }
}
