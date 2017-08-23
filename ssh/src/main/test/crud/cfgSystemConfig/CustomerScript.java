package crud.cfgSystemConfig;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import bean.CfgSystemConfig;
import ssh.dao.ICfgSystemConfigDao;

public class CustomerScript {
	
	public static void main(String[] args) {
		
		try{
			ApplicationContext context = new FileSystemXmlApplicationContext("src/main/resources/applicationContext.xml");
			ICfgSystemConfigDao userDAO = (ICfgSystemConfigDao) context.getBean("cfgSystemConfigDaoImpl");
			List<CfgSystemConfig> csc = userDAO.searchById(5);
			System.out.println(csc.get(0).getId());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
	
}
