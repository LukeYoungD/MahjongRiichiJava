import java.util.*;

public class Player {
    private List<Tile> hand; //13 Tiles, plus 1 drawn Tile, minus melds
    private int score;
    private boolean riichi;
    private List<Tile> discards;
    private List<Meld> meldsClosed;
    private List<Meld> meldsOpen;
    private List<Tile> pairs;
    private boolean tenpai;
    private boolean yaku;

    // Constructor
    public Player() {
        this.hand = new ArrayList<>();
        this.score = 35000; // Standard starting score
        this.riichi = false;
        this.discards = new ArrayList<>();
        this.meldsOpen = new ArrayList<>();
        this.meldsClosed = new ArrayList<>();
    }

    // Methods
    public void drawTile(Tile tile) {
        //add logic to put tile in sorted list
        //order is m1,m9,p1-9p9,s1-s9,z1-z7
        //logic: start from pos 0. iterate up until suit+rank <= current hand tile
        //always just add if hand is empty
        //spent a long time trying to be cool and adding to the empty hand at the end, but I think I'll just check for empty every time for now.
        //wait, I'll make a seperate method just for the initial draw?
        //oh I kept failing because I was trying to add something to the same list I'm iterating on...
        //back to good ol' for loop with i
        String tileString = tile.toStringSort();
        int handSize = hand.size();

        for (int i = 0; i < handSize; i++) {
            String elemString = hand.get(i).toStringSort();
            if (tileString.compareTo(elemString) <= 0 ) {
                hand.add(i,tile);
                return; //almost forgot to break
            }
        }
        hand.add(tile);
    }
    
    public void drawTileInitial(Tile tile) {
        hand.add(tile);
    }

    public void discardTile(Tile tile) {
        hand.remove(tile);
        discards.add(tile);
    }

    public void declareRiichi() {
        this.riichi = true;
    }

    public void checkTenpai() {
        //need to check valid closed melds, open melds, 7 pairs, 13 orphans
        //if 4 closed melds then tenpai (waiting for pair)
        if (this.meldsClosed.size() == 4) {this.tenpai = true;}
        //hard part iterating through hand to check for valid melds, sort helps a lot though
        //will have to break this out into its own method
        /* Find potential melds for tenpai in hand
         * Should break if 4 closed melds not possible
         * Will need for each pass:
         *  - a list of potential melds
         *  - a way to keep index on melds for all potential hand combos
         *  - a list of any existing pairs
         *      - ex. 1p,1p,2p,3p,4p,5p,5p,6p,7p,8p,9p,9p,9p
         *          - look at first tile, 1p. next tile will need to be 1p or 2p for a meld1
         *          - also need to index 1p as the potential pair tile
         *          - look at next tile, 1p. next tile will need to be 1p for a meld1. Start new meld2 with 1p looking for 2p
         *          - also index this as the potential pair
         *          - look next, 2p. meld1 broken. meld 2 looking for 3p next. start new meld with 2p. start new potential pair with 2p
         *          - ^now we have our main loop, making pairs and melds. End when no more tiles in hand
         *          - also make sure it doesn't try to chii meld the honor tiles
         *          - now we have a list of melds and pairs
         *          - melds: 123p,234p,345p,456p,567p,678p,789p,999p
         *          - pairs: 11p,55p,77p,99p
         *          - terminals and honors: 1p,1p,9p,9p
         *          - easiest to check for 13 orphans
         *          - iterate starting with m1, break as soon as one in the list isn't there. break if more than 2 in the list, break if more than 2 pairs, break if any meld.
         *          - next check for 6 pairs. if 6 pairs tenpai true
         *          - Now the annoying part, pick closed melds and exclude melds that use the same tiles. recursion seems to work. Node references would help too to save space
         *              - create a seperate hand: 1p,1p,2p,3p,4p,5p,5p,6p,7p,7p,8p,9p,9p
         *              - start by removing meld1(123p) from the hand(1p,4p,5p,5p,6p,7p,8p,9p,9p,9p)
         *              - check if we can remove meld2(234p), we can't. index meld2 as second start for next loop
         *              - check if we can remove meld3(345p), we can't. index meld3?
         *              - check if we can remove meld4(456p), we can. hand(1p,5p,7p,8p,9p,9p,9p)
         *                  - should I be popping like the 1p and 5p at some point? Does this indicate a break for tenpai? I guess they could be the potential pair tile...
         *                  - but once you have 2 potential pair tiles, you no longer have tenpai
         *                  - also there's an "x tiles from tenpai" that some people use. Maybe I should incorporate that. especially for the helper.
         *              - no meld5, no meld6, yes meld7(789p). hand(1p,5p,9p,9p)
         *                  - no meld8, check pairs. yes pair 99p. hand(1p,5p)
         *                      - 1 tile from tenpai for this hand
         *                          - valid tiles to achive tenpai are 1p,2p,3p,4p,5p,6p,7p,9p(special 9p case where you add a 9p and discard 1p or 5p to wait for a new pair)
         *              - pop off meld7, check remaining melds meld8(999p). hand(1p,5p,7p,8p)
         *                  - check pairs, none
         *                  - 1 tile from tenpai
         *                      - valid tiles to achieve tenpai are 1p,5p,6p,9p
         *              
         */
        //create seperate temp hand
        List<Tile> tenpaiHand = new ArrayList<>(this.hand);
        //check melds
        meldsCheck(tenpaiHand);
        //check pairs, serperate because can't return multiple obj
        pairsCheck(tenpaiHand);

        this.tenpai = false;
    }

    private List<Meld> meldsCheck(List<Tile> tempHand) {
        List<Meld> possibleMelds = new ArrayList<>();
        //iterate through the tenpaiHand, create melds recursively
        int handSize = tempHand.size();
        for (int i = 0; i < handSize - 2; i++) {
            //create temporary meld
            List<Tile> tempMeldList = new ArrayList<>();
            tempMeldList.add(tempHand.get(i));
            tempMeldList.add(tempHand.get(i+1));
            tempMeldList.add(tempHand.get(i+2));
            Meld tempMeld = new Meld("unknown", tempMeldList);
            //check meld is valid
            if(!tempMeld.getType().equals("invalid")) {
                //add meld to list
                possibleMelds.add(tempMeld);
            }
            //remove index from temp hand
            //I am stopping here because I forgot about the case for double/triple sequences
            //Will think of a better approach for all of this
            
            
        }
        return possibleMelds;
    }

    //might replace this with an internal Meld Class check
    /* Did replace this within the Meld Class
    private boolean isValidMeld(Tile tile1, Tile tile2, Tile tile3) {
        //is valid pon meld
        if (tile1.getRank() == tile2.getRank() && tile1.getRank() == tile3.getRank()) {
            return true;
        //is valid chii meld
        } else return tile1.getRank() == (tile2.getRank() + 1) && tile1.getRank() == (tile3.getRank() + 2);
    }
    */

    private List<Tile> pairsCheck(List<Tile> tempHand) {
        return null;
    }

    // Other methods like declareTsumo, declareRon, etc.

    public int getScore() {return this.score;}

    //sort hand
    public void sortHand() {
        //sort on draw
    }

    //delete after debug
    //get hand for debug print
    public List<Tile> getHand() {return hand;}
}
