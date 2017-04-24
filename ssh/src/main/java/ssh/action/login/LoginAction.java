package ssh.action.login;

import java.util.ArrayList;
import java.util.List;

import ssh.action.BaseAction;

public class LoginAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	
	private String[] array;
	private String a;
	private List<String> b = new ArrayList<String>();
	
	/**
	 * µn¤J¬ÛÃö action
	 * @return
	 */
	public String loginAction() {
		try{
			
			String[] b = this.array;
			this.a = "1233";
			this.b.add("asdsd");
			
			super.dataHandler(this.b);
			
		}catch(Exception e){
			
			super.dataHandler(e);
			
		}

		return SUCCESS;
	}
	
	public String[] getArray() {
		return array;
	}

	public void setArray(String[] array) {
		this.array = array;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public List<String> getB() {
		return b;
	}

	public void setB(List<String> b) {
		this.b = b;
	}

}
