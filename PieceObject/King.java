package PieceObject;

import abstraction.Piece;
import java.util.List;
import java.util.ArrayList;

public class King extends Piece {

    public King(Character name, int val, int col) {
        super(name, val, col);
        //TODO Auto-generated constructor stub
    }
    
    static int[] offset = {-9, -8, -7, -1, 1, 7, 8, 9};
    List<Integer> move = new ArrayList<Integer>();
    boolean isWhite = (col == 16);
    //TODO: implementing move possibility for King

    /*
     * splits into two cases: 
     * 1. There are pieces at offsets
     * 2. There are NOT any pieces at offsets
     */
    
    //Case 1: There are pieces at offsets
    /*
     * this method returns all the possible capture of the pieces surrounding
     *the king despite it itself got defended
     * @Param: board, index
     * @return: list of possible capture of the pieces surrounding the king
     */
    public List<Integer> isPieceAtOffsets(Piece[] b, int idx){
        List<Integer> tmp = new ArrayList<Integer>();
        for(int i = 0; i < offset.length; i++){
            if(b[idx + offset[i]] != null){
                if(b[idx].getCol() != b[idx].getCol()) tmp.add(idx + offset[i]);
            }
        }
        return tmp;
    }
    
    //supporting method for isPieceAtOffsets()
    public boolean isKingGotCaptured(Piece[] b, int idx){
        int sign = isWhite ? 1 : -1;
        int bound = isWhite ? 0 : 63;
        //checking the 2 closest diagonal for pawns
        if((idx + 7 * sign) < bound){
            if(b[idx + 7 * sign].getClass().getSimpleName().equals("Pawn") 
            || b[idx + 9 * sign].getClass().getSimpleName().equals("Pawn")) return true;
        }

        //checking diagonals for queen and bishop
        checkDiagonal(b, idx, 7, 0, 63);
        checkDiagonal(b, idx, 9, 7, 63);
        checkDiagonal(b, idx, -9, 0, 0);
        checkDiagonal(b, idx, -7, 7, 0);

        //checking file and row for queen and rook
        
    }

    //Supporting method for isKingGotCaptured
    public boolean checkDiagonal(Piece[] b, int idx, int offset, int remainder, int bound){
        if((idx + offset) > bound || (idx + offset) % 8 == remainder) return false;
        if(b[idx + offset] != null){
            if(b[idx + offset].getClass().getSimpleName().equals("Bishop")
            || b[idx + offset].getClass().getSimpleName().equals("Queen")) return true;
            else return false;
        }
        return checkDiagonal(b, idx + offset, offset, remainder,  bound);
    }


    //08/26/2022
    public boolean inCheck(Piece[] b, int idx){
        int sign = isWhite ? 1 : -1;
        int bound = isWhite ? 0 : 63;
        //for pawns


        return false;
    }
    @Override
    public List<Integer> totalMove(Piece[] b, int ind){
        return move;
    }
}
