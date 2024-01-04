import java.util.ArrayList;

public class Game {
    private final ArrayList<Guess> guesses = new ArrayList<Guess>();

    private final String word;

    private StringBuilder wordProgress = new StringBuilder();

    private int numMistakes = 0;

    private String[] hangman = {"  +---+", "      |", "      |", "      |", "      |", "      |", "========="};

    private int gameState; // 0: in-progress, 1: loss, 2: win

    private int difficulty; // 0: easy, 1: medium: 2: hard

    private int category; // 0: animals, 1: fruits, 2: countries

    public Game(int difficulty, int category, String word) {
        this.difficulty = difficulty;
        this.category = category;
        this.word = word;

        for (char letter : word.toCharArray()) {
            wordProgress.append("_");
        }

        System.out.println("Difficulty: " + GameManager.DIFFICULTIES[difficulty]);
        System.out.println("Category: " + GameManager.CATEGORIES[category]);
        updateHangman(numMistakes);
        System.out.println(wordProgress);
    }

    public String getDifficulty() {
        return GameManager.DIFFICULTIES[difficulty];
    }

    public String getCategory() {
        return GameManager.CATEGORIES[category];
    }

    public int getNumMistakes() {
        return numMistakes;
    }

    public int getGameState() {
        return gameState;
    }

    private void updateHangman(int numMistakes) {
        switch (numMistakes) {
            case 1:
                hangman[1] = "  |   |";
                break;
            case 2:
                hangman[2] = "  O   |";
                break;
            case 3:
                hangman[3] = "  |   |";
                break;
            case 4:
                hangman[3] = " /|   |";
                break;
            case 5:
                hangman[3] = " /|\\  |";
                break;
            case 6:
                hangman[4] = " /    |";
                break;
            case 7:
                hangman[4] = " / \\  |";
                break;
        }

        for(String part : hangman) {
            System.out.println(part);
        }
    }

    public void createGuess(String input) {
        if (gameState != 0) {
            System.out.println("Game over... Reply NEW to play again or SCORE for high scores!");
            return;
        }

        Guess guess = new Guess(input, guesses, word);
        guesses.add(guess);

        if (guess.isValid()) {
            if (guess.getCorrectIndexes().isEmpty()) {
                System.out.println("Incorrect! " + ++numMistakes + "/7 mistakes.");
                updateHangman(numMistakes);
                if (numMistakes == 7) {
                    System.out.println("You lose... Reply NEW to play again or SCORE for high scores!");
                    gameState = 1;
                    return;
                }
            } else {
                System.out.println("Correct!");
            }
        }

        for (int index : guess.getCorrectIndexes()) {
            wordProgress.setCharAt(index, guess.getLetter());
        }

        System.out.println(wordProgress);

        if (wordProgress.toString().equals(word)) {
            System.out.println("You win! Reply NEW to play again or SCORE for high scores!");
            gameState = 2;
        }
    }
}
