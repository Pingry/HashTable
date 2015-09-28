public class Runner
{

	public static void main( String [] args )
	{
		HashTable h= new HashTable(7);
		for (int i=0; i<5; i++)
		{
			h.put(new Object());
			h.put("hello");
		}

		System.out.println(h);
	}

			
}