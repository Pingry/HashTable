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
	//Table Created
	private Object[] table;
	
	//How many objects are in the table
	private int tableoccupy;
	
	//Load Factor of the table
	private double loadfactor = .7;
	
	/**
	* Default constructor. sets size to 100
	*/
	public HashTable()
	{
		table = new Object[100];	
	}
	
	/** 
	* Constructor that lets the user specify initial size.
	* @param capacity int that is the size of hash table.
	*/
	public HashTable(int capacity)
	{
		table = new Object[capacity];
	}
	
	/**
	*Puts the object in the hashtable. Deals with collisions by placing the object in the 
	*next open spot.
	*@param obj Object that is being put into the hashtable.
	*/
	public void put(Object obj)
	{
		int index = (Math.abs(obj.hashCode()))%table.length;
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
					tableoccupy++;
					added = true;
				}
				index++;
				
			}
		}
		if(tableoccupy / table.length >= loadfactor)
			rehash();
	}
	
	/**
	* Creates a string representation of the hashtable
	* @return string representation of hashtable
	*/
	public String toString()
	{
		String stringofhashtable = "[";
		for (int i = 0; i<table.length; i++)
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
	public void rehash() 
	{
		
		Object[] holdertable = new Object[table.length];
		holdertable = table;
		table = new Object[table.length*2];
		for (int i = 0; i < holdertable.length;i++)
		{
			if (holdertable[i]!=null)
				put(holdertable[i]);
		}
	}

}

/*
private void rehash() 
	{
		
		Object[] holdertable = new Object[table.length];
		for (int i = 0; i < table.length;i++)
			holdertable[i]=table[i];
		table = new Object[table.length*2];
		for (int i = 0; i < table.length;i++)
		{
			if (holdertable[i]!=null)
				put(holdertable[i]);
		}
	}
*/

