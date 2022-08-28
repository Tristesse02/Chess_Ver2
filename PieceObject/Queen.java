package PieceObject;

import abstraction.Piece;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Queen extends Piece{

    public Queen(Character name, int val, int col) {
        super(name, val, col);
        //TODO Auto-generated constructor stub
        move.put(0, new ArrayList<Integer>());
        move.put(1, new ArrayList<Integer>());
    }
    
    int[] offset;
    HashMap<Integer, List<Integer>> move = new HashMap<>();

    public void moveUpQueen(Piece[] b, int ind){
        for(int i = ind + 8; i < 64; i += 8){
            if(b[i] == null){
                move.get(0).add(i);
            }
            else{
                if(b[i].getCol() != b[ind].getCol()){
                    move.get(1).add(i);
                }
                break;
            }
        }
        return;
    }

    public void moveDownQueen(Piece[] b, int ind){
        for(int i = ind - 8; i >= 0; i -= 8){
            if(b[i] == null){
                move.get(0).add(i);
            }
            else{
                if(b[i].getCol() != b[ind].getCol()){
                    move.get(1).add(i);
                }
                break;
            }
        }
        return;
    }

    public void moveRightQueen(Piece[] b, int ind){
        boolean isEdge = false;
        for(int i = ind + 1; i < 64; i++){
            if(!isEdge){
                if(i % 8 == 7){
                    isEdge = true;
                }
                if(b[i] == null){
                    move.get(0).add(i);
                }
                else{
                    if(b[i].getCol() != b[ind].getCol()){
                        move.get(1).add(i);
                    }
                    break;
                }
            }
        }
        return;
    }

    public void moveLeftQueen(Piece[] b, int ind){
        boolean isEdge = false;
        for(int i = ind - 1; i >= 0; i--){
            if(!isEdge){
                if(i % 8 == 0){
                    isEdge = true;
                }
                if(b[i] == null){
                    move.get(0).add(i);
                }
                else{
                    if(b[i].getCol() != b[ind].getCol()){
                        move.get(1).add(i);
                    }
                    break;
                }
            }
        }
        return;
    }

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

    //TODO: implement the move possibility of the queen
    @Override
    public HashMap<Integer, List<Integer>> totalMove(Piece[]b, int ind){
        moveUpQueen(b, ind);
        moveDownQueen(b, ind);
        moveLeftQueen(b, ind);
        moveRightQueen(b, ind);
        moveUpLeft(b, ind);
        moveUpRight(b, ind);
        moveDownLeft(b, ind);
        moveDownRight(b, ind);
        return move;
    }

}
