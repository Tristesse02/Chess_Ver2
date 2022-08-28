package PieceObject;

import abstraction.Piece;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Rook extends Piece{

    int[] offset = {-8, -1, 1, 8};
    HashMap<Integer, List<Integer>> move = new HashMap<>();

    public Rook(Character name, int val, int col) {
        super(name, val, col);
        //TODO Auto-generated constructor stub
        move.put(0, new ArrayList<Integer>());
        move.put(1, new ArrayList<Integer>());
    }
    
    private void moveUp(Piece[] b, int ind){
        for(int i = ind + 8; i < 64; i += 8){
            if(b[i] != null){
                if(b[i].getCol() != b[ind].getCol()){
                    move.get(1).add(i);
                }
                break;
            } else {
                move.get(0).add(i);
            }
        }
    }

    private void moveDown(Piece[] b, int ind){
        for(int i = ind - 8; i >= 0; i -= 8){
            if(b[i] != null){
                if(b[i].getCol() != b[ind].getCol()){
                    move.get(1).add(i);
                }
                break;
            } else {
                move.get(0).add(i);
            }
        }
    }

    private void moveLeft(Piece[] b, int ind){
        int lowerBound = (ind / 8) * 8;
        for(int i = ind - 1; i >= lowerBound; i--){
            if(b[i] != null){
                if(b[i].getCol() != b[ind].getCol()){
                    move.get(1).add(i);
                }
                break;
            } else {
                move.get(0).add(i);
            }
        }
    }

    private void moveRight(Piece[] b, int ind){
        int upperBound = (ind / 8) * 8 + 8;
        for(int i = ind + 1; i < upperBound; i++){
            if(b[i] != null){
                if(b[i].getCol() != b[ind].getCol()){
                    move.get(1).add(i);
                }
                break;
            } else {
                move.get(0).add(i);
            }
        }
    }

    @Override
    public HashMap<Integer, List<Integer>> totalMove(Piece[] b, int ind){
        this.moveUp(b, ind);
        this.moveDown(b, ind);
        this.moveLeft(b, ind);
        this.moveRight(b, ind);
        return move;
    }

}
