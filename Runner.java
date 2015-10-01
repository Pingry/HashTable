/** This is my runner, I use this to make sure everything in my HashTable class
	is running smoothly
	
	@author Zachary Keller
	@version 9/29/15
*/
import java.util.Random;

public class Runner
{

/** Here in my main, I create a new hashTable and then populate it with
	objects. I hardcoded the length of 7 in because origninally I had that be
	args[0] but I found that to be kind of a hassle to work with. Same thing for the 
	justification of using 10 objects to populate the table. I do make sure to put in enough
	that the table has to rehash.
	@param args 
	
*/
	public static void main( String [] args )
	{
		HashTable<Integer, String> h= new HashTable<Integer, String>(7);
		h.put(1, "1stItem");
		h.put(1, "2ndItem");
		h.put(3, "3rdItem");
		h.put(4, "4thItem");
		h.put(5, "5thItem");;
		System.out.println(h.remove(5));

		System.out.println(h);  //-used to see the final table
	}

			
}