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
        Collection<ChessMove> official_moves = new ArrayList<>();
        if (piece == null) {
            return null;
        }
        Collection<ChessMove> possible_moves = piece.pieceMoves(board, start_position);
        for (ChessMove move : possible_moves) {
            ChessPiece piece_at_end = board.getPiece(move.getEndPosition());
            board.addPiece(move.getEndPosition(), piece);
            board.removePiece(move.getStartPosition());
            if (!isInCheck(piece.getTeamColor())) {
                official_moves.add(move);
            }
            board.addPiece(move.getStartPosition(), piece);
            board.removePiece(move.getEndPosition());
            if (piece_at_end != null) {
                board.addPiece(move.getEndPosition(), piece_at_end);
            }
        }
        return official_moves;
    }

    @Override
    public void makeMove(ChessMove move) throws InvalidMoveException {
        ChessPosition start = move.getStartPosition();
        ChessPosition end = move.getEndPosition();
        ChessPiece piece = board.getPiece(start);
        Collection<ChessMove> good_moves = validMoves(start);
        if (piece == null || !good_moves.contains(move) || piece.getTeamColor() != current_turn) {
            throw new InvalidMoveException("Invalid move.");
        }

        if (move.getPromotionPiece() != null) {
            ChessGame.TeamColor current_team = piece.getTeamColor();
            ChessPiece promotion_piece = getNewPiece(current_team, move.getPromotionPiece());
            board.addPiece(end, promotion_piece);
        } else {
            board.addPiece(end, piece);
        }
        board.removePiece(start);

        current_turn = (current_turn == TeamColor.WHITE) ? TeamColor.BLACK : TeamColor.WHITE;
    }

    @Override
    public boolean isInCheck(TeamColor team_color) {
        ChessPosition king_position = findKingPosition(team_color);
        if (king_position == null) {
            return false;
        }


        for (int row = 1; row <= 8; row++) {
            for (int column = 1; column <= 8; column++) {
                ChessPosition current_position = new ChessPositionImplementation(row, column);
                ChessPiece piece = board.getPiece(current_position);

                if (piece == null) {
                    continue;
                }

                if (piece.getTeamColor() != team_color) {
                    Collection<ChessMove> possible_moves = piece.pieceMoves(board, current_position);

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
                    Collection<ChessMove> potential_moves = validMoves(current_position);

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
                    Collection<ChessMove> legal_moves = validMoves(current_position);
                    if (!legal_moves.isEmpty()) {
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
        return null;
    }

    private boolean isInCheckBoard(ChessBoard board, TeamColor team_color) {
        ChessPosition king_position = findKingPosition(team_color);
        if (king_position == null) {
            return false;
        }

        for (int row = 1; row <= 8; row++) {
            for (int column = 1; column <= 8; column++) {
                ChessPiece piece = board.getPiece(new ChessPositionImplementation(row, column));

                if (piece == null) {
                    continue;
                }

                if (piece.getTeamColor() != team_color) {
                    Collection<ChessMove> possible_moves = validMovesBoard(board, new ChessPositionImplementation(row, column));

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

    private Collection<ChessMove> validMovesBoard(ChessBoard board, ChessPosition start_position) {
        ChessPiece piece = board.getPiece(start_position);
        Collection<ChessMove> official_moves = new ArrayList<>();
        if (piece == null) {
            return null;
        }
        Collection<ChessMove> possible_moves = piece.pieceMoves(board, start_position);
        for (ChessMove move : possible_moves) {
            ChessPiece piece_at_end = board.getPiece(move.getEndPosition());
            board.addPiece(move.getEndPosition(), piece);
            board.removePiece(move.getStartPosition());
            if (!isInCheck(piece.getTeamColor())) {
                official_moves.add(move);
            }
            board.addPiece(move.getStartPosition(), piece);
            board.removePiece(move.getEndPosition());
            if (piece_at_end != null) {
                board.addPiece(move.getEndPosition(), piece_at_end);
            }
        }
        return official_moves;
    }

    private ChessPiece getNewPiece(ChessGame.TeamColor team_color, ChessPiece.PieceType type) {
        ChessPiece white_pawn;
        if (team_color == ChessGame.TeamColor.WHITE && type == ChessPiece.PieceType.PAWN) {
            white_pawn = new Pawn(team_color);
        } else if (team_color == ChessGame.TeamColor.WHITE && type == ChessPiece.PieceType.BISHOP) {
            white_pawn = new Bishop(team_color);
        } else if (team_color == ChessGame.TeamColor.WHITE && type == ChessPiece.PieceType.KNIGHT) {
            white_pawn = new Knight(team_color);
        } else if (team_color == ChessGame.TeamColor.WHITE && type == ChessPiece.PieceType.ROOK) {
            white_pawn = new Rook(team_color);
        } else if (team_color == ChessGame.TeamColor.WHITE && type == ChessPiece.PieceType.QUEEN) {
            white_pawn = new Queen(team_color);
        } else if (team_color == ChessGame.TeamColor.WHITE && type == ChessPiece.PieceType.KING) {
            white_pawn = new King(team_color);
        } else if (team_color == ChessGame.TeamColor.BLACK && type == ChessPiece.PieceType.PAWN) {
            white_pawn = new Pawn(team_color);
        } else if (team_color == ChessGame.TeamColor.BLACK && type == ChessPiece.PieceType.BISHOP) {
            white_pawn = new Bishop(team_color);
        } else if (team_color == ChessGame.TeamColor.BLACK && type == ChessPiece.PieceType.KNIGHT) {
            white_pawn = new Knight(team_color);
        } else if (team_color == ChessGame.TeamColor.BLACK && type == ChessPiece.PieceType.ROOK) {
            white_pawn = new Rook(team_color);
        } else if (team_color == ChessGame.TeamColor.BLACK && type == ChessPiece.PieceType.QUEEN) {
            white_pawn = new Queen(team_color);
        } else if (team_color == ChessGame.TeamColor.BLACK && type == ChessPiece.PieceType.KING) {
            white_pawn = new King(team_color);
        } else {
            throw new IllegalArgumentException("Must specify a team color and a piece type.");
        }
        return white_pawn;
    }
}


