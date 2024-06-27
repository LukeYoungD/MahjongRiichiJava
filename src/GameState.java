import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameState {
    private List<Player> players;
    private List<Tile> wall;
    private List<Tile> deadWall;
    private int currentTurn;
    private String windRound;
    private int dealerPosition; //starts at 0, 1, 2
    private List<Tile> doraIndicators;

    // Constructor
    public GameState() {
        this.players = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            this.players.add(new Player());
        }
        this.wall = new ArrayList<>();
        this.deadWall = new ArrayList<>();
        this.currentTurn = 0;
        this.windRound = "East";
        this.dealerPosition = 0;
        this.doraIndicators = new ArrayList<>();
    }

    // Methods
    public void initializeGame() {
        // Initialize tiles, shuffle, deal tiles, etc.
        // Add (p)in, (s)ou tiles 1-9
        //      make first "5" of each suit red
        // Add (m)an 1, (m)an 9
        // Add 4 Winds (z) East=1, South=2, West=3, North=4
        // Add 3 Dragons (z) White=5, Green=6, Red=7
        // repeat * 4
        for (int i = 0; i < 4; i++) { //for 4 times
            char suit = 'p';
            for (int j = 0; j < 2; j++ ) { //for 2 suits
                
                for (int k = 0; k < 9; k++) { //for 9 numbers in a suit
                    //logic for terminal, insides, and red dora
                    if(k == 0 || k == 8) { //if number will be 1 or 9
                        wall.add(new Tile(suit, k + 1, "Terminal"));
                    } else if(i == 0 && k == 4){ //if this is the first 5 of this suit
                        wall.add(new Tile(suit, k + 1, "Inside",true));
                    } else { //add remaining number tiles
                        wall.add(new Tile(suit, k + 1, "Inside"));
                    }
                }
                suit = 's'; //switch suit to (p)in before iterating
            }
            //add the 1 and 9 Man
            wall.add(new Tile('m', 1, "Terminal"));
            wall.add(new Tile('m', 9, "Terminal"));
            //add Honors
            for (int j = 0; j < 7; j++) {
                wall.add(new Tile('h', j + 1, "Honor"));
            }
        }
        //Shufffle the Wall
        Collections.shuffle(wall);
        //Initial Drawing Phase
        //Technically, this should be 3 sets of drawing 4 tiles at a time, 
        //with a 4th round of drawing just 1 tile
        for (Player player : players) {
            for (int i = 0; i < 13; i++) {
                player.drawTile(wall.remove(wall.size() - 1));
            }
        }
        //indicate the dead wall? or just keep a count?
        //the logic for the dead wall might be easier with a seperate wall, but it's unnecessary
    }

    public void proceedToNextTurn() {
        currentTurn = (currentTurn + 1) % 3;
    }

    public void checkForWin() {
        // Check if any player has won
        /*
         * Win Conditions:
         * After Any Round: 
         *  - Player has less than 0 score
         * After East 3 or any South
         *  - Dealer did not win
         *  & Dealer did not Tenpai
         *   - Player score >= 40,000
         *  - Dealer did win & Dealer Player score >=40,000 & Dealer Player Score > other players
         * After South 3 end game
         */
    }

    public void calculateScores() {
        // Calculate the scores for the round
        //lol good luck on this one
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }
}
