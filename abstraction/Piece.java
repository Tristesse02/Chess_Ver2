package abstraction;

import java.util.List;

public abstract class Piece {
    protected Character name;
    protected int val;
    protected int col;

    public Piece(Character name, int val, int col){
        this.name = name;
        this.val = val;
        this.col = col;
    }

    public Character getName(){
        return name;
    }

    public void setName(Character name){
        this.name = name;
    }

    public int getCol(){
        return col;
    }

    public abstract List<Integer> totalMove(Piece[] b, int ind);
}
