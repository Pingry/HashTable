/**
	This is my HashTable. It includes a constructor and other methods that allow you to
	interact with the HashTable in certain ways. This is the barebones of what we hope to 
	have at the end.
	Thanks to Sam Scherl for help.

	@author Kevin Chow
	@version 9.29.15
	*/ 
	
import java.lang.Math;

public class HashTable
{
	//Table in question
	private Object[] table;
	
	//Size of table used various times by different methods. Is adjusted when needed.
	private int tablesize;
	
	//My personally chosen lf
	private double loadfactor = .7;
	
	/**
	* Default constructor. sets size to 100
	*/
	public HashTable()
	{
		table = new Object[100];
		tablesize = 100;
	
	}
	
	/** 
	* Constructor that lets the user specify initial size.
	* @param capacity int that is the size of hash table wanted.
	*/
	public HashTable(int capacity)
	{
		table = new Object[capacity];
		tablesize = capacity;
	}
	
	/**
	*Puts the object in the hashtable. Deals with collisions by placing the object in the 
	*next open spot.
	*@param obj Object that is being put into the hashtable.
	*/
	public void put(Object obj)
	{
		int index = (Math.abs(obj.hashCode()))%tablesize;
		int tablefull = 0;
		boolean added = false;
		if (table[index] == null)
				table[index] = obj;
		else
		{
			while (added == false)
			{
				if (table[index] == null)
				{
					table[index] = obj;
					added = true;
				}
				index++;
				
			}
		}
		for (int i = 0; i < tablesize; i++)
		{
			if (table[i] != null)
				tablefull++;
			
		}
		if(tablefull / tablesize >= loadfactor)
			rehash();
	
	}
	
	/**
	* Creates a string representation of the hashtable
	* @return string representation of hashtable
	*/
	public String toString()//String representation of the HashTable.
	{
		String stringofhashtable = "[";
		for (int i = 0; i<tablesize; i++)
		{
			if (table[i]!=null)
			{
				stringofhashtable += i;
				stringofhashtable +=":";
				stringofhashtable += table[i].toString();			
				stringofhashtable += ",";			

			}
				
		}
		stringofhashtable = stringofhashtable.substring(0,stringofhashtable.length()-1);
		stringofhashtable += "]";

		return stringofhashtable;
	}

	/**
	* Doubles the size of the HashTable and rehashes each item contained within. Should be
	* called when calling put function makes the current fill of the HashTable 
	* exceed the load factor.
	*/
	private void rehash() 
	{
		
		Object[] holdertable = new Object[tablesize];
		for (int i = 0; i < tablesize;i++)
			holdertable[i]=table[i];
		table = new Object[tablesize*2];
		tablesize = tablesize*2;
		for (int i = 0; i < tablesize;i++)
		{
			if (holdertable[i]!=null)
				put(holdertable[i]);
		}
	}

}

