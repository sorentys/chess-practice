package chess;

import java.util.ArrayList;
import java.util.Collection;

public class ChessGameImplementation implements ChessGame {
    private TeamColor current_turn;
    private ChessBoard board;
    public ChessGameImplementation() {
        board = new ChessBoardImplementation();
        current_turn = TeamColor.WHITE;
    }
    @Override
    public TeamColor getTeamTurn() {
        return current_turn;
    }

    @Override
    public void setTeamTurn(TeamColor team) {
        this.current_turn = team;
    }

    @Override
    public Collection<ChessMove> validMoves(ChessPosition start_position) {
        ChessPiece piece = board.getPiece(start_position);
        if (piece == null || piece.getTeamColor() != current_turn) {
            return new ArrayList<>();
        }
        return piece.pieceMoves(board, start_position);
    }

    @Override
    public void makeMove(ChessMove move) throws InvalidMoveException {
        ChessPosition start = move.getStartPosition();
        ChessPosition end = move.getEndPosition();
        ChessPiece piece = board.getPiece(start);

        if (piece == null || !validMoves(start).contains(move)) {
            throw new InvalidMoveException("Invalid move.");
        }

        board.addPiece(move.getEndPosition(), piece);
        board.removePiece(move.getStartPosition());

        current_turn = (current_turn == TeamColor.WHITE) ? TeamColor.BLACK : TeamColor.WHITE;
    }

    @Override
    public boolean isInCheck(TeamColor team_color) {
        ChessPosition king_position = findKingPosition(team_color);

        for (int row = 1; row <= 8; row++) {
            for (int column = 1; column <= 8; column++) {
                ChessPiece piece = board.getPiece(new ChessPositionImplementation(row, column));

                if (piece == null) {
                    continue;
                }

                if (piece.getTeamColor() != team_color) {
                    Collection<ChessMove> possible_moves = piece.pieceMoves(board, new ChessPositionImplementation(row, column));

                    for (ChessMove move : possible_moves) {
                        if ((move.getEndPosition().getRow() == king_position.getRow()) &&
                                (move.getEndPosition().getColumn() == king_position.getColumn())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean isInCheckmate(TeamColor team_color) {
        if (!isInCheck(team_color)) {
            return false;
        }

        ChessPosition king_position = findKingPosition(team_color);

        for (int row = 1; row <= 8; row++) {
            for (int column = 1; column <= 8; column++) {
                ChessPosition current_position = new ChessPositionImplementation(row, column);
                ChessPiece piece = getBoard().getPiece(current_position);

                if (piece != null && piece.getTeamColor() == team_color) {
                    Collection<ChessMove> potential_moves = piece.pieceMoves(getBoard(), current_position);

                    for (ChessMove move : potential_moves) {
                        ChessBoard temporary_board = board.copyBoard();
                        temporary_board.addPiece(move.getEndPosition(), piece);
                        temporary_board.removePiece(move.getStartPosition());

                        if (!isInCheckBoard(temporary_board, team_color)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean isInStalemate(TeamColor team_color) {
        if (isInCheck(team_color)) {
            return false;
        }

        for (int row = 1; row <= 8; row++) {
            for (int column = 1; column <= 8; column++) {
                ChessPosition current_position = new ChessPositionImplementation(row, column);
                ChessPiece piece = board.getPiece(current_position);

                if (piece != null && piece.getTeamColor() == team_color) {
                    Collection<ChessMove> legalMoves = piece.pieceMoves(board, current_position);
                    if (!validMoves(current_position).isEmpty()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void setBoard(ChessBoard board) {
        if (board == null) {
            throw new IllegalArgumentException("Provided board cannot be null.");
        }

        this.board = board;
    }

    @Override
    public ChessBoard getBoard() {
        return board;
    }

    private ChessPosition findKingPosition(ChessGame.TeamColor team_color) {
        for (int row = 1; row <= 8; row++) {
            for (int column = 1; column <= 8; column++) {
                ChessPosition position = new ChessPositionImplementation(row, column);
                ChessPiece piece = board.getPiece(position);
                if (piece != null &&
                        piece.getPieceType() == ChessPiece.PieceType.KING &&
                        piece.getTeamColor() == team_color) {
                    return position;
                }
            }
        }
        throw new IllegalStateException("King not found on board!");
    }

    private boolean isInCheckBoard(ChessBoard board, TeamColor team_color) {
        ChessPosition king_position = findKingPosition(team_color);

        for (int row = 1; row <= 8; row++) {
            for (int column = 1; column <= 8; column++) {
                ChessPiece piece = board.getPiece(new ChessPositionImplementation(row, column));

                if (piece == null) {
                    continue;
                }

                if (piece.getTeamColor() != team_color) {
                    Collection<ChessMove> possible_moves = piece.pieceMoves(board, new ChessPositionImplementation(row, column));

                    for (ChessMove move : possible_moves) {
                        if ((move.getEndPosition().getRow() == king_position.getRow()) &&
                                (move.getEndPosition().getColumn() == king_position.getColumn())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}


