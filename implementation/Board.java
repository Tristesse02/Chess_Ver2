package implementation;
import java.util.Arrays;
import java.util.Scanner;
import PieceObject.Pawn;
import abstraction.Piece;
import PieceObject.Rock;
import PieceObject.Bishop;
import PieceObject.Knight;
import PieceObject.Pawn;
import PieceObject.Queen;
import PieceObject.King;

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
        else if(c == 'R' || c == 'r') return new Rock(c, 5, col);
        else if(c == 'B' || c == 'b') return new Bishop(c, 3, col);
        else if(c == 'N' || c == 'n') return new Knight(c, 3, col);
        else if(c == 'Q' || c == 'q') return new Queen(c, 9, col);
        // else if(c == 'k' || c == 'K') return new King(c, 100, col);
        return null;
    }

    public void print(Piece[] board){
        String res = "";
        for(int i = 63; i >= 0; i--){
            if(i % 8 == 0){
                if(board[i] == null) System.out.println(i/8 + 1 + " -" + res);
                else System.out.println((i / 8 + 1) + " " + Character.toString(board[i].getName()) + res);
                res = "";
            } else {
                if(board[i] == null) res = " -" + res;
                else res = " " + Character.toString(board[i].getName()) + res;
            }
        }
        System.out.println("  a b c d e f g h\n");
    }

    public Piece[] getBoard(){
        return board;
    }

    public static void main(String[] args){
        Board b = new Board();
<<<<<<< HEAD
        b.readFEN("8/8/8/8/4p3/8/3PpK2/8 w - - 0 1");
        //Pawn p = (Pawn) b.getBoard()[28];
        int idx = 11;
=======
        b.readFEN("8/8/3n4/8/8/p2Q1B2/8/3R4 w - - 0 1");
        int idx = 19;
>>>>>>> 7e1a05a4e10b1cb19f3b9f3a24cef2d5a4e2cad2
        System.out.println(b.getBoard()[idx].totalMove(b.getBoard(), idx));
        System.out.println(b.getBoard().getClass().getSimpleName());
        // System.out.println(p.totalMove(b.getBoard(), 12));

        /* HOW TO CHECK UR CODE!!! */
        //STEP1: replacing the FEN by any position that you want, the program
        //are able to reconstruct from any position
        //STEP2: define -> NameOfTheObj aName = (NameOfTheObj) b.getBoard()[indexPositionOfThePiece]
        //STEP3: System.out.println(p.totalMove(b.getBoard(), index))
    }

}
