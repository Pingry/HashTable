public class Runner
{

	public static void main( String [] args )
	{
		HashTable h= new HashTable(Integer.parseInt(args[0]));
		for (int i=0; i<Integer.parseInt(args[1]); i++)
			h.put(new Object());
		System.out.println(h.toString());
	}

			
}