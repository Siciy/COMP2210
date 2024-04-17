import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Queue;

/**
 * Provides an implementation of the WordLadderGame interface. 
 *
 * @author Thomas Brown (trb0057@auburn.edu)
 */

public class Doublets implements WordLadderGame {

    TreeSet<String> lexicon;

    public Doublets(InputStream in) {
        try {
            lexicon = new TreeSet<String>(); 
            Scanner s = new Scanner(new BufferedReader(new InputStreamReader(in)));
            while (s.hasNext()) {
                String str = s.next();
                lexicon.add(str.toLowerCase());
                s.nextLine();
            }
            in.close();
        }
        catch (java.io.IOException e) {
            System.err.println("Error reading from InputStream.");
            System.exit(1);
        }
    }

    public int getWordCount() {
        return lexicon.size();
    }

    public boolean isWord(String str) {
        return lexicon.contains(str);
    }

    public int getHammingDistance(String str1, String str2) {
        if(str1.length() != str2.length())
            return -1;
        int count = 0;
        for(int x = 0; x < str1.length(); x++) {
            if(!str1.substring(x, x+1).equals(str2.substring(x, x+1)))
                count++;
        }
        return count;
    }

    public List<String> getNeighbors(String word) {
        List<String> neighbors = new ArrayList<>();
        if(word == null)
            return neighbors;
        neighbors = change(word);
        return neighbors;
    }

    public boolean isWordLadder(List<String> sequence) {
        if(sequence.size() == 0)
            return false;
        if(sequence.size() == 1)
            return true;
        for(int x = 0; x < sequence.size() - 1; x++) {
            String s1 = sequence.get(x);
            String s2 = sequence.get(x+1);
            if(getHammingDistance(s1, s2) == 1) {
                List<String> neighbors = getNeighbors(s1);
                if(!neighbors.contains(s2))
                    return false;
            }
            else
                return false;
        }
        return true;
    }

    public List<String> getMinLadder(String start, String end) {
        if(start.equals(end))
            return Arrays.asList(start);
        if(!isWord(start) || !isWord(end))
            return Arrays.asList();
        Queue<String> queue = new LinkedList<>();
        Map<String, String> searchedWords = new HashMap<>();
        queue.add(start);
        searchedWords.put(queue.peek(), null);
        while(!queue.isEmpty()) {
            String str = queue.poll();
            List<String> neighbors = getNeighbors(str);
            for(String current : neighbors) {
                if(isWord(current))
                    if(!searchedWords.containsKey(current)) {
                        searchedWords.put(current, str);
                        if(current.equals(end)) {
                            List<String> wordLadder = new ArrayList<>();
                            String word = end;
                            while (word != null) {
                                wordLadder.add(0, word);
                                word = searchedWords.get(word);
                            }
                            return wordLadder;
                        }
                        queue.add(current);
                    }
            }
        }
        return Arrays.asList();
    }

    public List<String> change(String word) {
        List<String> words = new ArrayList<>();
        String[] alphabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        for(int x = 0; x < word.length(); x++) {
            for(int y = 0; y < 26; y++) {
                String s = word.substring(0, x) + alphabet[y] + word.substring(x+1, word.length());
                if(isWord(s)) 
                    if(!s.equals(word))
                        words.add(s);
            }
        }
        return words;
    }
}

