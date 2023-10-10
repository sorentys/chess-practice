package chess;

import java.util.Collection;

public abstract class ChessPieceImplementation implements ChessPiece {
    private ChessGame.TeamColor team_color;

    public ChessPieceImplementation(ChessGame.TeamColor team_color) {
        this.team_color = team_color;
    }
    @Override
    public ChessGame.TeamColor getTeamColor() {
        return team_color;
    }

    @Override
    public abstract PieceType getPieceType();

    @Override
    public abstract Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition);
}
