package chess;

import java.util.ArrayList;
import java.util.Collection;

public class Rook extends ChessPieceImplementation {
    public Rook(ChessGame.TeamColor team_color) {
        super(team_color);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.ROOK;
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition this_position) {
        Collection<ChessMove> moves = new ArrayList<>();

        int[][] directions = {
            {0, 1}, //right
            {1, 0}, //up
            {0, -1}, //left
            {-1, 0}, //down
        };

        for (int[] direction : directions) {
            for (int distance = 1; distance <= 8; distance++){
                int new_row = this_position.getRow() + direction[0] * distance;
                int new_column = this_position.getColumn() + direction[1] * distance;

                if (new_row < 1 || new_row > 8 || new_column < 1 || new_column > 8) {
                    break;
                }

                ChessPosition new_position = new ChessPositionImplementation(new_row, new_column);
                ChessPiece piece_at_new_position = board.getPiece(new_position);

                if (piece_at_new_position == null) {
                    moves.add(new ChessMoveImplementation(this_position, new_position,null));
                } else {
                    if (piece_at_new_position.getTeamColor() != getTeamColor()) {
                        moves.add(new ChessMoveImplementation(this_position, new_position, null));
                    }
                    break;
                }
            }
        }
        return moves;
    }

}