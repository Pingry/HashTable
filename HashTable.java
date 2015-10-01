/**Data structure that uses the .hashCode() method to place objects into an array.  Objects can be inserted
*and removed in constant time.  Automatically resizes table when fill reaches a certain percentage.  
*
*@author Aaron Cooper
*@version 1.0
*/

public class HashTable
{
	private Object[] table;
	
	/** ratio of table that needs to be filled before rehash*/
	private final double LOAD_FACTOR = 0.6;
	private int size;
	private int quantityFilled;
	
	/**
	* Constructs an empty HashTable with an initial size 7.
	*/ 
	public HashTable()
	{
		table = new Object[7];
		size = 7;
		quantityFilled = 0;
	}
	
	//initializes empty HashTable with size n
	
	
	/**
	*Places obj into the table based on it's hashValue.  If that position is already taken, will move to the 
	*next available determined by quadratic probing to avoid clumping.  If the ratio of the HashTable filled
	*exceeds the LOAD_FACTOR, rehashes.
	*
	*@param obj Inserts the specified object in the HashTable
	*/
	public void insert(Object obj)
	{
		if(((double)(quantityFilled))/size >= LOAD_FACTOR)
			rehash();				 
		
		int hashValue = obj.hashCode() % size;		
		int quadVal = 1;
		while(true)
		{	
			if(table[hashValue] == null)
			{
				table[hashValue] = obj;
				quantityFilled ++;
				break;
			}
			else 
			{
				hashValue = (obj.hashCode() + quadVal) % size;
				quadVal *= 2;		
			}
		}
		
	}
	
	/**
	*Creates a new table of size equal to the next prime number from the current size.  Inserts each object in the current
	*table into the new table, and sets HashTable's table to the new table.
	*/
	private void rehash()
	{
		int n = 0;
		for(int i = size + 1; ! isPrime(i); i++)
		{
			n = i + 1;
		}
			
		Object[] newHash = new Object[n];
		for(int i = 0; i < size; i++)
		{
			if(table[i] != null)
			{
				int hashValue = table[i].hashCode() % size;		
				int quadVal = 1;
				while(true)
				{
						if(newHash[hashValue] == null)
						{
							newHash[hashValue] = table[i];
							break;
						}
						else
						{
							hashValue = (table[i].hashCode() + quadVal) % size;
							quadVal *= 2;
						}
				}
			}
		}
		table = newHash;
		size = n;
		
	}
		
	/**
	*Returns contents of HashTable as a string
	*@return string of each element in hash table, each on a new line.
	*/
	public String toString()
	{
		String finalString = "";
		for(int i = 0; i < size; i++)
		{
			finalString = finalString + table[i];
			finalString = finalString + "\n";
		}
		return finalString;
	}
	
	/**
	*Evaluates whether input int is prime.  Used for rehash, to avoid conflicts with quadratic probing.
	*@param n int evaluated for primality.
	*@return whether n is prime.
	*/
	private boolean isPrime(int n) //https://en.wikipedia.org/wiki/Primality_test#Pseudocode
	{
		if(n%2 == 0 || n%3 == 0)
			return false;
		int i = 5;
		while(i*i <= n)
		{
			if(n % i == 0 || n % (i + 2) == 0)
				return false;
			i += 6;
		}
		return true;
	}
	
	
	
	
}
