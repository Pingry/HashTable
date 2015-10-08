public class Runner
{
	public static void main(String[] args)
	{
		HashTable<Integer, String> list = new HashTable<Integer, String>(10);
		list.put(1, "potato");
		list.put(2, "tomato");
		list.put(19, "computer");
		list.put(4, "fruit");
		list.put(6, "corn");
		list.put(-6, "pizza");
		list.put(39, "apple");
		list.put(21, "berry");
		list.put(21, "orange");
		list.put(79, "milk");
		list.put(79, "skim milk");
		list.remove(19);
		list.put(119, "pork chop");
		list.remove(1);
		list.put(52, "artichoke");
		list.put(-58, "mozzarella");
		list.put(46, "fruit punch");
		list.put(86, "lemon");
		list.put(6, "coffee");
		list.put(26, "milkshake");
		list.put(40, "lunch");
		list.remove(46);
		list.remove(-6);
		list.remove(39);
		System.out.println(list.containsValue("milkshake"));
		System.out.println(list.containsValue("tomato"));
		System.out.println(list.containsKey(79));
		System.out.println(list.containsKey(5));
		System.out.println(list.get(39));
		System.out.println(list.get(52));
		System.out.println(list.remove(-58));
		System.out.println(list);
	}
}