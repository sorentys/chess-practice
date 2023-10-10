package chess;

public class ChessBoardImplementation implements ChessBoard{
    private ChessPiece[][] board = new ChessPiece[8][8];

    public ChessBoardImplementation() {
        this.board = new ChessPiece[8][8];
    }
    @Override
    public void addPiece(ChessPosition position, ChessPiece piece) {
        int row = position.getRow() - 1;
        int column = position.getColumn() - 1;

        if (position.getRow() == 0) {
            row = 0;
        }
        if (position.getColumn() == 0) {
            column = 0;
        }
        board[row][column] = piece;
    }

    @Override
    public void removePiece(ChessPosition position) {
        int row = position.getRow() - 1;
        int column = position.getColumn() - 1;
        board[row][column] = null;
    }

    @Override
    public ChessPiece getPiece(ChessPosition position) {
        int row = position.getRow() -1;
        int column = position.getColumn() - 1;

        if (position.getRow() == 0) {
            row = 0;
        }
        if (position.getColumn() == 0) {
            column = 0;
        }
        return board[row][column];
    }

    @Override
    public void resetBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = null;
            }
        }

        for (int column = 1; column <=8; column++) {
            board[1][column - 1] = new Pawn(ChessGame.TeamColor.WHITE);
            board[6][column - 1] = new Pawn(ChessGame.TeamColor.BLACK);
        }

        setSpecialPieces(0, ChessGame.TeamColor.WHITE);
        setSpecialPieces(7, ChessGame.TeamColor.BLACK);

    }

    private void setSpecialPieces(int row, ChessGame.TeamColor color) {
        board[row][0] = new Rook(color);
        board[row][1] = new Knight(color);
        board[row][2] = new Bishop(color);
        board[row][3] = new Queen(color);
        board[row][4] = new King(color);
        board[row][5] = new Bishop(color);
        board[row][6] = new Knight(color);
        board[row][7] = new Rook(color);
    }

    public ChessBoardImplementation copyBoard() {
        ChessPiece[][] new_board = new ChessPiece[8][8];

        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                new_board[row][column] = board[row][column];
            }
        }

        ChessBoardImplementation copy_board = new ChessBoardImplementation();
        copy_board.setBoard(new_board);
        return copy_board;
    }

    private void setBoard(ChessPiece[][] board_array) {
        board = board_array;
    }
 }
