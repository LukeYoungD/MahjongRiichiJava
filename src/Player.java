import java.util.*;

public class Player {
    private List<Tile> hand; //13 Tiles, plus 1 drawn Tile, minus melds
    private int score;
    private boolean riichi;
    private List<Tile> discards;
    private List<Meld> melds;
    private boolean tenpai;
    private boolean yaku;

    // Constructor
    public Player() {
        this.hand = new ArrayList<>();
        this.score = 35000; // Standard starting score
        this.riichi = false;
        this.discards = new ArrayList<>();
        this.melds = new ArrayList<>();
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

    // Other methods like declareTsumo, declareRon, etc.

    //sort hand
    public void sortHand() {
        //sort on draw, duh
    }

    //delete after debug
    //get hand for debug print
    public List<Tile> getHand() {return hand;}
}
