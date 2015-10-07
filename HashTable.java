/**
* This is a class that is a hashtable
* A hashtable is similar to a dictionary in Python, as it has keys and values stored
* @author Anish Seth
* @version 9.29.15
*/
public class HashTable<K,V>
{
	/**
	* Actual hashtable
	*/
	private Entry<K,V>[] table;
	/**
	* Loadfactor, when percent occupied is greater than or equal to the loadfactor, the table is rehashed.
	*/
	private final double loadfactor = .6;
	/**
	* Counts the number of total spaces occupied. Important for figuring out when to rehash.
	*/
	private int occupy;
	
	/**
	* Default constructor. Initializes capacity to 100.
	*/
	public HashTable()
	{
		table = new Entry[100];
		occupy = 0;
	}
	/**
	* Constructor. Initializes capacity to parameter.
	* @param capacity Capacity of the array
	*/
	public HashTable(int capacity)
	{
		table = new Entry[capacity];
		occupy = 0;
	}
	/**
	* Puts the object in the hashtable.
	* Looks for corresponding spot to put the value in, if that value is taken, will go to next open space.
	* @param key, value A key and its corresponding value that implements the hashCode() method
	*/
	public void put(K key, V value)
	{
		int x = key.hashCode()%table.length;
		
		while(x < table.length)
		{
			if(table[x] == null)
			{
				table[x] = new Entry(key, value);
				x+= table.length + 1;
				occupy++;
			}
			else
				x++;
			if(x == table.length)
				x =0;
		}
		if((float)occupy/(float)table.length >= loadfactor)
			rehash();

	}
	/**
	* String representation of the HashTable.
	* @ return x String containing representation of the hashtable
	*/
	public String toString()
	{
		String x = " ";
		for(int i = 0; i < table.length; i++)
		{
			if(table[i] == null)
				x += "null\n";
			else
				x += table[i].value + "\n"; 
		}
		return x;
	}
	/**
	* Rehashes the hashtable. Creates a new hashtable with twice the capacity and transfers the old values into the new table.
	*/
	private void rehash()
	{
		//x is a temporary copy of the hashtable
		Entry[] x = new Entry[table.length];
		for(int i = 0; i < table.length; i++)
		{
			x[i] = table[i];
		}
		//new hashtable with twice the capacity; copies back all the values from the tamporary array
		table = new Entry[table.length*2];
		for(int i = 0; i < table.length; i++)
		{
			if(x[i] != null)
				put((K) x[i].key, (V) x[i].value);
		}
	}
	/**
	* Removes the Entry with corresponding key and returns its value. Returns null if calue is not in the table
	* @param key Uses the key to find and remove its corresponding value
	*/
	public V remove (K key)
	{
		int x = key.hashCode() % table.length;
		for(int i = 0; i < table.length;i++)
		{
			if(x == (table[i].key.hashCode() % table.length))
			{
				occupy--;
				V remove = table[i].value;
				table[i] = null;
				return remove;
			}
		}
		return null;
	}
	/**
	* Finds the Entry with corresponding key and returns its value.
	* Returns null if value is not in the table.
	* Proceeds to remove the value.
	* @param key Uses the key to find its corresponding value
	*/
	public V get (K key)
	{
		int x = key.hashCode() % table.length;
		for(int i = 0; i < table.length;i++)
		{
			if(x == (table[i].key.hashCode() % table.length))
			{
				return table[i].value;
			}
		}
		return null;
	}
	/**
	 * Returns whether or not key exists in the table.
	 * @param Key the key
	 */
	public boolean containsKey(K key)
	{
		for(int i = 0; i < table.length; i++)
		{
			if(key == table[i].key)
				return true;
		}
		return false;
	}
	/**
	 * Returns whether or not value exists in the table.
	 * @param Value the value
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
	 * Should have appropriate constructors and accessors as necessary.
	 */
	private class Entry <K,V>
	{
		public K key;
		public V value;
		public Entry(K key, V value)
		{
			this.key = key;
			this.value = value;
		}
	}

}
