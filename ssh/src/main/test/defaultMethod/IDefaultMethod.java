package defaultMethod;

public interface IDefaultMethod {
	
	public void doRun();
	
	default public void defaultDoRun(){
		System.out.println("default Run");
	}
	
}
