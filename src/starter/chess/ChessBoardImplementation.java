package chess;

public class ChessBoardImplementation implements ChessBoard{
    private ChessPiece[][] chessboard = new ChessPiece[8][8];

    public ChessBoardImplementation() {
        this.chessboard = new ChessPiece[8][8];
    }
    @Override
    public void addPiece(ChessPosition position, ChessPiece piece) {
        int row = position.getRow() - 1;
        int column = position.getColumn() - 1;
        chessboard[row][column] = piece;
    }

    @Override
    public void removePiece(ChessPosition position) {
        int row = position.getRow() - 1;
        int column = position.getColumn() - 1;
        chessboard[row][column] = null;
    }

    @Override
    public ChessPiece getPiece(ChessPosition position) {
        int row = position.getRow() -1;
        int column = position.getColumn() - 1;
        return chessboard[row][column];
    }

    @Override
    public void resetBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessboard[i][j] = null;
            }
        }

        for (int column = 1; column <=8; column++) {
            chessboard[1][column - 1] = new Pawn(ChessGame.TeamColor.WHITE);
            chessboard[6][column - 1] = new Pawn(ChessGame.TeamColor.BLACK);
        }

        setSpecialPieces(0, ChessGame.TeamColor.WHITE);
        setSpecialPieces(7, ChessGame.TeamColor.BLACK);

    }

    private void setSpecialPieces(int row, ChessGame.TeamColor color) {
        chessboard[row][0] = new Rook(color);
        chessboard[row][1] = new Knight(color);
        chessboard[row][2] = new Bishop(color);
        chessboard[row][3] = new Queen(color);
        chessboard[row][4] = new King(color);
        chessboard[row][5] = new Bishop(color);
        chessboard[row][6] = new Knight(color);
        chessboard[row][7] = new Rook(color);
    }

    public ChessBoardImplementation copyBoard() {
        ChessPiece[][] new_board = new ChessPiece[8][8];

        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                new_board[row][column] = chessboard[row][column];
            }
        }

        ChessBoardImplementation copy_board = new ChessBoardImplementation();
        copy_board.setChessboard(new_board);
        return copy_board;
    }

    private void setChessboard(ChessPiece[][] board_array) {
        chessboard = board_array;
    }
 }
