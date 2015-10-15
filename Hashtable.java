/**
	This is my HashTable. It includes a constructor and other methods that allow you to
	interact with the HashTable in certain ways. Uses generics and key-value entries
	capability to add, remove, and locate specific spots in the hashtable.
	 
	Thanks to Sam Scherl and Anish Seth for helping and explaining some things to me.

	@author Kevin Chow
	@version 10.14.15
*/ 
	
import java.lang.Math;

public class HashTable<K,V>
{
	//Hashtable
	private Entry<K,V>[] table;
	
	//How many spaces in table that are occupied
	private int tableoccupy;
	
	//Load Factor of the table
	private double loadfactor = .7;
	
	/**
	 * Default constructor. Sets capacity to 100
	*/
	public HashTable()
	{
		table = new Entry[100];
		tableoccupy = 0;	
	}
	
	/** 
	 * Constructor that lets the user specify initial size.
	 * @param int capacity that is the size of hashtable.
	*/
	public HashTable(int capacity)
	{
		table = new Entry[capacity];
		tableoccupy = 0;
	}
	
	/**
	 *Puts a key-value entry in the hashtable. Deals with collisions by placing the 
	 *key-value in the next open spot.
	 *@param K key that is being put into the hashtable.
	 *@param V value that is being put into hashtable.
	*/
	public void put(K key, V value)
	{
		int index = (Math.abs(key.hashCode())) % table.length;
		
		//used to check when the entry is added (likely better way, can't think of)
		boolean added = false;
		
		if (table[index] == null)
		{
				table[index] = new Entry(key, value);
				tableoccupy++;
		}
		else
		{			
			while (added == false)
			{
				if (table[index] == null)
				{
					table[index] = new Entry(key, value);
					tableoccupy++;
					added = true;
				}
				index++;
			}
		}
		if( (float)tableoccupy / (float)table.length >= loadfactor)
			rehash();
	}
	
	/**
	 * Creates a string representation of the hashtable
	 * @return String representation of hashtable
	*/
	public String toString()
	{
		String stringofhashtable = "[";
		for (int i = 0; i<table.length; i++)
		{
			if (table[i]!=null)
			{
				if(table[i] != null)
					stringofhashtable += table[i].value + " | ";
				else
					stringofhashtable += "null | ";		

			}
				
		}
		//if this wasn't present the end of the printed out string would have an extra "|" 
		stringofhashtable = stringofhashtable.substring(0,stringofhashtable.length()-2);
		
		stringofhashtable += "]";

		return stringofhashtable;
	}

	/**
	 * Doubles the size of the HashTable and rehashes each item contained within. 
	 * Called when calling put function makes the current fill of the HashTable 
	 * exceed the load factor.
	*/
	public void rehash() 
	{
		
		Entry[] holdertable = table;
		table = new Entry[table.length*2];
		for (int i = 0; i < holdertable.length; i++)
		{
			if (holdertable[i]!=null)
				put((K) holdertable[i].key,(V) holdertable[i].value);
		}
	}
	
	/**
	 * Removes the Entry with corresponding key and returns its value. 
	 * Returns null if the key does not exist in the table.
	 * @param K key Uses the key to find and remove its corresponding value
	 * @return V value that corresponds to the key in question or null if no such value 
	 * exists
	*/
	public V remove(K key)
	{
		int keytolookfor = key.hashCode() % table.length;
		for(int i = 0; i < table.length; i++)
		{
			if(table[i] != null)
			{
				if(keytolookfor == (table[i].key.hashCode() % table.length))
				{
					V removed = table[i].value;
					table[i] = null;
					tableoccupy--;
					return removed;
				}
			}
		}
		return null;
	}
	/**
	 * Finds and returns the value that corresponds to key. 
	 * Returns null if the key does not exist in the table.
	 * @param K key Uses the key to find its corresponding value
	 * @return V value of the key or null if no entry with corresponding key
	*/
	public V get(K key)
	{
		int keytolookfor = key.hashCode() % table.length;
		for(int i = 0; i < table.length; i++)
		{
			if(table[i] != null)
			{
			if(keytolookfor == (table[i].key.hashCode() % table.length))
				return table[i].value;
			}
		}
		return null;
	}
	
	/**
	 * Returns whether or not key exists in the table.
	 * @param K key the key being checked for.
	 * @return boolean of whether or not key exists in table
	*/
	 
	public boolean containsKey(K key)
	{
		if(get(key) != null)
			return true;
		return false;
	}
	
	/**
	 * Returns whether or not value exists in the table.
	 * @param V value the value being checked for.
 	 * @return boolean of whether or not value exists in table
	*/
	public boolean containsValue(V value)
	{
		for(int i = 0; i < table.length; i++)
		{
			if(value == table[i].value)
				return true;
		}
		return false;
	}
	
	/**
	 * Nested class used to hold key-value pairings.
	*/
	private class Entry <K,V>
	{
		public K key;
		public V value;
		/**
		 * Constructor for Entry. Sets key and value.
		 */
		public Entry(K key, V value)
		{
			this.key = key;
			this.value = value;
		}
	}
	

}



