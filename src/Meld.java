import java.util.*;

public class Meld {
    //list of 3-4 tiles
    //mark one of the tiles as the chi/pon/kon tile
    private String type; //chi, pon, kan
    private List<Tile> tiles;

    public Meld(String type, List<Tile> tiles) {
        this.type = type;
        this.tiles = tiles;
    }

    public String getType() {
        return type;
    }

    public List<Tile> getTiles() {
        return tiles;
    }
}
