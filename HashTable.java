public class HashTable
{
	private Object[] table;
	private final double LOAD_FACTOR = 0.6;
	private int size;
	private int quantityFilled;
	
	//initializes empty HashTable with size 100
	public HashTable()
	{
		table = new Object[100];
		size = 100;
		quantityFilled = 0;
	}
	
	//initializes empty HashTable with size n
	public HashTable(int n)
	{
		table = new Object[n];
		size = n;
		quantityFilled = 0;
	}	
	
	/*
	Places obj into the table based on it's hashValue.  If that position is already taken, will move to the 
	next available slot.
	*/
	public void put(Object obj)
	{
		if(((double)(quantityFilled))/size >= LOAD_FACTOR)
			rehash();				 
		
		int hashValue = obj.hashCode() % size;		
		while(true)
		{
			if(table[hashValue] == null)
			{
				table[hashValue] = obj;
				quantityFilled ++;
				break;
			}
			else if(hashValue < size)
			{
				hashValue++;
			}
			else
			{
				hashValue = 0;
			}
		}
		
	}
	
	/*
	creates a new table of twice the size and places all of the original objects in the hash table
	into that new hashtable.
	*/
	private void rehash()
	{
		Object[] newHash = new Object[size * 2];
		for(int i = 0; i < size; i++)
		{
			if(table[i] != null)
			{
				int hashValue = table[i].hashCode() % size;		
				while(true)
				{
						if(newHash[hashValue] == null)
						{
							newHash[hashValue] = table[i];
							break;
						}
						else if(hashValue < size)
						{
							hashValue++;
						}
						else
						{
							hashValue = 0;
						}
				}
			}
		}
		table = newHash;
		size = size *2;
		
	}
	
	public String toString() // Returns a string of each Object in the HashTable
	{
		String finalString = "";
		for(int i = 0; i < size; i++)
		{
			finalString = finalString + table[i];
			finalString = finalString + "\n";
		}
		return finalString;
	}
	
	
	
	
}
