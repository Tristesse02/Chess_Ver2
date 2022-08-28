package PieceObject;

import abstraction.Piece;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Bishop extends Piece{

    public Bishop(Character name, int val, int col) {
        super(name, val, col);
        move.put(0, new ArrayList<Integer>());
        move.put(1, new ArrayList<Integer>());
        //TODO Auto-generated constructor stub
    }
    
    //TODO: implementing move possibility for bishop
    int[] offset = {-9, -7, 7, 9};
    HashMap<Integer, List<Integer>> move = new HashMap<>();
    
    /*
     * TODO1: writing method for the bishop to go in up - left diagonal
     * Adding every possible index move into the arrayList "move" define above
     * @Param Piece[], int
     * @return void
     */
    public void moveUpLeft(Piece[] b, int ind){
        boolean isEdge = false;
        for(int i = ind + 7; i < 64; i += 7){
            if(!isEdge){
                if(i % 8 == 0 || (i >= 56 && i <= 63)){
                    isEdge = true;
                }
                if(b[i] == null){
                    move.get(0).add(i);
                }
                else if(b[i].getCol() != b[ind].getCol()){
                    move.get(1).add(i);
                    break;
                }
            }
        }
        return;
    }
    /*
     * TODO2: like todo1 but in other direction
     */
    public void moveUpRight(Piece[] b, int ind){
        boolean isEdge = false;
        for(int i = ind + 9; i < 64; i += 9){
            if(!isEdge){
                if(i % 8 == 7 || (i >= 56 && i <= 63)){
                    isEdge = true;
                }
                if(b[i] == null){
                    move.get(0).add(i);
                }
                else if(b[i].getCol() != b[ind].getCol()){
                    move.get(1).add(i);
                    break;
                }
            }
        }
        return;
    }

    //TODO:
    public void moveDownLeft(Piece[] b, int ind){
        boolean isEdge = false;
        for(int i = ind - 9; i >= 0; i -= 9){
            if(!isEdge){
                if(i % 8 == 0 || (i >= 0 && i <= 7)){
                    isEdge = true;
                }
                if(b[i] == null){
                    move.get(0).add(i);
                }
                else if(b[i].getCol() != b[ind].getCol()){
                    move.get(1).add(i);
                    break;
                }
            }
        }
        return;
    }

    //TODO:
    public void moveDownRight(Piece[] b, int ind){
        boolean isEdge = false;
        for(int i = ind - 7; i >= 0; i -= 7){
            if(!isEdge){
                if(i % 8 == 7 || (i >= 0 && i <= 7)){
                    isEdge = true;
                }
                if(b[i] == null){
                    move.get(0).add(i);
                }
                else if(b[i].getCol() != b[ind].getCol()){
                    move.get(1).add(i);
                    break;
                }
            }
        }
        return;
    }   

    @Override
    public HashMap<Integer, List<Integer>> totalMove(Piece[] b, int ind){
        //TODO
        moveUpLeft(b, ind);
        moveUpRight(b, ind);
        moveDownLeft(b, ind);
        moveDownRight(b, ind);
        return move;
    }
}
