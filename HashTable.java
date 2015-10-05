public class HashTable
{
	private Object[] table;
	private final double LOAD_FACTOR = 0.6;
	private int size;
	private int quantityFilled;
	
	//initializes empty HashTable with size 100
	public HashTable()
	{
		table = new Object[7];
		size = 7;
		quantityFilled = 0;
	}
	
	//initializes empty HashTable with size n
	
	
	/*
	Places obj into the table based on it's hashValue.  If that position is already taken, will move to the 
	next available slot.
	*/
	public void insert(Object obj)
	{
		if(((double)(quantityFilled))/size >= LOAD_FACTOR)
			rehash();				 
		
		int hashValue = obj.hashCode() % size;		
		int quadVal = 1;
		while(true)
		{	
			if(table[hashValue] == null)
			{
				table[hashValue] = obj;
				quantityFilled ++;
				break;
			}
			else 
			{
				hashValue = (obj.hashCode() + quadVal) % size;
				quadVal *= 2;		
			}
		}
		
	}
	
	/*
	creates a new table of twice the size and places all of the original objects in the hash table
	into that new hashtable.
	*/
	private void rehash()
	{
		int n = 0;
		for(int i = size + 1; ! isPrime(i); i++)
		{
			n = i + 1;
		}
			
		Object[] newHash = new Object[n];
		for(int i = 0; i < size; i++)
		{
			if(table[i] != null)
			{
				int hashValue = table[i].hashCode() % size;		
				int quadVal = 1;
				while(true)
				{
						if(newHash[hashValue] == null)
						{
							newHash[hashValue] = table[i];
							break;
						}
						else
						{
							hashValue = (table[i].hashCode() + quadVal) % size;
							quadVal *= 2;
						}
				}
			}
		}
		table = newHash;
		size = n;
		
	}
		
	public String toString()
	{
		String finalString = "";
		for(int i = 0; i < size; i++)
		{
			finalString = finalString + table[i];
			finalString = finalString + "\n";
		}
		return finalString;
	}
	
	private boolean isPrime(int n) 
	{
		if(n%2 == 0 || n%3 == 0)
			return false;
		int i = 5;
		while(i*i <= n)
		{
			if(n % i == 0 || n % (i + 2) == 0)
				return false;
			i += 6;
		}
		return true;
	}
	
	
	
}
