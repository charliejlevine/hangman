import java.util.ArrayList;

public class Guess {
    private final String input;

    private char letter;

    private boolean isLetter;

    private boolean isUnique;

    private final ArrayList<Integer> correctIndexes = new ArrayList<Integer>();

    public Guess(String input, ArrayList<Guess> guesses, String word) {
        this.input = input;
        isLetter = input.length() == 1;

        if (!isLetter) {
            System.out.println("Input must be a single letter. Try again!");
            return;
        }

        letter = input.charAt(0);

        isUnique = true;
        for (Guess guess : guesses) {
            if (guess.getLetter() == letter) {
                System.out.println("You already guessed this letter. Try again!");
                isUnique = false;
                return;
            }
        }

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                correctIndexes.add(i);
            }
        }
    }

    public char getLetter() {
        return letter;
    }

    public boolean isValid() {
        return isLetter && isUnique;
    }

    public ArrayList<Integer> getCorrectIndexes() {
        return correctIndexes;
    }
}
