package implementation;
import java.util.Scanner;
import PieceObject.Pawn;
import abstraction.Piece;

public class Board {
    Piece[] board;

    public Board(){
        board = new Piece[64];
    }

    public void readFEN(String FEN){
        Scanner scnr = new Scanner(FEN);
        String pieces = scnr.next(); // extracting just the pieces part
        String[] arr = pieces.split("/");
        int cnt = 0;
        for(int i = arr.length - 1; i >= 0 ; i--){
            for(int j = 0; j < arr[i].length(); j++){
                if(Character.isDigit(arr[i].charAt(j))){
                    cnt += (arr[i].charAt(j) - 48);
                } 
                else{
                    board[cnt] = this.creatingNewPiece(arr[i].charAt(j));
                    cnt++;
                }
            }
        }
        scnr.close();

        print(board);
    }

    /*
     * supporting method - readFEN
     * @param input character
     * @return new Object Piece
     */

    public Piece creatingNewPiece(Character c){
        int col = Character.isLowerCase(c) ? 8 : 16;
        if(c == 'p' || c == 'P') return new Pawn(c, 1, col);
        // else if(c == 'R' || c == 'r') return new Rook(c, 5, col);
        // else if(c == 'B' || c == 'b') return new Bishop(c, 3, col);
        // else if(c == 'N' || c == 'n') return new Knight(c, 3, col);
        // else if(c == 'Q' || c == 'q') return new Queen(c, 9, col);
        // else if(c == 'k' || c == 'K') return new King(c, 100, col);
        return null;
    }

    public void print(Piece[] board){
        String res = "";
        for(int i = 63; i >= 0; i--){
            if(i % 8 == 0){
                if(board[i] == null) System.out.println("-" + res);
                else System.out.println(Character.toString(board[i].getName()) + res);
                res = "";
            } else {
                if(board[i] == null) res = " -" + res;
                else res = " " + Character.toString(board[i].getName()) + res;
            }
        }
        System.out.println(res);
    }

    public Piece[] getBoard(){
        return board;
    }

    public static void main(String[] args){
        Board b = new Board();
        b.readFEN("8/8/1p6/3p1p2/2P5/8/4P3/8 w - - 0 1");
        Pawn p = (Pawn) b.getBoard()[12];
        System.out.println(p.totalMove(b.getBoard(), 26));
    }

}
