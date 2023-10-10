package chess;

import java.util.ArrayList;
import java.util.Collection;

public class Queen extends ChessPieceImplementation {
    public Queen(ChessGame.TeamColor team_color) {
        super(team_color);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.QUEEN;
    }

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
            int direction_row = direction[0];
            int direction_column = direction[1];

            for (int i = 1; i <= 7; i++) {
                int new_row = this_position.getRow() + i * direction_row;
                int new_column = this_position.getColumn() + i * direction_column;

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
