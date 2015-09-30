/**
* This is a class that creates a hashtable to store objects
* A hashtable is similar to a dictionary in Python, as it has keys and values stored
* @author Anish Seth
* @version 9.29.15
*/
public class HashTable
{
	/** Actual hashtable*/
	private Object[] table;
	/** Loadfactor, when percent occupied is greater than or equal to the loadfactor, the table is rehashed.*/
	private final double loadfactor = .6;
	/** Counts the number of total spaces occupied. Important for figuring out when to rehash.*/
	private double occupy;
	
	/**
	* Default constructor. Initializes capacity to 100.
	*/
	public HashTable()
	{
		table = new Object[100];
		occupy = 0;
	}
	/**
	* Constructor. Initializes capacity to parameter.
	* @param capacity Capacity of the array
	*/
	public HashTable(int capacity)
	{
		table = new Object[capacity];
		occupy = 0;
	}
	/**
	* Puts the object in the hashtable.
	* Looks for corresponding spot to put the value in, if that value is taken, will go to next open space.
	* @param obj Any object that implements the hashCode() method
	*/
	public void put(Object obj)
	{
		if(occupy/table.length >= loadfactor)
			rehash();
		int key = obj.hashCode()%table.length;
		
		while(true)
		{
			if(table[key] == null)
			{
				table[key] = obj;
				occupy++;
			}
			else if(key < table.length)
				key++;
			else
				key = 0;
		}
	}
	/**
	* String representation of the HashTable.
	* @ return x String containing representation of the hashtable
	*/
	public String toString()
	{
		String x = "";

		return x;
	}
	/**
	* Doubles the size of the HashTable and rehashes each item contained within
	* Should be called anytime calling the put function makes the current fill of the HashTable exceed the load factor.
	*/
	private void rehash()
	{
		Object[] x = new Object[table.length];
		for(int i = 0; i < table.length; i++)
		{
			x[i] = table[i];
		}
		table = new Object[x.length*2];
	}

}