package chess;

import javax.imageio.plugins.tiff.GeoTIFFTagSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Pawn extends ChessPieceImplementation{
    private static final Scanner scanner = new Scanner(System.in);
    public Pawn(ChessGame.TeamColor team_color) {
        super(team_color);
    }

    @Override
    public ChessPiece.PieceType getPieceType() {
        return ChessPiece.PieceType.PAWN;
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition this_position) {
        Collection<ChessMove> moves = new ArrayList<>();

        int forward = (getTeamColor() == ChessGame.TeamColor.WHITE) ? 1: -1;
        int next_row = this_position.getRow() + forward;
        ChessPosition one_step_forward = new ChessPositionImplementation(this_position.getRow() + forward, this_position.getColumn());
        ChessPosition two_steps_forward = new ChessPositionImplementation(this_position.getRow() + forward * 2, this_position.getColumn());

        //one step forward
        if (board.getPiece(one_step_forward) == null) {
            if ((getTeamColor() == ChessGame.TeamColor.WHITE && this_position.getRow() == 7) ||
                    (getTeamColor() == ChessGame.TeamColor.BLACK && this_position.getRow() == 2)) {
                moves.addAll(getPromotionMoves(this_position, one_step_forward));
            } else {
                moves.add(new ChessMoveImplementation(this_position, one_step_forward, null));
            }

            //two steps forward
            if ((getTeamColor() == ChessGame.TeamColor.WHITE && this_position.getRow() == 2) ||
                    (getTeamColor() == ChessGame.TeamColor.BLACK && this_position.getRow() == 7)) {
                if (board.getPiece(two_steps_forward) == null) {
                    moves.add(new ChessMoveImplementation(this_position, two_steps_forward, null));
                }
            }
        }

        //diagonal captures
        for (int column_offset : new int[] {-1, 1}) {
            ChessPosition diagonal = new ChessPositionImplementation(this_position.getRow() + forward, this_position.getColumn() + column_offset);
            if (diagonal.getColumn() < 1 || diagonal.getColumn() > 8 || diagonal.getRow() < 1 || diagonal.getRow() > 8) {
                continue;
            }
            ChessPiece piece_at_diagonal = board.getPiece(diagonal);
            if (piece_at_diagonal != null && piece_at_diagonal.getTeamColor() != getTeamColor()) {
                if ((getTeamColor() == ChessGame.TeamColor.WHITE && this_position.getRow() == 7) ||
                        (getTeamColor() == ChessGame.TeamColor.BLACK && this_position.getRow() == 2)) {
                    moves.addAll(getPromotionMoves(this_position, diagonal));
                } else {
                    moves.add(new ChessMoveImplementation(this_position, diagonal, null));
                }
            }
        }

//        //promotion pieces
//        if (board.getPiece(new ChessPositionImplementation(next_row, this_position.getColumn())) == null) {
//            if ((next_row == 8 && getTeamColor() == ChessGame.TeamColor.WHITE) ||
//                    (next_row == 1 && getTeamColor() == ChessGame.TeamColor.BLACK)) {
//                for (ChessPiece.PieceType promotion_type : promotion_types) {
//                    moves.add(new ChessMoveImplementation(this_position, new ChessPositionImplementation(next_row, this_position.getColumn()), promotion_type));
//                }
//            } else {
//                moves.add(new ChessMoveImplementation(this_position, new ChessPositionImplementation(next_row, this_position.getColumn()), null));
//            }
//        }

        return moves;
    }

    private Collection<ChessMove> getPromotionMoves(ChessPosition current_position, ChessPosition target_position) {
        Collection<ChessMove> promotion_moves = new ArrayList<>();
        ChessPiece.PieceType[] promotion_types = {
            PieceType.BISHOP,
            PieceType.ROOK,
            PieceType.KNIGHT,
            PieceType.QUEEN
        };

        for (ChessPiece.PieceType type : promotion_types) {
            promotion_moves.add(new ChessMoveImplementation(current_position, target_position, type));
        }

        return promotion_moves;
    }
}
