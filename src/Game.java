import java.util.ArrayList;

public class Game {
    private final ArrayList<Guess> guesses = new ArrayList<Guess>();

    private final String word;

    private StringBuilder wordProgress = new StringBuilder();

    private int numMistakes = 0;

    private int gameState = -1; //-1: in-progress, 0: loss, 1: win

    public Game(String word) {
        this.word = word;

        for (char letter : word.toCharArray()) {
            wordProgress.append("_");
        }

        System.out.println(wordProgress);
    }

    public void createGuess(String input) {
        if (gameState != -1) {
            System.out.println("Game over... Type NEW to play again!");
            return;
        }

        Guess guess = new Guess(input, guesses, word);
        guesses.add(guess);

        if (guess.isValid() && guess.getCorrectIndexes().isEmpty()) {
            System.out.println(++numMistakes + "/7 mistakes!");
            if (numMistakes == 7) {
                System.out.println("You lose... Type NEW to play again!");
                gameState = 0;
                return;
            }
        }

        for (int index : guess.getCorrectIndexes()) {
            wordProgress.setCharAt(index, guess.getLetter());
        }

        System.out.println(wordProgress);

        if (wordProgress.toString().equals(word)) {
            System.out.println("You win! Type NEW to play again!");
            gameState = 1;
        }
    }
}
