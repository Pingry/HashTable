/**
 * @author Billy Fallon
 * @version 10/4/15
 */

public class HashTable <K, V>
{
	/**
	 * class field array table
	 * Stores objects that have been hashed
	 */
	private Entry<K,V>[] table;
	
	/**
	 * Default constructor. Initializes to capacity 100
	 */
	
	public HashTable()
	{
		table = new Entry[100];
		
	}
	
	/**
	 * Constructor. Initializes to capacity param int capacity
	 * 
	 * @param int that is desired capacity of HashTable
	 */
	
	public HashTable(int capacity)
	{
		table = new Entry[capacity];
	}
	
	/**
	 * Puts a key-value entry in the hashtable. Deals with collisions 
	 * by placing the object in the next open spot 
	 * 
	 * @param Object obj	
	 * @return void
	 */
	public void put (K key, V value)
	{
		Entry E = new Entry (key, value);
		int num = key.hashCode();
		num=num%table.length;
		if(num<0)
		{
			num+=table.length;
		}
		if(table[num]==null)
		{
			table[num] = E;
		}
		else
		{
			for(int i = num;i<table.length;i++)
			{	
				if(table[i]==null)
				{
					table[i]=E;
					return;
				}
			}
			int length = table.length;
			reHash();
			table[length] = E;
		}
		
	}
	
	/**
	 * String representation of the HashTable.
	 * 
	 * @param null
	 * @return String str which is a string representation of table array
	 */
	public String toString()
	{
		String str = "| ";
		for(int i = 0; i<table.length; i++)
		{
			str = str + table[i].value + " | ";
		}
		return str;
	}
	
	/**
	 * Doubles the size of the HashTable and rehashes each item contained within. 
	 * Should be called anytime calling the put function makes the current fill 
	 * of the HashTable exceed the load factor.
	 * 
	 * @return void 
	 */
	private void reHash()
	{
		Entry[] copy = new Entry[(table.length*2)];
		for(int i =0; i <table.length;i++)
		{
			copy[i] = table[i];
		}
		table = copy;
	}
	
	/**
	 * Removes the Entry with the corresponding key and returns its value. 
	 * Returns null if the key does not exist in the table.
	 * 
	 * @param key
	 * @param value
	 * @return void
	 */
	
	public void remove(K key, V value)
	{
		for(int i = 0; i<table.length; i++)
		{
			if(table[i]!=null&&table[i].key==key&&table[i].value==value)
			{
				table[i]=null;
			}
		}
	}
	
	/**
	 * Returns whether or not key exists in the table.
	 * 
	 * @param k1
	 * @return boolean
	 */
	
	public boolean containsKey (K k1)
	{
		for(int i = 0; i<table.length; i++)
		{
			if(table[i]!=null&&table[i].key==k1)
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns whether or not value exists in the table.
	 * 
	 * @param v1
	 * @return boolean
	 */
	
	public boolean containsValue (V v1)
	{
		for(int i = 0; i<table.length; i++)
		{
			if(table[i]!=null&&table[i].value==v1)
			{
				return true;
			}
		}
		return false;
	}
	/**
	 * Nested class used to hold key-value pairings. Should have appropriate constructors and accessors as necessary.
	 */
	private class Entry <K,V>
	{
		public K key;
		public V value;
		/**
		 * Initializes Entry Object
		 * 
		 * @param k
		 * @param v
		 */
		public Entry (K k, V v)
		{
			key = k;
			value = v;
		}
		
	}
}
