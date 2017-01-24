import java.lang.Math;
import java.util.Arrays;

/** This is my hashTable class. It creates and modifies the actual table
	that entries can be added to. The hashTable class contains put and remove
	functions that works in constant time, and a number of other helpful methods.
	
	@author Zachary Keller
	@version 1 Final- 10/7/15
*/


/** The HashTable Class, takes in two generics that eventually are used to create
	The entry
*/
public class HashTable<K,V>
{
/** Any percentage of occupancy above this is too much and will rehash.
	A modifier for this is below */
	private double loadFactor = .6;
	
/** the actual table that the objects are being appended to */
	private Entry[] table;
	
/** the number of objects in the table, is used to determine whether the loadFactor is being exceeded */
	private int occupied;
	
/** Used to calculate a prime number length of the table so that quadratic probing can work.
	Utilized in my primeChecker function and its helpers
	I made the length of the array such because that seems like an arbitrarily large enough number
	for the table to every possibly be, but it can be any number that the array can handle
	It has to be this way for the way I implemented the prime checker
*/
	private boolean[] primes = new boolean[10000];

/** Default constructor. Initializes the table to capacity 100.	*/
	public HashTable()
	{
		table = new Entry[100];
		occupied=0;
	}
	
/** Constructor with table length parameter
	@param c the length of the hashTable to be created
*/
	public HashTable(int c)
	{
		table = new Entry[c];
		occupied=0;
	}
	
/** Sets the load factor to some new percentage that the user desires,
	only works if the factor is less than 1 to prevent errors
	@param a The new loadFactor
*/	
	public void setLoadFactor(double a)
	{
		if (a < 1)
			loadFactor = a;
	}


/** Puts the object in the hashtable. 
	Deals with collisions by placing the object using quadratic probing
	@param k  The key, of type Generic K, that will be used to set the key of the entry
	@param v  The value, of type Generic V, that will be used to set the value of the entry
*/
	public void put(K k, V v)
	{
		Entry<K,V> e = new Entry<K,V>(k,v);
		int spot = e.getKey().hashCode();
		// System.out.println("Intial code is: " + spot);
		spot = spot% table.length;
		spot = Math.abs(spot);
		// System.out.println("Code after mod is: " + spot);
		// At this point, spot represents where the object will be placed
		// and change is how far the spot will be moved if there is an
		// object already in its spot
		int change = 0;
		boolean placed = false;
		while(placed == false)
		{
			int place= (spot + change) % table.length;
			if (table[place] !=null )
			{
				if ( (K) table[place].getKey() == k)
					return;
				if (change == 0)
					change += 1;
				else
					change = change * 2;
			}
			else
			{
				table[place] = e;
				placed = true;
				occupied += 1;
			}
		}
		double filled = ((double) occupied) / table.length;
		if (filled > loadFactor)
		{
			rehash();
		}
		
		
		
	}
	
/** Makes the size of the HashTable the next prime number after double the size of the previous one
	and calls put for each item contained within the old hashTable
*/ 
	 private void rehash()
	 {
	 	occupied = 0;
	 	Entry [] holder = new Entry[table.length];
	 	for (int i=0; i<table.length; i++)
	 	{
	 		holder[i] =table[i];
	 	}
	 	int newLen = (table.length * 2) + 1;
	 	System.out.println(newLen);
	 	table = new Entry[findNextPrime(newLen)];
	 	for ( int j=0; j<holder.length; j++)
	 	{
	 		if (holder[j] != null)
	 			this.put((K) holder[j].getKey(), (V) holder[j].getValue());
	 	}
	 }
	
/** This determines the next prime number after a given number
	I used a method copied from the internent to make this more efficient- see method below for more info.
	
	@param a the base number: it looks for the next prime after a;
	@return the next prime number
*/
	 private int findNextPrime(int a)
	 {
	 	if (primes[11] == false) // makes it so I only call fillSieve once, because primes[11] would not be false if I had already called it
	 	{
	 		fillSieve();
	 	}
	 	int i = a;	
	  	while (primes[i] != true)
	  	{
	  		i += 1;
	  		if (i == primes.length)
	  		{
	  			System.out.println("Error: rehashing has caused the new table to be to long");
	  			System.exit(0);
	  		}
	  	}

	  	return i;
	  	
	  		
	 }
	 
	  
/** This is code I copied from online (per Mr. Burkhat's recommendation). I basically starts
	with the assumption that all numbers in the length of prime array (see class fields above) are prime,
	and then eliminates ones that aren't by changing their value from true to false
	I made some changes but the original code can be found here: 
	http://www.mkyong.com/java/how-to-determine-a-prime-number-in-java/
*/
	private void fillSieve() 
	{
    	Arrays.fill(primes,true);        // assume all integers are prime.
    	primes[0]=primes[1]=false;       // we know 0 and 1 are not prime.
    	for (int i=2;i<primes.length;i++)
    	{
       		//if the number is prime, 
        	//then go through all its multiples and make their values false.
        	if (primes[i])
    		{
            	for (int j=2;i*j<primes.length;j++)
            	{
                	primes[i*j]=false;
            	}
        	}
    	}
	}

/** This code removes the entry from the table that has the given key, and then adjusts the
	table to make sure the removal of the one entry does not affect any others
	@param key  The key of the entry that is to be removed
	@return the value of the entry with the given key
*/
	 
