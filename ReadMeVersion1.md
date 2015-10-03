// Akshina Gupta

// 9/29/2015

// Hash Table Version 1

public class HashTable<K,V>
{
	private Entry[] table;
	
	private final double loadFactor = 0.6;
	
	private int numObjects;
	
	/** Default Constructor
	* The default size of the hashtable is 100
	*/
	public HashTable()
	{
		table = new Entry[100];
		numObjects = 0;
	}
	
	/** User input Constructor
	* @param capacity int that represents the length of the hashtable
	*/
	public HashTable(int capacity)
	{
		table = new Entry[capacity];
		numObjects = 0;
	}
	
	/** 
	* Attempts to place the object as quickly as possible
	* Uses Objects hashcode() method to generate a hash of the object
	* If that hash spot is empty then it places it into that spot in the table
	* Otherwise it puts the object in the next available spot
	* @param obj Object that represents the thing you want to place into the hash table
	*/
	public void put(K key, V value)
	{
		Entry e = new Entry(key,value);
		numObjects++;
		if (((numObjects*1.0)/(table.length)) >= loadFactor)
		{
			this.rehash();
		}
		int spot = (key.hashCode()) % (table.length);
		if(table[spot] == null)
		{
			table[spot] = e;
		}
		else
		{
			spot = (spot+1)%(table.length);
			//int x = 1;
			while(table[spot] != null)
			{
				spot = (spot+(1))%(table.length);
				//x=x*2;
			}
			table[spot] = e;
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
	
	public V remove(K key)
	{
		int spot = (key.hashCode()) % (table.length);
		int start = spot;
		if(table[spot] != null && ((K)(table[spot].key)).equals(key))
		{
			V value = (V)(table[spot].value);
			table[spot] = null;
			return value;
		}
		spot = (spot+1)%(table.length);
		while(table[spot] == null && ((K)(table[spot].key)).equals(key))
		{
			spot = (spot+(1))%(table.length);
			if(spot = start)
			{
				return null;
			}
		}
		V value = (V)(table[spot].value);
		table[spot] = null;
		return value;
	}
	
	public V get(K key)
	{
		int spot = (key.hashCode()) % (table.length);
		int start = spot;
		if(table[spot] != null && ((K)(table[spot].key)).equals(key))
		{
			V value = (V)(table[spot].value);
			return value;
		}
		spot = (spot+1)%(table.length);
		while(table[spot] == null && !((K)(table[spot].key)).equals(key))
		{
			spot = (spot+(1))%(table.length);
			if(spot = start)
			{
				return null;
			}
		}
		V value = (V)(table[spot].value);
		return value;
	}
	
	public boolean containsKey(K key)
	{
		int spot = (key.hashCode()) % (table.length);
		int start = spot;
		if(table[spot] != null && ((K)(table[spot].key)).equals(key))
		{
			return true;
		}
		spot = (spot+1)%(table.length);
		while(table[spot] == null && !((K)(table[spot].key)).equals(key))
		{
			spot = (spot+(1))%(table.length);
			if(spot == start)
			{
				return false;
			}
		}
		return true;
	}
	
	public boolean containsValue(V value)
	{
		for(int i = 0; i<table.length; i++)
		{
			if (((V)(table[spot].value)).equals(value))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	* Creates a new array that is double the size.
	* Hashes each of the objects in the old array into a new array.
	* Hashes in sequential order.
	*/
	private void rehash()
	{
		Entry[] oldTable = table;
		table = new Entry[table.length * 2];
		for(int x = 0; x < oldTable.length;x++)
		{
			if(oldTable[x] != null)
			{
				this.put((K)(oldTable[x].key),(V)(oldTable[x].value));
			}
		}
	}
	
	private class Entry<K,V>
	{
		public K key;
		public V value;
		
		public Entry(K k, V v)
		{
			key = k;
			value = v;
		}
	}
	
}
