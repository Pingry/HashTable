//Daria Fradkin
//September 28, 2015
//Simple HasTable object
//This version of HashTable will do a simple put using an object's hashcode. 
//It does not use separate key and value objects. 

public class HashTable<E>
{
	E[] ht;
	int coll;
	int c; //tracks the number of the collisions per variable
	double loadfactor;
	int n; //number of values in table
	
	//Default constructor. Initializes to capacity 100.
	public HashTable()
	{
		ht=(E[]) new Object[100];
		coll=0;
		loadfactor=0.5;
		n=0;
	}
	
	//Default constructor. Initializes to capacity.
	public HashTable(int capacity)
	{
		ht=(E[]) new Object[capacity];
		coll=0;
		loadfactor=0.5;
		n=0;
	}
	
	//Puts the object in the hashtable. Deals with collisions by quadratic probing.
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
			n++;
			if (n/ht.length>loadfactor)
				rehash();
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
		{
			ht[n]=obj;
			n++;
			if (n/ht.length>loadfactor)
				rehash();
		}
		else
			put(obj, (n+c*2)%ht.length);
	}
	
	//String representation of the HashTable.
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
	
	//Doubles the size of the HashTable and rehashes each item contained within. 
	//Should be called anytime calling the put function makes the current fill of the 
	//HashTable exceed the load factor.
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
		n=0;
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
