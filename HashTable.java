public class HashTable
{
	public static final double loadFactor = .6;
	private Object[] table;
	
	public HashTable()
	{
		table = new Object[100];
	}
	
	public HashTable(int c)
	{
		table = new Object[c];
	}
	
	public void put(Object o)
	{
		int spot = (int)  o.hashCode();
		spot=spot% table.length;
		int change=0;
		boolean placed= false;
		while( placed == false)
		{
			if (!(table[spot + change] instanceof Integer ))
				change+=1;
			else
			{
				table[spot + change] = o;
				placed= true;
			}
			if (change+spot>table.length)
				change= spot * -1;
		}
		
		
		
	}
	
	




}