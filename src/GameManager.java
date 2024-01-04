import java.util.*;

public class GameManager {
    private static final Random random = new Random();

    private static final Scanner scanner = new Scanner(System.in);

    public static final String[] DIFFICULTIES = {"Easy", "Medium", "Hard"};

    public static final String[] CATEGORIES = {"Animals", "Fruits", "Countries"};

    public static final String[][][] WORDS = {
            {
                    {"dog", "cat", "bird"},
                    {"apple", "banana", "orange"},
                    {"usa", "canada", "mexico"}
            },
            {
                    {"elephant", "giraffe", "lion"},
                    {"strawberry", "grapefruit", "kiwi"},
                    {"brazil", "china", "india"}
            },
            {
                    {"hippopotamus", "rhinoceros", "crocodile"},
                    {"pomegranate", "pineapple", "mango"},
                    {"australia", "singapore", "venezuela"}
            }
    };

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

            if (input.equalsIgnoreCase("score")) {
                games.sort(Comparator.comparingInt(Game::getNumMistakes));
                System.out.printf("%-15s %-15s %-15s%n", "Difficulty", "Category", "Mistakes");
                System.out.println("-----------------------------------------------");
                for (Game game : games) {
                    if (game.getGameState() == 2) {
                        System.out.printf("%-15s %-15s %-15d%n", game.getDifficulty(), game.getCategory(), game.getNumMistakes());
                    }
                }
                continue;
            }

            game.createGuess(input);
        }
    }

    public void createGame() {
        System.out.println("---New Hangman Game---");

        int difficulty = 0;
        while (difficulty == 0) {
            System.out.println("Reply EASY, MEDIUM, or HARD to choose your difficulty!");
            String input = scanner.nextLine();
            switch (input.toLowerCase()) {
                case "easy":
                    difficulty = 1;
                    break;
                case "medium":
                    difficulty = 2;
                    break;
                case "hard":
                    difficulty = 3;
                    break;
            }
        }

        int category = 0;
        while (category == 0) {
            System.out.println("Reply ANIMALS, FRUITS, or COUNTRIES to choose your category!");
            String input = scanner.nextLine();
            switch (input.toLowerCase()) {
                case "animals":
                    category = 1;
                    break;
                case "fruits":
                    category = 2;
                    break;
                case "countries":
                    category = 3;
                    break;
            }
        }

        String[] words = WORDS[difficulty - 1][category - 1];
        int numWords = words.length;
        int randIndex = random.nextInt(numWords);
        String word = words[randIndex];
        game = new Game(difficulty - 1, category - 1, word);
        games.add(game);
    }
}
