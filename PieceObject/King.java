package PieceObject;

import abstraction.Piece;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class King extends Piece {

    public King(Character name, int val, int col) {
        super(name, val, col);
        //TODO Auto-generated constructor stub
        move.put(0, new ArrayList<Integer>());
        move.put(1, new ArrayList<Integer>());
    }
    
    static int[] offset = {-9, -8, -7, -1, 1, 7, 8, 9};
    HashMap<Integer, List<Integer>> move = new HashMap<>();
    boolean isWhite = (col == 16);
    //TODO: implementing move possibility for King

    public int getRow(int idx){
        return (idx >= 0 && idx <= 63) ? (idx / 8 + 1) : -2;
    }
    
    //Case 1: There are pieces at offsets
    /*
     * this method returns all the possible capture of the pieces surrounding
     *the king despite it itself got defended
     * @Param: board, index
     * @return: list of possible capture of the pieces surrounding the king
     */
    public void isPieceAtOffsets(Piece[] b, int idx){
        for(int i = 0; i < offset.length; i++){
            if((idx + offset[i]) <= 63 && (idx + offset[i]) >= 0){
                if(b[idx + offset[i]] != null && b[idx + offset[i]].getCol() == b[idx].getCol()) continue;
                else{
                    Piece tmp = b[idx + offset[i]];
                    b[idx + offset[i]] = b[idx];
                    if(!inCheck(b, idx + offset[i])){
                        if(tmp == null) move.get(0).add(idx + offset[i]);
                        else move.get(1).add(idx + offset[i]);
                    } 
                    b[idx + offset[i]] = tmp;
                }
            }
        }
    }

    //supporting method for isPieceAtOffsets()
    //08/26/2022
    public boolean inCheck(Piece[] b, int idx){
        int sign = isWhite ? 1 : -1;
        int bound = isWhite ? 63 : 0;
        //for pawns:
        if(idx + 7 * sign <= bound){ //to make sure it not out of bound
            if(b[idx + 7 * sign] != null && Math.abs(getRow(idx) - getRow(idx + 7)) == 1){ //in the case of idx + 7:
                if(b[idx + 7 * sign].getClass().getSimpleName().equals("Pawn")
                && b[idx + 7 * sign].getCol() != b[idx].getCol()) return true;
            } else if(b[idx + 7 * sign] != null && Math.abs(getRow(idx) - getRow(idx + 9)) == 1){ //in the case of idx + 9:
                if(b[idx + 9 * sign].getClass().getSimpleName().equals("Pawn")
                && b[idx + 9 * sign].getCol() != b[idx].getCol()) return true;
            }
        }

        //for king:
        int[] kingOffsets = {-9, -8, -7, -1, 1, 7, 8, 9};
        for(int i = 0; i < kingOffsets.length; i++){
            if(b[idx + kingOffsets[i]] != null && (idx + kingOffsets[i]) >= 0 && (idx + kingOffsets[i]) <= 63){
                if(b[idx + kingOffsets[i]].getClass().getSimpleName().equals("King")
                && b[idx + kingOffsets[i]].getCol() != b[idx].getCol()) return true;
            }
            
        }
        
        //for Bishop and Queen:
        boolean isCheckByBQ = upLeft(b, idx)
                            ||upRight(b, idx)
                            ||downLeft(b, idx)
                            ||downRight(b, idx);
        
        //for Rook and Queen:
        boolean isCheckByRQ = left(b, idx, getRow(idx) * 8)
                            ||up(b, idx, getRow(idx) * 8 + 7)
                            ||right(b, idx, 63)
                            ||down(b, idx, 0);
        
        //for Knight:
        boolean isCheckByKnight = knightMove(b, idx);
        return isCheckByBQ || isCheckByRQ || isCheckByKnight;
    }

    //Supporting method for inCheck:
    public boolean upLeft(Piece[] b, int idx){
        boolean isEdge = false;
        for(int i = idx + 7; i < 64; i += 7){
            if(!isEdge){
                if(i % 8 == 0 || (i >= 56 && i <= 63)) isEdge = true;
                if(b[i] == null) continue;
                if(b[i].getCol() == b[idx].getCol()) return false;
                else if(b[i].getClass().getSimpleName().equals("Bishop")
                      ||b[i].getClass().getSimpleName().equals("Queen")) return true;
            }
        }
        return false;
    }

    public boolean upRight(Piece[] b, int idx){
        boolean isEdge = false;
        for(int i = idx + 9; i < 64; i += 9){
            if(!isEdge){
                if(i % 8 == 7 || (i >= 56 && i <= 63)) isEdge = true;
                if(b[i] == null) continue;
                else if(b[i].getCol() != b[idx].getCol()) return true;
            }
        }
        return false;
    }

    public boolean downLeft(Piece[] b, int idx){
        boolean isEdge = false;
        for(int i = idx - 9; i >= 0; i -= 9){
            if(!isEdge){
                if(i % 8 == 0 || (i >= 0 && i <= 7)) isEdge = true;
                if(b[i] == null) continue;
                else if(b[i].getCol() != b[idx].getCol()) return true;
            }
        }
        return false;
    }
    public boolean downRight(Piece[] b, int idx){
        boolean isEdge = false;
        for(int i = idx - 7; i >= 0; i -= 7){
            if(!isEdge){
                if(i % 8 == 7 || (i >= 0 && i <= 7)) isEdge = true;
                if(b[i] == null) continue;
                else if(b[i].getCol() != b[idx].getCol()) return true;
            }
        }
        return false;
    }

    //supporting method for inCheck:
    public boolean left(Piece[] b, int idx, int bound){
        for(int i = idx - 1; i >= bound; i--){
            if(b[i] == null) continue;
            if(b[i].getCol() == b[idx].getCol()) return false;
            else if(b[i].getClass().getSimpleName().equals("Queen")
                 || b[i].getClass().getSimpleName().equals("Rook")) return true;
        }
        return false;
    }

    public boolean right(Piece[] b, int idx, int bound){
        for(int i = idx + 1; i <= bound; i++){
            if(b[i] == null) continue;
            if(b[i].getCol() == b[idx].getCol()) return false;
            else if(b[i].getClass().getSimpleName().equals("Rook") 
                ||  b[i].getClass().getSimpleName().equals("Queen")){
                    return true;
                
            }
        }
        return false;
    }

    public boolean up(Piece[] b, int idx, int bound){
        for(int i = idx + 8; i <= bound; i+=8){
            if(b[i] == null) continue;
            if(b[i].getCol() == b[idx].getCol()) return false;
            else if(b[i].getClass().getSimpleName().equals("Rook") 
                ||  b[i].getClass().getSimpleName().equals("Queen")){
                    return true;
            }
        }
        return false;
    }

    public boolean down(Piece[] b, int idx, int bound){
        for(int i = idx - 8; i >= bound; i-=8){
            if(b[i] == null) continue;
            if(b[i].getCol() == b[idx].getCol()) return false;
            else if(b[i].getClass().getSimpleName().equals("Rook") 
                ||  b[i].getClass().getSimpleName().equals("Queen")){
                    return true;
                
            }
        }
        return false;
    }

    //supporting method for inCheck:
    public boolean knightMove(Piece[] b, int idx){
        int[] knight_offset = {17, 15, 10, 6, -6, -10, -15, -17};

        for(int i = 0; i < knight_offset.length; i++){
            if(b[idx + knight_offset[i]] != null && b[idx + knight_offset[i]].getCol() != b[idx].getCol()
            && b[idx + knight_offset[i]].getClass().getSimpleName().equals("Knight")) return true;
        }
        return false;
    }

    
    @Override
    public HashMap<Integer, List<Integer>> totalMove(Piece[] b, int idx){
        isPieceAtOffsets(b, idx);
        return move;
    }

}
