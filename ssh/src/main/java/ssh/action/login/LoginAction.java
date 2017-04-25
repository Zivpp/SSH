package ssh.action.login;

import java.util.ArrayList;
import java.util.List;

import ssh.action.BaseAction;
import ssh.login.TestDataPackageBean;
import ssh.login.TestStringData;

public class LoginAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	
	private TestDataPackageBean dataPackage;
	private String d_String;
	private Integer d_Int;
	private Long d_Long;
	private String[] d_Array;
	private ArrayList<String> d_List;
	private TestStringData testStringData;
	
	/**
	 * µn¤J¬ÛÃö action
	 * @return
	 */
	public String loginAction() {
		try{
			
			String d_String = this.d_String;
			Integer d_Int = this.d_Int;
			Long d_Long = this.d_Long;
			String[] d_Array = this.d_Array;
			ArrayList<String> d_List = this.d_List;
			//TestDataPackageBean dataPackage = this.dataPackage;
			TestStringData testStringData = this.testStringData;
			
			List<Object> re = new ArrayList<Object>();
			re.add(d_String);
			re.add(d_Int);
			re.add(d_Long);
			re.add(d_Array);
			re.add(d_List);
			re.add(testStringData);
			
			super.dataHandler(re);
			
		}catch(Exception e){	
			super.dataHandler(e);
		}

		return SUCCESS;
	}

	public TestDataPackageBean getDataPackage() {
		return dataPackage;
	}

	public void setDataPackage(TestDataPackageBean dataPackage) {
		this.dataPackage = dataPackage;
	}

	public String getD_String() {
		return d_String;
	}

	public void setD_String(String d_String) {
		this.d_String = d_String;
	}

	public Integer getD_Int() {
		return d_Int;
	}

	public void setD_Int(Integer d_Int) {
		this.d_Int = d_Int;
	}

	public Long getD_Long() {
		return d_Long;
	}

	public void setD_Long(Long d_Long) {
		this.d_Long = d_Long;
	}

	public String[] getD_Array() {
		return d_Array;
	}

	public void setD_Array(String[] d_Array) {
		this.d_Array = d_Array;
	}

	public ArrayList<String> getD_List() {
		return d_List;
	}

	public void setD_List(ArrayList<String> d_List) {
		this.d_List = d_List;
	}

	public TestStringData getTestStringData() {
		return testStringData;
	}

	public void setTestStringData(TestStringData testStringData) {
		this.testStringData = testStringData;
	}

}
