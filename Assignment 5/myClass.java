import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class myClass implements WordSearchGame {
   private boolean lexiconCheck;
   private String[][] board;
   private boolean[][] checked;
   private List <Integer> path;
   private List <Integer> realPath;
   private int length;
   private int minLength;
   private TreeSet <String> lexicon;
   private SortedSet<String> validWords;
   
   
   
   public myClass () {
      lexicon = new TreeSet<String>();
      validWords = new TreeSet<String>();
      path = new ArrayList<Integer>();
      realPath = new ArrayList<Integer>();
   }

   public void loadLexicon (String fileName){
      if (fileName == null) {
         throw new IllegalArgumentException();
      }
      Scanner scan;
      Scanner scanLine;
      String line;
      
      try { 
         scan = new Scanner (new FileReader(fileName));
         while (scan.hasNext()) {
            line = scan.nextLine();
            scanLine = new Scanner(line);
            //scanLine.useDelimiter(" ");
            while (scanLine.hasNext()) {
               lexicon.add(scanLine.next().toUpperCase());
            }
         }
         
      }
      catch (Exception e){
      lexiconCheck = false;
         throw new IllegalArgumentException();
      }
      lexiconCheck = true;
   }
   




   public void setBoard(String[] letterArray) {
      if (letterArray == null) {
         throw new IllegalArgumentException();
      }
   //square root of total numbers to get side
      double sqrt = Math.sqrt(letterArray.length);
   
   //check if the side is a whole number.
      if (sqrt % 1 != 0) {
         throw new IllegalArgumentException("not the correct length");
      }
      else {
         length = (int) sqrt;
         checked = new boolean[length][length];
         board = new String[length][length];
         int count = 0;
         for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
               checked[i][j] = false;
               board[i][j] = letterArray[count].toUpperCase();
               count++;
            }
         }
      }
   }
   
   
   
   





// board is a 2D array, therefor we need 2 for each loops
   public String getBoard() {
      String result = "";
      for (String[] array: board) {
      for (String element: array) {
      result = result + element;
      }
      }
      return result;
   }









   public SortedSet <String> getAllValidWords(int minimumWordLength) {
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException("min word length too short");
      }
      if(!lexiconCheck) {
         throw new IllegalStateException("loading lexicon failed");
      }
      
      minLength = minimumWordLength;
      validWords.clear();
      
      for(int i = 0; i < length; i++) {
         for(int j = 0; j < length; j++) {
            locateWord(board[i][j], i , j);
         }
      }
      return validWords;
   }
   
   
   
   
   
   
   
   public void locateWord(String word, int x, int y) {
      if (!isValidPrefix(word)) {
         return;
      }
   
      checked[x][y] = true;
   
      if (isValidWord(word) && word.length() >= minLength) {
         validWords.add(word.toUpperCase());
      }
   
      for (int i = -1; i <= 1; i++) {
         for (int j = -1; j <= 1; j++) {
            if ((x + i) <= ((int) length - 1)
               && (y + j) <= ((int) length - 1)
               && (x + i) >= 0 && (y + j) >= 0 && !checked[x + i][y + j]) {
               checked[x + i][y + j] = true;
               locateWord(word + board[x + i][y + j], x + i, y + j);
               checked[x + i][y + j] = false;
            }
         }
      }
      checked[x][y] = false;
   }

   
   
   
   
   

   public int getScoreForWords(SortedSet <String> words, int minimumWordLength){
      if(!lexiconCheck) {
         throw new IllegalStateException("loading lexicon failed");
      }
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException("wordToCheck is not valid");
      }
      
      int score = 0;
      
      for(String element: words) {
      int length = element.length();
      score = score + (length - minimumWordLength) + 1;
      }
      return score;
   }





   public boolean isValidWord (String wordToCheck) {
      if(!lexiconCheck) {
         throw new IllegalStateException("loading lexicon failed");
      }
      if (wordToCheck == null) {
         throw new IllegalArgumentException("wordToCheck is not valid");
      }
      return lexicon.contains(wordToCheck.toUpperCase());
   }






   public boolean isValidPrefix(String prefixToCheck) {
      if(!lexiconCheck) {
         throw new IllegalStateException("loading lexicon failed");
      }
      if (prefixToCheck == null) {
         throw new IllegalArgumentException("wordToCheck is not valid");
      }
      return lexicon.ceiling(prefixToCheck).startsWith(prefixToCheck);
   }







   public List<Integer> isOnBoard(String wordToCheck){
      if(!lexiconCheck) {
         throw new IllegalStateException("loading lexicon failed");
      }
      if (wordToCheck == null) {
         throw new IllegalArgumentException("wordToCheck is not valid");
      }
       
      realPath.clear();
      path.clear();
       
      for (int i = 0; i < (int) length; i++) {
         for (int j = 0; j < length; j++) {
            if (Character.toUpperCase(board[i][j].charAt(0))
               == Character.toUpperCase(wordToCheck.charAt(0))) {
               int value = j + (i * length);
               path.add(value);
               recursion(wordToCheck, board[i][j], i, j);
               if (!realPath.isEmpty()) {
                  return realPath;
               }
               path.clear();
               realPath.clear();
            }
         }
      }
      return path;
   }
   
   
   
   
   
   
   
   public void recursion(String wordToCheck, String current, int x, int y) {
      checked[x][y] = true;
      if (!(isValidPrefix(current))) {
         return;
      }
      if (current.toUpperCase().equals(wordToCheck.toUpperCase())) {
         realPath = new ArrayList<Integer>(path);
         return;
      }
      for (int i = -1; i <= 1; i++) {
         for (int j = -1; j <= 1; j++) {
            if (current.equals(wordToCheck)) {
               return;
            }
            if ((x + i) <= (length - 1)
               && (y + j) <= (length - 1)
               && (x + i) >= 0 && (y + j) >= 0 && !checked[x + i][y + j]) {
               checked[x + i][y + j] = true;
               int value = (x + i) * length + y + j;
               path.add(value);
               recursion(wordToCheck, current
                  + board[x + i][y + j], x + i, y + j);
               checked[x + i][y + j] = false;
               path.remove(path.size() - 1);
            }
         }
      }
      checked[x][y] = false;
      return;
   }

}