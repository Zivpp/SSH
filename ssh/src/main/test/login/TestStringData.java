package login;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class TestStringData extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String sex;
	private List<String> companyList;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public List<String> getCompanyList() {
		return companyList;
	}
	public void setCompanyList(List<String> companyList) {
		this.companyList = companyList;
	}
	
}
