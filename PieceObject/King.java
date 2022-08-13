package PieceObject;

import abstraction.Piece;
import java.util.List;
import java.util.ArrayList;

public class King extends Piece {

    public King(Character name, int val, int col) {
        super(name, val, col);
        //TODO Auto-generated constructor stub
    }
    
    int[] offset = {-9, -8, -7, -1, 1, 7, 8, 9};
    List<Integer> move = new ArrayList<>();
    //TODO: implementing move possibility for King

    @Override
    public List<Integer> totalMove(Piece[] b, int ind){
        return move;
    }
}
