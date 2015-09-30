/**
 * @author Billy Fallon
 * @version 9/30/15
 */

public class HashTable
{
	/**
	 * class field array table
	 * Stores objects that have been hashed
	 */
	private Object[] table;
	
	/**
	 * Default constructor. Initializes to capacity 100.
	 * @param null
	 * @return null
	 */
	public HashTable()
	{
		table = new Object[100];
		
	}
	
	/**
	 * Constructor. Initializes to capacity param int capacity
	 * @param int that is desired capacity of HashTable
	 * @return 
	 */
	public HashTable(int capacity)
	{
		table = new Object[capacity];
	}
	/**
	 * Puts the object in the hashtable. Deals with collisions by implementing quadratic probing
	 * @param Object obj	
	 * @return void
	 */
	public void put (Object obj)
	{
		int num = obj.hashCode();
		num = num%table.length;
		if(num<0)
		{
			num+=table.length;
		}
		
		if(table[num]==null)
		{
			table[num] = obj;
		}
		else
		{
			int quadraticProber = 0;
			int hold = 0;
			for(int i = num; i<table.length;i+=quadraticProber)
			{	
				if(table[i]==null)
				{
					table[i]=obj;
					return;
				}
				if(quadraticProber==0)
				{
					hold = i;
					i+=quadraticProber++;
					
				}
				else
				{
					hold = i;
					quadraticProber = quadraticProber*2;
					i+=quadraticProber;
				}
			}
			reHash();
			table[hold+quadraticProber] = obj;
			
		}
	}
	/**
	 * String representation of the HashTable.
	 * @param null
	 * @return String str which is a string representation of table array
	 */
	public String toString()
	{
		String str = "| ";
		for(int i = 0; i<table.length; i++)
		{
			str = str + table[i] + " | ";
		}
		return str;
	}
	
	/**
	 * Doubles the size of the HashTable and rehashes each item contained within. 
	 * Should be called anytime calling the put function makes the current fill 
	 * of the HashTable exceed the load factor.
	 * @param null
	 * @return void 
	 */
	private void reHash()
	{
		Object[] copy = new Object[(table.length*2)];
		for(int i =0; i <table.length;i++)
		{
			copy[i] = table[i];
		}
		table = copy;
	}
}
