package chess;

import java.util.Objects;

public class ChessMoveImplementation implements ChessMove{
    private final ChessPosition start_position;
    private final ChessPosition end_position;
    private final ChessPiece.PieceType promotion_piece;

    public ChessMoveImplementation(ChessPosition start_position, ChessPosition end_position, ChessPiece.PieceType promotion_piece) {
        this.start_position = start_position;
        this.end_position = end_position;
        this.promotion_piece = promotion_piece;
    }
    @Override
    public ChessPosition getStartPosition() {
        return start_position;
    }

    @Override
    public ChessPosition getEndPosition() {
        return end_position;
    }

    @Override
    public ChessPiece.PieceType getPromotionPiece() {
        return promotion_piece;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ChessMoveImplementation chess_move = (ChessMoveImplementation) obj;
        if (!start_position.equals(chess_move.start_position)) {
            return false;
        }
        if (!end_position.equals(chess_move.end_position)) {
            return false;
        }
        return promotion_piece == chess_move.promotion_piece;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start_position, end_position, promotion_piece);
    }

    @Override
    public String toString() {
        return "ChessMoveImplementation{" +
                "start_position=" + start_position +
                ", end_position=" + end_position +
                ", promotion_piece=" + promotion_piece +
                '}';
    }
}
