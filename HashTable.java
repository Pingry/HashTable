/**
@author Maddie Temares
@version October 4, 2015

This HashTable will store entries, given their keys and values.
*/

public class HashTable<K, V>
{
	private Entry<K,V>[] arr;
	private double loadfactor = 0.6;
	private double percentFilled = 0.0;
	private int numFilled = 0;
	
	/** 
	Default constructor. Initializes to capacity 100.
	*/
	public HashTable()
	{
		arr = new Entry[100];
	}
	
	/**
	@param capacity Size of the hash table.
	Initializes the array of Entries to the given capacity.
	*/
	public HashTable(int capacity)
	{
		arr = new Entry[capacity];
	}
	
	/**
	Puts the object in the hashtable. Deals with collisions by placing the object in the
	next open spot.
	@param obj
	@return void
	*/
	public void put(K key, V value)
	{
		int location = key.hashCode()%arr.length;
		if (location < 0)
		{
			location = location +arr.length;
		}
		while (arr[location] != null)
		{
			location++;
		}
		if (location < 0)
		{
			location = location +100;
		}
		arr[location] = new Entry(key, value);
		numFilled++;
		percentFilled = (numFilled)/(arr.length);
		if (percentFilled >= loadfactor)
		{
			rehash();
		}
	}
	
	/**
	String representation of the HashTable.
	@return String
	*/
	public String toString()
	{
		String s = "";
		for (Object o: arr)
			s += o + " ";
		return s;
	}
	
	/**
	Doubles the size of the HashTable and rehashes each item contained within. Should be
	called anytime calling the put function makes the current fill of the HashTable 
	exceed the load factor.
	@return void
	*/
	private void rehash()
	{
		Entry<K,V>[] old = new Entry[arr.length];
		for (int x =0; x<arr.length; x++)
		{
			old[x] = arr[x];
		}
		int newSize = arr.length * 2;
		arr = new Entry[newSize];
		for (Entry<K,V> e: old)
		{
			if (e!= null)
			{
				put(e.key, e.value);
			}
		}
	}
	
	/**
	Removes the entry with a given key.
	@param key
	@return V
	*/
	public V remove (K key)
	{
		int posslocation = key.hashCode()%arr.length;
		if (posslocation < 0)
		{
			posslocation = posslocation +arr.length;
		}
		while (arr[posslocation] != null)
		{
			posslocation++;
		}
		if (posslocation < 0)
		{
			posslocation = posslocation +100;
		}
		while (arr[posslocation].key != key)
		{
			posslocation++;
		}
		V toReturn = arr[posslocation].value;
		arr[posslocation] = null;
		return toReturn;
	}
	
	/**
	Gets the value corresponding to a given key.
	@param key
	@return V
	*/
	public V get(K key)
	{
		int posslocation = key.hashCode()%arr.length;
		if (posslocation < 0)
		{
			posslocation = posslocation +arr.length;
		}
		while (arr[posslocation] != null)
		{
			posslocation++;
		}
		if (posslocation < 0)
		{
			posslocation = posslocation +100;
		}
		while (arr[posslocation].key != key && posslocation< arr.length)
		{
			posslocation++;
		}
		if (posslocation == arr.length)
		{
			return null;
		}
		V toReturn = arr[posslocation].value;
		return toReturn;
	}
	
	
	/**
	Sees whether or not the array of entries contains an entry with a given key.
	@param key
	@return boolean
	*/
	public boolean containsKey (K key)
	{
		for (Entry e: arr)
		{
			if (e.key == key)
			{
				return true;
			}
		}
		return false;
	}
	
	
	/**
	Sees whether or not the array of entries contains an entry with a given value.
	@param value
	@return boolean
	*/
	public boolean containsValue (V value)
	{
		for (Entry e: arr)
		{
			if (e.value == value)
			{
				return true;
			}
		}
		return false;
	}
	

	public static void main(String[] args)
	{
		HashTable names = new HashTable();
		names.put("A", "first letter in the alphabet");
		names.put("Daria", "name of Daria");
		names.put("Dariasd", "name plus random characters");
		names.put("B", "second letter of alphabet");
		names.put("C", "third letter of alphabet");
		System.out.println(names);
		names.remove("A");
		System.out.println(names);
	}
	
	/**
	Nested class used to hold key-value pairings.
	*/
	private class Entry<K,V>
	{
		public K key;
		public V value;

		public Entry(K k, V v)
		{
			k = key;
			v = value;
		}
	}
	
	
}
