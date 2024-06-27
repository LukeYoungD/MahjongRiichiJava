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
}
