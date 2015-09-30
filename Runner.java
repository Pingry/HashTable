/** This is my runner, I use this to make sure everything in my HashTable class
	is running smoothly
	
	@author Zachary Keller
	@version 9/29/15
*/

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
		HashTable h= new HashTable(7);
		for (int i=0; i<5; i++)
		{
			h.put(new Object());
			h.put("testString");
		}

		//System.out.println(h);  -used to see the final table
	}

			
}