package PieceObject;

import abstraction.Piece;
import java.util.List;
import java.util.ArrayList;

public class Bishop extends Piece{

    public Bishop(Character name, int val, int col) {
        super(name, val, col);
        //TODO Auto-generated constructor stub
    }
    
    //TODO: implementing move possibility for bishop
    int[] offset = {-9, -7, 7, 9};
    List<Integer> move = new ArrayList<>();
    
    /*
     * TODO1: writing method for the bishop to go in up - left diagonal
     * Adding every possible index move into the arrayList "move" define above
     * @Param Piece[], int
     * @return void
     */
    public void moveUpLeft(Piece[] b, int ind){
        return;
    }

    /*
     * TODO2: like todo1 but in other direction
     */
    public void moveUpRight(Piece[] b, int ind){
        return;
    }

    //TODO:
    public void moveDownLeft(Piece[] b, int ind){
        return;
    }

    //TODO:
    public void moveDownRight(Piece[] b, int ind){
        return;
    }   

    @Override
    public List<Integer> totalMove(Piece[] b, int ind){
        //TODO
        return move;
    }
}
