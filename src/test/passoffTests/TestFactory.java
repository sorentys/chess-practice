package passoffTests;

import chess.*;

/**
 * Used for testing your code
 * Add in code using your classes for each method for each FIXME
 */
public class TestFactory {

    //Chess Functions
    //------------------------------------------------------------------------------------------------------------------
    public static ChessBoard getNewBoard(){
        ChessBoardImplementation chess_board = new ChessBoardImplementation();
		return chess_board;
    }

    public static ChessGame getNewGame(){
        ChessGameImplementation chess_game = new ChessGameImplementation();
		return chess_game;
    }

    public static ChessPiece getNewPiece(ChessGame.TeamColor team_color, ChessPiece.PieceType type){
        ChessPiece white_pawn;
        if (team_color == ChessGame.TeamColor.WHITE && type == ChessPiece.PieceType.PAWN) {
            white_pawn = new Pawn(team_color);
        }
        else if (team_color == ChessGame.TeamColor.WHITE && type == ChessPiece.PieceType.BISHOP) {
            white_pawn = new Bishop(team_color);
        }
        else if (team_color == ChessGame.TeamColor.WHITE && type == ChessPiece.PieceType.KNIGHT) {
            white_pawn = new Knight(team_color);
        }
        else if (team_color == ChessGame.TeamColor.WHITE && type == ChessPiece.PieceType.ROOK) {
            white_pawn = new Rook(team_color);
        }
        else if (team_color == ChessGame.TeamColor.WHITE && type == ChessPiece.PieceType.QUEEN) {
            white_pawn = new Queen(team_color);
        }
        else if (team_color == ChessGame.TeamColor.WHITE && type == ChessPiece.PieceType.KING) {
            white_pawn = new King(team_color);
        }
        else if (team_color == ChessGame.TeamColor.BLACK && type == ChessPiece.PieceType.PAWN) {
            white_pawn = new Pawn(team_color);
        }
        else if (team_color == ChessGame.TeamColor.BLACK && type == ChessPiece.PieceType.BISHOP) {
            white_pawn = new Bishop(team_color);
        }
        else if (team_color == ChessGame.TeamColor.BLACK && type == ChessPiece.PieceType.KNIGHT) {
            white_pawn = new Knight(team_color);
        }
        else if (team_color == ChessGame.TeamColor.BLACK && type == ChessPiece.PieceType.ROOK) {
            white_pawn = new Rook(team_color);
        }
        else if (team_color == ChessGame.TeamColor.BLACK && type == ChessPiece.PieceType.QUEEN) {
            white_pawn = new Queen(team_color);
        }
        else if (team_color == ChessGame.TeamColor.BLACK && type == ChessPiece.PieceType.KING) {
            white_pawn = new King(team_color);
        }
        else {
            throw new IllegalArgumentException("Must specify a team color and a piece type.");
        }
        return white_pawn;
    }

    public static ChessPosition getNewPosition(Integer row, Integer column){
        ChessPositionImplementation position = new ChessPositionImplementation(row, column);
		return position;
    }

    public static ChessMove getNewMove(ChessPosition start_position, ChessPosition end_position, ChessPiece.PieceType promotion_piece){
        ChessMoveImplementation move = new ChessMoveImplementation(start_position, end_position, promotion_piece);
		return move;
    }
    //------------------------------------------------------------------------------------------------------------------


    //Server API's
    //------------------------------------------------------------------------------------------------------------------
    public static String getServerPort(){
        return "8080";
    }
    //------------------------------------------------------------------------------------------------------------------


    //Websocket Tests
    //------------------------------------------------------------------------------------------------------------------
    public static Long getMessageTime(){
        /*
        Changing this will change how long tests will wait for the server to send messages.
        3000 Milliseconds (3 seconds) will be enough for most computers. Feel free to change as you see fit,
        just know increasing it can make tests take longer to run.
        (On the flip side, if you've got a good computer feel free to decrease it)
         */
        return 3000L;
    }
    //------------------------------------------------------------------------------------------------------------------
}
