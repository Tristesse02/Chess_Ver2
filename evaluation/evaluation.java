package evaluation;

import abstraction.Piece;
import java.util.List;
import java.util.ArrayList;


public class evaluation {
    int evaluation;
    public evaluation(){
        evaluation = 0;
    }

    public int convert(char s){
        if(s == 'P') return 10;
        if(s == 'p') return -10;
        if(s == 'Q') return 90;
        if(s == 'q') return -90;
        if(s == 'R') return 50;
        if(s == 'r') return -50;
        if(s == 'B' || s == 'N') return 30;
        if(s == 'b' || s == 'n') return -30;
        else return 0;
    }

    //Characteristic 1: Pieces value
    /*
     * This method checking number of pieces remaining on the board
     * @param: the board
     * @return: the List<Integer>: {the difference between 2 sides, sum of white side, sum of black side}
     * if negative: favor black
     * and else: favor white
     */
    public List<Integer> valueFavorSide(Piece[] b){
        int sumWhite = 0;
        int sumBlack = 0;
        for(int i = 0; i < b.length; i++){
            if(b[i].getCol() == 16) sumWhite += convert(b[i].getName());
            else sumBlack += convert(b[i].getName());
        }
        List<Integer> res = new ArrayList<Integer>();
        res.add(sumWhite - sumBlack);
        res.add(sumWhite);
        res.add(sumBlack);
        return res;
    }

    //Characteristic 2: passed pawn, connected pawn in open file

    //Characteristic 3: pawn close to promotion

    //Characteristic 4: space to manuver

    //Char 5:king safety: check the place where king should be standing
}
