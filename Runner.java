/** This is my runner, I use this to make sure everything in my HashTable class
	is running smoothly
	
	@author Zachary Keller
	@version 1 Final- 10/7/15
*/


public class Runner
{

/** Here in my main, I create a new hashTable and then populate it with
	keys and values. I then test the various functions in my HashTable class,
	and print out the actual table.
	@param args Usual main parameter
	
*/
	public static void main( String [] args )
	{
		HashTable<Integer, String> h= new HashTable<Integer, String>(7);
		h.put(1, "1stItem");
		h.put(2, "2ndItem");
		h.put(3, "3rdItem");
		h.put(4, "4thItem");
		h.put(5, "5thItem");
		h.remove(2);
		
		System.out.println("get: " + h.get(3));
		System.out.println("containsValue: " + h.containsValue("1stItem"));
		System.out.println("containsKey: " + h.containsKey(1));

		System.out.println( "The actual Table:  " + h);  //-used to see the final table
	}





			
}