	 public V remove(K key)
	 {
	 	int spot = key.hashCode();
	 	spot = spot% table.length;
		spot = Math.abs(spot);
		int finder=0;
		int place= (spot + finder) % table.length;
		while ( table[place] != null &&  (K) (table[place].getKey()) != key)
		{
			
			if (finder == 0)
				finder = 1;
			else
				finder = finder * 2;
				
			place= (spot + finder) % table.length;
				
				
		}
		if (table[place] == null)
		{
			return null;
		}
		
		Entry<K,V> temp = table[place];
		V output = temp.getValue();
		//actually removes the entry here
		table[place] = null;
		//now the rest of this function is just to reset the other entries
		//that may have been affected by the removal
		finder = finder * 2;
		place = (spot + finder) % table.length;
		while (table[place] != null)
		{
			put( (K) table[place].getKey() , (V) table[place].getValue());
			finder = finder * 2;
			place = (spot + finder) % table.length;
		}
		//because there is one fewer item in the table now
		occupied -= 1;
		return output;
		
	 }
	 
/** returns the value of an entry with the given key
	@param key  The key of the entry that is desired
	@return the value of the entry with the given key
*/

	public V get(K key)
	{
		int spot = key.hashCode();
	 	spot = spot% table.length;
		spot = Math.abs(spot);
		int finder=0;
		int place= (spot + finder) %table.length;
		while ( table[place]!= null &&  (K) (table[place].getKey()) != key)
		{
			
			if (finder == 0)
				finder = 1;
			else
				finder = finder * 2;
				
			place= (spot + finder) % table.length;
				
				
		}
		if (table[place] == null)
		{
			return null;
		}
		
		
		Entry<K,V> temp = table[place];
		V output = temp.getValue();
		return output;
		
	}
	
/** determines whether or not at least one entry with the
	given value exists in the hash table
	@param value  the value that is to be checked against the table
	@return whether or not the value exists in the table
*/
	public boolean containsValue(V value)
	{
		for (int i=0; i<table.length; i++)
		{
			if (table[i] != null)
			{
				if ( (V) table[i].getValue() == value)
				{
					return true;
				}
			}
		}
		return false;
	}
	
/** determines whether or not at least one entry with the
	given key exists in the hash table
	@param key  the key that is to be checked against the table
	@return whether or not the key exists in the table
*/
	public boolean containsKey(K key)
	{
		if (get(key) == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	
	}


/** Returns a string representation of the hash table, 
	complete with aesthetically pleasing dividers.
	@return the final string with the table in it
*/	 
	 public String toString()
	 {
	 	String s= "|";
	 	for (int i=0; i<table.length; i++)
	 	{
	 		// so that we can see the actual object, as opposed to its address
	 		if (table[i] != null)
	 		{
	 			V content= (V) table[i].getValue();
	 			s= s+ (content.toString());
	 		}
	 		else
	 			s= s+ table[i];
	 		s=s+ "|";
	 	}
	 	return s;
	 }
	 
	 /** The entry class that fits into the hashTable
	 	 Each entry contains a key and a value
	 */ 
	 private class Entry<K,V>
	 {
	 	/** key stored in the object */
	 	private K key;	
	 	/** value stored in the object */
	 	private V value;
	 	
	 	/** constructor, instantiates key and value
	 		@param k the key of the new entry
	 		@param v the value of the new entry
	 	 */
	 	public Entry(K k, V v)
	 	{
	 		key = k;
	 		value = v;
	 	}
	 	/** returns key
	 		@return the key */
	 	public K getKey()
	 	{
	 		return key;
	 	}
	 	
	 	/** returns value
	 		@return the value */
	 	public V getValue()
	 	{
	 		return value;
	 	}
	 
	 
	 
	 }
	




}