// Akshina Gupta
// 9/29/2015
// Hash Table Version 0

public class HashTable

{
	private Object[] table;
	
	private final double loadFactor = 0.6;
	
	private int numObjects;
	
	/** Default Constructor
	* The default size of the hashtable is 100
	*/
	public HashTable()
	{
		table = new Object[100];
		numObjects = 0;
	}
	
	/** User input Constructor
	* @param capacity int that represents the length of the hashtable
	*/
	public HashTable(int capacity)
	{
		table = new Object[capacity];
		numObjects = 0;
	}
	
	/** 
	* Attempts to place the object as quickly as possible
	* Uses Objects hashcode() method to generate a hash of the object
	* If that hash spot is empty then it places it into that spot in the table
	* Otherwise it puts the object in the next available spot
	* @param obj Object that represents the thing you want to place into the hash table
	*/
	public void put(Object obj)
	{
		numObjects++;
		if (((numObjects*1.0)/(table.length)) >= loadFactor)
		{
			this.rehash();
		}
		int spot = (obj.hashCode()) % (table.length);
		if(table[spot] == null)
		{
			table[spot] = obj;
		}
		else
		{
			spot = (spot+1)%(table.length);
			int x = 1;
			while(table[spot] != null)
			{
				spot = (spot+(1))%(table.length);
				x=x*2;
			}
			table[spot] = obj;
		}
	}
	
	/**
	* @return String representation of the array contains the toString() of every single object in the array
	*/
	public String toString()
	{
		String s = "";
		for(int x = 0; x < table.length;x++)
		{
			s += " " + table[x];
		}
		return s;
	}
	
	/**
	* Creates a new array that is double the size.
	* Hashes each of the objects in the old array into a new array.
	* Hashes in sequential order.
	*/
	private void rehash()
	{
		Object[] oldTable = table;
		table = new Object[table.length * 2];
		for(int x = 0; x < oldTable.length;x++)
		{
			if(oldTable[x] != null)
			{
				this.put(oldTable[x]);
			}
		}
	}
	
}
