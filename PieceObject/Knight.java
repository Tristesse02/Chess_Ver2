package PieceObject;

import abstraction.Piece;
import java.util.List;
import java.util.ArrayList;

public class Knight extends Piece{

    public Knight(Character name, int val, int col) {
        super(name, val, col);
        //TODO Auto-generated constructor stub
    }

    int[] offset = {17, 15, 10, 6, -6, -10, -15, -17};
    List<Integer> move = new ArrayList<>();

    //TODO: implementing move possibility for Knight
    @Override
    
}
