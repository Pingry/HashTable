public class HashTable<E>
{
	E[] ht;
	int coll;
	int c; //tracks the number of the collisions per variable
	
	public HashTable()
	{
		ht=(E[]) new Object[100];
		coll=0;
	}
	
	public HashTable(int capacity)
	{
		ht=(E[]) new Object[capacity];
		coll=0;
	}
	
	public void put(E obj)
	{
		c=0;
		//System.out.println(obj.hashCode());
		//System.out.println(obj.hashCode()%ht.length);
		int spot=obj.hashCode()%ht.length;
		if (spot<0)
			spot+=ht.length;
		if (ht[spot]==null)
		{
			ht[spot]=obj;
			//System.out.println(obj.hashCode()%ht.length);
		}
		else
			put(obj, (spot+1)%ht.length);
	}
	
	private void put(E obj, int n)
	{
		c++;
		coll++;
		if (coll==ht.length/4)
			rehash();
		if (ht[n]==null)
			ht[n]=obj;
		else
			put(obj, (n+c*2)%ht.length);
	}
	
	public String toString()
	{
		String r="";
		for (int i=0; i < ht.length; i++)
			if (ht[i]!=null)
				r+=(ht[i].toString() + " ");
			else
				r+="null ";
		r+="\n";
		return r;
	}
	
	private void rehash()
	{
		//ht1=new E[ht.length];
		//ht2=new E[ht.length];
		//for (int i=0; i<ht.length; i++)
		//	if (ht[i].hashCode()%(ht.length*2)<ht.length)
		//		ht1[ht[i].hashCode()%(ht.length*2]=ht[i];
		//	else
		//		ht2[i]=ht[i];
		coll=0;
		E[] ht1=(E[]) new Object[ht.length];
		ht1=ht;
		ht=(E[]) new Object[ht.length*2];
		for (int i=0; i<ht1.length; i++)
			if (ht1[i]!=null)
				put(ht1[i]);
	}
	
	/*
	public static void main(String[] args)
	{
		HashTable<String> names = new HashTable<String>();
		
		names.put("A");
		names.put("Daria");
		names.put("Dariasd");
		names.put("B");
		names.put("C");
		System.out.println(names);
	}
	*/
}
