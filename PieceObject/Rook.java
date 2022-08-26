package PieceObject;

import abstraction.Piece;
import java.util.List;
import java.util.ArrayList;

public class Rock extends Piece{

    public Rock(Character name, int val, int col) {
        super(name, val, col);
        //TODO Auto-generated constructor stub
    }
    
    int[] offset = {-8, -1, 1, 8};
    List<Integer> move = new ArrayList<>();

    public void moveUp(Piece[] b, int ind){
        for(int i = ind + 8; i < 64; i += 8){
            if(b[i] != null){
                if(b[i].getCol() != b[ind].getCol()){
                    move.add(i);
                }
                break;
            } else {
                move.add(i);
            }
        }
    }

    public void moveDown(Piece[] b, int ind){
        for(int i = ind - 8; i >= 0; i -= 8){
            if(b[i] != null){
                if(b[i].getCol() != b[ind].getCol()){
                    move.add(i);
                }
                break;
            } else {
                move.add(i);
            }
        }
    }

    public void moveLeft(Piece[] b, int ind){
        int lowerBound = (ind / 8) * 8;
        for(int i = ind - 1; i >= lowerBound; i--){
            if(b[i] != null){
                if(b[i].getCol() != b[ind].getCol()){
                    move.add(i);
                }
                break;
            } else {
                move.add(i);
            }
        }
    }

    public void moveRight(Piece[] b, int ind){
        int upperBound = (ind / 8) * 8 + 8;
        for(int i = ind + 1; i < upperBound; i++){
            if(b[i] != null){
                if(b[i].getCol() != b[ind].getCol()){
                    move.add(i);
                }
                break;
            } else {
                move.add(i);
            }
        }
    }

    @Override
    public List<Integer> totalMove(Piece[] b, int ind){
        this.moveUp(b, ind);
        this.moveDown(b, ind);
        this.moveLeft(b, ind);
        this.moveRight(b, ind);
        return move;
    }

}
