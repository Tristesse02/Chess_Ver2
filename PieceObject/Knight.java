package PieceObject;

import abstraction.Piece;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Knight extends Piece{

    int[] offset = {17, 15, 10, 6, -6, -10, -15, -17};
    HashMap<Integer, List<Integer>> move = new HashMap<>();

    public Knight(Character name, int val, int col) {
        super(name, val, col);
        //TODO Auto-generated constructor stub
        move.put(0, new ArrayList<Integer>());
        move.put(1, new ArrayList<Integer>());
    }

    //TODO: implementing move possibility for Knight

    /*
     * idx passed into in range [0, 63]
     */
    private static int getRow(int idx){
        if(idx >= 0 && idx <= 63) return idx / 8 + 1;
        return -2;

        //Alternative 1-line code:
        // return (idx >= 0 && idx <= 63) ? (idx / 8 + 1) : -2;
    }

    private void moveKnightLeftUp1(Piece[] b, int ind){
        int i = ind + 6;
        if(i>=0 && i<64 && getRow(i) == getRow(ind)+1){
            if(b[i] == null){
                move.get(0).add(i);
            }
            else if(b[i].getCol() != b[ind].getCol()){
                move.get(1).add(i);
            }
        }
        return;
    }

    private void moveKnightLeftUp2(Piece[] b, int ind){
        int i = ind + 15;
        if(i>=0 && i<64 && getRow(i) == getRow(ind)+2){
            if(b[i] == null){
                move.get(0).add(i);
            }
            else if(b[i].getCol() != b[ind].getCol()){
                move.get(1).add(i);
            }
        }
        return;
    }

    private void moveKnightRightUp1(Piece[] b, int ind){
        int i = ind + 10;
        if(i>=0 && i<64 && getRow(i) == getRow(ind)+1){
            if(b[i] == null){
                move.get(0).add(i);
            }
            else if(b[i].getCol() != b[ind].getCol()){
                move.get(1).add(i);
            }
        }
        return;
    }

    private void moveKnightRightUp2(Piece[] b, int ind){
        int i = ind + 17;
        if(i>=0 && i<64 && getRow(i) == getRow(ind)+2){
            if(b[i] == null){
                move.get(0).add(i);
            }
            else if(b[i].getCol() != b[ind].getCol()){
                move.get(1).add(i);
            }
        }
        return;
    }

    private void moveKnightLeftDown1(Piece[] b, int ind){
        int i = ind - 10;
        if(i>=0 && i<64 && getRow(i) == getRow(ind)-1){
            if(b[i] == null){
                move.get(0).add(i);
            }
            else if(b[i].getCol() != b[ind].getCol()){
                move.get(1).add(i);
            }
        }
        return;
    }

    private void moveKnightLeftDown2(Piece[] b, int ind){
        int i = ind - 17;
        if(i>=0 && i<64 && getRow(i) == getRow(ind)-2){
            if(b[i] == null){
                move.get(0).add(i);
            }
            else if(b[i].getCol() != b[ind].getCol()){
                move.get(1).add(i);
            }
        }
        return;
    }

    private void moveKnightRightDown1(Piece[] b, int ind){
        int i = ind - 6;
        if(i>=0 && i<64 && getRow(i) == getRow(ind)-1){
            if(b[i] == null){
                move.get(0).add(i);
            }
            else if(b[i].getCol() != b[ind].getCol()){
                move.get(1).add(i);
            }
        }
        return;
    }

    private void moveKnightRightDown2(Piece[] b, int ind){
        int i = ind - 15;
        if(i>=0 && i<64 && getRow(i) == getRow(ind)-2){
            if(b[i] == null){
                move.get(0).add(i);
            }
            else if(b[i].getCol() != b[ind].getCol()){
                move.get(1).add(i);
            }
        }
        return;
    }

    @Override
    public HashMap<Integer, List<Integer>> totalMove(Piece[] b, int ind){
        //TODO
        moveKnightLeftDown1(b, ind);
        moveKnightLeftDown2(b, ind);
        moveKnightLeftUp1(b, ind);
        moveKnightLeftUp2(b, ind);
        moveKnightRightUp1(b, ind);
        moveKnightRightUp2(b, ind);
        moveKnightRightDown1(b, ind);
        moveKnightRightDown2(b, ind);
        return move;
    }
}
