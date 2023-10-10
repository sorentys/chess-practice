package chess;

import java.util.ArrayList;
import java.util.Collection;

public class King extends ChessPieceImplementation {
    public King(ChessGame.TeamColor team_color) {
        super(team_color);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.KING;
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition this_position) {
        Collection<ChessMove> moves = new ArrayList<>();

        int[][] directions = {
            {0, 1}, //right
            {1, 0}, //up
            {0, -1}, //left
            {-1, 0}, //down
            {-1, -1}, //down left
            {-1, 1}, //up left
            {1, 1}, //up right
            {1, -1} //down right
        };

        for (int[] direction : directions) {
            int new_row = this_position.getRow() + direction[0];
            int new_column = this_position.getColumn() + direction[1];

            if (new_row >=  1 && new_row <= 8 && new_column >=1 && new_column <= 8) {
                ChessPiece target_piece = board.getPiece(new ChessPositionImplementation(new_row, new_column));

                if (target_piece == null || target_piece.getTeamColor() != getTeamColor()) {
                    moves.add(new ChessMoveImplementation(this_position, new ChessPositionImplementation(new_row, new_column), null));
                }
            }
        }
        return moves;
    }
}
