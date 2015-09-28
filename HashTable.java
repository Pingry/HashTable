public class HashTable
{
	private Object[] table;
	
	//Initializes private variable table with size = 100
	public HashTable()
	{
		table = new Object[100];
		
	}
	
	//Initializes private variable table with size = parameter capacity
	public HashTable(int capacity)
	{
		table = new Object[capacity];
	}
	
	//Puts parameter obj into table by putting it in the position of its hash code mod table's size. If there is already an object at that position,
	//it puts it at the next space. If that space is filled, then it uses quadraticProbing to place the object. If all spaces which quadratic probing  
	//checked are filled, it rehashes using the rehash method, and places it in the position where quadraticProbing would put it. 
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
	//Converts the ArrayList table into a string using a for loop.
	public String toString()
	{
		String str = "| ";
		for(int i = 0; i<table.length; i++)
		{
			str = str + table[i] + " | ";
		}
		return str;
	}
	
	//reHashes the ArrayList table by creating a new ArrayList copy with size = double table's size, copies all of table's contents into copy ArrayList using a for loop,
	//then sets table ArrayList = copy ArrayList.
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
