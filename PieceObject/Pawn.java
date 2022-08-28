package PieceObject;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import abstraction.Piece;

public class Pawn extends Piece{
    public Pawn(Character name, int val, int col) {
        super(name, val, col);
        //TODO Auto-generated constructor stub
        move.put(0, new ArrayList<Integer>());
        move.put(1, new ArrayList<Integer>());
    }

    boolean isWhite = (col == 16);
    int[] offset = {7, 8, 9, 16};
    HashMap<Integer, List<Integer>> move = new HashMap<>();

    public void possibleMoveWC(Piece[] b, int ind){
        int bound = isWhite ? 0 : 63;
        int sign = isWhite ? 1 : -1;

        if(ind >= bound + 8 * sign && ind <= bound + 15 * sign){
            if(b[ind + offset[1] * sign] == null) move.get(0).add(ind + offset[1] * sign);
            if(b[ind + offset[3] * sign] == null) move.get(0).add(ind + offset[3] * sign);
        } else {
            if(b[ind + offset[1] * sign] == null
            && ind + offset[1] * sign <= 63
            && ind + offset[1] * sign >= 0) move.get(0).add(ind + offset[1] * sign);
        }
        
    }

    public void possibleCapture(Piece[] b, int ind){
        int sign = (isWhite) ? 1 : -1;

        int[] pos = {ind + offset[0] * sign, ind + offset[2] * sign};
        if(b[pos[0]] != null && b[pos[0]].getCol() != b[ind].getCol()) move.get(1).add(ind + offset[0] * sign);
        if(b[pos[1]] != null && b[pos[1]].getCol() != b[ind].getCol()) move.get(1).add(ind + offset[2] * sign);

    }
    
    @Override
    public HashMap<Integer, List<Integer>> totalMove(Piece[] b, int ind){
        possibleMoveWC(b, ind);
        possibleCapture(b, ind);
        return move;
    }

}
