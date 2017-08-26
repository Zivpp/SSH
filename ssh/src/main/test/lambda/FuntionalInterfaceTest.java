package lambda;

@FunctionalInterface
public interface FuntionalInterfaceTest {
	
	/**
	 * say hello
	 * @param name
	 */
	public void hello(String name);
	
	
	default public void hello2(String name){
		System.out.println(name);
	}
	
	static public void hello3(String name){
		
	}
}
