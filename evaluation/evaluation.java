package evaluation;

import abstraction.Piece;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class evaluation{

    HashMap<Integer, List<Integer>> hm; // converting place that it is currently standing -> list of its possible move

    public evaluation(Piece[] b){
        hm = new HashMap<>();
        this.tracking_piece_and_its_totalMove(b);
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

    //TODO: finding pieces indices after 1 loop - using dictionary and list of move as value
    /*
     * @param: Piece[] b
     * @return: dictionary: <Piece_name, List_of_idx_pieces_stays_at>
     */
    public void tracking_piece_and_its_totalMove(Piece[] b){
        for(int i = 0; i < b.length; i++){
            if(b[i] != null) hm.put(i, b[i].totalMove(b, i).get(0));
        }
    }

    //Characteristic 1: Pieces value
    /*
     * This method checking number of pieces remaining on the board
     * @param: the board
     * @return: the List<Integer>: {the difference between 2 sides, sum of white side, sum of black side}
     * if negative: favor black
     * and else: favor white
     */
    public int[] valueFavorSide(Piece[] b, HashMap<Integer, List<Integer>> hm){
        int sumWhite = 0;
        int sumBlack = 0;
        int[] res = new int[3];
        for(Integer i : hm.keySet()){
            char c = b[i].getName();
            if(Character.isLowerCase(c)){
                sumBlack += convert(c) * hm.get(c).size();
            } else {
                sumWhite += convert(c) * hm.get(c).size();
            }
        }
        res[0] = sumWhite - sumBlack;
        res[1] = sumWhite;
        res[2] = sumBlack;
        return res;
    }

    //Characteristic 2: passed pawn, connected pawn in open file

    //Characteristic 3: pawn close to promotion
    public int pawnPromo(Piece[] b)

    //Characteristic 4: space to manuver
    public int gotSpace()

    //Char 5:king safety: check the place where king should be standing
    
    //Char 6: piece with less point capture piece with more point
    /*loop through valueSet -> using b[value] to find the Pieces location -> got it total_capture_possibilities
    * return List<Integer>: {maxScore, org_idx, idx_Capture}
    */
    public int[] capture(Piece[] b, HashMap<Integer, List<Integer>> hm){
        //loop through each character in the hm
        int minBlack = 0;
        int minWhite = 0;
        int[] res = new int[3];

        for(Integer i : hm.keySet()){
            for(Integer j : hm.get(i)){
                if(b[i].getCol() == 16){
                    if(b[i].getVal() - b[j].getVal() < minWhite){
                        minWhite = b[i].getVal() - b[j].getVal();
                        res[1] = i;
                        res[2] = j;
                    }
                } else {
                    if(b[i].getVal() - b[j].getVal() < minBlack){
                        minBlack = b[i].getVal() - b[j].getVal();
                        res[1] = i;
                        res[2] = j;
                    }
                }
            }
        }
        res[0] = minBlack - minWhite;
        return res;
    }

    //char 7: looking for checks
    
}
