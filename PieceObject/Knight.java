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
    public static int getRow(int index){
        if(index==0 || index==1 || index==2 || index==3 || index==4 || index==5 || index==6 || index==7) return 1;
        if(index==8 || index==9|| index==10 || index==11 || index==12 || index==13 || index==14 || index==15){return 2;}
        if(index==16 || index==17|| index==18 || index==19 || index==20 || index==21 || index==22 || index==23){return 3;}
        if(index==24 || index==25 || index==26 || index==27 || index==28 || index==29 || index==30 || index==31){return 4;}
        if(index==32 || index==33 || index==34 || index==35 || index==36 || index==37 || index==38 || index==39){return 5;}
        if(index==40 || index==41 || index==42 || index==43 || index==44 || index==45 || index==46 || index==47){return 6;}
        if(index==48 || index==49 || index==50 || index==51 || index==52 || index==53 || index==54 || index==55){return 7;}
        if(index==56 || index==57 || index==58 || index==59 || index==60 || index==61 || index==62 || index==63){return 8;}
        return -2;
    }

    public void moveKnightLeftUp1(Piece[] b, int ind){
        int i = ind + 6;
        if(i>=0 && i<64 && getRow(i) == getRow(ind)+1){
            if(b[i] == null){
                move.add(i);
            }
            else if(b[i].getCol() != b[ind].getCol()){
                move.add(i);
            }
        }
        return;
    }

    public void moveKnightLeftUp2(Piece[] b, int ind){
        int i = ind + 15;
        if(i>=0 && i<64 && getRow(i) == getRow(ind)+2){
            if(b[i] == null){
                move.add(i);
            }
            else if(b[i].getCol() != b[ind].getCol()){
                move.add(i);
            }
        }
        return;
    }

    public void moveKnightRightUp1(Piece[] b, int ind){
        int i = ind + 10;
        if(i>=0 && i<64 && getRow(i) == getRow(ind)+1){
            if(b[i] == null){
                move.add(i);
            }
            else if(b[i].getCol() != b[ind].getCol()){
                move.add(i);
            }
        }
        return;
    }

    public void moveKnightRightUp2(Piece[] b, int ind){
        int i = ind + 17;
        if(i>=0 && i<64 && getRow(i) == getRow(ind)+2){
            if(b[i] == null){
                move.add(i);
            }
            else if(b[i].getCol() != b[ind].getCol()){
                move.add(i);
            }
        }
        return;
    }

    public void moveKnightLeftDown1(Piece[] b, int ind){
        int i = ind - 10;
        if(i>=0 && i<64 && getRow(i) == getRow(ind)-1){
            if(b[i] == null){
                move.add(i);
            }
            else if(b[i].getCol() != b[ind].getCol()){
                move.add(i);
            }
        }
        return;
    }

    public void moveKnightLeftDown2(Piece[] b, int ind){
        int i = ind - 17;
        if(i>=0 && i<64 && getRow(i) == getRow(ind)-2){
            if(b[i] == null){
                move.add(i);
            }
            else if(b[i].getCol() != b[ind].getCol()){
                move.add(i);
            }
        }
        return;
    }

    public void moveKnightRightDown1(Piece[] b, int ind){
        int i = ind - 6;
        if(i>=0 && i<64 && getRow(i) == getRow(ind)-1){
            if(b[i] == null){
                move.add(i);
            }
            else if(b[i].getCol() != b[ind].getCol()){
                move.add(i);
            }
        }
        return;
    }

    public void moveKnightRightDown2(Piece[] b, int ind){
        int i = ind - 15;
        if(i>=0 && i<64 && getRow(i) == getRow(ind)-2){
            if(b[i] == null){
                move.add(i);
            }
            else if(b[i].getCol() != b[ind].getCol()){
                move.add(i);
            }
        }
        return;
    }

    public List<Integer> totalMove(Piece[] b, int ind){
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
