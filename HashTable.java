//Daria Fradkin
//October 1, 2015
//Simple HasTable object
//This version of HashTable will do a simple put using an object's hashcode. 
//It does not use separate key and value objects. 

public class HashTable<K,V>
{
	Entry[] ht;
	int coll;
	int c; //tracks the number of the collisions per variable
	double loadfactor;
	int n; //number of values in table
	
	//Default constructor. Initializes to capacity 100.
	public HashTable()
	{
		ht=(Entry[]) new Object[100];
		coll=0;
		loadfactor=0.5;
		n=0;
	}
	
	//Default constructor. Initializes to capacity.
	public HashTable(int capacity)
	{
		ht=(Entry[]) new Object[capacity];
		coll=0;
		loadfactor=0.5;
		n=0;
	}
	
	//Puts the object in the hashtable. Deals with collisions by quadratic probing.
	public void put(K key, V value)
	{
		Entry e = new Entry<K,V>(key, value);
		c=0;
		//System.out.println(obj.hashCode());
		//System.out.println(obj.hashCode()%ht.length);
		int spot=key.hashCode()%ht.length;
		if (spot<0)
			spot+=ht.length;
		if (ht[spot]==null)
		{
			ht[spot]=e;
			n++;
			if (n/ht.length>loadfactor)
				rehash();
			//System.out.println(obj.hashCode()%ht.length);
		}
		else
			put(e, (spot+1)%ht.length);
	}
	
	private void put(Entry e, int n)
	{
		c++;
		coll++;
		if (coll==ht.length/4)
			rehash();
		if (ht[n]==null)
		{
			ht[n]=e;
			n++;
			if (n/ht.length>loadfactor)
				rehash();
		}
		else
			put(e, n+1);
	}
	
	//String representation of the HashTable.
	public String toString()
	{
		String r="";
		for (int i=0; i < ht.length; i++)
			if (ht[i]!=null)
				r+=(ht[i].key.toString() + " " + ht[i].value.toString());
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
		Entry[] ht1=(Entry[]) new Object[ht.length];
		ht1=ht;
		ht=(Entry[]) new Object[ht.length*2];
		for (int i=0; i<ht1.length; i++)
			if (ht1[i]!=null)
				put( (K) ht1[i].key, (V) ht1[i].value); //typecast it to value, because Object 
	}
	
	
	
	private class Entry<K,V>
	{
		public K key;
		public V value;
		
		public Entry(K k, V v)
		{
			key=k;
			value=v;
		}
	}
	
	///*
	public static void main(String[] args)
	{
		HashTable<String,String> fruit = new HashTable<String,String>();
		
		fruit.put("apple","red");
		fruit.put("banana","yellow");
		fruit.put("pear","green");
		fruit.put("grape","purple");
		fruit.put("orange","orange");
		System.out.println(fruit);
	}
	//*/
}
