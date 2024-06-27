public class Tile {
    private char suit; //chi, pon, sou, honors
    private int rank;
    private String type; //inside, terminals, dragons, wind
    private boolean isDora;
    private boolean isRed;
    private boolean isSideways;

    public Tile(char suit, int rank, String type) {
        this.suit = suit;
        this.rank = rank;
        this.type = type;
        this.isDora = false;
        this.isSideways = false;
    }

    public Tile(char suit, int rank, String type, boolean red) {
        this.suit = suit;
        this.rank = rank;
        this.type = type;
        this.isDora = false;
        this.isRed = red;
        this.isSideways = false;
    }
    //getters and setters
    public char getSuit() {return suit; }
    public int getRank() {return rank; }
    public String getType() {return type; }
    public Boolean isDora() {return isDora; }
    public Boolean isSideways() {return isSideways; }

    public void setDora(boolean bool) {
        this.isDora = bool;
    }
    public void setSideways(boolean bool) {
        this.isSideways = bool;
    }
}