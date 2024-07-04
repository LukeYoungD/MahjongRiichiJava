import java.util.*;

public class Meld {
    //list of 3-4 tiles
    //mark one of the tiles as the chi/pon/kon tile
    private String type; //chi, pon, kan
    private List<Tile> tiles;

    public Meld(String type, List<Tile> tiles) {
        this.type = type;
        this.tiles = tiles;
        if (this.type.contains("unknown")) { //using unknown for now, can change types to use numbers to save space if necessary
            this.type = checkType(this.tiles);
        }
    }

    private String checkType(List<Tile> tiles2) {
        
        if (tiles2.get(0).getSuit() == tiles2.get(1).getSuit() && tiles2.get(1).getSuit() == tiles2.get(2).getSuit()) {
            //is valid pon meld
            if (tiles2.get(0).getRank() == tiles2.get(1).getRank() && tiles2.get(1).getRank() == tiles2.get(2).getRank()) {
                return "pon";
            //else is chii meld
            } else if (tiles2.get(0).getSuit() != 'z' && tiles2.get(0).getRank() == (tiles2.get(1).getRank() + 1) && tiles2.get(0).getRank() == (tiles2.get(2).getRank() + 2)) {
                return "chii";
            }
        } 
        return "invalid";
    }

    public String getType() {
        return type;
    }

    public List<Tile> getTiles() {
        return tiles;
    }
}
