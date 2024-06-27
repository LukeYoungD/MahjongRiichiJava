public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        //Game State
        GameState gameState = new GameState();
        gameState.initializeGame();

        // Example game loop
        for (int i = 0; i < 10; i++) { // Simulate 10 turns
            Player currentPlayer = gameState.getPlayers().get(gameState.getCurrentTurn());
            currentPlayer.suggestMove(gameState);
            gameState.proceedToNextTurn();
        }
    }
}
