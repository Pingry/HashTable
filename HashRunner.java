/**
 * @ author Kartikeya Sharma
 * @ course Advanced Topics
 * @ instructor Mr. Burkhart
 * @ period Period 6
 * @ description Primitive runner to test HashTable. Note that the runner does not include a comprehensive
     series of statements to adequately test all functionalities of the HashTable class. Most
     testing statements have been removed for submission purposes.
 */

/**
 Questions for Mr. Burkhart:
 1. What happens if someone stores two of the same value? - i.e. the remove function
 2. Clarify comment on Google Classroom - was not here for class on Monday (10/5/15)
 3. When removing an entry from the table, should we replace it with null?
 */
 
public class HashRunner{
      @SuppressWarnings("unchecked") public static void main (String[] args){
         HashTable t = new HashTable<Object,Object>();
         t.put(new Object(),new Object());
         String a = "Computer Science";
         String b = "Strings print out their String literal in the toString() method.";
         t.put(a,b);
         String c = "Chipwhich";
         String d = "Strings that are different.";
         t.put(c,d);
         String e = "Google Classroom";
         String f = "Strings with different hashcodes.";
         t.put(e,f);
         System.out.print(t);
     }
 }