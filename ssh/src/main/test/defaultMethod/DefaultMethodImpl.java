package defaultMethod;

public class DefaultMethodImpl implements IDefaultMethod{

	
	@Override
    public void doRun() {
        System.out.println("Run");
    }
	
	public static void main(String[] args) {
		DefaultMethodImpl obj = new DefaultMethodImpl();
		obj.doRun();
		obj.defaultDoRun();
	}

}
