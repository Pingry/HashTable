/**
 * Kartikeya Sharma
 * Advanced Topics
 * Mr. Burkhart
 * Period 6
 */

/**
 * Primitive runner to test HashTable. Note that the runner does not include a comprehensive
 * series of statements to adequately test all functionalities of the HashTable class. Most
 * testing statements have been removed for submission purposes.
 */
 
 public class HashRunner{
     public static void main (String[] args){
         HashTable t = new HashTable();
         t.put(new Object());
         String s = "Strings print out their String literal in the toString() method.";
         t.put(s);
         System.out.print(t);
     }
 }