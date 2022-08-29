package evaluation;

import abstraction.Piece;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class evaluation{

    HashMap<Integer, List<Integer>> hmi1; // converting place that it is currently standing -> list of its possible move without capture
    HashMap<Integer, List<Integer>> hmi2; // converting place that it is currently standing -> list of its possible capture
    HashMap<Character, List<Integer>> hmc;
    public evaluation(Piece[] b){
        hmi1 = new HashMap<>();
        hmi2 = new HashMap<>();
        hmc = new HashMap<>();
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
     * @return: void
     * @create: dictionary: hmc = <Piece_name, List_of_idx_pieces_stays_at>
     *        : dictionary: hmi = <Piece_idx, List_of_move>
     */
    private void tracking_piece_and_its_totalMove(Piece[] b){
        for(int i = 0; i < b.length; i++){
            if(b[i] != null){
                hmi1.put(i, b[i].totalMove(b, i).get(0));
                hmi2.put(i, b[i].totalMove(b, i).get(1));
                if(!hmc.containsKey(b[i].getName())){
                    hmc.put(b[i].getName(), new ArrayList<Integer>());
                }
                hmc.get(b[i].getName()).add(i);
            }
            
        }
    }

    private int getRow(int idx){
        return idx / 8 + 1;
    }

    private int getColumn(int idx){
        return idx % 8 + 1;
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
    public int passedPawn(Piece[] b, HashMap<Character, ArrayList<Integer>> hm){
        List<Integer> tmpWhite = new ArrayList<>();
        List<Integer> tmpBlack = new ArrayList<>();
        for(int i = 0; i < hm.get('P').size(); i++){
            tmpWhite.add(hm.get('P').get(i) % 8);
        }
        
        for(int i = 0; i < hm.get('p').size(); i++){
            tmpBlack.add(hm.get('p').get(i) % 8);
        }

        for(int i = 0; i < tmpWhite.size(); i++){
            if(tmpBlack.contains(tmpWhite.get(i))){
                tmpWhite.remove(i);
                tmpBlack.remove(Integer.valueOf(tmpWhite.get(i)));
            }
        }
        return (tmpWhite.size() - tmpBlack.size()) * 10;
    }

    //Characteristic 3: pawn close to promotion
    public int pawnPromo(HashMap<Character, ArrayList<Integer>> hm){
        int maxWhite = 0;
        int maxBlack = 0;
        for(int i = 0; i < hm.get('P').size(); i++){
            maxWhite += getRow(hm.get('P').get(i)) * 10;
        } 
        for(int i = 0; i < hm.get('p').size(); i++){
            maxBlack += getRow(hm.get('p').get(i)) * 10;
        }
        return maxWhite - maxBlack;
    }

    //Characteristic 4: space to manuver
    public int gotSpace(Piece[] b, HashMap<Integer, List<Integer>> hmnc, HashMap<Integer, List<Integer>> hmc){
        int maxWhite = 0;
        int maxBlack = 0;
        for(Integer i : hmnc.keySet()){
            if(Character.isLowerCase(b[i].getName())){
                maxWhite += hmnc.get(i).size();
            } else {
                maxBlack += hmnc.get(i).size();
            }
        }

        for(Integer i : hmc.keySet()){
            if(Character.isLowerCase(b[i].getName())){
                maxWhite += hmc.get(i).size();
            } else {
                maxBlack += hmc.get(i).size();
            }
        }
        return maxWhite - maxBlack;
    }

    //Char 5:king safety: check the place where king should be standing
    public int KingSafety(HashMap<Character, List<Integer>> hm){
        int maxWhite = 0;
        int maxBlack = 0;
        if(hm.get('P').contains(2)||
           hm.get('P').contains(3)||
           hm.get('P').contains(6)||
           hm.get('P').contains(7)) maxWhite = 80;
        
        else if(hm.get('P').contains(4)) maxWhite = 50;
        else if(getRow(hm.get('P').get(0)) == 2) maxWhite = -20;
        else if(getRow(hm.get('P').get(0)) > 2) maxWhite = -80;
        else maxWhite = 60;

        if(hm.get('p').contains(57)||
           hm.get('p').contains(58)||
           hm.get('p').contains(62)||
           hm.get('p').contains(63)) maxBlack = -80;
        
        else if(hm.get('p').contains(60)) maxBlack = -50;
        else if(getRow(hm.get('p').get(0)) == 7) maxBlack = 20;
        else if(getRow(hm.get('p').get(0)) < 7) maxBlack = 80;
        else maxBlack = -60;
        return maxWhite - maxBlack;
    }
    
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

    //Char 7: controlling the center:
    public int centerControlling(HashMap<Integer, List<Integer>> hm){
        //TODO: maybe creating just check how many pieces are in the center
    }

    //char 8: looking for checks
    public int check(){

    }

    //char 9: connected pawn
    
}
