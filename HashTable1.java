import java.lang.Math;

/** 
 * This class is a hash table to store objects and contains several methods to interact with the hash table. it
 * also uses generics and key, value associations to enable easy accessing, adding, and removing of items in the hash table
 * @author Sam Scherl
 * @version 10.3.15
 */
public class HashTable1<K, V>
{
	private final double loadFactor = .5;
	
	/** an array of type Entry which will function as a hash table */
	private Entry[] table;
	
	/** an int which represents the number of Entries in the hash table*/
	private double occupied;
	
	/** 
	* Default constructor which sets size of the hash table to 100.
	*/
	public HashTable1()
	{
		table = new Entry[100];
		occupied = 0;
	}
	
	/** 
	* Constructor which sets the initial size of the hash table to the given capacity.
	* @param capacity    the initial capacity of the array
	*/
	public HashTable1(int capacity)
	{
		table = new Entry[capacity];
		occupied = 0;
	}
	
	/**
	* Create a new Entry object which contains both the key and the value.
	* Puts the Entry in the hash table based off the key's hash code. 
	* Deals with collisions by placing the Entry in the next open spot.
	* If the percentage of the hash table which is full is above the loadFactor it will double the size of the hash table and rehash the every Entry within.
	* If the key previously exists within the hash table it will replace the old Entry containing the key wih the new Entry containing the key.
	* @param key   a key which will be hashed to find the Entry's location in the hash table.
	* @param value	a value which will be stored within the hash table along with the given key
	*/
	public void put(K key, V value)
	{
		Entry input = new Entry(key, value);
		if (table[Math.abs(input.key.hashCode()) % table.length] == null || ((K) table[Math.abs(input.key.hashCode()) % table.length].key).equals(key))
			table[Math.abs(input.key.hashCode() % table.length)] = input;
		else
		{
		//displacement is used to increment through the hash table, away from the original location which key.hashCode() points to
			int displacement = 0;
			while (table[(Math.abs(input.key.hashCode()) + displacement) % table.length] != null)
				displacement += 1;
			table[(Math.abs(input.key.hashCode()) + displacement) % table.length] = input;
		}		
		if (occupied / table.length >= loadFactor)
			rehash();
	}
	
	/**
	* Creates a String representation of the HashTable. with the each index next to its key and value.
	* @return	the string which contains a visualization of the hashtable
	*/
	public String toString()
	{
		String output = "";
		for (int i = 0; i < table.length; i++)
		{
			if (table[i] != null)
				output = output + i + ": " + (K) table[i].key + ", " + (V) table[i].value + " \n";
			else
				output = output + i + ": " + "null" + "\n";
		}
		return output;
	}
	
	/**
	* Doubles the size of the hash table and rehashes each Entry contained within.
	*/
	private void rehash()
	{	
		occupied = 0;
		Entry[] holder = table;
		table = new Entry[table.length * 2];
		for (int i = 0; i < holder.length; i++)
		{
			if (holder[i] != null)
				put((K) holder[i].key, (V) holder[i].value);
		}		
	}
	
	/**
	* Removes an Entry in the hash table that contains the inputed key and relocates any Entry that might be affected by the removal.
	* If the specified key does not exist within the hash table, it returns null and does not change the hash table
	* @param key	a key which may be contained within an Entry in the hash table
	* @return	a value which was contained within the removed Entry with the given key  
	*/
	public V remove(K key)
	{
		if (findKey(key) == -1)
			return null;
		occupied--;
		V output = (V) table[(Math.abs(key.hashCode()) + findKey(key)) % table.length].value;
		table[(Math.abs(key.hashCode()) + findKey(key)) % table.length] = null;
		int displacement = findKey(key) + 1;;
		while (table[(Math.abs(key.hashCode()) + displacement) % table.length] != null)
		{
			Entry holder = new Entry((K) table[(Math.abs(key.hashCode()) + displacement) % table.length].key, (V) table[(Math.abs(key.hashCode()) + displacement) % table.length].value);
			table[(Math.abs(key.hashCode()) + displacement) % table.length] = null;
			put((K) holder.key, (V) holder.value);
			displacement++;
			
		}
		return output;
	}
	
	/** 
	* Returns the value associated with a given key in the hash table. Returns null if the key is
	* not present in the hash table.
	* @param key	a key which may be contained within an Entry in the hash table
	* @return	a value which is in the Entry containing the given key
	*/
	public V get(K key)
	{
		if (findKey(key) == -1)
			return null;
		return (V) table[(Math.abs(key.hashCode()) + findKey(key)) % table.length].value;
	}

	/**
	* Returns true if the given key is contained within an Entry within the list and false if there
	* is no entry with the given key.
	* @param key	a key which may be contained within an Entry in the hash table
	* @return	a boolean which will be true if the key is within the hash table, false if it is not
	*/
	public boolean containsKey(K key)
	{
		if (findKey(key) == -1)
			return false;
		return true;
	}

	/**
	* Returns true if the given value is contained within an Entry within the list and false if there
	* is no entry with the given value.
	* @param value	a value which may be contained within an Entry in the hash table
	* @return	a boolean which will be true if the value is within the hash table, false if it is not
	*/
	public boolean containsValue(V value)
	{
		for (int i = 0; i < table.length; i++)
		{
			if (table[i] != null)
			{
				if (((V) table[i].value).equals(value))
					return true;
			}
		}
		return false;
	}
	
	/**
	* A helper function which will find whether a given key is present within the hash table. If the key does exist, it will return 
	* how many places away the Entry containing the key is from the key's original hash location. If the key does not exist
	* it will return -1;
	* @param key	a key which may be contained within an Entry in the hash table
	* @return	an int that represents the keys location relative to the actual hash value of the key
	*/
	public int findKey(K key)
	{
		if (table[Math.abs(key.hashCode()) % table.length] == null)
			return -1;
		//displacement is used to increment through the hash table, away from the original location which key.hashCode() points to
		int displacement = 0;
		while(table[(Math.abs(key.hashCode()) + displacement) % table.length] != null && !((K) table[(Math.abs(key.hashCode()) + displacement) % table.length].key).equals(key))
			displacement++;
		if (table[(Math.abs(key.hashCode()) + displacement) % table.length] == null )
			return -1;
		return displacement;
	}
	
	/**
	* A class which contains both a key and a value as class fields
	*/
	private class Entry<K, V>
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