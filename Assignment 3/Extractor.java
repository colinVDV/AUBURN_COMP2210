import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
import java.io.FileNotFoundException;

/**
 * Extractor.java. Implements feature extraction for collinear points in
 * two dimensional data.
 *
 * @author  Colin Vande Vijvere (ccv0004@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version TODAY
 *
 */
public class Extractor {
   
   /** raw data: all (x,y) points from source data. */
   private Point[] points;
   
   /** lines identified from raw data. */
   private SortedSet<Line> lines;
  
   /**
    * Builds an extractor based on the points in the file named by filename. 
    */
   public Extractor(String filename) throws FileNotFoundException {
      Scanner scan = new Scanner(new File(filename));
         //first int in file is the number of points
      int fileSize = scan.nextInt();
         
      //creating point object and storing them in the array
      points = new Point[fileSize];
      for (int i = 0; i < fileSize; i++) {
         int x = scan.nextInt();
         int y = scan.nextInt();
         Point p = new Point(x,y);
         points[i] = p;
      }
   } 
  
   /**
    * Builds an extractor based on the points in the Collection named by pcoll. 
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   public Extractor(Collection<Point> pcoll) {
      points = pcoll.toArray(new Point[]{});
   }
  
   /**
    * Returns a sorted set of all line segments of exactly four collinear
    * points. Uses a brute-force combinatorial strategy. Returns an empty set
    * if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesBrute() {
      lines = new TreeSet<Line>();
     
      for (Point element: points) {
         for (Point elementB : points) {
            for (Point elementC : points) {
               for (Point elementD: points) {
                  Line line = new Line();
                  line.add(element);
                  line.add(elementB);
                  line.add(elementC);
                  line.add(elementD);
               
                  if (line.length() == 4) {
                     lines.add(line);
                  }
               }
            }
         }
      }
      
      
      return lines;
   }
  
   /**
    * Returns a sorted set of all line segments of at least four collinear
    * points. The line segments are maximal; that is, no sub-segments are
    * identified separately. A sort-and-scan strategy is used. Returns an empty
    * set if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesFast() {
     // instance variables
      lines = new TreeSet<Line>();
      Point[] copy = Arrays.copyOf(points, points.length);
      Line line = new Line(); 
      boolean check = true;
      
      //for each loop looping through original array
      for(Point element : points) {
      
         //Sorting the copied array using slopeOrder
         Arrays.sort(copy, element.slopeOrder);
         
         //looping through the new sorted array
         for(Point elementB : copy) {
            line.add(copy[0]);
            //boolean turns false if we run out of elements
            check = line.add(elementB);
            
            if(!check) {
            // if our line reached length four, we can add it to the lines array
               if (line.length() >= 4 && lines.contains(line) == false) {
                  lines.add(line);
               }
               //creating new line with different first element
               line = new Line();
               line.add(elementB);
            }
         }
      } 
      return lines;
   }
}
