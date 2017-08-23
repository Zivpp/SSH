package crud.cfgSystemConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import bean.CfgSystemConfig;
import ssh.dao.ICfgSystemConfigDao;

public class Update {
public static void main(String[] args) {
		
		try{
			
			ApplicationContext context = new FileSystemXmlApplicationContext("src/main/resources/applicationContext.xml");
			ICfgSystemConfigDao dao = (ICfgSystemConfigDao) context.getBean("cfgSystemConfigDaoImpl");
			CfgSystemConfig csc = dao.queryById(4);
			csc.setCode("Test update to 4");
			csc = dao.update(csc);
			
			System.out.println(csc.getCode());
			
		}catch(Exception e){
			
			System.out.println(e.getMessage());
		}
	}
}
