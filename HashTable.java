import java.lang.Math;

public class HashTable
{
	//load factor
	private final double loadFactor = .5;
	
	//the actual table of type array
	private Object[] table;
	
	//default constructor. sets size to 100
	public HashTable()
	{
		table = new Object[100];
	}
	
	//constructor which lets user input the initial size
	public HashTable(int capacity)
	{
		table = new Object[capacity];
	}
	
	//puts the object in the hashtable. Deals with collisions by placing the object in the next open spot
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
		int full = 0;
		for (int i = 0; i < table.length; i++)
		{
			if (table[i] != null)
				full++;
		}
		if ((double)full / table.length >= loadFactor)
			rehash();
	}
	
	//String representation of the HashTable.
	public String toString()
	{
		String output = "";
		for (int i = 0; i < table.length; i++)
			output = output + i + ": " + table[i] + " \n";
		return output;
	}
	
	//Doubles the size of the HashTable and rehashes each item contained within.
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
	