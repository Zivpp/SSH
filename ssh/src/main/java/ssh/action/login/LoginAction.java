package ssh.action.login;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import login.TestDataPackageBean;
import login.TestStringData;
import ssh.action.BaseAction;
import ssh.facade.ILoginFacade;
import ssh.util.StringUtil;

public class LoginAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	
	private TestDataPackageBean dataPackage;
	private String d_String;
	private Integer d_Int;
	private Long d_Long;
	private String[] d_Array;
	private ArrayList<String> d_List;
	private TestStringData testStringData;
	
	private String account;
	private String password;	
	
	@Autowired
	@Qualifier("loginFacadeImpl")
	private ILoginFacade loginFacade;

	/**
	 * µn¤J¬ÛÃö action
	 * @return
	 */
	public String loginAction() {
		try{
						
			if(!StringUtil.isEmpty(account) && !StringUtil.isEmpty(password)){	

				String passStr = loginFacade.login(account,password);
				
				super.dataHandler(passStr);
				
			}
			
			
			
			
			//Struts josn auto Transform example : 
//			String d_String = this.d_String;
//			Integer d_Int = this.d_Int;
//			Long d_Long = this.d_Long;
//			String[] d_Array = this.d_Array;
//			ArrayList<String> d_List = this.d_List;
//			TestStringData testStringData = this.testStringData;
//			TestDataPackageBean dataPackage = this.dataPackage;
//			
//			List<Object> reuslt = new ArrayList<Object>();	
//			reuslt.add(d_String);
//			reuslt.add(d_Int);
//			reuslt.add(d_Long);
//			reuslt.add(d_Array);
//			reuslt.add(d_List);
//			reuslt.add(testStringData);
//			reuslt.add(dataPackage);
			
//			super.dataHandler();

			
			//Hibernate example :
//			try{
//				Session session = HibernateUtil.getSessionFactory().openSession();
//			    Transaction tx = session.beginTransaction();
//			
//			    User user = new User();
//			    user.setUserId(1);
//			    user.setUserName("Ziv");
//			    user.setCreateBy("Ziv");
//			    user.setCreateDate(new Date());
//			
//			    session.save(user);
//			    tx.commit();
//			
//			    session.close();
//			    HibernateUtil.shutdown();
//			}catch(Exception e){
//				throw new Exception(e);
//			}
//			
//		    super.dataHandler(PASS);
//			
			
		}catch(Exception e){	
			System.out.println(e.getMessage());
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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
