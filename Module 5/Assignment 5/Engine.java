import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Engine implements WordSearchGame {
    private TreeSet<String> lexicon;
    private String[][] board;
    private int size;

    public void loadLexicon(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException();
        }
        try {
            lexicon = new TreeSet<String>();
            Scanner file = new Scanner(new File(fileName));
            while (file.hasNext()) {
                lexicon.add(file.next().toUpperCase());
            }
            file.close();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
        }
    }

    public void setBoard(String[] letterArray) {
        if (letterArray == null) {
            throw new IllegalArgumentException();
        }
        int length = (int) Math.sqrt(letterArray.length);
        if (length * length != letterArray.length) {
            throw new IllegalArgumentException("letterArray is not square.");
        }
        board = new String[length][length];
        int count = 0;
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < length; y++) {
                board[x][y] = letterArray[count];
                count++;
            }
        }
        size = length;
    }

    public String getBoard() {
        String s = "";
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                s += board[x][y] + " ";
            }
        }
        return s;
    }

    public SortedSet<String> getAllScorableWords(int minimumWordLength) {
        if (minimumWordLength < 1) {
            throw new IllegalArgumentException();
        }
        if (lexicon == null) {
            throw new IllegalStateException();
        }
        SortedSet<String> scorableWords = new TreeSet<>();
        if(size == 1 && board[0][0].length() >= minimumWordLength)
            scorableWords.add(board[0][0]);
        return scorableWords;
    }


    public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
        if (words == null || minimumWordLength < 1) {
            throw new IllegalArgumentException();
        }
        int totalScore = 0;
        for (String word : words) {
            if (word.length() >= minimumWordLength) {
                int wordScore = 1 + (word.length() - minimumWordLength);
                totalScore += wordScore;
            }
        }
        return totalScore;
    }

    public boolean isValidWord(String wordToCheck) {
        if (wordToCheck == null || lexicon == null) {
            throw new IllegalArgumentException();
        }
        if (lexicon.contains(wordToCheck)) {
            return true;
        }
        return false;
    }

    public boolean isValidPrefix(String prefixToCheck) {
        if (prefixToCheck == null || lexicon == null) {
            throw new IllegalArgumentException();
        }
        String w = lexicon.ceiling(prefixToCheck);
        if (w != null) {
            return w.startsWith(prefixToCheck);
        }
        return false;
    }

    public List<Integer> isOnBoard(String wordToCheck) {
        if (wordToCheck == null || wordToCheck.isEmpty()) {
            return new LinkedList<>();
        }
        wordToCheck = wordToCheck.toUpperCase();
        LinkedList<Integer> positions = new LinkedList<>();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (board[x][y].equals(String.valueOf(wordToCheck.charAt(0))) && dfsSearchForWords(wordToCheck, x, y, 0, positions)) {
                    return positions;
                }
            }
        }
        return new LinkedList<>();
    }

    private boolean dfsSearchForWords(String wordToCheck, int x, int y, int index, LinkedList<Integer> positions) {
        if (index == wordToCheck.length()) {
            return true;
        }
        if (x < 0 || x >= size || y < 0 || y >= size) {
            return false;
        }
        if (!board[x][y].equals(String.valueOf(wordToCheck.charAt(index)))) {
            return false;
        }
        int position = x * size + y;
        positions.add(position);
        String temp = board[x][y];
        board[x][y] = "";
        int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
        int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
        for (int i = 0; i < 8; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            if (dfsSearchForWords(wordToCheck, newX, newY, index + 1, positions)) {
                return true;
            }
        }
        positions.removeLast();
        board[x][y] = temp;
        return false;
    }
}
