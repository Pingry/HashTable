import java.util.ArrayList;

public class HashTable
{
	private ArrayList<Object> table;
	
	//Initializes private variable table with size = 100
	public HashTable()
	{
		table = new ArrayList<Object> (100);
		
	}
	
	//Initializes private variable table with size = parameter capacity
	public HashTable(int capacity)
	{
		table = new ArrayList<Object> (capacity);
	}
	
	//Puts parameter obj into table by putting it in the position of its hash code mod table's size. If there is already an object at that position,
	//it puts it at the next opne space. If all spaces are filled, it rehashes using the rehash method, and places it in the next open position.
	public void put (Object obj)
	{
		int num = obj.hashCode();
		num = num%table.size();
		
		if(table.get(num)!=null)
		{
			table.set(num, obj);
		}
		else
		{
			for(int i = num; i<table.size();i++)
			{
				if(table.get(i)!=null)
				{
					table.set(i, obj);
					return;
				}
			}
			int size = table.size();
			reHash();
			table.set(size,obj);
			
		}
	}
	//Converts the ArrayList table into a string using a for loop.
	public String toString()
	{
		String str = "| ";
		for(int i = 0; i<table.size(); i++)
		{
			str = str + table.get(i) + "| ";
		}
		return str;
	}
	
	//reHashes the ArrayList table by creating a new ArrayList copy with size = double table's size, copies all of table's contents into copy ArrayList using a for loop,
	//then sets table ArrayList = copy ArrayList.
	private void reHash()
	{
		ArrayList<Object> copy = new ArrayList<Object> (table.size()*2);
		for(int i =0; i <table.size();i++)
		{
			copy.set(i,table.get(i));
		}
		table = copy;
	}
}
