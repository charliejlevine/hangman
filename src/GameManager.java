import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameManager {
    private static final Random random = new Random();

    private static final Scanner scanner = new Scanner(System.in);

    private final String[] words = {"apple", "orange", "banana"};

    private final ArrayList<Game> games = new ArrayList<Game>();

    private Game game;

    public GameManager() {
        createGame();

        while(true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("new")) {
                createGame();
                continue;
            }

            game.createGuess(input);
        }
    }

    public void createGame() {
        int numWords = words.length;
        int randIndex = random.nextInt(numWords);
        String word = words[randIndex];
        game = new Game(word);
        games.add(game);
    }
}
