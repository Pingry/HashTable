import java.lang.Math;

/** 
 * this class is a hashTable to store objects and contains several methods to interact with the hashTable
 * @author Sam Scherl
 * @version 9.29.15
 */
public class HashTable
{
	private final double loadFactor = .5;
	
	/** the actual hashtable */
	private Object[] table;
	
	/** the number of occupied spaces in the array */
	private static double occupied;
	
	/**
	* default constructor. sets size to 100
	*/
	public HashTable()
	{
		table = new Object[100];
		occupied = 0;
	}
	
	/** 
	* constructor which lets user input the initial size
	* @param capacity    the initial capacity of the array
	*/
	public HashTable(int capacity)
	{
		table = new Object[capacity];
		occupied = 0;
	}
	
	/**
	* puts the object in the hashtable. Deals with collisions by placing the object in the next open spot
	* @param obj    any type of object which has a hashCode() function
	*/
	public void put(Object obj)
	{
		if (table[Math.abs(obj.hashCode()) % table.length] == null)
			table[Math.abs(obj.hashCode() % table.length)] = obj;
		else
		{
			int displacement = 0;
			while (table[(Math.abs(obj.hashCode()) + displacement) % table.length] != null)
				displacement += 1;
			table[(Math.abs(obj.hashCode()) + displacement) % table.length] = obj;
		}
		occupied++;
		if (occupied / table.length >= loadFactor)
			rehash();
	}
	
	/**
	* String representation of the HashTable. with the each index next to its contents
	* @return output    the string which contains a visualization of the hashtable
	*/
	public String toString()
	{
		String output = "";
		for (int i = 0; i < table.length; i++)
			output = output + i + ": " + table[i] + " \n";
		return output;
	}
	
	/**
	* Doubles the size of the HashTable and rehashes each item contained within.
	*/
	private void rehash()
	{	
		Object[] holder = new Object[table.length];
		for (int i = 0; i < table.length; i++)
			holder[i] = table[i];
		table = new Object[table.length * 2];
		for (int i = 0; i < holder.length; i++)
		{
			if (holder[i] != null)
				put(holder[i]);
		}		
	}
}
	