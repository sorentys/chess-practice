package chess;

import java.util.ArrayList;
import java.util.Collection;

public class Knight extends ChessPieceImplementation {
    public Knight(ChessGame.TeamColor team_color) {
        super(team_color);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.KNIGHT;
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition this_position) {
        Collection<ChessMove> moves = new ArrayList<>();

        int[][] possible_offsets = {
            {2, 1},
            {1, 2},
            {-1, 2},
            {-2, 1},
            {-2, -1},
            {-1, -2},
            {1, -2},
            {2, -1}
        };

        for (int[] offset : possible_offsets) {
            int new_row = this_position.getRow() + offset[0];
            int new_column = this_position.getColumn() + offset[1];

            if (new_row < 1 || new_row > 8 || new_column < 1 || new_column > 8) {
                continue;
            }

            ChessPosition new_position = new ChessPositionImplementation(new_row, new_column);
            ChessPiece piece_at_new_position = board.getPiece(new_position);

            if (piece_at_new_position == null || piece_at_new_position.getTeamColor() != this.getTeamColor()) {
                moves.add(new ChessMoveImplementation(this_position, new_position, null));
            }
        }
        return moves;
    }
}
