
public class LinkedHashDriver {

	public static void main (String [] args) {
		LinkedHashTable x = new LinkedHashTable();
		
		x.add(12340, "nodeIn0");
		x.add(12341, "nodeIn1");
		x.add(12342, "nodeIn2");
		x.add(12343, "nodeIn3");
		x.add(12344, "nodeIn4");
		x.add(12345, "nodeIn5");
		x.add(5, "anotherNodeIn5");
		x.add(12346, "nodeIn6");
		x.add(12347, "nodeIn7");
		x.add(12347, "anotherNodeIn7");		//should overwrite "nodeIn7
		x.add(12357, "yetAnotherNodeIn7");
		//System.out.println("Removed " + x.remove(12347));
		System.out.println("String " + x.toString());
	}
}
