public class HashTable()
{
	private Object[] table;
	private int tablesize;

	public HashTable()//Default constructor. Initializes to capacity 100.
	{
		table = new Object[100];
		tablesize = 100;
	
	}
	public HashTable(int capacity) //Another constructor that sets the capacity 
	//of Hashtable
	{
		table = new int[capacity];
		tablesize = capacity;
	}
	
	public void put(Object obj)/*Puts the object in the hashtable. Deals with collisions
	by placing the object in the next open spot (OR ask John Lima for an even better way 
	of finding an open spot!)*/
	{
		int index = (obj.hashCode())%tablesize;
		boolean added = false;
		int quadprobe = 1;
		if (table[index] == null)
				table[index] = obj;
		else
		{
			while (added == false)
			{
				if (table[index+quadprobe] == null)
				{
					table[index+quadprobe] = obj;
					added = true;
				}
				quadprobe = quadprobe * 2;
				
			}
		}
	
	}
	public String toString()//String representation of the HashTable.
	{
		String stringofhashtable = "[";
		for (i = 0; i<tablesize; i++)
		{
			if (table[i]!=null)
			{
				stringofhashtable += table[i].toString();
				stringofhashtable += ",";
			}
				
		}
		stringofhashtable += "]";

		return stringofhashtable;
	}

	private void rehash() /*Doubles the size of the HashTable and rehashes each item 
	contained within. Should be called anytime calling the put function makes the 
	current fill of the HashTable exceed the load factor.*/
	{
		//make a holding array that is clone of original, make global double size, call put
		//on all the clones objects and pt into the double sized global. 
	}

	
	
}
